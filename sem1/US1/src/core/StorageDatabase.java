package core;

import java.util.Date;
import java.util.List;

public interface StorageDatabase {
	boolean addItem(int whId, Product product);

	boolean addWareHouse(WareHouse warehouse);

	List<Product> searchProducts(String ean, Date dateFrom, Date dateTo, int count, int wareHouseId);

	int searchCount(String ean, int wareHouseId);

	Product searchProduct(int productNum);
}
