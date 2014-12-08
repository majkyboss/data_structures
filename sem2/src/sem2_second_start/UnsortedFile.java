package sem2_second_start;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;

import util.BinaryFileHandler;

public class UnsortedFile {
	private String path;
	private int blockFactor = 0;
	private int recordLength = 0;
	private final LinkedList<Integer> validBlocksAddress = new LinkedList<Integer>();
	private int length = 0;

	public UnsortedFile(String path, int blockFactor, int recordLength) {
		super();
		this.path = path;
		this.blockFactor = blockFactor;
		this.recordLength = recordLength;
	}

	public LinkedList<Record> getBlock(int blockIndex) {
		int start = blockIndex * blockFactor * recordLength;
		int length = blockFactor * recordLength;
		byte[] bytes = new byte[length];
		try {
			bytes = BinaryFileHandler.loadBinaryFile(new FileInputStream(new File(path)), start, length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// load records
		LinkedList<Record> block = new LinkedList<Record>();
		int offset = 0;
		while (offset < bytes.length) {
			byte[] carBytes = Arrays.copyOfRange(bytes, offset, recordLength);
			Car c = new Car(carBytes);
			block.add(c);
			offset += recordLength;
		}

		return block;
	}

	public long getValidBlockAddress() {
		if (validBlocksAddress.isEmpty()) {
			return length;
		} else {
			return validBlocksAddress.element();
		}
	}

	public void addBlock() {

	}

	public void putBlock(byte[] block, int blockIndex) {
		int start = blockIndex * blockFactor * recordLength;
		int length = blockFactor * recordLength;
		BinaryFileHandler.saveToBinaryFile(block, new File(path), start, block.length);
		if (block.length==length){
			
		}
	}

	//
	// public byte[] getFirstValidBlock(){
	// long address = getValidBlockAddress();
	// int length = blockFactor*recordLength;
	// byte[] bytes = new byte[length];
	// bytes = BinaryFileHandler.loadBinaryFile(new FileInputStream(new
	// File(path)), address, length);
	//
	// return bytes;
	// }
}
