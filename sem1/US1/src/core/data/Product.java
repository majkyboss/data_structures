package core.data;

import java.util.Date;

import core.NodeValue;

public class Product implements NodeValue {
	private String name;
	private String ean;
	private Date productionDate;
	private Date minDate;
	private int productNumber;
	private double cost;
	private ECV ecv;

	private WareHouse currentPlace;

	@Override
	public Object getNodeValue() {
		return this;
	}

	public String getEan() {
		return ean;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public WareHouse getCurrentPlace() {
		return currentPlace;
	}

	public void setCurrentPlace(WareHouse currentPlace) {
		this.currentPlace = currentPlace;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}
}
