package sem2;

import util.IBinarySerial;

public interface RecordValue extends IBinarySerial {
	byte[] getBytes();

	void fillFromBytes(byte[] value, int offset);

	int getByteSize();

	int getKey();
}
