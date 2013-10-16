// autonexus.js

var ID_MOVE = $.findPacketId("MOVE");
var ID_PLAYERHIT = $.findPacketId("PLAYERHIT");
var ID_SHOOT = $.findPacketId("SHOOT");
var ID_SHOOT2 = $.findPacketId("SHOOT2");
var ID_UPDATE = $.findPacketId("UPDATE");
var ID_CREATE_SUCCESS = $.findPacketId("CREATE_SUCCESS");
var ID_NEW_TICK = $.findPacketId("NEW_TICK");
var ID_AOE = $.findPacketId("AOE");
var ID_ESCAPE = $.findPacketId("ESCAPE");

var STATDATA_MAXHEALTH = 0;
var STATDATA_HEALTH = 1;
var STATDATA_DEFENCEBONUS = 49

var nexusHealthPercentage = 20;

var playerObjectId = -1;
var health = -1;
var maxHealth = -1;
var defenceBonus = -1;
var bulletIdDamageMap = {};
var bEscapeToNexusSent = false; // true = don't confirm any more hits
var playerLocation = null;

function onClientPacket(event) {
	if (bEscapeToNexusSent) {
		event.cancel();
		return;
	}
	var packet = event.getPacket();
	switch (packet.id()) {
		case ID_MOVE: {
			playerLocation = packet.newPosition;
			break;
		}
		case ID_PLAYERHIT: {
			// predict what the damage will be
			health -= getDamageEstimate(bulletIdDamageMap[packet.bulletId]);
			// if the predicted health percentage is below nexusHealthPercentage...
			if (100 * health / maxHealth <= nexusHealthPercentage) {
				// send ESCAPE packet to the server
				// and prevent any more packets from being sent after that
				var escapePacket = $.createPacket(ID_ESCAPE);
				$.sendToServer(escapePacket);
				bEscapeToNexusSent = true;
				event.cancel();
			}
			break;
		}
	}
}

function onServerPacket(event) {
	var packet = event.getPacket();
	switch (packet.id()) {
		case ID_SHOOT2:
		case ID_SHOOT: {
			// store projectile damage...
			bulletIdDamageMap[packet.bulletId] = packet.damage;
			break;
		}
		case ID_UPDATE: {
			for (var i = 0; i < packet.newObjs.length; i++) {
				var objectData = packet.newObjs[i];
				if (objectData.status.objectId == playerObjectId) {
					for (var j = 0; j < objectData.status.data.length; j++) {
						var statData = objectData.status.data[j];
						// update player data...
						if (statData.obf0 == STATDATA_MAXHEALTH) {
							maxHealth = statData.obf1;
						} else if (statData.obf0 == STATDATA_HEALTH) {
							health = statData.obf1;
						} else if (statData.obf0 == STATDATA_DEFENCEBONUS) {
							defenceBonus = statData.obf1;
						}
					}
				}
			}
			break;
		}
		case ID_CREATE_SUCCESS: {
			// keep the player's objectId
			playerObjectId = packet.objectId;
			break;
		}
		case ID_NEW_TICK: {
			for (var i = 0; i < packet.statuses.length; i++) {
				var status = packet.statuses[i];
				if (status.objectId == playerObjectId) {
					for (var j = 0; j < status.data.length; j++) {
						var statData = status.data[j];
						// update the player's health
						if (statData.obf0 == STATDATA_HEALTH) {
							health = statData.obf1;
						}
					}
				}
			}
			break;
		}
		case ID_AOE: {
			if (playerLocation != null && playerLocation.distanceTo(packet.pos) <= packet.radius) {
				// predict what the damage will be
				health -= getDamageEstimate(packet.damage);
				// if the predicted health percentage is below nexusHealthPercentage...
				if (100 * health / maxHealth <= nexusHealthPercentage) {
					// send ESCAPE packet to the server
					// and prevent any more packets from being sent after that
					var escapePacket = $.createPacket(ID_ESCAPE);
					$.sendToServer(escapePacket);
					bEscapeToNexusSent = true;
					event.cancel();
				}
			}
			break;
		}
	}
}

function getDamageEstimate(baseDamage) {
	// not a perfect damage calculation at all, but good enough for govt work
	var damage = baseDamage - defenceBonus;
	if (damage < 0.15 * baseDamage) {
		damage = 0.15 * baseDamage;
	}
	if (isNaN(damage)) {
		return 100;
	}
	return damage;
}
