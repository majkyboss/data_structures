package sem2_newStart.blocksBased;

import sem2_newStart.recordBased.Car;

public class CarBlock extends Block<Car> {

	public CarBlock(int recordByteSize, short blockFactor) {
		super(recordByteSize, blockFactor);
	}

	public CarBlock(byte[] bytes) {
		super(bytes);
	}

	@Override
	protected void initRecords() {
		if (records!=null) {
			for (int i = 0; i < records.length; i++) {
				records[i] = new CarRecord();
			}
		}
	}

}