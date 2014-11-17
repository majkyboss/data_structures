package core;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import rb.RBNode;
import rb.RBTree;
import core.data.Client;
import core.data.Product;
import core.data.ProductPlace;
import core.data.ProductValueItem;
import core.data.TransportProduct;
import core.data.WareHouse;

public class Db implements StorageDatabase {
	private final RBTree<Integer> warehousesById;
	private final RBTree<Integer> itemsByProductNumber;
	private final RBTree<String> allClients;

	public Db() {
		this.warehousesById = new RBTree<>();
		this.itemsByProductNumber = new RBTree<>();
		this.allClients = new RBTree<>();
	}

	private void initDB() {
		// add whs
		// add products
		// add clients
		//
		// create transports
		// end transports
		int whCount = 10;
		for (int i = 0; i < whCount; i++) {
			WareHouse wh = new WareHouse();
			// wh.set
		}

	}

	@Override
	public List<Product> searchProducts(String ean, Date dateFrom, Date dateTo, Integer count, int wareHouseId) {
		// 1. find the ean node
		// 2. find date node
		// 3. load <count> nodes which are on the right side from the found node
		List<Product> products = new LinkedList<>();

		RBTree<Date> dateTree = getDateTree(wareHouseId, ean);
		if (dateTree != null) {
			int i = 0; // number of items added to list
			for (Iterator<RBNode<Date>> iterator = dateTree.getInOrderIterator(dateFrom); iterator.hasNext();) {
				RBNode<Date> dateNode = iterator.next();
				//@f:off
				if (dateNode == null 
						|| (dateNode instanceof DateNode && dateTo != null && dateNode.getKey().compareTo(dateTo) > 0)
						|| (count != null && i >= count)) {
					break;
				}
				//@f:on
				for (RBNode<Integer> pnNode : ((DateNode) dateNode).getValue()) {
					if (count == null || i < count) {
						products.add(((ProductNumberNode) pnNode).getValue());
						i++;
					}
				}
			}
		}

		return products;
	}

	private RBTree<Date> getDateTree(int warehouseId, String ean) {
		WareHouse wh = getWarehouse(warehouseId);
		if (wh != null) {
			RBNode<String> eanNode = wh.getStoredItemsByEan().find(ean);
			if (eanNode != null && eanNode instanceof EanNode) {
				return ((EanNode) eanNode).getValue();
			}
		}

		return null;
	}

	@Override
	public int searchCount(String ean, int wareHouseId) {
		RBNode<Integer> wh = warehousesById.find(wareHouseId);

		if (wh != null && wh instanceof WareHouseNode) {
			RBTree<String> eanTree = ((WareHouse) wh.getValue()).getStoredItemsByEan();
			RBNode<String> eanNode = eanTree.find(ean);
			if (eanNode != null && eanNode instanceof EanNode) {
				RBTree<Date> dateTree = (RBTree<Date>) ((EanNode) eanNode).getValue();
				return dateTree.size();
			}
		}

		return 0;
	}

	@Override
	public Product searchProduct(int productNum) {
		// 1. try to find in product database the node with product with entered
		// product number
		Object product = itemsByProductNumber.find(productNum).getValue();

		if (product != null && product instanceof Product) {
			return (Product) product;
		}

		return null;
	}

	@Override
	public boolean addProduct(int whId, Product product) {
		// add product to warehouse
		// 1. find the warehouse with entered id
		// 2. if warehouse does not exist return false
		// 3. else add entered product to found warehouse
		// 4. return value from add method from warehouse

		// set to product the current place

		// add product to product tree

		boolean retVal = false;

		// RBNode<Integer> wareHouseNode = warehousesById.find(whId);
		WareHouse wh = getWarehouse(whId);
		if (wh == null) {
			retVal = false;
		}
		retVal = wh.addProduct(product);

		// set references to parent structures
		if (retVal) {
			product.setCurrentPlace(wh);
		}

		RBNode<Integer> productNode = itemsByProductNumber.find(product.getProductNumber());
		if (productNode == null) {
			// if the item is not in db create new item
			productNode = new ProductNumberNode(product);
			itemsByProductNumber.insert(productNode);
		}

		return retVal;
	}

	@Override
	public Client searchClient(String clientId, int wareHouseId) {
		// 1. find warehouse by id
		// 2. find the client

		RBNode<Integer> wh = warehousesById.find(wareHouseId);
		if (wh != null && wh instanceof WareHouseNode) {
			return ((WareHouse) wh.getValue()).getClient(clientId);
		}

		return null;
	}

	@Override
	public boolean makeTransportToWareHouse(int productNumber, int wareHouseDestinationId, Date expectedDate) {
		ProductPlace destination = getWarehouse(wareHouseDestinationId);
		return makeTransport(productNumber, destination, expectedDate);
	}

	@Override
	public boolean makeTransportToClient(int productNumber, String clientId, Date expectedDate) {
		ProductPlace destination = getClient(clientId);
		return makeTransport(productNumber, destination, expectedDate);
	}

	private boolean makeTransport(int productNumber, ProductPlace destination, Date expectedDate) {
		// 1. find product by PN
		// 2. remove item from warehouse
		// 3. make transport
		// 4. add product to transport and transport to product
		// 5. find destination place
		// 6. set departure and destination
		// 7. set dispatched date
		// 8. set expected date
		// 9. set ECV of car
		// 10. add transport to dispatched tree in departure warehouse (by
		// product id)
		// 11. reset current place in product

		// 1
		Product product = searchProduct(productNumber);
		if (product == null) {
			return false;
		}
		// 2
		WareHouse whDeparture = product.getCurrentPlace();
		deleteFromWh(product);
		// 3, 4
		TransportProduct transport = new TransportProduct(product);
		product.setTransport(transport);
		// 5
		// from parameter
		// 6
		transport.setDeparture(whDeparture);
		transport.setDestination(destination);
		// 7
		transport.setDispatchedDate(new Date());
		// 8
		transport.setExpectedDate(expectedDate);
		// 9
		// TODO generate ecv
		transport.setCarEcv("some ECV");
		// 10
		RBTree<Integer> dispatched = whDeparture.getDispatchedByPN();
		boolean retVal = dispatched.insert(new TransportNode(transport));
		// 11
		product.setCurrentPlace(null);
		product.setProductNumbersTree(null);
		product.setDateTree(null);
		product.setEanTree(null);

		return retVal;
	}

	@Override
	public boolean endTransport(int productNum, Date arrivalDate) {
		// 1. find transport by productNum
		// 2. delete transport from warehouse
		// 3. set arrived date
		// 4. add transport to arrivedItems of destination
		// 5. add product to destination (only warehouse)
		// 6. delete product from all products tree
		Product product = searchProduct(productNum);

		TransportProduct transport = product.getTransport();
		WareHouse wh = (WareHouse) transport.getDeparture();
		RBTree<Integer> whDispatched = wh.getDispatchedByPN();
		whDispatched.delete(whDispatched.find(transport.getPN()));

		transport.setArrivedDate(arrivalDate);

		ProductPlace destinationPlace = transport.getDestination();
		boolean retVal = destinationPlace.addArrivedItem(transport);

		if (destinationPlace instanceof WareHouse) {
			retVal &= ((WareHouse) destinationPlace).addProduct(product);
		}

		if (retVal) {
			retVal &= (itemsByProductNumber.delete(itemsByProductNumber.find(productNum)) != null);
		}

		return retVal;
	}

	@Override
	public List<Client> searchClients(int wareHouseId) {
		List<Client> clients = new LinkedList<>();
		WareHouse wh = getWarehouse(wareHouseId);
		if (wh != null) {
			RBTree<String> clientsTree = wh.getClientsById();
			for (RBNode<String> clientNode : clientsTree) {
				Client c = ((ClientNode) clientNode).getValue();
				clients.add(c);
			}
		}

		return clients;
	}

	/**
	 * use {@link #getLiveTransportRBTree(int)} which is quickly
	 */
	@Override
	public List<TransportProduct> getLiveTransport(int wareHouseId) {
		List<TransportProduct> transports = new LinkedList<>();
		WareHouse wh = getWarehouse(wareHouseId);
		if (wh != null) {
			RBTree<Integer> dispatchedTree = wh.getDispatchedByPN();
			for (RBNode<Integer> transportNode : dispatchedTree) {
				transports.add((TransportProduct) transportNode.getValue());
			}
		}

		return transports;
	}

	public RBTree<Integer> getLiveTransportRBTree(int wareHouseId) {

		WareHouse wh = getWarehouse(wareHouseId);
		if (wh != null) {
			return wh.getDispatchedByPN();
		}

		return null;
	}

	@Override
	public List<TransportProduct> showArrivedProductsInClinet(int wareHouseFromId, String clientId) {
		WareHouse wh = getWarehouse(wareHouseFromId);
		if (wh != null) {
			Client client = wh.getClient(clientId);
			if (client != null) {
				return showArrivedProducts(client);
			}
		}

		return null;
	}

	@Override
	public List<TransportProduct> showArrivedProductsInWareHouse(int wareHouseToId) {
		WareHouse wh = getWarehouse(wareHouseToId);
		if (wh != null) {
			return showArrivedProducts(wh);
		}

		return null;
	}

	private List<TransportProduct> showArrivedProducts(ProductPlace place) {
		return place.getArrivedItems();
	}

	@Override
	public List<Product> searchProducts(Date dateFrom, int daysUntilDate, int wareHouseId) {

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dateFrom);
		calendar.add(Calendar.DAY_OF_YEAR, daysUntilDate);
		Date dateTo = calendar.getTime();

		List<Product> products = new LinkedList<>();

		WareHouse wh = getWarehouse(wareHouseId);
		if (wh != null) {
			RBTree<String> eanTree = wh.getStoredItemsByEan();
			for (RBNode<String> eanNode : eanTree) {
				List<Product> eanProducts = searchProducts(eanNode.getKey(), dateFrom, dateTo, null, wareHouseId);
				products.addAll(eanProducts);
			}
		}
		return products;
	}

	@Override
	public boolean addWarehouse(WareHouse wh) {
		// 1. search if warehouse is not already inserted

		// 2. create WHNode
		// 3. add WHValue to WHNode
		// 4. store WHNode with WHValue to DB

		RBNode<Integer> item = warehousesById.find(wh.getId());
		if (item != null) {
			return false;
		}

		WareHouseNode newItem = new WareHouseNode(wh);

		return warehousesById.insert(newItem);
	}

	@Override
	public boolean addClient(Client c, int whId) {
		// 1. find warehouse
		// 2. add entered client into warehouse
		// 3. set ref for client to wh

		boolean retVal = false;

		WareHouse wh = getWarehouse(whId);
		if (wh != null) {
			retVal = wh.addClient(c);
			if (retVal) {
				c.setWarehouse(wh);
				allClients.insert(new ClientNode(c));
			}
		}

		return retVal;
	}

	@Override
	public boolean deleteProduct(int productNum) {
		// delete from allproducts
		// delete from arrived items
		// delete from stored
		RBNode<Integer> productNode = itemsByProductNumber.find(productNum);
		if (productNode != null && productNode instanceof ProductNumberNode) {
			Product product = ((ProductNumberNode) productNode).getValue();
			itemsByProductNumber.delete(productNode);

			deleteFromArrived(product);

			deleteFromWh(product);
		}

		return true;
	}

	private void deleteFromArrived(Product product) {

		int productNum = product.getProductNumber();

		// check all warehouses and delete if found
		for (RBNode<Integer> whNode : warehousesById) {
			((WareHouseNode) whNode).getValue().deleteArrivedItem(productNum);
		}

		for (RBNode<String> clientNode : allClients) {
			((ClientNode) clientNode).getValue().deleteArrivedItem(productNum);
		}
	}

	private void deleteFromWh(Product product) {
		WareHouse wh = product.getCurrentPlace();
		if (wh != null) {
			RBTree<Integer> pnTree = product.getProductNumbersTree();
			pnTree.delete(pnTree.find(product.getProductNumber()));

			RBTree<Date> dateTree = product.getDateTree();
			RBNode<Date> dateNode = dateTree.find(product.getMinDate());
			if (dateNode.getSize() == 0) {
				dateTree.delete(dateNode);

				RBTree<String> eanTree = product.getEanTree();
				RBNode<String> eanNode = eanTree.find(product.getEan());
				if (eanNode.getSize() == 0) {
					eanTree.delete(eanNode);
				}
			}
		}
	}

	@Override
	public boolean removeClient(String clientId, int warehouseId) {
		// remove from warehouse
		// remove from clients
		WareHouse wh = getWarehouse(warehouseId);
		wh.getClientsById().delete(wh.getClientsById().find(clientId));

		allClients.delete(allClients.find(clientId));

		return true;
	}

	@Override
	public boolean removeWarehouse(int wareHouseForDelId, int warehouseToMoveId) {
		// delete from warehouses
		// move all clients to destination warehouse
		// move all transports to destination warehouse
		// set every transport departure as new warehouse
		// move all product one by one to destination warehouse

		boolean retVal = true;

		RBNode<Integer> whToDelNode = warehousesById.find(wareHouseForDelId);
		warehousesById.delete(whToDelNode);
		WareHouse destinationWarehouse = getWarehouse(warehouseToMoveId);

		WareHouse wh = ((WareHouseNode) whToDelNode).getValue();
		RBTree<String> clientsTree = wh.getClientsById();
		for (RBNode<String> clientNode : clientsTree) {
			Client client = ((ClientNode) clientNode).getValue();
			retVal &= destinationWarehouse.addClient(client);
		}

		RBTree<Integer> dispatchedTree = wh.getDispatchedByPN();
		for (RBNode<Integer> transportNode : dispatchedTree) {
			TransportProduct transport = ((TransportNode) transportNode).getValue();
			transport.setDeparture(destinationWarehouse);
			retVal &= destinationWarehouse.getDispatchedByPN().insert(new TransportNode(transport));
		}

		for (RBNode<String> eanNode : wh.getStoredItemsByEan()) {
			for (RBNode<Date> dateNode : ((EanNode) eanNode).getValue()) {
				for (RBNode<Integer> pnNode : ((DateNode) dateNode).getValue()) {
					Product p = ((ProductNumberNode) pnNode).getValue();
					retVal &= destinationWarehouse.addProduct(p);
				}
			}
		}

		return retVal;
	}

	@Override
	public List<ProductValueItem> getProductsValue(int wareHouseId) {
		List<ProductValueItem> productValues = new LinkedList<>();
		WareHouse wh = getWarehouse(wareHouseId);
		for (RBNode<String> eanNode : wh.getStoredItemsByEan()) {
			ProductValueItem productValue = new ProductValueItem();
			productValue.setEan(eanNode.getKey());
			int count = 0;
			double value = 0.0;
			for (RBNode<Date> dateNode : ((EanNode) eanNode).getValue()) {
				for (RBNode<Integer> pnNode : ((DateNode) dateNode).getValue()) {
					Product p = ((ProductNumberNode) pnNode).getValue();
					if (p != null) {
						count++;
						value += p.getCost();
					}
				}
			}
			productValue.setValue(value);
			productValue.setCount(count);
			productValues.add(productValue);
		}

		return productValues;
	}

	public WareHouse getWarehouse(int warehouseId) {
		RBNode<Integer> whNode = warehousesById.find(warehouseId);
		if (whNode != null && whNode instanceof WareHouseNode) {
			Object wh = whNode.getValue();
			if (wh != null && wh instanceof WareHouse) {
				return (WareHouse) wh;
			}
		}
		return null;
	}

	public Client getClient(String cId, int warehouseId) {
		// 1. find warehouse by id
		// 2. find client by id
		RBNode<Integer> whNode = warehousesById.find(warehouseId);
		if (whNode != null && whNode instanceof WareHouseNode) {
			RBNode<String> clientNode = ((WareHouse) whNode.getValue()).getClientsById().find(cId);
			if (clientNode != null && clientNode instanceof ClientNode) {
				return ((ClientNode) clientNode).getValue();
			}
		}

		return null;
	}

	private Client getClient(String clientId) {
		RBNode<String> cNode = allClients.find(clientId);
		if (cNode != null && cNode instanceof ClientNode) {
			return ((ClientNode) cNode).getValue();
		}
		return null;
	}

	@Override
	public List<WareHouse> getWarehouses() {
		List<WareHouse> whs = new LinkedList<>();
		for (RBNode<Integer> whNode : warehousesById) {
			whs.add(((WareHouseNode) whNode).getValue());
		}

		return whs;
	}
}
