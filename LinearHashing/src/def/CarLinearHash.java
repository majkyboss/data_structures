package def;

import util.EmptyBlockFactory;

public class CarLinearHash {
	private String path = "";
	private UnsortedFile sortedFile;
	private UnsortedFile overCrowdingFile;
	private UnsortedFile dataFile;
	private int blockFactor;

	public CarLinearHash(String path) {
		super();
		this.path = path;
	}

	public void addCar(Car car) {
		// save to unsorted data file
		Integer dataBlockIndex = dataFile.getNotFullBlockAddress();
		if (dataBlockIndex == null) {
			dataBlockIndex = dataFile.getInvalidBlockAddress();
		}
		if (dataBlockIndex == null) {
			dataBlockIndex = dataFile.getLastBlockIndex() + 1;
		}
		Block block = EmptyBlockFactory.carBlock(blockFactor);
		dataFile.loadBlock(dataBlockIndex, block);
		block.appendRecord(car);
		dataFile.writeBlock(dataBlockIndex, block);

		// save to sorted car file
		// add record to index block:
		// if block is not full
		// add record to block
		// save block to sorted file
		// else
		// while block is full{
		// get overcrowding block (find address and load block)
		// if ocBlock is null
		// set new ocBlock
		// block = ocBlock
		// }
		// add record to block
		// save block to ocFile

		// TODO change to hash functions
		Integer sortedBlockIndex = sortedFile.getFreeRecordBlockIndex();
		OvercrowdedBlock ocBlock1 = EmptyBlockFactory.carOcBlock(blockFactor);
		sortedFile.loadBlock(sortedBlockIndex, ocBlock1);
		if (!ocBlock1.isFull()) {
			ocBlock1.appendRecord(car);
			sortedFile.writeBlock(sortedBlockIndex, ocBlock1);
		} else {
			Integer ocAddress = ocBlock1.getOcAddress();
			while (ocBlock1.isFull()) {
				ocAddress = ocBlock1.getOcAddress();
				if (ocAddress == null) {
					Integer newAddress = overCrowdingFile.getInvalidBlockAddress();
					ocBlock1.setOcAddress(newAddress);
					ocAddress = newAddress;
				}
				overCrowdingFile.loadBlock(ocAddress, ocBlock1);
			}
			ocBlock1.appendRecord(car);
			overCrowdingFile.writeBlock(ocAddress, ocBlock1);
		}

	}

	public Car getCarByCarNumber(String carNumber) {
		return null;
	}

	public Car getCarByVinNumber(String vinNumber) {
		return null;
	}
}
