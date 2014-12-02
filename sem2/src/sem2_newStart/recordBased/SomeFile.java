package sem2_newStart.recordBased;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import sem2_newStart.Record;
import util.BinaryFileHandler;

public class SomeFile<T> {
	private String path = "";
	private int blockSize = 0;
	private int absoluteAddress = 0;

	public SomeFile(String path, int blockSize) {
		super();
		this.path = path;
		this.blockSize = blockSize;
	}

	public void write(Record<T> record) {
		BinaryFileHandler.saveToBinaryFile(record.getBytes(), new File(path), absoluteAddress, record.getByteSize());
	}

	public void read(int length, Record<T> record) {
		try {
			byte[] bytes = BinaryFileHandler.loadBinaryFile(new FileInputStream(new File(path)), absoluteAddress, record.getByteSize());
			record.fillFromBytes(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void seek(int relativeAddress) {
		// entered block number, to get real address multiply by block size
		absoluteAddress = relativeAddress * blockSize;
	}

	public int getActualAddress() {
		return absoluteAddress;
	}

	public int getValidAddress() {
		return 0;
	}
}
