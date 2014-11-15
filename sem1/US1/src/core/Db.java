package core;

import gui.tables.ProductValueItem;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import rb.RBNode;
import rb.RBTree;
import core.data.Client;
import core.data.Product;
import core.data.TransportProduct;
import core.data.WareHouse;

public class Db implements StorageDatabase {
	private final RBTree<Integer> warehousesById;
	private final RBTree<Integer> itemsByProductNumber;

	public Db() {
		this.warehousesById = new RBTree<>();
		this.itemsByProductNumber = new RBTree<>();
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
			RBTree<String> eanTree = ((WareHouse) wh.getValue()).getTreeByEan();
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

		if (product instanceof Product) {
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

		RBNode<Integer> wareHouseNode = warehousesById.find(whId);
		if (wareHouseNode == null) {
			retVal = false;
		} else if (wareHouseNode instanceof WareHouseNode) {
			retVal = ((WareHouse) wareHouseNode.getValue()).addProduct(product);
		}

		if (retVal) {
			product.setCurrentPlace((WareHouse) wareHouseNode.getValue());
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
	public boolean makeTransportToWareHouse(int productNumber, int wareHouseId, Date expectedDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean makeTransportToClient(int productNumber, String clientId, Date expectedDate) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean addClient(Client c, int whId) {
		RBNode<Integer> whNode = warehousesById.find(whId);
		if (whNode != null && whNode instanceof WareHouseNode) {
			// change structure:
			// whNode.getValue() will return whNode which has references to
			// items stored dispatched and clients, no whNode will have refs to
			// single structures
			// ((WareHouseNode)whNode.getValue()).
			whNode.getValue();
		}

		// TODO Auto-generated method stub
		return false;
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

}
