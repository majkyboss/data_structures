package sem2;

import util.BitConverter;
import util.IBinarySerial;

public class Block implements IBinarySerial {
	private final int recordsNumber;
	private final Record[] records;

	// private Block overCrowdingBlock;
	// private int blockAddress = 0;

	public Block(int blockFactor) {
		super();
		this.recordsNumber = blockFactor;
		records = new Record[blockFactor];
	}

	public void addRecord(RecordValue value) {
		Record rec = getFreeRecord();
		if (rec != null) {
			rec.setValue(value);
		}
	}

	private Record getFreeRecord() {
		for (int i = 0; i < records.length; i++) {
			Record record = records[i];
			if (!record.isValid()) {
				return record;
			}
		}
		return null;
	}

	public boolean hasInvalidRecord() {
		if (getFreeRecord() != null) {
			return true;
		}
		return false;
	}


	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[getByteSize()];
		

		return bytes;
	}

	@Override
	public void fillFromBytes(byte[] value, int offset) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getByteSize() {
		int count = 0;
		
		return count;
	}

}
