package core;

import java.util.Date;
import java.util.List;

public interface StorageDatabase {
	boolean addItem(int whId, Product product);

	boolean addWareHouse(WareHouse warehouse);

	List<Product> searchProducts(String ean, Date dateFrom, Date dateTo, int count, int wareHouseId);

	int searchCount(String ean, int wareHouseId);

	Product searchProduct(int productNum);

	Client searchClient(String clientId, int wareHouseId);

	boolean makeTransportToWareHouse(int productNumber, int wareHouseId, Date expectedDate);

	boolean makeTransportToClient(int productNumber, String clientId, Date expectedDate);

	boolean endTransport(int productNum, Date arrivalDate);

	List<Client> searchClients(int wareHouseId);
}
