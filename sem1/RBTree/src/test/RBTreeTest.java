package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.IntegerNodeKey;
import core.IntegerNodeValue;
import core.NodeKey;
import core.NodeValue;
import rb.RBNode;
import rb.RBTree;
import rb.RBTreeCheckProperties;

public class RBTreeTest {

	@Test
	public void test() {
		int itemsCount = (int) Math.pow(10, 5);
		// !!! 10^7 - out of memory, time: ~1600s
		// 10^6.5 - time: ~12s

		// fail("Not yet implemented");
		RBTree tree = new RBTree();

		for (int i = 0; i < itemsCount; i++) {
			// generate one item
			int key = (int) (Math.random() * itemsCount);
			IntegerNodeKey treeKey = new IntegerNodeKey(key);
			IntegerNodeValue treeValue = new IntegerNodeValue(key);
			// insert generated item to RB tree
			tree.insert(new RBNode(treeKey, treeValue));
		}

		boolean checked = RBTreeCheckProperties.checkProperties(tree.getRoot());

		assertTrue(checked);
	}
	
	@Test
	public void testNodes() {
		
		

		assertTrue(false);
	}
	
	class RBNodeItemByEan extends rb.abst.RBNode<RBNodeItemByEan> implements Comparable<RBNodeItemByEan>{
		private int ean; 
		
		public RBNodeItemByEan(NodeKey key, NodeValue value) {
			super(key, value);
			// TODO Auto-generated constructor stub
		}
		
		public RBNodeItemByEan(int ean){
			super(null, null);
			this.ean = ean;
		}

		@Override
		public int compareTo(RBNodeItemByEan arg0) {
			// TODO Auto-generated method stub
			return 0;
		}		
	}
}
