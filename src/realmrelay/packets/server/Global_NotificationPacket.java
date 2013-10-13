package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;


public class Global_NotificationPacket extends Packet {
	
	public static final byte ID = 45;
	
	public int type;
	public String text;

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.type = in.readInt();
		this.text = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.type);
		out.writeUTF(this.text);
	}

}
