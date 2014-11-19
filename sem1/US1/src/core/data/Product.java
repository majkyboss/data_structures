package core.data;

import java.util.Date;

import rb.RBTree;

public class Product {
	private String name;
	private String ean;
	private Date productionDate;
	private Date minDate;
	private int productNumber;
	private double cost;

	private WareHouse currentPlace;
	private RBTree<Integer> productNumbersTree;
	private RBTree<Date> dateTree;
	private RBTree<String> eanTree;
	private TransportProduct transport;

	public Product() {
		super();
		this.productNumber = IdCounter.getNextId();
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

	public RBTree<Integer> getProductNumbersTree() {
		return productNumbersTree;
	}

	public void setProductNumbersTree(RBTree<Integer> productNumbersTree) {
		this.productNumbersTree = productNumbersTree;
	}

	public RBTree<Date> getDateTree() {
		return dateTree;
	}

	public void setDateTree(RBTree<Date> dateTree) {
		this.dateTree = dateTree;
	}

	public RBTree<String> getEanTree() {
		return eanTree;
	}

	public void setEanTree(RBTree<String> eanTree) {
		this.eanTree = eanTree;
	}

	public TransportProduct getTransport() {
		return transport;
	}

	public void setTransport(TransportProduct transport) {
		this.transport = transport;
	}

	// public void parseProdDate(String date) {
	// try {
	// setProductionDate(DateFormat.getDateInstance(DateFormat.SHORT).parse(date));
	// } catch (ParseException e) {
	// System.out.println("Product.parseProductionDate()");
	// e.printStackTrace();
	// }
	// }
	//
	// public void parseMinDate(String date) {
	// try {
	// setMinDate(DateFormat.getDateInstance(DateFormat.SHORT).parse(date));
	// } catch (ParseException e) {
	// System.out.println("Product.parseMinDate()");
	// e.printStackTrace();
	// }
	// }
}
