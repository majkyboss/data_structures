package core;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

import rb.RBNode;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import core.data.Client;
import core.data.IdCounter;
import core.data.Product;
import core.data.ProductPlace;
import core.data.TransportProduct;
import core.data.WareHouse;

public class CSVHandler {
	private final StorageDatabase db;
	private final DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

	private final String exportDir = "/csvExport/";

	private final String whsFileName = "warehouses.csv";
	private final String[] whColumns = new String[] { "id", "name", "address" };
	private String[] whValues = new String[whColumns.length];
	private final String clientsFileName = "clients.csv";
	private final String[] clientsColumns = new String[] { "id", "name", "address", "warehouse id" };
	private String[] clientValues = new String[clientsColumns.length];
	private final String productsByPnFileName = "productsByPN.csv";
	private final String[] productColumns = new String[] { "name", "ean", "productionDate", "minDate", "productNumber", "cost", "warehouse id" };
	private String[] productValues = new String[productColumns.length];
	private final String dispatchedProductsFileName = "dispatchedItems.csv";
	private final String[] transportColumns = new String[] { "productNumber", "departure id", "destination id", "dispatched date", "expected date", "car ECV" };
	private String[] transportValues = new String[transportColumns.length];
	private final String arrivedToWareHousesFileName = "arrivedToWH.csv";
	private final String arrivedToClientsFileName = "arrivedToClients.csv";

	public CSVHandler(StorageDatabase db) {
		super();
		this.db = db;
	}

	public boolean load_csv(String dirPath) throws IOException, ParseException {

		// load all WHs
		// __ basic informations
		// load all Clients
		// set Clients to WHs
		// load all products by PN
		// load dispatched
		// load arrived items
		// __ wh - find in items by PN
		// __ clients - create new one

		// TODO use parametric path
		dirPath = System.getProperty("user.dir");

		CSVReader reader = new CSVReader(new FileReader(dirPath + exportDir + whsFileName), ',', '"', 1);
		List<String[]> allRows = reader.readAll();
		for (String[] row : allRows) {
			whValues = row;
			// could inserts parallely
			WareHouse wh = new WareHouse();
			wh.setId(Integer.parseInt(whValues[0]));
			wh.setName(whValues[1]);
			wh.setAddress(whValues[2]);
			db.addWarehouse(wh);
			IdCounter.checkMax(Integer.parseInt(whValues[0]));
		}
		reader.close();

		reader = new CSVReader(new FileReader(dirPath + exportDir + clientsFileName), ',', '"', 1);
		allRows = reader.readAll();
		for (String[] row : allRows) {
			clientValues = row;
			// could inserts parallely
			Client client = new Client();
			client.setId(clientValues[0]);
			client.setName(clientValues[1]);
			client.setAddress(clientValues[2]);
			db.addClient(client, Integer.parseInt(clientValues[3]));
			IdCounter.checkMax(Integer.parseInt(clientValues[0]));
		}
		reader.close();

		reader = new CSVReader(new FileReader(dirPath + exportDir + productsByPnFileName), ',', '"', 1);
		allRows = reader.readAll();
		for (String[] row : allRows) {
			productValues = row;
			// could inserts parallely
			Product product = new Product();
			product.setName(productValues[0]);
			product.setEan(productValues[1]);
			product.setProductionDate(shortDateFormat.parse(productValues[2]));
			product.setMinDate(shortDateFormat.parse(productValues[3]));
			int productNumber = Integer.parseInt(productValues[4]);
			product.setProductNumber(productNumber);
			product.setCost(Double.parseDouble(productValues[5]));
			String whId = productValues[6];
			if (!whId.equals("")) {
				db.addProduct(Integer.parseInt(productValues[6]), product);
			} else {
				if (db instanceof Db) {
					((Db) db).addProduct(product);
				}
			}
			IdCounter.checkMax(productNumber);
		}
		reader.close();

		reader = new CSVReader(new FileReader(dirPath + exportDir + dispatchedProductsFileName), ',', '"', 1);
		allRows = reader.readAll();
		for (String[] row : allRows) {
			transportValues = row;
			Product product = db.searchProduct(Integer.parseInt(transportValues[0]));
			TransportProduct transport = new TransportProduct(product);
			product.setTransport(transport);
			WareHouse departure = db instanceof Db ? ((Db) db).getWarehouse(Integer.parseInt(transportValues[1])) : null;
			transport.setDeparture(departure);
			WareHouse destination = db instanceof Db ? ((Db) db).getWarehouse(Integer.parseInt(transportValues[2])) : null;
			transport.setDestination(destination);
			transport.setDispatchedDate(shortDateFormat.parse(transportValues[3]));
			transport.setExpectedDate(shortDateFormat.parse(transportValues[4]));
			transport.setCarEcv(transportValues[5]);

			departure.getDispatchedByPN().insert(new TransportNode(transport));
		}
		reader.close();

		reader = new CSVReader(new FileReader(dirPath + exportDir + arrivedToWareHousesFileName), ',', '"', 1);
		allRows = reader.readAll();
		for (String[] row : allRows) {
			transportValues = row;
			Product product = db.searchProduct(Integer.parseInt(transportValues[0]));
			TransportProduct transport = new TransportProduct(product);
			WareHouse departure = db instanceof Db ? ((Db) db).getWarehouse(Integer.parseInt(transportValues[1])) : null;
			transport.setDeparture(departure);
			WareHouse destination = db instanceof Db ? ((Db) db).getWarehouse(Integer.parseInt(transportValues[2])) : null;
			transport.setDestination(destination);
			transport.setDispatchedDate(shortDateFormat.parse(transportValues[3]));
			transport.setExpectedDate(shortDateFormat.parse(transportValues[4]));
			transport.setCarEcv(transportValues[5]);

			destination.addArrivedItem(transport);
		}
		reader.close();

		reader = new CSVReader(new FileReader(dirPath + exportDir + arrivedToClientsFileName), ',', '"', 1);
		allRows = reader.readAll();
		for (String[] row : allRows) {
			transportValues = row;
			Product product = new Product();
			product.setName(transportValues[transportColumns.length + 0]);
			product.setEan(transportValues[transportColumns.length + 1]);
			product.setProductionDate(shortDateFormat.parse(transportValues[transportColumns.length + 2]));
			product.setMinDate(shortDateFormat.parse(transportValues[transportColumns.length + 3]));
			int productNumber = Integer.parseInt(transportValues[0]);
			product.setProductNumber(productNumber);
			product.setCost(Double.parseDouble(transportValues[transportColumns.length + 5]));

			TransportProduct transport = new TransportProduct(product);

			WareHouse departure = db instanceof Db ? ((Db) db).getWarehouse(Integer.parseInt(transportValues[1])) : null;
			transport.setDeparture(departure);
			ProductPlace destination = db.searchClient(transportValues[2], departure.getId());
			transport.setDestination(destination);
			transport.setDispatchedDate(shortDateFormat.parse(transportValues[3]));
			transport.setExpectedDate(shortDateFormat.parse(transportValues[4]));
			transport.setCarEcv(transportValues[5]);

			destination.addArrivedItem(transport);
			IdCounter.checkMax(productNumber);
		}
		reader.close();

		return false;
	}

	public boolean save_csv(String dirPath) throws IOException {
		// preset save
		// TODO use parametric path
		dirPath = System.getProperty("user.dir");
		try {
			Files.createDirectory(Paths.get(dirPath + exportDir));
		} catch (FileAlreadyExistsException e) {
			System.out.println("path already created...");
		}

		// ------------------------------------------------------------------------------------------------------
		// saving all warehouses
		CSVWriter writer = new CSVWriter(new FileWriter(dirPath + exportDir + whsFileName), ',', '"');
		// prepare warehouses
		List<WareHouse> whs = db.getWarehouses();
		writer.writeNext(whColumns);
		for (WareHouse wareHouse : whs) {
			whValues = new String[whColumns.length];
			whValues[0] = wareHouse.getId() + "";
			whValues[1] = wareHouse.getName();
			whValues[2] = wareHouse.getAddress();
			writer.writeNext(whValues);
		}
		whs = null;
		writer.close();

		// ------------------------------------------------------------------------------------------------------
		// saving all clients
		writer = new CSVWriter(new FileWriter(dirPath + exportDir + clientsFileName), ',', '"');
		List<Client> clients = db.getAllClients();
		writer.writeNext(clientsColumns);
		for (Client client : clients) {
			clientValues = new String[clientsColumns.length];
			clientValues[0] = client.getId();
			clientValues[1] = client.getName();
			clientValues[2] = client.getAddress();
			clientValues[3] = client.getWarehouse().getId() + "";
			writer.writeNext(clientValues);
		}
		clients = null;
		writer.close();

		// ------------------------------------------------------------------------------------------------------
		// saving stored products
		writer = new CSVWriter(new FileWriter(dirPath + exportDir + productsByPnFileName), ',', '"');
		List<Product> products = db.getAllProducts();
		writer.writeNext(productColumns);
		for (Product product : products) {
			productValues = new String[productColumns.length];
			productValues[0] = product.getName();
			productValues[1] = product.getEan();
			productValues[2] = shortDateFormat.format(product.getProductionDate());
			productValues[3] = shortDateFormat.format(product.getMinDate());
			productValues[4] = product.getProductNumber() + "";
			productValues[5] = product.getCost() + "";
			productValues[6] = product.getCurrentPlace() != null ? product.getCurrentPlace().getId() + "" : "";
			writer.writeNext(productValues);
		}
		products = null;
		writer.close();

		// ------------------------------------------------------------------------------------------------------
		// saving dispatched products
		writer = new CSVWriter(new FileWriter(dirPath + exportDir + dispatchedProductsFileName), ',', '"');

		for (WareHouse wh : db.getWarehouses()) {
			for (RBNode<Integer> dispathedNode : wh.getDispatchedByPN()) {
				if (dispathedNode instanceof TransportNode) {
					TransportProduct transport = ((TransportNode) dispathedNode).getValue();
					writeTransport(writer, transport);
				}
			}
		}
		writer.close();

		// ------------------------------------------------------------------------------------------------------
		// saving arrived products
		writer = new CSVWriter(new FileWriter(dirPath + exportDir + arrivedToWareHousesFileName), ',', '"');

		for (WareHouse wh : db.getWarehouses()) {
			saveArrived(writer, wh.getArrivedItems());
		}

		writer.close();

		writer = new CSVWriter(new FileWriter(dirPath + exportDir + arrivedToClientsFileName), ',', '"');
		for (Client client : db.getAllClients()) {
			saveArrived(writer, client.getArrivedItems());
		}
		writer.close();

		return false;
	}

	private void saveArrived(CSVWriter writer, List<TransportProduct> arrivedProducts) {
		for (TransportProduct transport : arrivedProducts) {
			writeTransport(writer, transport);
		}
	}

	private void writeTransport(CSVWriter writer, TransportProduct transport) {
		writer.writeNext(transportColumns);

		transportValues = new String[transportColumns.length];
		String departureId = "";
		ProductPlace departure = transport.getDeparture();
		if (departure instanceof Client) {
			departureId = ((Client) departure).getId();
		} else if (departure instanceof WareHouse) {
			departureId = ((WareHouse) departure).getId() + "";
		}
		String destinationId = "";
		ProductPlace destination = transport.getDestination();
		String[] pValues = new String[0];
		if (destination instanceof Client) {
			destinationId = ((Client) destination).getId();

			// prepare products to write
			Product product = transport.getProduct();
			pValues = new String[productColumns.length];
			pValues[0] = product.getName();
			pValues[1] = product.getEan();
			pValues[2] = shortDateFormat.format(product.getProductionDate());
			pValues[3] = shortDateFormat.format(product.getMinDate());
			pValues[4] = product.getProductNumber() + "";
			pValues[5] = product.getCost() + "";
			// pValues[6] = product.getCurrentPlace() != null ?
			// product.getCurrentPlace().getId() + "" : "";
		} else if (destination instanceof WareHouse) {
			destinationId = ((WareHouse) destination).getId() + "";
		}

		transportValues[0] = transport.getPN() + "";
		transportValues[1] = departureId;
		transportValues[2] = destinationId;
		transportValues[3] = shortDateFormat.format(transport.getDispatchedDate());
		transportValues[4] = shortDateFormat.format(transport.getExpectedDate());
		transportValues[5] = transport.getCarEcv();

		String[] valuesToWrite = new String[transportValues.length + pValues.length];
		System.arraycopy(transportValues, 0, valuesToWrite, 0, transportValues.length);
		System.arraycopy(pValues, 0, valuesToWrite, transportValues.length, pValues.length);

		writer.writeNext(valuesToWrite);
	}

}
