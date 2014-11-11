package rb;

import core.NodeValue;

public abstract class RBNode<T extends Comparable<T>, V> implements NodeCompare<T> {
	public static final int COLOR_NODE_RED = 1;
	public static final int COLOR_NODE_BLACK = 2;
	public static final int COLOR_NODE_DOUBLE_BLACK = 3;
	private int color;
	private RBNode<T, V> parent;
	protected NodeValue<V> value;
	private RBNode<T, V> leftChild;
	private RBNode<T, V> rightChild;

	public RBNode(NodeValue<V> value) {
		this.value = value;
	}

	public void setColor(int nodeColor) {
		color = nodeColor;
	}

	public int getColor() {
		return color;
	}

	public RBNode<T, V> getParent() {
		return parent;
	}

	public NodeValue<V> getValue() {
		return value;
	}

	void setValue(NodeValue<V> value) {
		// if there is no access modifier it will be accessible from this class and this package too, not from subclasses and not from world
		this.value = value;
	}

	public void setParent(RBNode<T, V> parent) {
		this.parent = parent;
	}

	public RBNode<T, V> getLeftChild() {
		return leftChild;
	}

	public RBNode<T, V> getRightChild() {
		return rightChild;
	}

	public void setLeftChild(RBNode<T, V> node) {
		leftChild = node;
	}

	public void setRightChild(RBNode<T, V> node) {
		rightChild = node;
	}

	public void deleteChild(RBNode<T, V> node) {
		if (leftChild != null && leftChild.equals(node)) {
			leftChild = null;
		} else if (rightChild != null && rightChild.equals(node)) {
			rightChild = null;
		}
	}

	@Override
	public String toString() {
		return // "RBNode" +
				// getKey() +
				// super.toString().substring(super.toString().indexOf(" ")) +
				// BinaryNode k:"+ key.getKeyValue() +" v:"+
				// value.getNodeValue()
				// "k:"+
		getKey() +
		// " v:"+ value.getNodeValue()+
		// " c:" + (color == RBNode.COLOR_NODE_BLACK ? "B" : "R")
		"";
	}

	@Override
	public int compareTo(NodeCompare<T> node) {

		return this.getKey().compareTo(node.getKey());
	}

	@Override
	public int compareTo(T key) {
		return this.getKey().compareTo(key);
	}

	@Override
	public abstract T getKey();

	// public abstract void setKey(T key);

}
