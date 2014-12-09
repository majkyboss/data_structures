package def;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;

import util.BinaryFileHandler;

public class UnsortedFile {
	/**
	 * Same as length
	 */
	private int fisrtUnusedAddress = 0;
	private int lastBlockIndex = -1;
	private int blockFactor;
	private int recordByteSize;
	private String path;
	private LinkedList<Integer> invalidBlocks = new LinkedList<Integer>();
	private LinkedList<Integer> notFullValidBlocks = new LinkedList<Integer>();

	public UnsortedFile(String path, int blockFactor, int recordByteSize) {
		super();
		this.path = path;
		this.blockFactor = blockFactor;
		this.recordByteSize = recordByteSize;
		fisrtUnusedAddress = (int) new File(path).length();
	}

	public void appendBlock(Block block) {
		if (!block.isValid()) {
			return;
		}
		// BinaryFileHandler.saveToBinaryFile(block.getBytes(), new File(path),
		// fisrtUnusedAddress, recordByteSize * blockFactor);
		BinaryFileHandler.saveToBinaryFile(block.getBytes(), new File(path), (lastBlockIndex + 1) * blockFactor * recordByteSize, recordByteSize * blockFactor);
		fisrtUnusedAddress += (lastBlockIndex + 2) * blockFactor * recordByteSize;
		lastBlockIndex++;
		block.setIndex(lastBlockIndex);
		if (!block.isFull()) {
			notFullValidBlocks.addLast(lastBlockIndex);
		}
	}

	public Block loadBlock(int blockIndex, Block toBlock) {
		byte[] bytes = new byte[recordByteSize * blockFactor];
		try {
			bytes = BinaryFileHandler.loadBinaryFile(new FileInputStream(new File(path)), blockIndex * blockFactor * recordByteSize, recordByteSize * blockFactor);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (bytes.length == 0) {
			bytes = new byte[recordByteSize * blockFactor];
		}

		toBlock.fillFromBytes(bytes);
		toBlock.setIndex(blockIndex);
		return toBlock;
	}

	public void writeBlock(int blockIndex, Block block) {
		if (!block.isValid()) {
			addToInvalid(blockIndex);
			return;
		}
		BinaryFileHandler.saveToBinaryFile(block.getBytes(), new File(path), blockIndex * blockFactor * recordByteSize, recordByteSize * blockFactor);

		// if block is not full and block is valid (from first if in this
		// method)
		if (!block.isFull()) {
			notFullValidBlocks.add(blockIndex);
			notFullValidBlocks.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return Integer.compare(o1, o2);
				}
			});
		}

		// if last block was 2 and new block was added to index more then
		// lastBlockIndex+1 (lastBlockIndex+2>newBlockIndex>lastBlockIndex ...
		// means append to end), add all skipped blocks to invalid list
		if (blockIndex - 1 > lastBlockIndex) {
			int tempIndex = blockIndex - 1;
			while (tempIndex > lastBlockIndex) {
				invalidBlocks.add(tempIndex);
				tempIndex--;
			}
		}

		if (blockIndex * blockFactor * recordByteSize + blockFactor * recordByteSize > fisrtUnusedAddress) {
			fisrtUnusedAddress = blockIndex * blockFactor * recordByteSize + blockFactor * recordByteSize;
			lastBlockIndex = blockIndex;
		}
	}

	private void addToInvalid(int blockIndex) {
		invalidBlocks.add(blockIndex);
		invalidBlocks.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1.intValue(), o2.intValue());
			}
		});

		if (blockIndex == invalidBlocks.peekLast()) {
			LinkedList<Integer> blocksToDel = new LinkedList<Integer>();
			int lastIndex = blockIndex;

			blocksToDel.add(lastIndex);
			invalidBlocks.removeLast();
			while (!invalidBlocks.isEmpty() && lastIndex - 1 == invalidBlocks.peekLast()) {
				lastIndex = invalidBlocks.removeLast();
				blocksToDel.add(lastIndex);
			}
			BinaryFileHandler.trimFile(new File(path), lastIndex * blockFactor * recordByteSize);
			fisrtUnusedAddress = lastIndex * blockFactor * recordByteSize;
			lastBlockIndex = lastIndex - 1;
		}
	}

	public String toString(Block tempBlock) {
		int blockIndex = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("---------------------------------\n");
		sb.append("File: ");
		sb.append(new File(path).getName());
		sb.append("\n---------------------------------\n");
		while (blockIndex * blockFactor * recordByteSize < fisrtUnusedAddress) {
			loadBlock(blockIndex, tempBlock);
			sb.append(tempBlock.toString());
			blockIndex++;
		}

		return sb.toString();
	}

	public Integer getNotFullBlockAddress() {
		return notFullValidBlocks.pollFirst();
	}

	public Integer getInvalidBlockAddress() {
		if (invalidBlocks.isEmpty()) {
			return lastBlockIndex + 1;
		} else {
			return invalidBlocks.pollFirst();
		}
	}

	public Integer getFreeRecordBlockIndex() {
		if (notFullValidBlocks.isEmpty()) {
			if (invalidBlocks.isEmpty()) {
				return lastBlockIndex + 1;
			} else {
				return invalidBlocks.pollFirst();
			}
		} else {
			return notFullValidBlocks.pollFirst();
		}
	}

	public int getLastBlockIndex() {
		return lastBlockIndex;
	}
}
