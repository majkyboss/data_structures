package core;

public class IntegerNodeValue implements NodeValue<Integer> {
	private Integer value;

	public IntegerNodeValue(Integer value) {
		super();
		this.value = value;
	}

	@Override
	public Integer getNodeValue() {
		return value;
	}

}
