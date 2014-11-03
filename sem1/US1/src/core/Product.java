package core;

import java.util.Date;

public class Product implements NodeValue{
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
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getEan() {
		return ean;
	}
}
