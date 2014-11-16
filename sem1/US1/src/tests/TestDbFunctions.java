package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import core.Db;
import core.data.Client;
import core.data.Product;
import core.data.TransportProduct;
import core.data.WareHouse;

public class TestDbFunctions {
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

	@Test
	public void function1_searchProducts() {
	}

	@Test
	public void function2_searchCount() throws ParseException {
		// add few products with some ean, some of them have to be same

		// 1. create products with same EAN codes
		// 2. create products with different EAN codes
		// 3. create warehouse
		// 4. store warehouse to db
		// 5. store products to db
		// 6. get counts

		Db database = new Db();

		// creating and adding warehouse
		WareHouse wh = new WareHouse();
		wh.setAddress("adresa prveho skladu");
		int id = wh.getId();
		wh.setName("prvy sklad");
		boolean whAdded = database.addWarehouse(wh);
		assertTrue(whAdded);

		// inserting iMax products
		int iMax = 100;
		int same = 15;
		String ean1 = "000000000011";
		String ean2 = "000000002200";
		for (int i = 0; i < iMax; i++) {
			Product p = new Product();
			p.setCost(Math.random() * 300);
			p.setEan(ean1);
			p.setMinDate(shortDateFormat.parse("1.12.2014"));
			p.setName("produkt " + i);
			p.setProductionDate(new Date());
			p.setProductNumber(i);

			if (i > 10 && i <= 10 + same) {
				p.setEan(ean2);
			}

			boolean pAdded = database.addProduct(id, p);
			// if some product was not added
			assertTrue(pAdded);
		}

		int count = database.searchCount(ean2, id);
		assertEquals(count, same);
	}

	@Test
	public void function4_addProduct_and_3_SearchProduct() throws ParseException {
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

	@Test
	public void function5_searchClient() {
		addAndFindClient();
	}

	@Test
	public void function6_makeTransportToWareHouse() throws ParseException {
		// create warehouse
		// add warehouse
		// create product
		// add product
		// create dest wh
		// add dest wh
		// create exp date
		// make transport
		Db database = new Db();
		WareHouse whDeparture = new WareHouse();
		whDeparture.setName("prvy sklad");
		boolean departureAdded = database.addWarehouse(whDeparture);
		assertTrue(departureAdded);

		Product p = new Product();
		p.setCost(300.50);
		p.setEan("000000000001");
		p.setMinDate(shortDateFormat.parse("1.12.2054"));
		p.setName("produkt jedna");
		p.setProductionDate(new Date());
		p.setProductNumber(1);
		boolean productAdded = database.addProduct(whDeparture.getId(), p);
		assertTrue(productAdded);

		WareHouse whDestination = new WareHouse();
		whDestination.setName("druhy sklad");
		boolean destinationAdded = database.addWarehouse(whDestination);
		assertTrue(destinationAdded);

		Date expDate = shortDateFormat.parse("10.12.2014");

		boolean transportMade = database.makeTransportToWareHouse(p.getProductNumber(), whDestination.getId(), expDate);
		assertTrue(transportMade);

		WareHouse whLoaded = database.getWarehouse(whDeparture.getId());
		TransportProduct transpLoaded = whLoaded.getDispatchedItem(p.getProductNumber());

		assertEquals(transpLoaded.getProduct(), p);
	}

	@Test
	public void function7_makeTransportToClient() {
	}

	@Test
	public void function8_endTransport() {
	}

	@Test
	public void function9_searchClients() {
	}

	@Test
	public void function10_getLiveTransport() {
	}

	@Test
	public void function11_showArrivedProductsInWareHouse() {
	}

	@Test
	public void function12_showArrivedProductsInClinet() {
	}

	@Test
	public void function13_searchProducts() {
	}

	@Test
	public void function14_addWarehouse() {
		// 1. add warehouse
		// 2. add product to created warehouse

		WareHouse wh = new WareHouse();
		wh.setAddress("adresa prveho skladu");
		int id = wh.getId();
		wh.setName("prvy sklad");

		Db database = new Db();
		boolean whAdded = database.addWarehouse(wh);

		assertTrue(whAdded);

		WareHouse whFromDb = database.getWarehouse(id);
		assertEquals(wh, whFromDb);
	}

	@Test
	public void function15_addClient() {
		addAndFindClient();
	}

	@Test
	public void function16_deleteProduct() {
	}

	@Test
	public void function17_removeClient() {
	}

	@Test
	public void function18_removeWarehouse() {
	}

	@Test
	public void function19_getProductsValue() {
	}

	private void addAndFindClient() {
		// 1. create warehouse
		// 2. add warehouse
		// 3. create client
		// 4. add client
		// 5. find client

		Db database = new Db();

		WareHouse wh = new WareHouse();
		wh.setAddress("adresa prveho skladu");
		int whId = wh.getId();
		wh.setName("prvy sklad");
		boolean whAdded = database.addWarehouse(wh);
		assertTrue(whAdded);

		Client c = new Client();
		c.setAddress("adresa prveho odberatela");
		String cId = c.getId();
		c.setName("prvy odberatel");
		c.setWarehouse(wh);

		boolean cAdded = database.addClient(c, whId);
		assertTrue(cAdded);

		Client cLoadedFromDB = database.getClient(cId, whId);

		assertEquals(cLoadedFromDB, c);
	}

}
