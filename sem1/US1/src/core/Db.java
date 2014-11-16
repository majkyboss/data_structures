package core;

import gui.tables.ProductValueItem;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import rb.RBNode;
import rb.RBTree;
import core.data.Client;
import core.data.Product;
import core.data.ProductPlace;
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

	@Override
	public List<Product> searchProducts(String ean, Date dateFrom, Date dateTo, int count, int wareHouseId) {
		// 1. find the ean node
		// 2. find date node
		// 3. load <count> nodes which are on the right side from the found node
		// TODO !!! make method in tree which gets more then one node

		return new LinkedList<>();
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
			return ((WareHouse) wh.getValue()).findClient(clientId);
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
		// 4. add product to transport
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
		RBTree<Integer> pnTree = product.getProductNumbersTree();
		if (pnTree.size() == 1)
			pnTree.delete(pnTree.find(productNumber));
		RBTree<Date> dateTree = product.getDateTree();
		if (dateTree.size() == 1)
			dateTree.delete(dateTree.find(product.getMinDate()));
		RBTree<String> eanTree = product.getEanTree();
		if (eanTree.size() == 1)
			eanTree.delete(eanTree.find(product.getEan()));
		// 3, 4
		TransportProduct transport = new TransportProduct(product);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Client> searchClients(int wareHouseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransportProduct> getLiveTransport(int wareHouseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransportProduct> showArrivedProductsInWareHouse(int wareHouseFromId, int wareHouseToId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransportProduct> showArrivedProductsInClinet(int wareHouseFromId, String clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> searchProducts(Date dateFrom, int daysUntilDate, int wareHouseId) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeClient(String clientId, int warehouseId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeWarehouse(int wareHouseForDelId, int warehouseToMoveId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProductValueItem> getProductsValue(int wareHouseId) {
		// TODO Auto-generated method stub
		return null;
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
}
