package rb;

//import core.NodeValue;

public abstract class RBNode<T extends Comparable<T>> implements
		NodeCompare<T>, ItemWithKey<T> {
	public static final int COLOR_NODE_RED = 1;
	public static final int COLOR_NODE_BLACK = 2;
	public static final int COLOR_NODE_DOUBLE_BLACK = 3;
	private int color;
	private RBNode<T> parent;
	protected Object value;
	private RBNode<T> leftChild;
	private RBNode<T> rightChild;

	public RBNode() {
		// this.value = value;
	}

	public void setColor(int nodeColor) {
		color = nodeColor;
	}

	public int getColor() {
		return color;
	}

	public RBNode<T> getParent() {
		return parent;
	}

	/**
	 * Gets value stored in this tree node. The concrete value you can get from
	 * method {@link NodeValue#getNodeValue()}
	 * 
	 * @return
	 */
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		// if there is no access modifier it will be accessible from this class
		// and this package too, not from subclasses and not from world
		this.value = value;
	}

	public void setParent(RBNode<T> parent) {
		this.parent = parent;
	}

	public RBNode<T> getLeftChild() {
		return leftChild;
	}

	public RBNode<T> getRightChild() {
		return rightChild;
	}

	public void setLeftChild(RBNode<T> node) {
		leftChild = node;
	}

	public void setRightChild(RBNode<T> node) {
		rightChild = node;
	}

	public void deleteChild(RBNode<T> node) {
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

	/**
	 * Returns size of this node. If node is the basic node, then it returns 1.
	 * If the node is composite node then overwrited method in child class will
	 * return
	 * the current size of the composite node
	 * 
	 * @return current size of the node
	 */
	public int getSize() {
		return 1;
	}

	// public abstract void setKey(T key);

}
