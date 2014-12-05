package sem2.blocksBased;

import sem2.recordBased.Car;

public class CarBlock extends Block<Car> {

	public CarBlock(int recordByteSize, short blockFactor) {
		super(recordByteSize, blockFactor);
	}

	public CarBlock(byte[] bytes) {
		super(bytes);
	}

	@Override
	protected void initRecords() {
		if (records != null) {
			for (int i = 0; i < records.length; i++) {
				records[i] = new CarRecord();
			}
		}
	}

	@Override
	protected boolean addToFullBlock(Car value) {
		// do nothing
		return false;
	}

}
