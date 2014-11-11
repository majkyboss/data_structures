package core;

import java.util.Date;

import rb.RBNode;
import rb.RBTree;
import core.data.Product;
import core.data.WareHouseValue;

public class WareHouseNode extends RBNode<Integer> {
	private RBTree<String> storedByEan;

	// private RBTree<Integer> dispatchedByPN;

	public WareHouseNode() {
		super();
		this.storedByEan = new RBTree<String>();
		// this.dispatchedByPN = new RBTree<Integer>();
	}

	@Override
	public Integer getKey() {
		return ((WareHouseValue) value).getId();
	}

	public boolean addProduct(Product product) {
		// 1. try to find ean item
		// 2. if does not exist create new item
		//
		// 3. add product to item

		boolean retVal = false;

		// add product to exist ean group - add one lie in db
		RBNode<String> item = storedByEan.find(product.getEan());

		if (item == null) {
			// if the ean is not in db
			// insert new ean node
			EanNode newNode = new EanNode(product.getEan());

			item = newNode;
		}

		storedByEan.insert(item);

		if (item instanceof EanNode) {
//			RBTree<Date> itemsByDate = ((EanNode) item).getNodeValue();
			// the right command should be nodeToAdd.getValue().getNodeValue() but because the EanNode implements NodeValue interface I can skip it
			retVal = ((EanNode) item).addProduct(product);
		}

		return retVal;
	}

}
