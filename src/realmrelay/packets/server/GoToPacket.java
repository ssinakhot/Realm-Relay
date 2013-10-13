package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.data.Location;
import realmrelay.packets.Packet;



public class GoToPacket extends Packet {
	
	public static final byte ID = 14;
	
	public int objectId;
	public Location pos = new Location();

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.objectId = in.readInt();
		this.pos.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.objectId);
		this.pos.writeToOutput(out);
	}

}
