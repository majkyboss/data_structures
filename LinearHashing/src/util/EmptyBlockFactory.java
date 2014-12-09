package util;

import def.Block;
import def.Car;
import def.OvercrowdedBlock;

public class EmptyBlockFactory {
	public static Block carBlock(int blockFactor) {
		Car[] cars = new Car[blockFactor];
		for (int i = 0; i < cars.length; i++) {
			cars[i] = new Car();
		}
		Block block = new Block(cars, blockFactor);
		return block;
	}

	public static OvercrowdedBlock carOcBlock(int blockFactor) {
		Car[] cars = new Car[blockFactor];
		for (int i = 0; i < cars.length; i++) {
			cars[i] = new Car();
		}
		OvercrowdedBlock block = new OvercrowdedBlock(cars, blockFactor);
		return block;
	}
}
