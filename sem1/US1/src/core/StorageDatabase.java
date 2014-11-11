package core;

import gui.tables.ProductValueItem;

import java.util.Date;
import java.util.List;

public interface StorageDatabase {
	boolean addItem(int whId, Product product);

	boolean addWareHouse(WareHouseValue warehouse);

	List<Product> searchProducts(String ean, Date dateFrom, Date dateTo, int count, int wareHouseId);

	int searchCount(String ean, int wareHouseId);

	Product searchProduct(int productNum);

	Client searchClient(String clientId, int wareHouseId);

	boolean makeTransportToWareHouse(int productNumber, int wareHouseId, Date expectedDate);

	boolean makeTransportToClient(int productNumber, String clientId, Date expectedDate);

	boolean endTransport(int productNum, Date arrivalDate);

	List<Client> searchClients(int wareHouseId);

	List<TransportProduct> getLiveTransport(int wareHouseId);

	List<TransportProduct> showArrivedProductsInWareHouse(int wareHouseFromId, int wareHouseToId);

	List<TransportProduct> showArrivedProductsInClinet(int wareHouseFromId, String clientId);

	List<Product> searchProducts(Date dateFrom, int daysUntilDate, int wareHouseId);

	boolean addClient(Client c, int whId);

	boolean addWarehouse(WareHouseValue wh);

	boolean deleteProduct(int productNum);

	boolean removeClient(String clientId, int warehouseId);

	boolean removeWarehouse(int wareHouseForDelId, int warehouseToMoveId);

	List<ProductValueItem> getProductsValue(int wareHouseId);
}
