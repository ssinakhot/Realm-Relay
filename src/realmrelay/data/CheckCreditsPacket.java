package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class CheckCreditsPacket extends Packet {
	
	public static final byte ID = 33;

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
	}

}
