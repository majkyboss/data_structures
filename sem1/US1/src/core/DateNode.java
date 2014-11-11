package core;

import java.util.Date;

import core.data.Product;
import rb.RBNode;
import rb.RBTree;

public class DateNode extends RBNode<Date> {
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

		return false;
	}

}
