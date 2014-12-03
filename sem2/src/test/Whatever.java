package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;

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
		assertNotEquals(hash1, hash2 - 1);
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

	@Test
	public void testObjectArray() throws Exception {
		Object[] array = new Object[4];
		array[0] = new TempObject(0, 1);
		array[1] = new TempObject(2, 3);
		array[2] = new Object();
		array[3] = new TempObject2(4, 5);

		System.out.println();
	}

	@SuppressWarnings("unused")
	private static class TempObject {
		int a;
		int b;

		public TempObject(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}
	}

	@SuppressWarnings("unused")
	private static class TempObject2 {
		int c;
		int d;

		public TempObject2(int c, int d) {
			super();
			this.c = c;
			this.d = d;
		}
	}
}
