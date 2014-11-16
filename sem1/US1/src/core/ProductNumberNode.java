package core;

import rb.RBNode;
import core.data.Product;

public class ProductNumberNode extends RBNode<Integer> {
	

	public ProductNumberNode(Product product) {
		super();
		value = product;
	}

	@Override
	public Integer getKey() {
		return ((Product) value).getProductNumber();
	}

	@Override
	public Product getValue() {
		// TODO Auto-generated method stub
		return (Product) super.getValue();
	}
}
