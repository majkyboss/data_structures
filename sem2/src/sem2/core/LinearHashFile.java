package sem2.core;

import sem2.Record;
import sem2.blocksBased.Block;
import sem2.core.specificFiles.DataFile;
import sem2.core.specificFiles.OverCrowdingFile;
import sem2.core.specificFiles.SortedFile;

public abstract class LinearHashFile<T> {
	private int recordByteSize = 0;
	private short dfBlockFactor = 0;
	private short sfBlockFactor = 0;
	private short ocfBlockFactor = 0;
	private DataFile<T> dataFile;
	private SortedFile<Integer> sortedFile;
	private OverCrowdingFile<Integer> ocFile;

	public LinearHashFile(int recordByteSize, short blockFactor, String dataFilePath, String sortedFilePath, String ocFilePath) {
		super();
		this.recordByteSize = recordByteSize;
		this.dfBlockFactor = blockFactor;
		this.sfBlockFactor = blockFactor;
		this.ocfBlockFactor = blockFactor;
		
		this.dataFile = new DataFile<>(dataFilePath, dfBlockFactor, recordByteSize);
		this.sortedFile = new SortedFile<>(sortedFilePath, sfBlockFactor, Integer.BYTES);
		this.ocFile = new OverCrowdingFile<>(ocFilePath, ocfBlockFactor, Integer.BYTES);
	}

	public void add(T value) {
		int dfAddress = dataFile.getValidAddress();
		Block<T> block = getEmptyBlock();
		dataFile.seek(dfAddress);
		dataFile.read(block);
		block.addValue(value);
		dataFile.write(block);

		// add data file address into sorted file

		int sfAddress = getAddressFromHash(value.hashCode());
		ContinueIntBlock sfBlock = new ContinueIntBlock(sfBlockFactor);
		sortedFile.seek(sfAddress);
		sortedFile.read(sfBlock);
		Record<Integer> sfRecord = null;

		// find first free record in block
		sfRecord = sfBlock.getFreeRecord();
		// if no record was found, the block is full
		if (sfRecord == null) {
			boolean inserted = false;
			while (!inserted) {
				// save value into overcrowding block in ocFile
				int ocAddress = sfBlock.getOverCrowdingBlockAddress();
				if (ocAddress == -1) {
					// there is no set ocBlock, so
					// create new block in oc file
					// or find first free block in file
					ocAddress = ocFile.getValidAddress();
					if (ocAddress == -1) {
						// create new block in oc file
						ContinueIntBlock newBlock = new ContinueIntBlock(ocfBlockFactor);
						ocAddress = ocFile.getMaxAddress() + 1;
						newBlock.setAddress(ocAddress);
						inserted = newBlock.addValue(dfAddress);

						sfBlock.setOverCrowdingBlockAddress(newBlock.getAddress());
						sortedFile.seek(sfBlock.getAddress());
						sortedFile.write(sfBlock);

						ocFile.seek(newBlock.getAddress());
						ocFile.write(newBlock);
					} else {
						// use first free
						ContinueIntBlock newBlock = new ContinueIntBlock(ocfBlockFactor);
						ocFile.seek(ocAddress);
						ocFile.read(newBlock);

						inserted = newBlock.addValue(dfAddress);

						sfBlock.setOverCrowdingBlockAddress(newBlock.getAddress());
						sortedFile.seek(sfBlock.getAddress());
						sortedFile.write(sfBlock);

						ocFile.write(newBlock);
					}
				} else {
					// ocBlock found // ocAddress != -1

					// find first free record
					ocFile.seek(ocAddress);
					// updating actual block , after this the ocBlock is actual
					ocFile.read(sfBlock);
					// if it if not posiible to add value to block, the block is
					// full
					inserted = sfBlock.addValue(dfAddress);
				}
			}
		}

		if (sfRecord != null) {
			sfBlock.addValue(dfAddress);
			sortedFile.write(sfBlock);
		}

	}

	private int getAddressFromHash(int hashCode) {
		// TODO implement hashing
		return 0;
	}

//	private ContinueIntBlock getSortedBlock() {
//		return new ContinueIntBlock(dfBlockFactor);
//	}

	abstract protected Block<T> getEmptyBlock();
}

