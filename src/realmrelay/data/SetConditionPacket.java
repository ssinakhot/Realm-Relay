package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class SetConditionPacket extends Packet {
	
	public static final byte ID = 7;
	
	public int conditionEffect;
	public float conditionDuration;

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.conditionEffect = in.readUnsignedByte();
		this.conditionDuration = in.readFloat();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeByte(this.conditionEffect);
		out.writeFloat(this.conditionDuration);
	}

}
