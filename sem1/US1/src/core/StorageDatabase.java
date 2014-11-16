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
	 * vyh¾adanie zadaného poètu tovarov s konkrétnym EAN kódom a s dátumom
	 * najneskoršej
	 * spotreby väèším ako zadanı dátum nachádzajúcich sa v zadanom ve¾kosklade
	 * (identifikovanı
	 * svojim identifikátorom), ktoré nie sú expedované
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
	 * zistenie poètu tovarov s konkrétnym EAN kódom nachádzajúcich sa v zadanom
	 * ve¾kosklade
	 * (identifikovanı svojim identifikátorom), ktoré nie sú expedované
	 * 
	 * @param ean
	 * @param wareHouseId
	 * @return
	 */
	int searchCount(String ean, int wareHouseId);

	/**
	 * vyh¾adanie tovaru (identifikovanı svojim vırobnım kódom) a vıpis všetkıch
	 * o òom
	 * dostupnıch informácii (v prípade prebiehajúcej expedície aj informácie o
	 * nej) - vrátane
	 * informácie o tom, v ktorom ve¾kosklade sa nachádza
	 * 
	 * @param productNum
	 * @return
	 */
	Product searchProduct(int productNum);

	/**
	 * naskladnenie (pridanie) tovaru do ve¾koskladu (identifikovanı svojim
	 * identifikátorom)
	 * 
	 * @param whId
	 * @param product
	 * @return
	 */
	boolean addProduct(int whId, Product product);

	/**
	 * vyh¾adanie lokálneho odberate¾a pod¾a jeho identifikátoru a priradeného
	 * ve¾koskladu
	 * (identifikovanı svojim identifikátorom)
	 * 
	 * @param clientId
	 * @param wareHouseId
	 * @return
	 */
	Client searchClient(String clientId, int wareHouseId);

	/**
	 * vykonanie záznamu o zaèiatku expedovania daného tovaru (identifikovanı
	 * vırobnım kódom)
	 * do iného ve¾koskladu (identifikovanı svojim identifikátorom)
	 * 
	 * @param productNumber
	 * @param wareHouseId
	 * @param expectedDate
	 * @return
	 */
	boolean makeTransportToWareHouse(int productNumber, int wareHouseDestinationId, Date expectedDate);

	/**
	 * vykonanie záznamu o zaèiatku expedovania daného tovaru (identifikovanı
	 * vırobnım kódom)
	 * k odberate¾ovi (identifikovanı svojim identifikátorom)
	 * 
	 * @param productNumber
	 * @param clientId
	 * @param expectedDate
	 * @return
	 */
	boolean makeTransportToClient(int productNumber, String clientId, Date expectedDate);

	/**
	 * vykonanie záznamu o vyloení tovaru (identifikovanı vırobnım kódom) –
	 * koniec
	 * expedovania do ve¾koskladu (nepoznáme jeho identifikátor), alebo k
	 * odberate¾ovi
	 * (nepoznáme jeho identifikátor)
	 * 
	 * @param productNum
	 * @param arrivalDate
	 * @return
	 */
	boolean endTransport(int productNum, Date arrivalDate);

	/**
	 * vıpis odberate¾ov zadaného ve¾koskladu (identifikovanı svojim
	 * identifikátorom) zotriedenı
	 * pod¾a ich identifikátorov
	 * 
	 * @param wareHouseId
	 * @return
	 */
	List<Client> searchClients(int wareHouseId);

	/**
	 * vıpis práve expedovanıch tovarov z daného ve¾koskladu (identifikovanı
	 * svojim
	 * identifikátorom)
	 * 
	 * @param wareHouseId
	 * @return
	 */
	List<TransportProduct> getLiveTransport(int wareHouseId);

	/**
	 * vıpis uskutoènenıch dodávok k zadanému odberate¾ovi (identifikovanı
	 * svojim
	 * identifikátorom) priradeného k ve¾koskladu (identifikovanı svojim
	 * identifikátorom) -
	 * poadujú sa informácie: zaèiatok expedovania, koniec expedovania, EÈV
	 * 
	 * @param wareHouseFromId
	 * @param wareHouseToId
	 * @return
	 */
	List<TransportProduct> showArrivedProductsInWareHouse(int wareHouseFromId, int wareHouseToId);

	/**
	 * vıpis uskutoènenıch dodávok k zadanému ve¾koskladu (identifikovanı svojim
	 * identifikátorom) - poadujú sa informácie: zaèiatok expedovania, koniec
	 * expedovania, EÈV
	 * 
	 * @param wareHouseFromId
	 * @param clientId
	 * @return
	 */
	List<TransportProduct> showArrivedProductsInClinet(int wareHouseFromId, String clientId);

	/**
	 * vıpis tovarov na danom ve¾kosklade (identifikovanı svojim
	 * identifikátorom), ktorım konèí
	 * spotreba do zadaného poètu dní (napr. do 5 dní) od zadaného dátumu
	 * 
	 * @param dateFrom
	 * @param daysUntilDate
	 * @param wareHouseId
	 * @return
	 */
	List<Product> searchProducts(Date dateFrom, int daysUntilDate, int wareHouseId);

	/**
	 * pridanie ve¾koskladu
	 * 
	 * @param c
	 * @param whId
	 * @return
	 */
	boolean addClient(Client c, int whId);

	/**
	 * pridanie odberate¾a ve¾koskladu (identifikovanı svojim identifikátorom)
	 * 
	 * @param wh
	 * @return
	 */
	boolean addWarehouse(WareHouse wh);

	/**
	 * vyradenie konkrétneho tovaru z evidencie (identifikovanı vırobnım kódom)
	 * 
	 * @param productNum
	 * @return
	 */
	boolean deleteProduct(int productNum);

	/**
	 * vıpis celkového mnostva a sumárnej hodnoty tovarov (pod¾a EAN kódov) pre
	 * danı
	 * ve¾kosklad (identifikovanı svojim identifikátorom), to znamená uvies
	 * ko¾ko kusov tovaru sa
	 * nachádza v sklade s danım EAN kódom a aká je sumárna cena za tieto tovary
	 * 
	 * @param clientId
	 * @param warehouseId
	 * @return
	 */
	boolean removeClient(String clientId, int warehouseId);

	/**
	 * zrušenie ve¾koskladu (identifikovanı svojim identifikátorom) - celá jeho
	 * agenda sa presunie
	 * do iného ve¾koskladu (identifikovanı svojim identifikátorom)
	 * 
	 * @param wareHouseForDelId
	 * @param warehouseToMoveId
	 * @return
	 */
	boolean removeWarehouse(int wareHouseForDelId, int warehouseToMoveId);

	/**
	 * zrušenie odoberate¾a (identifikovanı svojim identifikátorom) ve¾koskladu
	 * (identifikovanı
	 * svojim identifikátorom)
	 * 
	 * @param wareHouseId
	 * @return
	 */
	List<ProductValueItem> getProductsValue(int wareHouseId);
}
