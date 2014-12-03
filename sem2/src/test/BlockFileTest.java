package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import sem2.blocksBased.BlockFile;
import sem2.blocksBased.CarBlock;
import sem2.blocksBased.CarRecord;
import sem2.recordBased.Car;
import util.BinaryFileHandler;

public class BlockFileTest {

	@Test
	public void test() throws FileNotFoundException {
		String path = "testDir/blockFileTest";
		String carNumber = "ZA111AA";
		String vinNumber = "01234567891234567";
		int weight = 500;

		Car c = new Car(carNumber, vinNumber, weight);
		CarRecord record = new CarRecord();
		record.setValue(c);

		CarBlock block = new CarBlock(record.getByteSize(), (short) 2);
		block.addValue(c);

		BlockFile<Car> file = new BlockFile<>(path, record.getByteSize());
		// get first valid address
		int address = file.getValidAddress();

		file.seek(address);
		file.write(block);

		byte[] loadedBytes = BinaryFileHandler.loadBinaryFile(new FileInputStream(new File(path)), file.getActualAddress(), block.getByteSize());
		byte[] expectedBytes = new byte[] { 0x00, 0x02, 0x00, 0x5a, 0x00, 0x41, 0x00, 0x31, 0x00, 0x31, 0x00, 0x31, 0x00, 0x41, 0x00, 0x41, 0x00, 0x30, 0x00, 0x31, 0x00, 0x32, 0x00, 0x33, 0x00, 0x34, 0x00, 0x35, 0x00, 0x36, 0x00, 0x37, 0x00, 0x38, 0x00, 0x39, 0x00, 0x31, 0x00, 0x32, 0x00, 0x33, 0x00, 0x34, 0x00, 0x35, 0x00, 0x36, 0x00, 0x37, 0x00, 0x00, 0x01, (byte) 0xf4, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		assertArrayEquals(expectedBytes, loadedBytes);

		// loading record
		@SuppressWarnings("unused")
		CarRecord recordLoaded = new CarRecord();
		CarBlock blockLoaded = new CarBlock(record.getByteSize(), (short) 2);
		// read from actual adrress
		file.read(blockLoaded);

		Car carLoaded = blockLoaded.getValue(0);

		assertEquals(carNumber, carLoaded.getCarNumber());
		assertEquals(vinNumber, carLoaded.getVinNumber());
		assertEquals(weight, carLoaded.getWeight());
	}

}
