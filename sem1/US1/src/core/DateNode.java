package core;

import java.util.Date;

import rb.RBNode;
import rb.RBTree;
import core.data.Product;

public class DateNode extends RBNode<Date> implements NodeValue {
	private Date key;
	private RBTree<Integer> itemsByProductNumber;

	public DateNode(Date key) {
		super();
		this.key = key;
		itemsByProductNumber = new RBTree<>();
	}

	@Override
	public Date getKey() {
		return key;
	}

	public boolean addProduct(Product product) {
		// 1. try to find PN node
		// 2. if does not exist create new PN node
		// 3. add product to node

		RBNode<Integer> item = itemsByProductNumber.find(product.getProductNumber());
		if (item == null) {
			ProductNumberNode newItem = new ProductNumberNode(product);
			item = newItem;
		}

		// .... product is already added to PNNode in construktor

		return false;
	}

	@Override
	public RBTree<Integer> getNodeValue() {
		return itemsByProductNumber;
	}

}
