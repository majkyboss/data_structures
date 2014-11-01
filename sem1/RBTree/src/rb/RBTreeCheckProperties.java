package rb;

import java.util.HashMap;
import java.util.Iterator;

import core.NodeKey;

public class RBTreeCheckProperties {

	public static boolean checkProperties(RBNode treeRoot) {
		boolean rootProp = checkRoot(treeRoot);
		boolean redsChildren = checkRedNodesChildren(treeRoot);
		boolean blackHeight = checkBH(treeRoot);

		return rootProp && redsChildren && blackHeight;
	}

	private static boolean checkRoot(RBNode root) {
		if (root.getColor() != RBNode.COLOR_NODE_BLACK) {
			return false;
		}
		return true;
	}

	private static boolean checkRedNodesChildren(RBNode node) {
		RBNode problemNode = null;
		boolean ret = true;
		if (node != null) {
			if (node.getColor() == RBNode.COLOR_NODE_RED) {
				RBNode lc = node.getLeftChild();
				if (lc != null && lc.getColor() != RBNode.COLOR_NODE_BLACK) {
					ret = false;
					problemNode = lc;
				}
				RBNode rc = node.getRightChild();
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

	private static void inOrderTraverse(RBNode root) {
		if (root != null) {
			inOrderTraverse(root.getLeftChild());

			inOrderTraverse(root.getRightChild());
		}
	}

	private static HashMap<RBNode, Integer> calcBH(RBNode node, int counter, HashMap<RBNode, Integer> bh) {
		if (node != null) {
			if (node.getColor() == RBNode.COLOR_NODE_BLACK) {
				counter++;
			}
			RBNode left = node.getLeftChild();
			RBNode right = node.getRightChild();
			if (left == null && right == null) {
				bh.put(node, counter);
			} else {
				calcBH(left, counter, bh);
				calcBH(right, counter, bh);
			}
		}
		return bh;
	}

	private static boolean checkBH(RBNode treeRoot) {
		HashMap<RBNode, Integer> bhsTable = new HashMap<>();
		bhsTable = calcBH(treeRoot, 0, bhsTable);

		int bh = 0;
		Iterator iterator = bhsTable.keySet().iterator();
		bh = bhsTable.get(iterator.next());
		for (; iterator.hasNext();) {
			if (bhsTable.get(iterator.next()) != bh) {
				return false;
			}
		}
		return true;
	}
}