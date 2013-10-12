package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class PongPacket extends Packet {
	
	public static final byte ID = 9;
	
	public int serial;
	public int time;

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.serial = in.readInt();
		this.time = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.serial);
		out.writeInt(this.time);
	}

}
