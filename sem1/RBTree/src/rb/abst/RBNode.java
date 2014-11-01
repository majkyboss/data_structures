package rb.abst;

import rb.BinaryNode;
import core.NodeKey;
import core.NodeValue;

public abstract class RBNode<T> extends BinaryNode implements Comparable<T> {
	public static final int COLOR_NODE_RED = 1;
	public static final int COLOR_NODE_BLACK = 2;
	public static final int COLOR_NODE_DOUBLE_BLACK = 3;
	private int color;

	public RBNode(NodeValue value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	public void setColor(int nodeColor) {
		color = nodeColor;
	}

	public int getColor() {
		return color;
	}

	@Override
	public RBNode getLeftChild() {
		// TODO Auto-generated method stub
		return (RBNode) super.getLeftChild();
	}

	@Override
	public RBNode getRightChild() {
		// TODO Auto-generated method stub
		return (RBNode) super.getRightChild();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "RBNode" +
				super.toString().substring(super.toString().indexOf(" ")) +
				" c:" + (color == RBNode.COLOR_NODE_BLACK ? "B" : "R");
	}

}

