package rb;

import java.util.HashMap;
import java.util.Iterator;

public class RBTreeCheckProperties<T extends Comparable<T>, V> {
	public RBTreeCheckProperties() {
		super();
	}

	public boolean checkProperties(RBTree<T,V> tree/* RBNode treeRoot */) {
		RBNode<T,V> treeRoot = tree.root;
		boolean rootProp = checkRoot(treeRoot);
		// if (!rootProp)
		// System.out.println("");
		boolean redsChildren = checkRedNodesChildren(treeRoot);
		// if (!redsChildren)
		// System.out.println("");
		boolean blackHeight = checkBH(treeRoot);
		// if (!blackHeight)
		// System.out.println("");

		return rootProp && redsChildren && blackHeight;
	}

	private boolean checkRoot(RBNode<T,V> root) {
		if (root != null && root.getColor() != RBNode.COLOR_NODE_BLACK) {
			return false;
		}
		return true;
	}

	private boolean checkRedNodesChildren(RBNode<T,V> node) {
		@SuppressWarnings("unused")
		RBNode<T,V> problemNode = null;
		boolean ret = true;
		if (node != null) {
			if (node.getColor() == RBNode.COLOR_NODE_RED) {
				RBNode<T,V> lc = node.getLeftChild();
				if (lc != null && lc.getColor() != RBNode.COLOR_NODE_BLACK) {
					ret = false;
					problemNode = lc;
				}
				RBNode<T,V> rc = node.getRightChild();
				if (rc != null && rc.getColor() != RBNode.COLOR_NODE_BLACK) {
					ret = false;
					problemNode = rc;
				}
			}

			ret = checkRedNodesChildren(node.getLeftChild());
			ret = checkRedNodesChildren(node.getRightChild());
		}

		return ret;
	}

	// private void inOrderTraverse(RBNode<T,V> root) {
	// if (root != null) {
	// inOrderTraverse(root.getLeftChild());
	//
	// inOrderTraverse(root.getRightChild());
	// }
	// }

	private HashMap<RBNode<T,V>, Integer> calcBH(RBNode<T,V> node, int counter, HashMap<RBNode<T,V>, Integer> bh) {
		if (node != null) {
			if (node.getColor() == RBNode.COLOR_NODE_BLACK) {
				counter++;
			}
			RBNode<T,V> left = node.getLeftChild();
			RBNode<T,V> right = node.getRightChild();
			if (left == null && right == null) {
				bh.put(node, counter);
			} else {
				calcBH(left, counter, bh);
				calcBH(right, counter, bh);
			}
		}
		return bh;
	}

	private boolean checkBH(RBNode<T,V> treeRoot) {
		HashMap<RBNode<T,V>, Integer> bhsTable = new HashMap<>();
		bhsTable = calcBH(treeRoot, 0, bhsTable);

		if (bhsTable.isEmpty()) {
			return true;
		}

		int bh = 0;
		Iterator<RBNode<T,V>> iterator = bhsTable.keySet().iterator();
		bh = bhsTable.get(iterator.next());
		for (; iterator.hasNext();) {
			RBNode<T,V> node = iterator.next();
			if (bhsTable.get(node) != bh) {
				return false;
			}
		}
		return true;
	}
}