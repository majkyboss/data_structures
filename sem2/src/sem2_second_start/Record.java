package sem2_second_start;

public interface Record {
	int getByteSize();

	byte[] getBytes();

	void fillFromBytes(byte[] bytes);
}
