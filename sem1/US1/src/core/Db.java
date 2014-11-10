package core;

import gui.tables.ProductValueItem;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import rb.RBNode;
import rb.RBTree;

public class Db implements StorageDatabase {
	private int id;
	private RBTree<Integer> warehouses;
	private RBTree<Integer> itemsByProductNumber;

	public Db() {
		this.id = IdCounter.getNextId();
		this.warehouses = new RBTree<>();
		this.itemsByProductNumber = new RBTree<Integer>();
	}

	@Override
	public boolean addItem(int whId, Product product) {
		RBNode<Integer> whNode = warehouses.find(whId);

		return true;
	}

	@Override
	public boolean addWareHouse(WareHouse warehouse) {
		boolean inserted = warehouses.insert(new RBNode<Integer>(warehouse) {

			@Override
			public Integer getKey() {
				return ((WareHouse) value).getId();
			}
		});

		return true;
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
	public boolean addWarehouse(WareHouse wh) {
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
