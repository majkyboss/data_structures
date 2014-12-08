package def;

import java.util.Date;

import util.BitConverter;

public class Car implements Record {
	private String carNumber = "";
	private int carNumberByteSize = 7 * BitConverter.CHAR_SIZE;
	private String vinNumber = "";
	private int vinNumberByteSize = 17 * BitConverter.CHAR_SIZE;
	private int axleCount;
	private int weight;
	private boolean wanted;
	private Date endDateSTK;
	private Date endDateEK;
	private boolean valid = false;

	public Car() {
		super();
		valid = false;
	}

	public Car(String carNumber) {
		super();
		this.carNumber = carNumber;
		valid = true;
	}

	@Override
	public int getByteSize() {
		int size = 1 + carNumberByteSize;
		return size;
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[getByteSize()];
		int offset = 0;
		BitConverter.putBoolean(valid, bytes, offset);
		offset += 1;
		BitConverter.putString(carNumber, bytes, offset);
		offset += carNumberByteSize;
		return bytes;
	}

	@Override
	public void fillFromBytes(byte[] bytes, int offset) {
		valid = BitConverter.getBoolean(bytes, offset);
		offset += 1;
		carNumber = BitConverter.getString(bytes, offset, carNumberByteSize);
		offset += carNumberByteSize;
	}

	@Override
	public boolean isValid() {
		return valid;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("carNumber: ");
		sb.append(carNumber);
		// sb.append("; vinNumber ");
		// sb.append(vinNumber);
		// sb.append("; axleCount");
		// sb.append(axleCount);
		// sb.append("; weight");
		// sb.append(weight);
		// sb.append("; wanted");
		// sb.append(wanted);
		// sb.append("; endDateSTK");
		// sb.append(endDateSTK);
		// sb.append("; endDateEK");
		// sb.append(endDateEK);

		return sb.toString();
	}
}
