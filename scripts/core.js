// core.js

var ID_HELLO;
var ID_FAILURE;
var ID_RECONNECT;
var ID_CREATE_SUCCESS;
var ID_NOTIFICATION;

var helloPacket = null;

function onEnable(event) {
	ID_HELLO = event.findPacketId("HELLO");
	ID_FAILURE = event.findPacketId("FAILURE");
	ID_RECONNECT = event.findPacketId("RECONNECT");
	ID_CREATE_SUCCESS = event.findPacketId("CREATE_SUCCESS");
	ID_NOTIFICATION = event.findPacketId("NOTIFICATION");
	event.echo("Waiting for HELLO from client...");
}

function onConnect(event) {
	event.echo("Connected to remote server.");
	event.sendToServer(helloPacket);
}

function onConnectFail(event) {
	event.echo("Connection to remote server failed!");
	event.kickUser();
}

function onDisconnect(event) {
	event.echo("Disconnected from remote server.");
	event.kickUser();
}

function onClientPacket(event) {
	var packet = event.getPacket();
	switch (packet.id()) {
		case ID_HELLO: {
			event.cancel();
			event.connect(packet.gameId);
			helloPacket = packet;
			event.echo("buildVersion: " + packet.buildVersion);
			break;
		}
	}
}

function onServerPacket(event) {
	var packet = event.getPacket();
	switch (packet.id()) {
		case ID_FAILURE: {
			event.echo(packet + " " + packet.errorId + " " + packet.errorDescription);
			break;
		}
		case ID_RECONNECT: {
			var host;
			var port;
			if (packet.port == -1) { // -1 means same server/port you are on
				host = event.getRemoteHost();
				port = event.getRemotePort();
			} else {
				host = packet.host;
				port = packet.port;
			}
			event.setGameIdSocketAddress(packet.gameId, host, port);
			packet.host = "localhost";
			packet.port = 2050;
			break;
		}
		case ID_CREATE_SUCCESS: {
			event.scheduleEvent(1, "displayRealmRelayNotification", packet.objectId);
			break;
		}
	}
}

function displayRealmRelayNotification(event, playerObjectId) {
	var notificationPacket = event.createPacket(ID_NOTIFICATION);
	notificationPacket.objectId = playerObjectId;
	notificationPacket.message = "{\"key\":\"server.plus_symbol\",\"tokens\":{\"amount\":\"Realm Relay enabled!\"}}";
	notificationPacket.color = 0x33FFFF;
	event.sendToClient(notificationPacket);
}