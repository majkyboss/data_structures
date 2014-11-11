package core;

import java.util.Date;

import rb.RBNode;
import rb.RBTree;
import core.data.Product;

public class EanNode extends RBNode<String> {
	private RBTree<Date> itemsByDate;

	public EanNode(NodeValue value) {
		super(value);
		itemsByDate = new RBTree<>();
	}

	@Override
	public String getKey() {
		return ((Product) value).getEan();
	}

}
