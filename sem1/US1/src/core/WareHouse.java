package core;

import java.util.Date;

import rb.RBNode;
import rb.RBTree;

public class WareHouse extends ProductPlace implements NodeValue {
	private String name;
	private int id;
	private Address address;
	private RBTree<String> storedByEan;
	private RBTree<ProductNumberKey> dispatchedByPN;

	public WareHouse() {
		super();
		this.name = "";
		this.id = IdCounter.getNextId();
		this.address = new Address();
		this.storedByEan = new RBTree<>();
		this.dispatchedByPN = new RBTree<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addProduct(Product product) {
		// add product to exist ean group - add one lie in db
		RBNode<String> foundNode = storedByEan.find(product.getEan());
		if (foundNode == null) {
			// or if the ean is not in db insert it
			storedByEan.insert(new RBNode<String>(product) {
				private RBTree<Date> itemsByDate = new RBTree<>();

				@Override
				public String getKey() {
					return ((Product) value).getEan();
				}
			});

		}

	}

	@Override
	public Object getNodeValue() {
		return this;
	}
}
