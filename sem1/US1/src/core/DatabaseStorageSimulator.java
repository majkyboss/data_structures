package core;

import gui.tables.ProductValueItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import core.data.Client;
import core.data.Product;
import core.data.TransportProduct;
import core.data.WareHouse;

public class DatabaseStorageSimulator implements StorageDatabase {
	private static DatabaseStorageSimulator instance;

	private DatabaseStorageSimulator() {
		super();
	}

	public static DatabaseStorageSimulator getInstance() {
		if (instance == null) {
			instance = new DatabaseStorageSimulator();
		}
		return instance;
	}

	@Override
	public List<Product> searchProducts(String ean, Date dateFrom, Date dateTo, int count, int wareHouseId) {
		LinkedList<Product> l = new LinkedList<>();
		Product p = new Product();
		p.setName("first product");
		p.setEan("00000001");
		p.setProductionDate(getDate("8.11.2014"));
		p.setMinDate(getDate("25.11.2014"));
		p.setProductNumber(1);
		p.setCost(25.5);
		l.add(p);
		p = new Product();
		p.setName("second product");
		p.setEan("00000002");
		p.setProductionDate(getDate("7.11.2014"));
		p.setMinDate(getDate("24.11.2014"));
		p.setProductNumber(2);
		p.setCost(35.5);
		l.add(p);

		return l;
	}

	private Date getDate(String stringDate) {
		Date date = new Date();
		try {
			date = DateFormat.getDateInstance(DateFormat.SHORT).parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public int searchCount(String ean, int wareHouseId) {
		return 2;
	}

	@Override
	public Product searchProduct(int productNum) {
		Product p = new Product();
		p.setName("first product");
		p.setEan("00000001");
		p.setProductionDate(getDate("8.11.2014"));
		p.setMinDate(getDate("25.11.2014"));
		p.setProductNumber(1);
		p.setCost(25.5);
		return p;
	}

	@Override
	public boolean addProduct(int whId, Product product) {
		return true;
	}

	@Override
	public Client searchClient(String clientId, int wareHouseId) {
		Client c = new Client();

		return c;
	}

	@Override
	public boolean makeTransportToWareHouse(int productNumber, int wareHouseId, Date expectedDate) {
		return true;
	}

	@Override
	public boolean makeTransportToClient(int productNumber, String clientId, Date expectedDate) {
		return true;
	}

	@Override
	public boolean endTransport(int productNum, Date arrivalDate) {
		return true;
	}

	@Override
	public List<Client> searchClients(int wareHouseId) {
		LinkedList<Client> l = new LinkedList<>();
		Client c = new Client();
		c.setName("first client");
		c.setId("1");
		c.setAddress("address 1");
		l.add(c);
		c = new Client();
		c.setName("second client");
		c.setId("1");
		c.setAddress("address 1");
		l.add(c);

		return l;
	}

	@Override
	public List<TransportProduct> getLiveTransport(int wareHouseId) {
		Product p = new Product();
		p.setProductNumber(1);

		LinkedList<TransportProduct> l = new LinkedList<>();
		TransportProduct pt = new TransportProduct(p);
		pt.setDeparture(new WareHouse());
		pt.setDestination(new WareHouse());
		pt.setDispatchedDate(new Date());
		pt.setExpectedDate(new Date());
		pt.setCarEcv("ZA555ZA");
		l.add(pt);

		p = new Product();
		p.setProductNumber(2);
		pt = new TransportProduct(p);
		pt.setDeparture(new WareHouse());
		pt.setDestination(new WareHouse());
		pt.setDispatchedDate(new Date());
		pt.setExpectedDate(new Date());
		pt.setCarEcv("ZA444ZA");
		l.add(pt);

		return l;
	}

	@Override
	public List<TransportProduct> showArrivedProductsInWareHouse(int wareHouseFromId, int wareHouseToId) {
		Product p = new Product();
		p.setProductNumber(1);

		LinkedList<TransportProduct> l = new LinkedList<>();
		TransportProduct pt = new TransportProduct(p);
		pt.setDeparture(new WareHouse());
		pt.setDestination(new WareHouse());
		pt.setDispatchedDate(new Date());
		pt.setExpectedDate(new Date());
		pt.setCarEcv("ZA555ZA");
		l.add(pt);

		p = new Product();
		p.setProductNumber(2);
		pt = new TransportProduct(p);
		pt.setDeparture(new WareHouse());
		pt.setDestination(new WareHouse());
		pt.setDispatchedDate(new Date());
		pt.setExpectedDate(new Date());
		pt.setCarEcv("ZA444ZA");
		l.add(pt);

		return l;
	}

	@Override
	public List<TransportProduct> showArrivedProductsInClinet(int wareHouseFromId, String clientId) {
		Product p = new Product();
		p.setProductNumber(1);

		LinkedList<TransportProduct> l = new LinkedList<>();
		TransportProduct pt = new TransportProduct(p);
		pt.setDeparture(new WareHouse());
		pt.setDestination(new Client());
		pt.setDispatchedDate(new Date());
		pt.setExpectedDate(new Date());
		pt.setCarEcv("ZA555ZA");
		pt.setArrivedDate(new Date());
		l.add(pt);

		p = new Product();
		p.setProductNumber(2);
		pt = new TransportProduct(p);
		pt.setDeparture(new WareHouse());
		pt.setDestination(new Client());
		pt.setDispatchedDate(new Date());
		pt.setExpectedDate(new Date());
		pt.setCarEcv("ZA444ZA");
		pt.setArrivedDate(new Date());
		l.add(pt);

		return l;
	}

	@Override
	public List<Product> searchProducts(Date dateFrom, int daysUntilDate, int wareHouseId) {
		LinkedList<Product> l = new LinkedList<>();
		Product p = new Product();
		p.setName("first product");
		p.setEan("00000001");
		p.setProductionDate(getDate("8.11.2014"));
		p.setMinDate(getDate("25.11.2014"));
		p.setProductNumber(1);
		p.setCost(25.5);
		l.add(p);
		p = new Product();
		p.setName("second product");
		p.setEan("00000002");
		p.setProductionDate(getDate("7.11.2014"));
		p.setMinDate(getDate("24.11.2014"));
		p.setProductNumber(2);
		p.setCost(35.5);
		l.add(p);

		return l;
	}

	@Override
	public boolean addClient(Client c, int whId) {
		return true;
	}

	@Override
	public boolean addWarehouse(WareHouse wh) {
		return true;
	}

	@Override
	public boolean deleteProduct(int productNum) {
		return true;
	}

	@Override
	public boolean removeClient(String clientId, int warehouseId) {
		return true;
	}

	@Override
	public boolean removeWarehouse(int wareHouseForDelId, int warehouseToMoveId) {
		return true;
	}

	@Override
	public List<ProductValueItem> getProductsValue(int wareHouseId) {
		LinkedList<ProductValueItem> list = new LinkedList<>();
		ProductValueItem item = new ProductValueItem();
		item.setEan("00000001");
		item.setCount(2);
		item.setValue(32.8);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);
		item = new ProductValueItem();
		item.setEan("00000002");
		item.setCount(5);
		item.setValue(65.5);
		list.add(item);

		return list;
	}
}
