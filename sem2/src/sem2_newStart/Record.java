package sem2_newStart;

public abstract class Record<T> {
	protected T value;
	private int byteSize;
	private int address;
	private boolean valid;

	public Record(int byteSize) {
		super();
		this.byteSize = byteSize;
	}

	public Record(byte[] bytes) {
		super();
		fillFromBytes(bytes);
	}

	public int getByteSize() {
		return byteSize;
	}

	public abstract byte[] getBytes();

	public abstract void fillFromBytes(byte[] bytes);

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
