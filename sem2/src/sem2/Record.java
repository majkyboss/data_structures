package sem2;

public abstract class Record<T> extends FileItem {
	protected T value;
	private boolean valid;

	public Record(int byteSize) {
		super(byteSize);
	}

	public Record(byte[] bytes) {
		super(bytes);
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
