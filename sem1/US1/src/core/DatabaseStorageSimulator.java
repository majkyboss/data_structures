package core;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
	public boolean addItem(int whId, Product product) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean addWareHouse(WareHouse warehouse) {
		// TODO Auto-generated method stub
		return true;
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
}
