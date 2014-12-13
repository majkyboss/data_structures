package def;

import java.io.File;
import java.util.LinkedList;

import util.BinaryFileHandler;
import util.EmptyBlockFactory;

public class CarLinearHash {
	private String path = "";
	private UnsortedFile sortedFileByCarNumber;
	// TODO add block to second file too
	private UnsortedFile sortedFileByVin;
	private UnsortedFile overCrowdingFile;
	private UnsortedFile dataFile;
	private int blockFactor;
	private int u;
	private int m;
	private int s;
	private int n = 0;
	private double dMax;

	public CarLinearHash(String dirPath, Car emptyCar, boolean cleanFiles) {
		super();
		this.path = dirPath;
		this.blockFactor = 2;
		this.u = 0;
		this.m = 2;
		this.s = 0;
		this.dMax = 0.8;

		if (!dirPath.endsWith("/")) {
			dirPath = dirPath + "/";
		}

		if (cleanFiles) {
			// BinaryFileHandler.trimFile(new File(dirPath + "sortedFile"), 0);
			// BinaryFileHandler.trimFile(new File(dirPath + "ocFile"), 0);
			BinaryFileHandler.trimFile(new File(dirPath + "dataFile"), 0);
		}

		int recordbyteSize = emptyCar.getByteSize();
		sortedFileByCarNumber = new UnsortedFile(dirPath + "sortedFile", blockFactor, recordbyteSize * blockFactor + Integer.BYTES);
		overCrowdingFile = new UnsortedFile(dirPath + "ocFile", blockFactor, recordbyteSize * blockFactor + Integer.BYTES);
		dataFile = new UnsortedFile(dirPath + "dataFile", blockFactor, recordbyteSize * blockFactor);
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

		// description:
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
		// Integer sortedBlockIndex = sortedFile.getFreeRecordBlockIndex();
		int sortedBlockIndex = 0;
		sortedBlockIndex = getBlockIndex(car.getCarNumber().hashCode());
		// TODO same for another key attributes

		// insert record
		insertCarToSorted(car, sortedBlockIndex);

		// linear hash fix up
		// d = n/N (počet obsadených miest k počtu alokovaných miest)
		// pocet obsadenych
		n++;
		// pocet alokovanych: (last+1=count) * (records in one block)
		int Nsorted = (sortedFileByCarNumber.getLastBlockIndex() + 1) * blockFactor;
		int Novercrovding = (overCrowdingFile.getLastBlockIndex() + 1) * blockFactor;
		double d = n / (Nsorted + Novercrovding);
		if (d > dMax) {
			int a = (int) (this.s + this.m * Math.pow(2, this.u));
			// OvercrowdedBlock newBlock =
			// EmptyBlockFactory.carOcBlock(blockFactor);
			// newBlock.setIndex(a);
			OvercrowdedBlock tempBlock = EmptyBlockFactory.carOcBlock(blockFactor);
			sortedFileByCarNumber.loadBlock(s, tempBlock);
			LinkedList<Record> recordsToMove = new LinkedList<Record>();
			for (int i = 0; i < blockFactor; i++) {
				Record c = tempBlock.getRecord(i);
				if (c == null) {
					break;
				}
				if (c.isValid() && getHuPlus1(((Car) c).getCarNumber().hashCode()) != this.s) {
					recordsToMove.add(c);
					tempBlock.removeRecord(i);
				}
			}
			// if in tempBlock overcrowding space is something
			int ocAdddress = tempBlock.getOcAddress();
			OvercrowdedBlock parentBlock = tempBlock;
			UnsortedFile file = sortedFileByCarNumber;
			while (ocAdddress != -1) {
				OvercrowdedBlock ocBlock = EmptyBlockFactory.carOcBlock(blockFactor);
				overCrowdingFile.loadBlock(ocAdddress, ocBlock);
				for (int i = 0; i < blockFactor; i++) {
					Record c = ocBlock.getRecord(i);
					if (c == null) {
						break;
					}
					if (c.isValid()) {
						if (getHuPlus1(((Car) c).getCarNumber().hashCode()) != this.s) {
							recordsToMove.add(c);
							ocBlock.removeRecord(i);
						} else if (!parentBlock.isFull()) {
							parentBlock.appendRecord(c);
							ocBlock.removeRecord(i);
						}
					}

				}
				ocAdddress = ocBlock.getOcAddress();
				if (parentBlock.isFull()) {
					file.writeBlock(parentBlock.getIndex(), parentBlock);
					parentBlock = ocBlock;
					file = overCrowdingFile;
				} else {
					// if parentBlock is not full and I checked all blocks in
					// ocBlock and anyone was valid or all blocks were removed
					// (so invalid too) - logically
					parentBlock.setOcAddress(ocAdddress);
					overCrowdingFile.writeBlock(ocBlock.getIndex(), ocBlock);
					// write block already adds the invalid block to invalid
					// list
					// overCrowdingFile.addToInvalid(ocBlock.getIndex());
				}
			}
			sortedFileByCarNumber.writeBlock(tempBlock.getIndex(), tempBlock);
			// TODO implements addCars
			for (Record record : recordsToMove) {
				insertCarToSorted((Car) record, a);
			}
		}

	}

	private void insertCarToSorted(Car car, int sortedBlockIndex) {
		OvercrowdedBlock ocBlock1 = EmptyBlockFactory.carOcBlock(blockFactor);
		sortedFileByCarNumber.loadBlock(sortedBlockIndex, ocBlock1);
		if (!ocBlock1.isFull()) {
			ocBlock1.appendRecord(car);
			sortedFileByCarNumber.writeBlock(sortedBlockIndex, ocBlock1);
		} else {
			Integer ocAddress = ocBlock1.getOcAddress();
			if (ocAddress == -1) {
				Integer newAddress = overCrowdingFile.getInvalidBlockAddress();
				ocBlock1.setOcAddress(newAddress);
				sortedFileByCarNumber.writeBlock(sortedBlockIndex, ocBlock1);
				ocAddress = newAddress;
				overCrowdingFile.loadBlock(ocAddress, ocBlock1);
			}

			while (ocBlock1.isFull()) {
				ocAddress = ocBlock1.getOcAddress();
				if (ocAddress == -1) {
					Integer newAddress = overCrowdingFile.getInvalidBlockAddress();
					ocBlock1.setOcAddress(newAddress);
					overCrowdingFile.writeBlock(ocBlock1.getIndex(), ocBlock1);
					ocAddress = newAddress;
				}
				overCrowdingFile.loadBlock(ocAddress, ocBlock1);
			}
			ocBlock1.appendRecord(car);
			overCrowdingFile.writeBlock(ocAddress, ocBlock1);
		}
	}

	private int getBlockIndex(int hashedKey) {
		int i = getHu(hashedKey);
		if (i < this.s) {
			i = getHuPlus1(hashedKey);
		}

		return 0;
	}

	private int getHu(int hashedKey) {
		return (int) (hashedKey % (m * Math.pow(2, u)));
	}

	private int getHuPlus1(int hashedKey) {
		return (int) (hashedKey % (m * Math.pow(2, u + 1)));
	}

	private int searchIndex(int hashedKey) {
		// f1
		int blockIndex = (int) (hashedKey % (m * Math.pow(2, u)));
		if (blockIndex < this.s) {
			// f2
			blockIndex = (int) (hashedKey % (m * Math.pow(2, u + 1)));
		}

		return 0;
	}

	public Car getCarByCarNumber(String carNumber) {
		return null;
	}

	public Car getCarByVinNumber(String vinNumber) {
		return null;
	}
}
