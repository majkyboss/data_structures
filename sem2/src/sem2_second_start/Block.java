package sem2_second_start;

import java.util.LinkedList;

import util.BitConverter;

public class Block implements Record {
	private int address = 0;
	private int blockFactor = 0;
	private int recordByteSize = 0;
//	private LinkedList<? extends Record> records = new LinkedList<Record>();
	private Record[] records;

	public Block(int blockFactor, LinkedList<? extends Record> records, int recordByteSize) {
		super();
		assert records.size() <= blockFactor;

		this.blockFactor = blockFactor;
		this.records = new Record[blockFactor];
		
		this.recordByteSize = recordByteSize;
	}

	@Override
	public int getByteSize() {
		int size = 0;
		for (Record record : records) {
			size += record.getByteSize();
		}
		return size;
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[blockFactor*recordByteSize];
		int offset = 0;
		for (Record record : records) {
			BitConverter.putBytes(record.getBytes(), bytes, offset);
			offset += record.getByteSize();
		}
		return bytes;
	}

	@Override
	public void fillFromBytes(byte[] bytes) {

	}

	@Override
	public boolean isValid() {
		for (Record record : records) {
			if (!record.isValid()) {
				return false;
			}
		}
		return true;
	}

}
