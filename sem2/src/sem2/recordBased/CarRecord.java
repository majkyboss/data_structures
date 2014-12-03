package sem2.recordBased;

import sem2.Record;
import util.BitConverter;

public class CarRecord extends Record<Car> {

	public CarRecord() {
		super(Car.carNumber_maxLength + Car.vinNumber_maxLength + Integer.BYTES);
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[getByteSize()];

		if (value == null) {
			return bytes;
		}

		int offset = 0;
		BitConverter.putString(value.getCarNumber(), bytes, offset);
		offset += Car.carNumber_maxLength;
		BitConverter.putString(value.getVinNumber(), bytes, offset);
		offset += Car.vinNumber_maxLength;
		BitConverter.putInt(value.getWeight(), bytes, offset);
		return bytes;
	}

	@Override
	public void fillFromBytes(byte[] bytes) {
		int offset = 0;
		int carNum_length = Car.carNumber_maxLength;
		String carNum = BitConverter.getString(bytes, offset, carNum_length);
		offset += carNum_length;
		int vinNum_length = Car.vinNumber_maxLength;
		String vinNum = BitConverter.getString(bytes, offset, vinNum_length);
		offset += vinNum_length;
		int weight = BitConverter.getInt(bytes, offset);
		Car c = new Car(carNum, vinNum, weight);
		value = c;
		setValid(true);
	}
}
