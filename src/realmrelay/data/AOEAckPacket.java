package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class AOEAckPacket extends Packet {
	
	public static final byte ID = 37;
	
	public int time;
	public Location position = new Location();

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.time = in.readInt();
		this.position.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.time);
		this.position.writeToOutput(out);
	}

}
