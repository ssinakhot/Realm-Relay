package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class Global_NotificationPacket extends Packet {
	
	public static final byte ID = 45;
	
	public int type;
	public String text;

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.type = in.readInt();
		this.text = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.type);
		out.writeUTF(this.text);
	}

}
