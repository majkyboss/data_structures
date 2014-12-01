package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import sem2_newStart.Car;
import sem2_newStart.CarRecord;
import sem2_newStart.SomeFile;
import util.BinaryFileHandler;

public class SomeFileTest {

	@Test
	public void test() throws FileNotFoundException {
		String path = "testDir/someFileTes";
		String carNumber = "ZA111AA";
		String vinNumber = "01234567891234567";
		int weight = 500;

		Car c = new Car(carNumber, vinNumber, weight);
		CarRecord record = new CarRecord();
		record.setValue(c);

		SomeFile<Car> file = new SomeFile<>(path, record.getByteSize());
		// get first valid address
		int address = file.getValidAddress();

		file.seek(address);
		file.write(record);

		byte[] loadedBytes = BinaryFileHandler.loadBinaryFile(new FileInputStream(new File(path)), file.getActualAddress(), record.getByteSize());
		byte[] expectedBytes = new byte[] { 0x00, 0x5a, 0x00, 0x41, 0x00, 0x31, 0x00, 0x31, 0x00, 0x31, 0x00, 0x41, 0x00, 0x41, 0x00, 0x30, 0x00, 0x31, 0x00, 0x32, 0x00, 0x33, 0x00, 0x34, 0x00, 0x35, 0x00, 0x36, 0x00, 0x37, 0x00, 0x38, 0x00, 0x39, 0x00, 0x31, 0x00, 0x32, 0x00, 0x33, 0x00, 0x34, 0x00, 0x35, 0x00, 0x36, 0x00, 0x37, 0x00, 0x00, 0x01, (byte) 0xF4 };
		assertArrayEquals(expectedBytes, loadedBytes);

		// loading record
		CarRecord recordLoaded = new CarRecord();
		// read from actual adrress
		file.read(recordLoaded.getByteSize(), recordLoaded);

		assertEquals(carNumber, recordLoaded.getValue().getCarNumber());
		assertEquals(vinNumber, recordLoaded.getValue().getVinNumber());
		assertEquals(weight, recordLoaded.getValue().getWeight());
	}

}
