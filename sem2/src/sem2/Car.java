package sem2;

import util.BitConverter;

public class Car implements RecordValue {
	private static final int ATTR_1_SIZE = Integer.BYTES;
	private static final int ATTR_2_SIZE = Integer.BYTES;
	private static final int BYTE_SIZE = ATTR_1_SIZE + ATTR_2_SIZE;

	private int attr1 = 0;
	private int attr2 = 1;

	@Override
	public int getByteSize() {
		return BYTE_SIZE;
	}

	@Override
	public int getKey() {
		return hashCode();
	}

	@Override
	public void fillFromBytes(byte[] bytes, int offset) {
		attr1 = BitConverter.getInt(bytes, offset);
		offset += ATTR_1_SIZE;
		attr2 = BitConverter.getInt(bytes, offset);
		offset += ATTR_2_SIZE;
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[getByteSize()];
		int offset = 0;
		BitConverter.putInt(attr1, bytes, offset);
		offset += ATTR_1_SIZE;
		BitConverter.putInt(attr2, bytes, offset);

		return bytes;
	}
}
