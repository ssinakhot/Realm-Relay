// core.js

var ID_HELLO = 51;
var ID_FAILURE = 0;
var ID_RECONNECT = 15;
var ID_CREATE_SUCCESS = 47;
var ID_NOTIFICATION = 91;
var ID_PLAYERTEXT = 80;


var lastIp = null;
var lastPort = null;
var lastGameId = null;
var lastKeyTime = null;	
var lastKey = null;

var reconPacket = null;
var helloPacket = null;

function onEnable(event) {
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
	if(packet.id() == ID_HELLO) {
		//console.log("HELLO");
			event.cancel();
			event.connect(packet.gameId);
			helloPacket = packet;
			event.echo("buildVersion: " + 17.2);			
			if(helloPacket.gameId != -2 && helloPacket.gameId != -5)
				{
					event.setGlobal("helloPacket", helloPacket);					
					
					
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
			if(packet.gameId != -2 && helloPacket.gameId != -5)
			{
				event.setGlobal("reconHost", host);
				event.setGlobal("reconPort", port);
				
			}
			event.echo(host);
			event.setGameIdSocketAddress(0, host, port);
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
	 notificationPacket.message = "{\"key\":\"blank\",\"tokens\":{\"data\":\" "+ "Trollaux is GAYY!" +"\"}}";
	 notificationPacket.color = 0xFF7F00;
	 event.sendToClient(notificationPacket);
	}