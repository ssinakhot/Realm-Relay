package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;



public class TradeStartPacket extends Packet {
	
	public static final byte ID = 77;
	
	public Item[] myItems = new Item[0];
	public String yourName;
	public Item[] yourItems = new Item[0];

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.myItems = new Item[in.readShort()];
		for (int i = 0; i < this.myItems.length; i++) {
			Item item = new Item();
			item.parseFromInput(in);
			this.myItems[i] = item;
		}
		this.yourName = in.readUTF();
		this.yourItems = new Item[in.readShort()];
		for (int i = 0; i < this.yourItems.length; i++) {
			Item item = new Item();
			item.parseFromInput(in);
			this.yourItems[i] = item;
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeShort(this.myItems.length);
		for (Item item: this.myItems) {
			item.writeToOutput(out);
		}
		out.writeUTF(this.yourName);
		out.writeShort(this.yourItems.length);
		for (Item item: this.yourItems) {
			item.writeToOutput(out);
		}
	}

}
