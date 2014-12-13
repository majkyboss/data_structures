package def;

import util.BitConverter;

public class Block {
	private final Record[] records;
	private int blockFactor;
	private int index = 0;

	public Block(Record[] records, int blockFactor) {
		super();
		assert records.length <= blockFactor;
		this.records = records;
		this.blockFactor = blockFactor;
	}

	public int getByteSize() {
		return records[0].getByteSize() * blockFactor;
	}

	public byte[] getBytes() {
		byte[] bytes = new byte[this.getByteSize()];
		int offset = 0;
		for (int i = 0; i < records.length; i++) {
			BitConverter.putBytes(records[i].getBytes(), bytes, offset);
			offset += records[i].getByteSize();
		}
		return bytes;
	}

	public void fillFromBytes(byte[] bytes) {
		int offset = 0;
		for (int i = 0; i < records.length; i++) {
			records[i].fillFromBytes(bytes, offset);
			offset += records[i].getByteSize();
		}
	}

	public boolean isFull() {
		for (int i = 0; i < records.length; i++) {
			if (!records[i].isValid()) {
				return false;
			}
		}
		return true;
	}

	public boolean isValid() {
		for (int i = 0; i < records.length; i++) {
			if (records[i].isValid()) {
				return true;
			}
		}
		return false;
	}

	public boolean appendRecord(Record record) {
		for (int i = 0; i < records.length; i++) {
			if (!records[i].isValid()) {
				records[i] = record;
				return true;
			}
		}
		return false;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Block ");
		sb.append(index);
		sb.append(":\n");

		for (int i = 0; i < records.length; i++) {
			sb.append("\t");
			if (records[i].isValid()) {
				sb.append(records[i].toString());
			} else {
				sb.append("invalid");
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public Record getRecord(int index) {
		if (index < records.length) {
			return records[index];
		}
		return null;
	}

	public void removeRecord(int index) {
		Record record = records[index];
		record.setValid(false);
//		record.fillFromBytes(new byte[record.getByteSize()], 0);
	}

}
