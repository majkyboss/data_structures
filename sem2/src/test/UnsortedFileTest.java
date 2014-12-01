package test;

import static org.junit.Assert.*;

import org.junit.Test;

import sem2.RecordValue;
import sem2.UnSortedFile;
import util.BitConverter;

public class UnsortedFileTest {

	@Test
	public void test() {
		DataValue dv = new DataValue();
		int blockFactor = 4;
		int fileFactor = 6;
		String filePath = "testDir/unsortedFileTest";
		UnSortedFile<DataValue> file = new UnSortedFile<>(dv.getByteSize(), blockFactor, fileFactor, filePath);

		fail("Not yet implemented");
	}

	public static class DataValue implements RecordValue {
		private static int idCounter = 0;
		int id = idCounter++;

		// TODO add string attribute and contructor which sets the attributes

		@Override
		public int getByteSize() {
			return Integer.BYTES;
		}

		@Override
		public int getKey() {
			return hashCode();
		}

		@Override
		public void fillFromBytes(byte[] bytes, int offset) {
			id = BitConverter.getInt(bytes, offset);
			offset += Integer.BYTES;
		}

		@Override
		public byte[] getBytes() {
			byte[] bytes = new byte[getByteSize()];
			BitConverter.putInt(id, bytes, 0);
			return bytes;
		}

	}
}
