package core;

public class Client extends ProductPlace {
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
}
