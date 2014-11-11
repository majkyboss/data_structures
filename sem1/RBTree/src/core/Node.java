package core;

import java.util.ArrayList;

public interface Node {
	/**
	 * Gets all children nodes of this node.
	 * 
	 * @return all children nodes.
	 */
	ArrayList<Node> getChildren();

	/**
	 * Gets the children node according to entered node number.
	 * 
	 * @param nodeNumber
	 *            children node number of this node
	 * @return children node
	 * @exception IndexOutOfBoundsException
	 *                if entered node number argument is higher than children
	 *                nodes count, or if the entered argument is less then 0
	 */
	Node getChild(int nodeNumber) throws IndexOutOfBoundsException;

	Node getParent();

	Object getValue();
}
