package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import rb.RBTree;
import rb.RBTreeCheckProperties;
import core.IntegerNodeValue;
import core.NodeValue;

public class RBTreeTest {

	@Test
	public void test() {
		int itemsCount = (int) Math.pow(10, 5);
		// !!! 10^7 - out of memory, time: ~1600s
		// 10^6.5 - time: ~12s

		// fail("Not yet implemented");
		RBTree<Integer> tree = new RBTree<Integer>();

		for (int i = 0; i < itemsCount; i++) {
			// generate one item
			int key = (int) (Math.random() * itemsCount);
			//IntegerNodeKey treeKey = new IntegerNodeKey(key);
			IntegerNodeValue treeValue = new IntegerNodeValue(key);
			// insert generated item to RB tree
			tree.insert(new RBNodeItemByEan(treeValue));
		}

		RBTreeCheckProperties<Integer> checker = new RBTreeCheckProperties<>();
		boolean checked = checker.checkProperties(tree);

		assertTrue(checked);
	}

	@Test
	public void testNodes() {

		assertTrue(false);
	}

	class RBNodeItemByEan extends rb.RBNode<Integer>{

		public RBNodeItemByEan(NodeValue value) {
			super(value);
		}

		private int ean;

		@Override
		public Integer getKey() {
			return ean;
		}

	}
}
