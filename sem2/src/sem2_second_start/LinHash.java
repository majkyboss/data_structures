package sem2_second_start;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.List;

public class LinHash {
	private int blockCount = 16;
	private String dataFilePath = "testDir/dataFile";
	private int blockFactor = 0;
	private int recordLength = 0;
	private final File dataFile;
	private LinkedList<Integer> notFullDataBlocks = new LinkedList<Integer>();

	public LinHash(int blockCount, int blockFactor, int recordLength) {
		super();
		this.blockCount = blockCount;
		this.blockFactor = blockFactor;
		this.recordLength = recordLength;
		dataFile = new File(dataFilePath);
	}

	public void add(Car item) {
		// add to data file
		// - find first not full block address and get it
		// - - find first not full block address
		int dataFileAddress = 0;
		if (!notFullDataBlocks.isEmpty()) {
			dataFileAddress = notFullDataBlocks.element();
		} else {
			dataFileAddress = (int) dataFile.length();
			LinkedList<Car> records = new LinkedList<Car>();
			records.add(item);
			Block b = new Block(blockFactor, records, item.getByteSize());
		}
		// - - return the byte array - from found address with length size of
		// block
		// - - if there is no not full block
		// - - - create new block at address max + 1
		// - add record into first invalid record
		// -- find first invalid record and save data to this record

		// - returns the block address

		// add to sorted file
		// - calculate address - hash functions
		// ...
	}

	public void addAll(List<Car> item) {

	}
}
