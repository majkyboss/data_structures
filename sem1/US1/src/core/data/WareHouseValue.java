package core.data;

import core.NodeValue;

public class WareHouseValue extends ProductPlace implements NodeValue {
	private int id;

	public WareHouseValue() {
		super();
		this.id = IdCounter.getNextId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public Object getNodeValue() {
		return this;
	}
}
