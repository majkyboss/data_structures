package showTree;

import java.awt.Graphics;
import java.awt.HeadlessException;
import java.util.HashMap;

import javax.swing.JFrame;

import rb.RBNode;
import rb.RBTree;

public class DisplayBinaryTree extends JFrame {
	private RBTree tree;
	private HashMap<RBNode, NodePosition> nodePositions;

	public DisplayBinaryTree(RBTree tree) throws HeadlessException {
		super();
		this.tree = tree;
	}

	@Override
	public void paint(Graphics arg0) {
		super.paint(arg0);
		if (tree != null) {

		}
	}

	class NodePosition {
		public int leftOffset = 0;
		public int topOffset = 0;
	}

	public int inOrderTraverse(RBNode root) {
		if (root != null) {
			
			int a = inOrderTraverse(root.getLeftChild());
			int b = inOrderTraverse(root.getRightChild());
			return a > b ? a : b;
		}
		return 0;
	}
}
