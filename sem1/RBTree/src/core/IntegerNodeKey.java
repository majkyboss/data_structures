package core;

public class IntegerNodeKey implements NodeKey {
	private Integer key;

	public IntegerNodeKey(Integer key) {
		super();
		this.key = key;
	}

	@Override
	public int compareTo(NodeKey o) {
		return key.compareTo(((IntegerNodeKey) o).getKeyValue());
	}

	@Override
	public Integer getKeyValue() {
		return key;
	}

}
