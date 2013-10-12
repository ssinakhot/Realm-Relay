package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class UpdatePacket extends Packet {
	
	public static final byte ID = 66;
	
	public Tile[] tiles = new Tile[0];
	public ObjectData[] newObjs = new ObjectData[0];
	public int[] drops = new int[0];

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.tiles = new Tile[in.readShort()];
		for (int i = 0; i < this.tiles.length; i++) {
			Tile tile = new Tile();
			tile.parseFromInput(in);
			this.tiles[i] = tile;
		}
		this.newObjs = new ObjectData[in.readShort()];
		for (int i = 0; i < this.newObjs.length; i++) {
			ObjectData objectData = new ObjectData();
			objectData.parseFromInput(in);
			this.newObjs[i] = objectData;
		}
		this.drops = new int[in.readShort()];
		for (int i = 0; i < this.drops.length; i++) {
			this.drops[i] = in.readInt();
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeShort(this.tiles.length);
		for (Tile tile: this.tiles) {
			tile.writeToOutput(out);
		}
		out.writeShort(this.newObjs.length);
		for (ObjectData obj: this.newObjs) {
			obj.writeToOutput(out);
		}
		out.writeShort(this.drops.length);
		for (int drop: this.drops) {
			out.writeInt(drop);
		}
	}

}
