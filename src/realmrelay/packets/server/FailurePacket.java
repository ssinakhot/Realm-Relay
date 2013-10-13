package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;


public class FailurePacket extends Packet {
	
	public static final byte ID = 0;
	
	public int errorId;
	public String errorDescription;

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.errorId = in.readInt();
		this.errorDescription = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.errorId);
		out.writeUTF(this.errorDescription);
	}

}
