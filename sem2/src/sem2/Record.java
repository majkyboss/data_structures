package sem2;

public class Record {
	private RecordValue value;
	private boolean valid = false;
	private int address = 0;
	private int byteSize = 0;

	public byte[] getBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	public void fillFromBytes(byte[] value, int offset) {
		// TODO Auto-generated method stub
	}

	public int getByteSize() {
		return byteSize;
	}

	public boolean equals(Record par) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isValid() {
		return valid;
	}

	public void setInvalid() {
		valid = false;
	}

	public int getAddress() {
		// TODO Auto-generated method stub
		return 0;
	}

	public RecordValue getValue() {
		return value;
	}

	public void setValue(RecordValue value) {
		this.value = value;
		valid = true;
	}

}
