package core.treeItems;

import java.util.Date;

import rb.RBNode;
import rb.RBTree;
import core.Product;
import core.ProductNumberKey;
import core.WareHouseValue;

public class WareHouseTreeItem extends RBNode<Integer> {
	private RBTree<String> storedByEan;
	private RBTree<ProductNumberKey> dispatchedByPN;

	public WareHouseTreeItem(WareHouseValue value) {
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

}
