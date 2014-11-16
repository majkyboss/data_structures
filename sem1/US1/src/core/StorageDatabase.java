package core;

import gui.tables.ProductValueItem;

import java.util.Date;
import java.util.List;

import core.data.Client;
import core.data.Product;
import core.data.TransportProduct;
import core.data.WareHouse;

public interface StorageDatabase {

	/**
	 * vyh�adanie zadan�ho po�tu tovarov s konkr�tnym EAN k�dom a s d�tumom
	 * najneskor�ej
	 * spotreby v���m ako zadan� d�tum nach�dzaj�cich sa v zadanom ve�kosklade
	 * (identifikovan�
	 * svojim identifik�torom), ktor� nie s� expedovan�
	 * 
	 * @param ean
	 * @param dateFrom
	 * @param dateTo
	 * @param count
	 * @param wareHouseId
	 * @return
	 */
	List<Product> searchProducts(String ean, Date dateFrom, Date dateTo, int count, int wareHouseId);

	/**
	 * zistenie po�tu tovarov s konkr�tnym EAN k�dom nach�dzaj�cich sa v zadanom
	 * ve�kosklade
	 * (identifikovan� svojim identifik�torom), ktor� nie s� expedovan�
	 * 
	 * @param ean
	 * @param wareHouseId
	 * @return
	 */
	int searchCount(String ean, int wareHouseId);

	/**
	 * vyh�adanie tovaru (identifikovan� svojim v�robn�m k�dom) a v�pis v�etk�ch
	 * o �om
	 * dostupn�ch inform�cii (v pr�pade prebiehaj�cej exped�cie aj inform�cie o
	 * nej) - vr�tane
	 * inform�cie o tom, v ktorom ve�kosklade sa nach�dza
	 * 
	 * @param productNum
	 * @return
	 */
	Product searchProduct(int productNum);

	/**
	 * naskladnenie (pridanie) tovaru do ve�koskladu (identifikovan� svojim
	 * identifik�torom)
	 * 
	 * @param whId
	 * @param product
	 * @return
	 */
	boolean addProduct(int whId, Product product);

	/**
	 * vyh�adanie lok�lneho odberate�a pod�a jeho identifik�toru a priraden�ho
	 * ve�koskladu
	 * (identifikovan� svojim identifik�torom)
	 * 
	 * @param clientId
	 * @param wareHouseId
	 * @return
	 */
	Client searchClient(String clientId, int wareHouseId);

	/**
	 * vykonanie z�znamu o za�iatku expedovania dan�ho tovaru (identifikovan�
	 * v�robn�m k�dom)
	 * do in�ho ve�koskladu (identifikovan� svojim identifik�torom)
	 * 
	 * @param productNumber
	 * @param wareHouseId
	 * @param expectedDate
	 * @return
	 */
	boolean makeTransportToWareHouse(int productNumber, int wareHouseDestinationId, Date expectedDate);

	/**
	 * vykonanie z�znamu o za�iatku expedovania dan�ho tovaru (identifikovan�
	 * v�robn�m k�dom)
	 * k odberate�ovi (identifikovan� svojim identifik�torom)
	 * 
	 * @param productNumber
	 * @param clientId
	 * @param expectedDate
	 * @return
	 */
	boolean makeTransportToClient(int productNumber, String clientId, Date expectedDate);

	/**
	 * vykonanie z�znamu o vylo�en� tovaru (identifikovan� v�robn�m k�dom) �
	 * koniec
	 * expedovania do ve�koskladu (nepozn�me jeho identifik�tor), alebo k
	 * odberate�ovi
	 * (nepozn�me jeho identifik�tor)
	 * 
	 * @param productNum
	 * @param arrivalDate
	 * @return
	 */
	boolean endTransport(int productNum, Date arrivalDate);

	/**
	 * v�pis odberate�ov zadan�ho ve�koskladu (identifikovan� svojim
	 * identifik�torom) zotrieden�
	 * pod�a ich identifik�torov
	 * 
	 * @param wareHouseId
	 * @return
	 */
	List<Client> searchClients(int wareHouseId);

	/**
	 * v�pis pr�ve expedovan�ch tovarov z dan�ho ve�koskladu (identifikovan�
	 * svojim
	 * identifik�torom)
	 * 
	 * @param wareHouseId
	 * @return
	 */
	List<TransportProduct> getLiveTransport(int wareHouseId);

	/**
	 * v�pis uskuto�nen�ch dod�vok k zadan�mu odberate�ovi (identifikovan�
	 * svojim
	 * identifik�torom) priraden�ho k ve�koskladu (identifikovan� svojim
	 * identifik�torom) -
	 * po�aduj� sa inform�cie: za�iatok expedovania, koniec expedovania, E�V
	 * 
	 * @param wareHouseFromId
	 * @param wareHouseToId
	 * @return
	 */
	List<TransportProduct> showArrivedProductsInWareHouse(int wareHouseFromId, int wareHouseToId);

	/**
	 * v�pis uskuto�nen�ch dod�vok k zadan�mu ve�koskladu (identifikovan� svojim
	 * identifik�torom) - po�aduj� sa inform�cie: za�iatok expedovania, koniec
	 * expedovania, E�V
	 * 
	 * @param wareHouseFromId
	 * @param clientId
	 * @return
	 */
	List<TransportProduct> showArrivedProductsInClinet(int wareHouseFromId, String clientId);

	/**
	 * v�pis tovarov na danom ve�kosklade (identifikovan� svojim
	 * identifik�torom), ktor�m kon��
	 * spotreba do zadan�ho po�tu dn� (napr. do 5 dn�) od zadan�ho d�tumu
	 * 
	 * @param dateFrom
	 * @param daysUntilDate
	 * @param wareHouseId
	 * @return
	 */
	List<Product> searchProducts(Date dateFrom, int daysUntilDate, int wareHouseId);

	/**
	 * pridanie ve�koskladu
	 * 
	 * @param c
	 * @param whId
	 * @return
	 */
	boolean addClient(Client c, int whId);

	/**
	 * pridanie odberate�a ve�koskladu (identifikovan� svojim identifik�torom)
	 * 
	 * @param wh
	 * @return
	 */
	boolean addWarehouse(WareHouse wh);

	/**
	 * vyradenie konkr�tneho tovaru z evidencie (identifikovan� v�robn�m k�dom)
	 * 
	 * @param productNum
	 * @return
	 */
	boolean deleteProduct(int productNum);

	/**
	 * v�pis celkov�ho mno�stva a sum�rnej hodnoty tovarov (pod�a EAN k�dov) pre
	 * dan�
	 * ve�kosklad (identifikovan� svojim identifik�torom), to znamen� uvies�
	 * ko�ko kusov tovaru sa
	 * nach�dza v sklade s dan�m EAN k�dom a ak� je sum�rna cena za tieto tovary
	 * 
	 * @param clientId
	 * @param warehouseId
	 * @return
	 */
	boolean removeClient(String clientId, int warehouseId);

	/**
	 * zru�enie ve�koskladu (identifikovan� svojim identifik�torom) - cel� jeho
	 * agenda sa presunie
	 * do in�ho ve�koskladu (identifikovan� svojim identifik�torom)
	 * 
	 * @param wareHouseForDelId
	 * @param warehouseToMoveId
	 * @return
	 */
	boolean removeWarehouse(int wareHouseForDelId, int warehouseToMoveId);

	/**
	 * zru�enie odoberate�a (identifikovan� svojim identifik�torom) ve�koskladu
	 * (identifikovan�
	 * svojim identifik�torom)
	 * 
	 * @param wareHouseId
	 * @return
	 */
	List<ProductValueItem> getProductsValue(int wareHouseId);
}
