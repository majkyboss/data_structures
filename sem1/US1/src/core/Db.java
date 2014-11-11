package core;

import gui.tables.ProductValueItem;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import rb.RBNode;
import rb.RBTree;

public class Db implements StorageDatabase {
	private int id;
	private RBTree<Integer> warehousesById;
	private RBTree<Integer> itemsByProductNumber;

	public Db() {
		this.id = IdCounter.getNextId();
		this.warehousesById = new RBTree<>();
		this.itemsByProductNumber = new RBTree<>();
	}

	@Override
	public List<Product> searchProducts(String ean, Date dateFrom, Date dateTo, int count, int wareHouseId) {
		// TODO method to find data in DB

		return new LinkedList<>();
	}

	@Override
	public int searchCount(String ean, int wareHouseId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Product searchProduct(int productNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addProduct(int whId, Product product) {
		// 1. find if the item with same value is not inserted
		RBNode<Integer> whNode = warehousesById.find(whId);
		if (whNode != null) {
			return false;
		}

		// 2. create item with entered value

		// 3. add item to warehouse

		return true;
	}

	@Override
	public Client searchClient(String clientId, int wareHouseId) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addWarehouse(WareHouseValue wh) {
		// TODO Auto-generated method stub
		return false;
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

}
