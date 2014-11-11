package core;

import rb.RBNode;
import rb.RBTree;
import core.data.Product;
import core.data.WareHouseValue;

public class WareHouseNode extends RBNode<Integer> {
	private RBTree<String> storedByEan;
	private RBTree<Integer> dispatchedByPN;

	public WareHouseNode(WareHouseValue value) {
		super(value);
		this.storedByEan = new RBTree<>();
		this.dispatchedByPN = new RBTree<>();
	}

	@Override
	public Integer getKey() {
		return ((WareHouseValue) value).getId();
	}

	public void addProduct(Product product) {
		// add product to exist ean group - add one lie in db
		RBNode<String> foundNode = storedByEan.find(product.getEan());
		if (foundNode != null) {
			//if the ean is in db
			// insert product do ean node -------------------------
				// find if the ean node has 
		} else {
			// or if the ean is not in db insert the product to db
			//
			EanNode nodeToAdd = new EanNode(product);

			storedByEan.insert(nodeToAdd);
		}
	}

}
