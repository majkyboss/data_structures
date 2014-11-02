package rb;

import core.NodeValue;

public abstract class RBNode<T extends Comparable<T>> implements NodeCompare<T>{
	public static final int COLOR_NODE_RED = 1;
	public static final int COLOR_NODE_BLACK = 2;
	public static final int COLOR_NODE_DOUBLE_BLACK = 3;
	private int color;
	private RBNode<T> parent;
	private NodeValue value;
	private RBNode<T> leftChild;
	private RBNode<T> rightChild;

	public RBNode(NodeValue value) {
		this.value = value;
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

	public NodeValue getValue() {
		return value;
	}

	public void setParent(RBNode<T> parent) {
		this.parent = parent;
	}

	public RBNode<T> getLeftChild() {
		return leftChild;
	}

	public RBNode<T> getRightChild() {
		// TODO Auto-generated method stub
		return rightChild;
	}
	
	public void setLeftChild(RBNode<T> node) {
		leftChild = node;
	}

	public void setRightChild(RBNode<T> node) {
		rightChild = node;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return //"RBNode" +
				//getKey() +
				//super.toString().substring(super.toString().indexOf(" ")) +
				//BinaryNode k:"+ key.getKeyValue() +" v:"+ value.getNodeValue()
				"k:"+ getKey() +" v:"+ value.getNodeValue()+
				" c:" + (color == RBNode.COLOR_NODE_BLACK ? "B" : "R");
	}

	@Override
	public int compareTo(NodeCompare<T> node){

		return this.getKey().compareTo(node.getKey());
	}
	
	@Override
	public int compareTo(T key){
		return this.getKey().compareTo(key);
	}

	@Override
	public abstract T getKey();

	
}
