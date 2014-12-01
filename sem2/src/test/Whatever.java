package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class Whatever {

	@Test
	public void test() {
		First f1 = new First();
		First f2 = new First();
		int hash1 = f1.getHash();
		int hash2 = f2.getHash();

		System.out.println(hash1);
		System.out.println(hash2);
		// 1924582348
		// 11003494

		// 1924582348
		// 11003494

		int retVal = Integer.compare(hash1, hash2);
		assertEquals(hash1, hash2 - 1);
	}

	public static class First {
		private static int idCounter = 0;
		int id = idCounter;

		public int getHash() {
			// return id;
			return this.hashCode();
		}
	}

	@Test
	public void testMethods() {
		int offset = 0;
		method1(offset);
		System.out.println("");
	}

	private void method1(int offset) {
		method2(offset);
		System.out.println("");
	}

	private void method2(int offset) {
		offset += 5;
	}

}
