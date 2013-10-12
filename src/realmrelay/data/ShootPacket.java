package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;



public class ShootPacket extends Packet {
	
	public static final byte ID = 10;
	
	public int bulletId;
	public int ownerId;
	public int bulletType;
	public Location startingPos = new Location();
	public float angle;
	public short damage;
	public int numShots;
	public float angleInc;
	
	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.bulletId = in.readUnsignedByte();
		this.ownerId = in.readInt();
		this.bulletType = in.readUnsignedByte();
		this.startingPos.parseFromInput(in);
		this.angle = in.readFloat();
		this.damage = in.readShort();
		try {
			this.numShots = in.readUnsignedByte();
			this.angleInc = in.readFloat();
		} catch (IOException e) {
			this.numShots = 1;
			this.angleInc = 0;
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeByte(this.bulletId);
		out.writeInt(this.ownerId);
		out.writeByte(this.bulletType);
		this.startingPos.writeToOutput(out);
		out.writeFloat(this.angle);
		out.writeShort(this.damage);
		if (this.numShots != 1 || this.angleInc != 0) {
			out.writeByte(this.numShots);
			out.writeFloat(this.angleInc);
		}
	}

}
