package aaa;

import java.io.File;

import util.BinaryFileHandler;
import def.Block;
import def.Car;
import def.CarLinearHash;
import def.Record;
import def.UnsortedFile;

public class MainTest {
	//@f:off
//	public static void main(String[] args) throws FileNotFoundException {
//		int blockFactor = 2;
//		Car[] cars = new Car[blockFactor];
//		for (int i = 0; i < cars.length; i++) {
//			cars[i] = new Car("car " + i);
//		}
//
//		Block b = new Block(cars, blockFactor);
//		int address = new Car("").getByteSize() * blockFactor * 1;
//		int blockSize = new Car("").getByteSize() * blockFactor;
//
//		BinaryFileHandler.saveToBinaryFile(b.getBytes(), new File("testDir/dataFile"), address, blockSize);
//
//		System.out.println();
//
//		byte[] bytes = BinaryFileHandler.loadBinaryFile(new FileInputStream(new File("testDir/dataFile")), address, blockSize);
//		Car[] carsLoaded = new Car[blockFactor];
//		for (int i = 0; i < carsLoaded.length; i++) {
//			carsLoaded[i] = new Car();
//		}
//		Block bLoaded = new Block(carsLoaded, blockFactor);
//		bLoaded.fillFromBytes(bytes);
//
//		System.out.println();
//
//	}
	//@f:on
//@f:off
//	public static void main(String[] args) {
//		int blockFactor = 2;
//		String path = "testDir/dataFile";
//
//		Car car1 = new Car("car 1");
//
//		Record[] records = new Record[blockFactor];
//		for (int i = 0; i < records.length; i++) {
//			records[i] = new Car();
//		}
//		records[0] = car1;
//
//		Block block = new Block(records, blockFactor);
//		BinaryFileHandler.trimFile(new File(path), 0);
	// attention: last parameter is not record byte size anymore
//		UnsortedFile file = new UnsortedFile(path, blockFactor, car1.getByteSize());
//		file.appendBlock(block);
//
//		Car car2 = new Car("car 2");
//		records[0] = car2;
//		block = new Block(records, blockFactor);
//		file.appendBlock(block);
//
//		// -------------------------------------
//		for (int i = 0; i < records.length; i++) {
//			records[i] = new Car();
//		}
//
//		int blockIndex = 1;
//		block = new Block(records, blockFactor);
//		Car car3 = new Car("car 3");
//		file.loadBlock(blockIndex, block);
//		if (!block.isFull()) {
//			block.appendRecord(car3);
//		}
//		file.writeBlock(blockIndex, block);
//
//		// -------------------------------------
//		for (int i = 0; i < records.length; i++) {
//			records[i] = new Car();
//		}
//		block = new Block(records, blockFactor);
//		file.appendBlock(block);
//
//		// -------------------------------------
//		for (int i = 0; i < records.length; i++) {
//			records[i] = new Car();
//		}
//
//		blockIndex = 2;
//		block = new Block(records, blockFactor);
//		Car car4 = new Car("car 4");
//		file.loadBlock(blockIndex, block);
//		if (!block.isFull()) {
//			block.appendRecord(car4);
//		}
//		// file.writeBlock(blockIndex, block);
//		file.writeBlock(blockIndex + 1, block);
//		System.out.println(file.toString(block));
//
//		for (int i = 0; i < records.length; i++) {
//			records[i] = new Car();
//		}
//		block = new Block(records, blockFactor);
//		file.writeBlock(blockIndex + 1, block);
//
//		// --------------------------------------
//		// printing
//		System.out.println(file.toString(block));
//
//	}
	//@f:on

	public static void main(String[] args) {
//		int blockFactor = 2;
		String path = "testDir/";

		CarLinearHash file = new CarLinearHash(path, new Car(), true);
		for (int i = 0; i < 500; i++) {
			Car car = new Car("car "+i);
			file.addCar(car);
		}
		
		System.out.println(file.toString());

		// Record[] records = new Record[blockFactor];
		// for (int i = 0; i < records.length; i++) {
		// records[i] = new Car();
		// }
		// records[0] = car1;
		//
		// Block block = new Block(records, blockFactor);
		// BinaryFileHandler.trimFile(new File(path), 0);
		// // UnsortedFile file = new UnsortedFile(path, blockFactor,
		// car1.getByteSize());
		// file.appendBlock(block);
		//
		// Car car2 = new Car("car 2");
		// records[0] = car2;
		// block = new Block(records, blockFactor);
		// file.appendBlock(block);
		//
		// // -------------------------------------
		// for (int i = 0; i < records.length; i++) {
		// records[i] = new Car();
		// }
		//
		// int blockIndex = 1;
		// block = new Block(records, blockFactor);
		// Car car3 = new Car("car 3");
		// file.loadBlock(blockIndex, block);
		// if (!block.isFull()) {
		// block.appendRecord(car3);
		// }
		// file.writeBlock(blockIndex, block);
		//
		// // -------------------------------------
		// for (int i = 0; i < records.length; i++) {
		// records[i] = new Car();
		// }
		// block = new Block(records, blockFactor);
		// file.appendBlock(block);
		//
		// // -------------------------------------
		// for (int i = 0; i < records.length; i++) {
		// records[i] = new Car();
		// }
		//
		// blockIndex = 2;
		// block = new Block(records, blockFactor);
		// Car car4 = new Car("car 4");
		// file.loadBlock(blockIndex, block);
		// if (!block.isFull()) {
		// block.appendRecord(car4);
		// }
		// // file.writeBlock(blockIndex, block);
		// file.writeBlock(blockIndex + 1, block);
		// System.out.println(file.toString(block));
		//
		// for (int i = 0; i < records.length; i++) {
		// records[i] = new Car();
		// }
		// block = new Block(records, blockFactor);
		// file.writeBlock(blockIndex + 1, block);
		//
		// // --------------------------------------
		// // printing
		// System.out.println(file.toString(block));

	}
}
