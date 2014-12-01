package sem2;

import util.BitConverter;
import util.IBinarySerial;

public class Block {
	private final int blockFactor;
	private final Record[] records;
	private int address;
	private int recordSize;
	private int byteSize = 0;

	// private Block overCrowdingBlock;
	// private int blockAddress = 0;

	public Block(int recordSize, int blockFactor, int address) {
		super();
		this.blockFactor = blockFactor;
		this.recordSize = recordSize;
		this.address = address;
		this.byteSize = recordSize * blockFactor;
		records = new Record[blockFactor];

		for (int i = 0; i < records.length; i++) {
			records[i] = new Record(recordSize, i);
		}
	}

	public void add(RecordValue value) {
		Record rec = getFirstInvalidRecord();
		if (rec != null) {
			rec.setValue(value);
		}
	}

	// private Record getFreeRecord() {
	// for (int i = 0; i < records.length; i++) {
	// Record record = records[i];
	// if (!record.isValid()) {
	// return record;
	// }
	// }
	// return null;
	// }

	public boolean hasInvalidRecord() {
		if (getFirstInvalidRecord() != null) {
			return true;
		}
		return false;
	}

	public byte[] getBytes() {
		byte[] bytes = new byte[getByteSize()];
		int offset = 0;
		for (int i = 0; i < records.length; i++) {
			Record r = records[i];
			BitConverter.putBytes(r.getBytes(), bytes, offset);
			offset += recordSize;
		}

		return bytes;
	}

	public void fillFromBytes(byte[] value, int offset) {
		for (int i = 0; i < records.length; i++) {
			Record record = records[i];
			record.fillFromBytes(value, offset);
		}
	}

	public int getByteSize() {
		return byteSize;
	}

	public Record getFirstInvalidRecord() {
		for (int i = 0; i < records.length; i++) {
			Record record = records[i];
			if (!record.isValid()) {
				return record;
			}
		}
		return null;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public int getAddress() {
		return address;
	}

}
