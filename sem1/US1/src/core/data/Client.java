package core.data;


public class Client extends ProductPlace {
	public Client() {
		super();
		this.id = IdCounter.getNextId() + "";
	}

	private String id;
	private WareHouseValue warehouse;

	public WareHouseValue getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WareHouseValue warehouse) {
		this.warehouse = warehouse;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
