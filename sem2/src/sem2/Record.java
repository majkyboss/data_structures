package sem2;

public class Record {
	private RecordValue value;
	private boolean valid = false;
	private int address = 0;
	private int byteSize = 0;

	public Record(int recordSize, int address) {
		super();
		byteSize = recordSize;
		this.address = address;
	}

	public byte[] getBytes() {
		// TODO add header
		return value.getBytes();
	}

	public void fillFromBytes(byte[] value, int offset) {
		this.value.fillFromBytes(value, offset);
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

	public void setAddress(int address) {
		this.address = address;
	}

	public int getAddress() {
		return address;
	}

	public RecordValue getValue() {
		return value;
	}

	public void setValue(RecordValue value) {
		if (value != null) {
			this.value = value;
			valid = true;
		}
	}

}
