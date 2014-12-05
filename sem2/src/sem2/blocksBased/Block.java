package sem2.blocksBased;

import java.util.Arrays;

import sem2.FileItem;
import sem2.Record;
import util.BitConverter;

public abstract class Block<T> extends FileItem {
	protected Record<?>[] records;
	private boolean full = false;
	// private int recordSize;
	private short blockFactor;

	public Block(int recordByteSize, short blockFactor) {
		super(recordByteSize * blockFactor);
		this.blockFactor = blockFactor;
		records = new Record<?>[blockFactor];
		initRecords();
	}

	public Block(byte[] bytes) {
		super(bytes);
	}

	protected abstract void initRecords();

	protected byte[] getHeader() {
		byte[] bytes = new byte[getHeaderSize()];
		int offset = 0;
		BitConverter.putShort(blockFactor, bytes, offset);

		return bytes;
	}

	protected int getHeaderSize() {
		return Short.BYTES;
	}

	@Override
	public int getByteSize() {
		return super.getByteSize() + getHeaderSize();
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[getByteSize()];
		int offset = 0;
		BitConverter.putBytes(getHeader(), bytes, offset);
		offset += getHeaderSize();

		for (int i = 0; i < records.length; i++) {
			if (records[i] != null) {
				Record<?> record = records[i];
				BitConverter.putBytes(record.getBytes(), bytes, offset);
				offset += record.getByteSize();
			}
		}

		return bytes;
	}

	protected void fillHeaderFromBytes(byte[] bytes) {
		int offset = 0;
		blockFactor = BitConverter.getShort(bytes, offset);
	}

	@Override
	public void fillFromBytes(byte[] bytes) {
		int offset = 0;
		fillHeaderFromBytes(bytes);
		int headerSize = getHeaderSize();
		offset += headerSize;

		// Do not forget - initialize the array and records
		records = new Record<?>[blockFactor];
		initRecords();

		int recordSize = (getByteSize() - headerSize) / blockFactor;

		if (getByteSize() == bytes.length) {
			for (int i = 0; i < records.length; i++) {
				Record<?> record = records[i];
				byte[] recordBytes = Arrays.copyOfRange(bytes, offset, offset + recordSize);
				record.fillFromBytes(recordBytes);
				offset += recordSize;
			}
		}

		// --- --- --- divide bytes into same parts and then fill the records
		// separately
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

	public boolean addValue(T value) {
		Record<?> record = getFreeRecord();
		if (record != null) {
			((Record<T>) record).setValue(value);
			if (records[records.length - 1] != null && record.equals(records[records.length - 1])) {
				full = true;
			}
			return true;
		} else {
			return addToFullBlock(value);
		}
	}

	abstract protected boolean addToFullBlock(T value);

	// finds first free record
	public Record<T> getFreeRecord() {
		Record<?> ret = null;
		for (int i = 0; i < records.length; i++) {
			ret = records[i];
			if (!ret.isValid()) {
				return (Record<T>) ret;
			}
		}
		return null;
	}

	public T getValue(int recordPosition) {
		T value = (T) records[recordPosition].getValue();

		return value;
	}

	public short getBlockFactor() {
		return blockFactor;
	}

}
