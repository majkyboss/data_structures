package core;

import java.util.Date;

import rb.RBNode;
import rb.RBTree;

public class WareHouse extends ProductPlace implements NodeValue{
	private String name;
	private int id;
	private Address address;
	private RBTree<String> stored;
	private RBTree<ProductNumberKey> dispatched;
	
	public WareHouse() {
		super();
		this.name = "";
		this.id = IdCounter.getNextId();
		this.address = new Address();
		this.stored = new RBTree<>();
		this.dispatched = new RBTree<>();
	}

	public int getId() {
		return id;
	}

	public void addProduct(Product product){
		//add product to exist ean group - add one lie in db
		RBNode<String> foundNode = stored.find(product.getEan());
		
		//or if the ean is not in db insert it
		stored.insert(new RBNode<String>(product) {
			private RBTree<Date> items = new RBTree<>();
			
			@Override
			public String getKey() {
				return ((Product)value).getEan();
			}
		});
	}

	@Override
	public Object getNodeValue() {
		return this;
	}
}
