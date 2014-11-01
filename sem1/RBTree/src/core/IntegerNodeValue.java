package core;

public class IntegerNodeValue implements NodeValue {
	private Integer value;
	
	public IntegerNodeValue(Integer value) {
		super();
		this.value = value;
	}

	@Override
	public Object getNodeValue() {
		return value;
	}

}
