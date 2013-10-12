package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class UpdateAckPacket extends Packet {
	
	public static final int ID = 42;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
	}

	@Override
	public byte id() {
		return ID;
	}

}
