package sem2_second_start;

import util.BitConverter;

public class Car implements Record {
	private int vin_numer = 0;

	@Override
	public int getByteSize() {
		return Integer.BYTES;
	}

	@Override
	public byte[] getBytes() {
		// TODO Auto-generated method stub
		int offset = 0;
		byte[] bytes = new byte[getByteSize()];
		BitConverter.putInt(vin_numer, bytes, offset);
		return bytes;
	}

	@Override
	public void fillFromBytes(byte[] bytes) {
		int offset = 0;
		vin_numer = BitConverter.getInt(bytes, offset);

	}

}
