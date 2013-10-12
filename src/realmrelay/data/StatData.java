package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class StatData implements IData {
	
	public int obf0;
	public int obf1;
	public String obf2;
	
	public boolean isUTFData() {
		switch (this.obf0) {
			case 31: 
			case 62: 
			case 82: 
			case 38: 
			case 54: { 
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.obf0 = in.readUnsignedByte();
		if (this.isUTFData()) {
			this.obf2 = in.readUTF();
		} else {
			this.obf1 = in.readInt();
		}
	}
	
	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeByte(this.obf0);
		if (this.isUTFData()) {
			out.writeUTF(this.obf2);
		} else {
			out.writeInt(this.obf1);
		}
	}

}
