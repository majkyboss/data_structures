package core;

import java.util.Date;

public class ProductTransport {
	private int productId;
	private ProductPlace departure;
	private ProductPlace destination;
	private Date dispatchedDate;
	private Date expectedDate;
	private String carEcv;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public ProductPlace getDeparture() {
		return departure;
	}

	public void setDeparture(ProductPlace departure) {
		this.departure = departure;
	}

	public ProductPlace getDestination() {
		return destination;
	}

	public void setDestination(ProductPlace destination) {
		this.destination = destination;
	}

	public Date getDispatchedDate() {
		return dispatchedDate;
	}

	public void setDispatchedDate(Date dispatchedDate) {
		this.dispatchedDate = dispatchedDate;
	}

	public Date getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}

	public String getCarEcv() {
		return carEcv;
	}

	public void setCarEcv(String carEcv) {
		this.carEcv = carEcv;
	}

}
