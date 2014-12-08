package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;

import sem2_second_start.Block;
import sem2_second_start.Car;
import sem2_second_start.LinHash;
import sem2_second_start.Record;
import util.BinaryFileHandler;

public class SecondTests {
	private final LinkedList<Integer> validBlocksAddress = new LinkedList<Integer>();

	@Test
	public void test() throws FileNotFoundException {
		boolean boolValue = true;
		byte byteValue = (byte) (boolValue ? 1 : 0);

		// boolValue = false;
		// byteValue = (byte) (boolValue ? 1 : 0);

		byte[] bytes = new byte[] { byteValue };

		BinaryFileHandler.saveToBinaryFile(bytes, new File("testDir/testBooleanFile"), 0, bytes.length);
		boolValue = !boolValue;

		bytes = BinaryFileHandler.loadBinaryFile(new FileInputStream(new File("testDir/testBooleanFile")), 0, 1);

		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == 0x00)
				boolValue = false;
			else if (bytes[i] == 0x01) {
				boolValue = true;
			}
		}
		int a = validBlocksAddress.poll();

		validBlocksAddress.add(1);
		validBlocksAddress.add(2);

		System.out.println();
		assertTrue(true);
	}

	@Test
	public void testBlockSave() throws Exception {
		int blockFactor = 4;
		String pathname = "testDir/blockFileTest";
		// pathname = "testDir/dataFile";
		int blockAddress = 2 * blockFactor;

		LinkedList<Record> records = new LinkedList<Record>();
		for (int i = 0; i < blockFactor - 1; i++) {
			records.add(new Car(i));
		}
		Block block = new Block(blockFactor, records, new Car(0).getByteSize());

		BinaryFileHandler.saveToBinaryFile(block.getBytes(), new File(pathname), blockAddress, block.getByteSize());

	}

	@Test
	public void testBlockLoad() throws Exception {
		int blockFactor = 4;
		String pathname = "testDir/blockFileTest";
		int recordSize = new Car(0).getByteSize();

		int blockAddress = 2 * blockFactor;
		int blockSize = blockFactor * recordSize;

		byte[] bytes = BinaryFileHandler.loadBinaryFile(new FileInputStream(new File(pathname)), blockAddress, blockSize);

		int offset = 0;
		LinkedList<Record> records = new LinkedList<>();
		while (offset < bytes.length) {
			byte[] carBytes = Arrays.copyOfRange(bytes, offset, offset + recordSize);
			Car c = new Car(carBytes);
			records.add(c);
			offset += recordSize;
		}

		Block block = new Block(blockFactor, records, recordSize);

		for (int i = 0; i < blockFactor - 1; i++) {
			assertTrue(records.get(i).isValid());
		}

		System.out.println();
	}

	@Test
	public void testLHash() throws Exception {
		Car c = new Car(7);
		int blockCount = 5;
		int blockFactor = 4;

		LinHash linhash = new LinHash(blockCount, blockFactor, c.getByteSize());

		linhash.add(c);
	}
}
