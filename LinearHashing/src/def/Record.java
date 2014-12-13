package def;

public interface Record {
	int getByteSize();

	byte[] getBytes();

	void fillFromBytes(byte[] bytes, int offset);

	boolean isValid();
	
	void setValid(boolean valid);
}
