package sem2.core;

import sem2.Record;
import util.BitConverter;

public class IntegerRecord extends Record<Integer> {

	public IntegerRecord() {
		super(Integer.BYTES);
	}

	public IntegerRecord(byte[] bytes) {
		super(bytes);
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[getByteSize()];
		
		if (value == null) {
			return bytes;
		}
		
		int offset = 0;
		BitConverter.putInt(value, bytes, offset);
		return bytes;
	}

	@Override
	public void fillFromBytes(byte[] bytes) {
		boolean nullBytes = true;
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] != 0) {
				nullBytes = false;
				break;
			}
		}
		if (nullBytes) {
			return;
		}
		
		int offset = 0;
		value = BitConverter.getInt(bytes, offset);
		setValid(true);
	}

}
