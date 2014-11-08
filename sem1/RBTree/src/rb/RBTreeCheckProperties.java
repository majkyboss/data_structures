package rb;

import java.util.HashMap;
import java.util.Iterator;

public class RBTreeCheckProperties<T extends Comparable<T>> {
	public RBTreeCheckProperties() {
		super();
	}

	public boolean checkProperties(RBTree<T> tree/* RBNode treeRoot */) {
		RBNode<T> treeRoot = tree.root;
		boolean rootProp = checkRoot(treeRoot);
//		if (!rootProp)
//			System.out.println("");
		boolean redsChildren = checkRedNodesChildren(treeRoot);
//		if (!redsChildren)
//			System.out.println("");
		boolean blackHeight = checkBH(treeRoot);
//		if (!blackHeight)
//			System.out.println("");

		return rootProp && redsChildren && blackHeight;
	}

	private boolean checkRoot(RBNode<T> root) {
		if (root.getColor() != RBNode.COLOR_NODE_BLACK) {
			return false;
		}
		return true;
	}

	private boolean checkRedNodesChildren(RBNode<T> node) {
		RBNode<T> problemNode = null;
		boolean ret = true;
		if (node != null) {
			if (node.getColor() == RBNode.COLOR_NODE_RED) {
				RBNode<T> lc = node.getLeftChild();
				if (lc != null && lc.getColor() != RBNode.COLOR_NODE_BLACK) {
					ret = false;
					problemNode = lc;
				}
				RBNode<T> rc = node.getRightChild();
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

	private void inOrderTraverse(RBNode<T> root) {
		if (root != null) {
			inOrderTraverse(root.getLeftChild());

			inOrderTraverse(root.getRightChild());
		}
	}

	private HashMap<RBNode<T>, Integer> calcBH(RBNode<T> node, int counter,
			HashMap<RBNode<T>, Integer> bh) {
		if (node != null) {
			if (node.getColor() == RBNode.COLOR_NODE_BLACK) {
				counter++;
			}
			RBNode<T> left = node.getLeftChild();
			RBNode<T> right = node.getRightChild();
			if (left == null && right == null) {
				bh.put(node, counter);
			} else {
				calcBH(left, counter, bh);
				calcBH(right, counter, bh);
			}
		}
		return bh;
	}

	private boolean checkBH(RBNode<T> treeRoot) {
		HashMap<RBNode<T>, Integer> bhsTable = new HashMap<>();
		bhsTable = calcBH(treeRoot, 0, bhsTable);

		int bh = 0;
		Iterator<RBNode<T>> iterator = bhsTable.keySet().iterator();
		bh = bhsTable.get(iterator.next());
		for (; iterator.hasNext();) {
			RBNode<T> node = iterator.next();
			if (bhsTable.get(node) != bh) {
				return false;
			}
		}
		return true;
	}
}