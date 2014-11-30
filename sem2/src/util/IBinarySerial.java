package util;

public interface IBinarySerial {
	byte[] getBytes();

	void fillFromBytes(byte[] value, int offset);

	int getByteSize();
}
