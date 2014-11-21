package core;

import java.util.Date;

import rb.RBNode;
import rb.RBTree;
import core.data.Product;

public class EanNode extends RBNode<String> {
	private RBTree<Date> itemsByDate;
	private String key;

	public EanNode(String ean) {
		super();
		key = ean;
		itemsByDate = new RBTree<>();
		value = itemsByDate;
	}

	@Override
	public String getKey() {
		return key;
	}

	// public RBTree<Date> getIncludedTree() {
	// return itemsByDate;
	// }

	@Override
	public RBTree<Date> getValue() {
		return (RBTree<Date>) super.getValue();
	}

	@Override
	protected void setValue(Object value) {
		itemsByDate = (RBTree<Date>) value;
		value = itemsByDate;
		
		RBNode<Date> dateRoot = itemsByDate.getRoot();
		if (dateRoot!=null && dateRoot instanceof DateNode) {
			RBTree<Integer> itemsByProductNumber = ((DateNode) dateRoot).getValue();
			RBNode<Integer> productRoot = itemsByProductNumber.getRoot();
			if (productRoot!=null) {
				Product product = ((ProductNumberNode)productRoot).getValue();
				if (product!=null) {
					key = product.getEan();
				}
			}
		}
		
		
	}

	public boolean addProduct(Product product) {
		// 1. try to find date node
		// 2. if does not exist create new date node
		// 3. add product to node

		boolean retVal = false;

		RBNode<Date> item = itemsByDate.find(product.getMinDate());

		if (item == null) {
			DateNode newItem = new DateNode(product.getMinDate());
			item = newItem;
		}

		itemsByDate.insert(item);

		if (item instanceof DateNode) {
			retVal = ((DateNode) item).addProduct(product);
		}
		if (retVal) {
			product.setDateTree(itemsByDate);
		}

		return retVal;
	}

	@Override
	public int getSize() {
		// int oldSize = super.getSize();
		return itemsByDate.size();
	}
}
