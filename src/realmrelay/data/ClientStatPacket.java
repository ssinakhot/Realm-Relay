package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class ClientStatPacket extends Packet {
	
	public static final byte ID = 8;
	
	public String name;
	public int value;

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.name = in.readUTF();
		this.value = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(this.name);
		out.writeInt(this.value);
	}

}
