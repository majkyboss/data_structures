package rb;

import java.util.ArrayList;
import java.util.Arrays;

import core.Node;
import core.NodeValue;

public class BinaryNode implements Node {
	public static final int LEFT_NODE = 0;
	public static final int RIGHT_NODE = 1;
	private Node[] nodes = new BinaryNode[2]; // 0-Left node , 1-Right node
	private Node parent;
	private NodeValue value;

	public BinaryNode(NodeValue value) {
		this.value = value;
	}

	@Override
	public ArrayList<Node> getChildren() {
		ArrayList<Node> nodes = new ArrayList<Node>(Arrays.asList(this.nodes));
		return nodes;
	}

	/**
	 * Gets the children node according to entered node number. Because this is
	 * binary tree, every node could have no more than 2 children nodes. You can
	 * also use constants {@link #LEFT_NODE} and {@link #RIGHT_NODE} for
	 * accessing the children nodes.
	 * 
	 * @exception IndexOutOfBoundsException
	 *                if entered node number argument is higher than 2, or if
	 *                the entered argument is less then 0
	 */
	@Override
	public BinaryNode getChild(int nodeNumber) throws IndexOutOfBoundsException {
		if (nodeNumber > 2) {
			throw new IndexOutOfBoundsException(
					"Entered parameter is out of bounds. Binary tree node has not more than 2 children.");
		}
		return (BinaryNode) nodes[nodeNumber];
	}

	public BinaryNode getLeftChild() {
		return (BinaryNode) getChild(LEFT_NODE);
	}

	public BinaryNode getRightChild() {
		return (BinaryNode) getChild(RIGHT_NODE);
	}

	// public void setChild(int nodeNumber, Node node) throws
	// IndexOutOfBoundsException{
	// if (nodeNumber > 2/*nodes.length*/) {
	// throw new
	// IndexOutOfBoundsException("Entered parameter is out of bounds. Binary tree node has not more than 2 children.");
	// }
	// nodes[nodeNumber] = node;
	// }

	public void setLeftChild(Node node) {
		nodes[LEFT_NODE] = node;
	}

	public void setRightChild(Node node) {
		nodes[RIGHT_NODE] = node;
	}

	@Override
	public Node getParent() {
		return parent;
	}

	@Override
	public NodeValue getValue() {
		return value;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
}
