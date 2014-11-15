package core.data;

import core.NodeValue;

public class Client extends ProductPlace implements NodeValue {
	public Client() {
		super();
		this.id = IdCounter.getNextId() + "";
	}

	private String id;
	private WareHouse warehouse;

	public WareHouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WareHouse warehouse) {
		this.warehouse = warehouse;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Object getNodeValue() {
		return this;
	}
}
