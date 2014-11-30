package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import util.BinaryFileHandler;
import util.BitConverter;
import util.IBinarySerial;

public class BinaryTest {
	private static final String file1 = "testDir/saved";

	@Test
	public void test() throws FileNotFoundException {
		int max_val = Integer.MAX_VALUE;
		int min_val = Integer.MIN_VALUE;
		String text_value = "çest";

		ClassForSave cfs = new ClassForSave();
		cfs.attr1 = max_val;
		cfs.attr2 = min_val;
		InClass inc = new InClass();
		inc.text = text_value;
		cfs.inC = inc;

		int length = cfs.getByteSize();
		File fileToSave = new File(file1);
		BinaryFileHandler.saveToBinaryFile(cfs.getBytes(), fileToSave, 0, length);

		int byteCountToLoad = new ClassForSave().getByteSize();
		byte[] loadedBytes = BinaryFileHandler.loadBinaryFile(new FileInputStream(fileToSave), 0, byteCountToLoad);

		ClassForSave loadedClass = new ClassForSave();
		loadedClass.fillFromBytes(loadedBytes, 0);
		System.out.println();

		assertEquals(max_val, loadedClass.attr1.intValue());
		assertEquals(min_val, loadedClass.attr2.intValue());
		assertEquals(text_value, cfs.inC.text);
	}

	static class ClassForSave implements IBinarySerial {
		private static byte[] header = new byte[] {};
		private static int attr1_length = Integer.BYTES;
		private static int attr2_length = Integer.BYTES;

		Integer attr1 = 0;
		Integer attr2 = 0;
		InClass inC = null;

		public byte[] getBytes() {
			byte[] retVal = new byte[getByteSize()];
			int offset = 0;
			BitConverter.putBytes(header, retVal, offset);
			offset += header.length;
			BitConverter.putInt(attr1, retVal, offset);
			offset += attr1_length;
			BitConverter.putInt(attr2, retVal, offset);
			offset += attr2_length;
			BitConverter.putBytes(inC.getBytes(), retVal, offset);

			return retVal;
		}

		public void fillFromBytes(byte[] value, int offset) {

			offset += header.length;
			attr1 = BitConverter.getInt(value, offset);
			offset += attr1_length;
			attr2 = BitConverter.getInt(value, offset);
			offset += attr2_length;
			inC = new InClass();
			inC.fillFromBytes(value, offset);
		}

		public int getByteSize() {
			return header.length + attr1_length + attr2_length + new InClass().getByteSize();
		}
		//
		// int getSize() {
		// return attr1_length + attr2_length + inC.getSize();
		// }

	}

	static class InClass implements IBinarySerial {
		private static final int TEXT_MAX_LENGTH = 4;
		String text = "";

		public byte[] getBytes() {
			byte[] bytes = new byte[getByteSize()];
			BitConverter.putString(text, bytes, 0);
			return bytes;
		}

		public void fillFromBytes(byte[] value, int offset) {
			text = BitConverter.getString(value, offset, getByteSize());
		}

		//
		// int getSize() {
		// return text.length();
		// }

		public int getByteSize() {
			return TEXT_MAX_LENGTH * Character.BYTES;
		}
	}

}
