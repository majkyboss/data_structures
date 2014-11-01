package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import core.IdCounter;

public class IdCounterTester {

	@Test
	public void test() {
		int firstId = IdCounter.getLastId();

		int nextId = IdCounter.getNextId();

		for (int i = 0; i < 5; i++) {
			System.out
					.println("------------------------------------------------");
			System.out.println("iteration " + i);
			System.out.println("first id: " + firstId);
			System.out.println("next id: " + nextId);
			System.out.println("current id counter: "
					+ IdCounter.getLastId());

			firstId = IdCounter.getLastId();

			nextId = IdCounter.getNextId();
		}

		assertEquals(firstId + 1, nextId);
		assertEquals(nextId, IdCounter.getLastId());
	}

}
