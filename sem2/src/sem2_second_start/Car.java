package sem2_second_start;

import util.BitConverter;

public class Car implements Record {
	private int vin_numer = 0;
	private boolean valid = false;

	public Car(int vin_numer) {
		super();
		this.vin_numer = vin_numer;
		valid = true;
	}

	public Car(byte[] bytes) {
		super();
		fillFromBytes(bytes);
	}

	@Override
	public int getByteSize() {
		return 1 + Integer.BYTES;
	}

	@Override
	public byte[] getBytes() {
		// TODO Auto-generated method stub
		int offset = 0;
		byte[] bytes = new byte[getByteSize()];
		BitConverter.putBoolean(valid, bytes, offset);
		offset++;
		BitConverter.putInt(vin_numer, bytes, offset);
		return bytes;
	}

	@Override
	public void fillFromBytes(byte[] bytes) {
		int offset = 0;
		valid = BitConverter.getBoolean(bytes, offset);
		offset++;
		vin_numer = BitConverter.getInt(bytes, offset);
	}

	@Override
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
