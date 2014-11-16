package core.data;

import java.util.LinkedList;
import java.util.List;

public class ProductPlace {
	protected String name;
	protected String address;
	protected final List<TransportProduct> arrivedItems;

	public ProductPlace() {
		super();
		this.name = "";
		this.address = "";
		arrivedItems = new LinkedList<TransportProduct>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<TransportProduct> getArrivedItems() {
		return arrivedItems;
	}

	public boolean addArrivedItem(TransportProduct transport) {
		return arrivedItems.add(transport);
	}

}
