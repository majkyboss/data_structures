package sem2;

public abstract class FileItem {
	/**
	 * may not reflect real size - use {@link #getByteSize()} method
	 */
	private int byteSize;
	protected int address;
	
	public FileItem(int byteSize) {
		super();
		this.byteSize = byteSize;
	}

	public FileItem(byte[] bytes) {
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
}
