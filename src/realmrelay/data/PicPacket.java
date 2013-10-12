package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;



public class PicPacket extends Packet {
	
	public static final byte ID = 5;
	
	public BitmapData bitmapData = new BitmapData();

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.bitmapData.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		this.bitmapData.writeToOutput(out);
	}

}
