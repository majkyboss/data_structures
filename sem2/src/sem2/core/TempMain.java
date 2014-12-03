package sem2.core;

import sem2.blocksBased.BlockFile;
import sem2.blocksBased.CarBlock;
import sem2.blocksBased.CarRecord;
import sem2.recordBased.Car;

public class TempMain {
	public static void main(String[] args) {

		// test objsects params
		String path = "dataDir/unsortedCars";
		String carNumber = "ZA111AA";
		String vinNumber = "01234567891234567";
		int weight = 500;

		// structure params
		short blockFactor = 4;

		BlockFile<Car> unsortedFile = new BlockFile<>(path, blockFactor);
		Car car = new Car(carNumber, vinNumber, weight);
		CarBlock carBlock = new CarBlock(new CarRecord().getByteSize(), blockFactor);
		carBlock.addValue(car);

		int address = unsortedFile.getValidAddress();
		unsortedFile.seek(address);
		unsortedFile.write(carBlock);
	}
}
