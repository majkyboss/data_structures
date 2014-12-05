package sem2.blocksBased;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import util.BinaryFileHandler;

public class BlockFile<T> {
	private String path = "";
	private int blockByteSize = 0;
	private int absoluteAddress = 0;
	private int recordByteSize = 0;
	private int blockFactor = 0;
	private LinkedList<Integer> validBlocks;// TODO set valid blocks structure
											// where the addresses to valid (or
											// free) blocks will be
	private int maxAddress = 0;

	public BlockFile(String path, int blockFactor, int recordByteSize) {
		super();
		this.path = path;
		this.blockByteSize = blockFactor * recordByteSize;
		this.recordByteSize = recordByteSize;
		this.blockFactor = blockFactor;
	}

	public void write(Block<T> block) {
		BinaryFileHandler.saveToBinaryFile(block.getBytes(), new File(path), absoluteAddress, block.getByteSize());
		increaseMaxAddress(absoluteAddress + block.getByteSize());
	}

	public void read(Block<T> block) {
		try {
			byte[] bytes = BinaryFileHandler.loadBinaryFile(new FileInputStream(new File(path)), absoluteAddress, block.getByteSize());
			block.fillFromBytes(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void seek(int absoluteAddress) {
		this.absoluteAddress = absoluteAddress;
	}

	public int getActualAddress() {
		return absoluteAddress;
	}

	private void increaseMaxAddress(int address) {
		if (maxAddress < address) {
			maxAddress = address;
		}
	}

	public int getMaxAddress() {
		return maxAddress;
	}

	/**
	 * @return first valid block in file. Does not matter if free or partially
	 *         free.
	 */
	public int getValidAddress() {
		// TODO !!! implement finding first valid block address in file

		return 0;
	}
}
