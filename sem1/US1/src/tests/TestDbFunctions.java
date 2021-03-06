package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import core.Db;
import core.data.Client;
import core.data.Product;
import core.data.ProductPlace;
import core.data.TransportProduct;
import core.data.WareHouse;

public class TestDbFunctions {
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

	@Test
	public void function1_searchProducts() throws ParseException {
		// add warehouse
		// add few items with same ean codes, diff dates

		Db database = new Db();

		// creating and adding warehouse
		WareHouse wh = new WareHouse();
		wh.setAddress("adresa prveho skladu");
		int whId = wh.getId();
		wh.setName("prvy sklad");
		boolean whAdded = database.addWarehouse(wh);
		assertTrue(whAdded);

		// inserting iMax products
		int iMax = 100;
		int same = 15;
		String ean1 = "000000000011";
		String ean2 = "000000002200";

		List<Product> products = new LinkedList<Product>();

		Date minDate = shortDateFormat.parse("1.12.2020");
		for (int i = 0; i < iMax; i++) {
			Product p = new Product();
			p.setCost(Math.random() * 300);
			p.setEan(ean1);
			// add products with min year 2020-2029
			// p.setMinDate(shortDateFormat.parse("1.12.202" + (i % 10)));
			p.setMinDate(shortDateFormat.parse("1.12.2021"));
			p.setName("produkt " + i);
			p.setProductionDate(new Date());
			p.setProductNumber(i);

			if (i > 10 && i <= 10 + same) {
				p.setEan(ean2);
				if (i < 15) {
					p.setMinDate(minDate);
					products.add(p);
				}
			}

			boolean pAdded = database.addProduct(whId, p);
			// if some product was not added
			assertTrue(pAdded);
		}

		int count = 4;
		List<Product> productsLoaded = database.searchProducts(ean2, shortDateFormat.parse("1.11.2020"), null, count, whId);
		Set<Product> addedItems = new HashSet<>(products);
		Set<Product> loadedItems = new HashSet<>(productsLoaded);
		assertEquals(addedItems, loadedItems);
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
	public void function7_makeTransportToClient() throws ParseException {
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

		Client cDestination = new Client();
		cDestination.setName("odberatel prvy");
		boolean destinationAdded = database.addClient(cDestination, whDeparture.getId());
		assertTrue(destinationAdded);

		Date expDate = shortDateFormat.parse("10.12.2014");

		boolean transportMade = database.makeTransportToClient(p.getProductNumber(), cDestination.getId(), expDate);
		assertTrue(transportMade);

		WareHouse whLoaded = database.getWarehouse(whDeparture.getId());
		TransportProduct transpLoaded = whLoaded.getDispatchedItem(p.getProductNumber());

		assertEquals(transpLoaded.getProduct(), p);
	}

	@Test
	public void function8_endTransport() throws ParseException {
		// add warehouse
		// add product to wh
		// add destination place
		// move product from wh to destination

		Client c = new Client();
		c.setName("odberatel prvy");

		WareHouse wh = new WareHouse();
		wh.setName("prvy sklad");

		ProductPlace[] places = new ProductPlace[] { c, wh };

		for (int i = 0; i < places.length; i++) {
			ProductPlace destination = places[i];

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

			boolean destinactionAdded = false;
			if (destination instanceof Client) {
				destinactionAdded = database.addClient((Client) destination, whDeparture.getId());
			} else if (destination instanceof WareHouse) {
				destinactionAdded = database.addWarehouse((WareHouse) destination);
			}
			assertTrue(destinactionAdded);

			Date expDate = new Date();
			boolean transportAdded = false;
			if (destination instanceof Client) {
				transportAdded = database.makeTransportToClient(p.getProductNumber(), ((Client) destination).getId(), expDate);
			} else if (destination instanceof WareHouse) {
				transportAdded = database.makeTransportToWareHouse(p.getProductNumber(), ((WareHouse) destination).getId(), expDate);
			}
			assertTrue(transportAdded);

			Date arrDate = new Date();
			boolean transportEnd = database.endTransport(p.getProductNumber(), arrDate);
			assertTrue(transportEnd);

			TransportProduct transport = destination.getArrivedItems().get(0);
			assertEquals(transport.getProduct(), p);
			// TODO check also warehouse (stored products and dispatched
			// products)
		}

	}

	@Test
	public void function9_searchClients() {
		Db database = new Db();
		WareHouse wh = new WareHouse();
		wh.setName("prvy sklad");
		boolean departureAdded = database.addWarehouse(wh);
		assertTrue(departureAdded);

		List<Client> clients = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			Client c = new Client();
			c.setName("klient " + i);
			boolean clientAdded = database.addClient(c, wh.getId());
			assertTrue(clientAdded);
			clients.add(c);
		}

		List<Client> clientsLoaded = database.searchClients(wh.getId());
		Set<Client> clientsSet = new HashSet<>(clients);
		Set<Client> clientsLoadedSet = new HashSet<>(clientsLoaded);

		assertEquals(clientsSet, clientsLoadedSet);
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
		Date dateFrom = new Date();
		int daysUntilDate = 5;

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dateFrom);
		calendar.add(Calendar.DAY_OF_YEAR, daysUntilDate);
		Date dateTo = calendar.getTime();

		WareHouse wh = new WareHouse();
		wh.setName("prvy sklad");
		int whId = wh.getId();

		Db database = new Db();
		assertTrue(database.addWarehouse(wh));

		List<Product> products = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			Product product = new Product();
			product.setCost(300.50);
			product.setEan("000000000001");
			calendar.setTime(dateFrom);
			calendar.add(Calendar.DAY_OF_YEAR, i + 1);
			product.setMinDate(calendar.getTime());
			product.setName("produkt jedna");
			product.setProductionDate(new Date());
			product.setProductNumber(1);
			if (calendar.getTime().compareTo(dateTo) <= 0) {
				products.add(product);
			}

			assertTrue(database.addProduct(whId, product));
		}

		List<Product> loadedProducts = database.searchProducts(new Date(), daysUntilDate, whId);
		Set<Product> productsSet = new HashSet<>(products);
		Set<Product> loadedProductsSet = new HashSet<>(loadedProducts);

		assertEquals(productsSet, loadedProductsSet);
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
