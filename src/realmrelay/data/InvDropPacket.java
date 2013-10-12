package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class InvDropPacket extends Packet {
	
	public static final byte ID = 38;
	
	public SlotObject slotObject = new SlotObject();

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.slotObject.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		this.slotObject.writeToOutput(out);
	}

}
