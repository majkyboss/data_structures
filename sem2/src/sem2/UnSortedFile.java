package sem2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UnSortedFile<T extends RecordValue> {
	private Block[] blocks;
	private int blocksNum = 0;
	private final int blockFactor;

	public UnSortedFile(int blocksNum, int blockFactor) {
		super();
		this.blockFactor = blockFactor;
		this.blocksNum = blocksNum;
		blocks = new Block[blocksNum];

		for (int i = 0; i < blocksNum; i++) {
			Block block = new Block(blockFactor);
			blocks[i] = block;
		}
	}

	// private void init() {
	// blocks = new Block[blocksNum];
	// for (int i = 0; i < blocksNum; i++) {
	// Block block = new Block(blockFactor);
	// blocks[i] = block;
	// }
	// }

	public void addRecord(T record) {
		Block freeBlock = getFreeBlock();
		if (freeBlock != null) {
			freeBlock.addRecord(record);
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
