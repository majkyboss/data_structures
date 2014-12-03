package sem2.blocksBased;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import util.BinaryFileHandler;

public class BlockFile<T> {
	private String path = "";
	private int blockSize = 0;
	private int absoluteAddress = 0;

	public BlockFile(String path, int blockSize) {
		super();
		this.path = path;
		this.blockSize = blockSize;
	}

	public void write(Block<T> block) {
		BinaryFileHandler.saveToBinaryFile(block.getBytes(), new File(path), absoluteAddress, block.getByteSize());
	}

	public void read(Block<T> block) {
		try {
			byte[] bytes = BinaryFileHandler.loadBinaryFile(new FileInputStream(new File(path)), absoluteAddress, block.getByteSize());
			block.fillFromBytes(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void seek(int relativeAddress) {
		// entered block number, to get real address multiply by block size
		// and it sets the address to the block
		absoluteAddress = relativeAddress * blockSize;
	}

	public int getActualAddress() {
		return absoluteAddress;
	}

	public int getValidAddress() {
		// TODO implement finding first valid address in file

		return 0;
	}
}
