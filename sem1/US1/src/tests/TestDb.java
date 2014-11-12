package tests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import core.Db;
import core.data.Product;
import core.data.WareHouse;

public class TestDb {
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

	@Test
	public void testProduct() throws ParseException {
		// 1. add warehouse
		// 2. add product to created warehouse

		WareHouse wh = new WareHouse();
		wh.setAddress("adresa prveho skladu");
		int id = wh.getId();
		wh.setName("prvy sklad");

		Product p = new Product();
		p.setCost(300.50);
		p.setEan("000000000001");
		p.setMinDate(shortDateFormat.parse("1.12.2014"));
		p.setName("produkt jedna");
		p.setProductionDate(new Date());
		p.setProductNumber(1);

		Db database = new Db();
		boolean whAdded = database.addWarehouse(wh);
		boolean pAdded = database.addProduct(id, p);

		Assert.assertTrue(whAdded);
		assertTrue(pAdded);

		Object productLoadedFromDb = database.searchProduct(p.getProductNumber());
		assertEquals(p, productLoadedFromDb);
	}

}
