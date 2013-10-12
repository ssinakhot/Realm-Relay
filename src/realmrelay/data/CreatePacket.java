package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class CreatePacket extends Packet {
	
	public static final byte ID = 49;
	
	public int classType;
	public int skinType;

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.classType = in.readUnsignedShort();
		this.skinType = in.readUnsignedShort();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeShort(this.classType);
		out.writeShort(this.skinType);
	}

}
