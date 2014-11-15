package test;

import rb.RBNode;

/**
 * key and value are same
 * 
 * @author Banik
 * 
 */
public class IntegerNode extends RBNode<Integer> {
	public IntegerNode(Integer value) {
		super();
		setValue(value);
	}

	@Override
	public Integer getKey() {
		return (Integer) value;
	}
}
