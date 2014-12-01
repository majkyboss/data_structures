package sem2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import util.BinaryFileHandler;

public class UnSortedFile<T extends RecordValue> {
	private Block[] blocks;
	private int blocksNum = 0;
	private final int blockFactor;
	private final int recordSize;
	private final String filePath;

	public UnSortedFile(int recordSize, int blockFactor, int blocksNum, String filePath) {
		super();
		this.recordSize = recordSize;
		this.blockFactor = blockFactor;
		this.blocksNum = blocksNum;
		this.filePath = filePath;

		// fill file by 0
		//

		byte[] initBytes = new byte[getByteSize()];
		BinaryFileHandler.saveToBinaryFile(initBytes, new File(filePath), 0, initBytes.length);

		blocks = new Block[blocksNum];

		for (int i = 0; i < blocksNum; i++) {
			Block block = new Block(recordSize, blockFactor, i);
			blocks[i] = block;
			block.setAddress(i);
		}
	}

	public int getByteSize() {
		// TODO add header size
		return recordSize * blockFactor * blocksNum;
	}

	public void addRecord(T record) {
		// write data to first invalid record
		// - find block where the first invalid record is
		// - find first invalid record
		// - write data to record address

		for (int i = 0; i < blocks.length; i++) {
			Block block = blocks[i];
			Record invalidRecord = null;
			if ((invalidRecord = block.getFirstInvalidRecord()) != null) {
				int address = invalidRecord.getAddress();

			}
		}

		Block freeBlock = getFreeBlock();
		if (freeBlock != null) {
			freeBlock.add(record);
		}
	}

	private Block getFreeBlock() {
		// TODO Auto-generated method stub
		for (int i = 0; i < blocks.length; i++) {
			Block block = blocks[i];
			if (block.hasInvalidRecord()) {
				return block;
			}
		}
		return null;
	}

	public void add(RecordValue value) {
		int blockAddress = getFirstFreeBlockAddress();
		Block b = null;
		if (blockAddress < 0) {
			b = new Block(recordSize, blockFactor, blocksNum + 1);
		} else {
			b = loadBlock(blockAddress);
		}
		b.add(value);
		writeBlock(b);
	}

	private int getFirstFreeBlockAddress() {
		// TODO Auto-generated method stub ----------------------------------------------------------
		return 0;
	}

	private void writeBlock(Block b) {
		BinaryFileHandler.saveToBinaryFile(b.getBytes(), new File(filePath), b.getAddress(), b.getByteSize());
	}

	private Block loadBlock(int blockAddress) {
		try {
			byte[] bytes = BinaryFileHandler.loadBinaryFile(new FileInputStream(new File(filePath)), blockAddress, recordSize * blockFactor);
			Block b = new Block(recordSize, blockFactor, blockAddress);
			b.fillFromBytes(bytes, 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	// -------------------------------------------------------
	// /**
	// * Attribute specify location of file on disk
	// */
	// private final String path;
	//
	// /**
	// * Attribute specify the number of records in one block
	// */
	// private final int recordsInBlock;
	//
	// public UnSortedFile(String path, int recordsInBlock ) {
	// super();
	// this.path = path;
	// this.recordsInBlock = recordsInBlock;
	// IRecord.class.get
	// }
	//
	// public String getPath() {
	// return path;
	// }
	//
	// /**
	// * Read outArray.length bytes from file specified in attribute
	// * {@link UnSortedFile#path}
	// *
	// * @param outArray
	// * @param address
	// * @return
	// * @throws IOException
	// */
	// private void read(byte[] outArray, int address, int length) throws
	// IOException {
	// File file = new File(path);
	// RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
	//
	// randomAccessFile.seek(address);
	// randomAccessFile.readFully(outArray, 0, outArray.length);
	//
	// randomAccessFile.close();
	// }
	//
	// /**
	// * Writes data from inArray into this file specified in
	// * {@link UnSortedFile#path}. Writing will start at address and will write
	// * length of data from inArray started at offset.
	// *
	// * @param inArray
	// * @param address
	// * address of
	// * @param offset
	// * start index in inArray
	// * @param length
	// * @throws IOException
	// */
	// private void write(byte[] inArray, int address, int offset, int length)
	// throws IOException {
	// File file = new File(path);
	// RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
	//
	// randomAccessFile.seek(address);
	// randomAccessFile.write(inArray, offset, length);
	//
	// randomAccessFile.close();
	// }
	//
	// public Block readBlock(int address) throws IOException {
	// byte[] outArray = new byte[2];
	// File file = new File(path);
	// RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
	//
	// randomAccessFile.seek(address);
	// randomAccessFile.readFully(outArray, 0, outArray.length);
	//
	// randomAccessFile.close();
	//
	// return null;
	// }
}
