package sem2;

public class Car implements RecordValue {
	private static final int ATTR_1_SIZE = 1;
	private static final int ATTR_2_SIZE = 2;

	@Override
	public int getByteSize() {
		return ATTR_1_SIZE + ATTR_2_SIZE;
	}

	@Override
	public int getKey() {
		return hashCode();
	}
}
