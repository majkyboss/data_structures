package test;




import org.junit.Assert;
import org.junit.Test;

import rb.RBNode;
import rb.RBTree;
import rb.RBTreeCheckProperties;
import core.IntegerNodeValue;
import core.NodeValue;

public class RBTreeTest {
	private static int maxNum = (int) Math.pow(10, 10);
	private RBTree<Integer> tree = new RBTree<Integer>();
	

	@Test
	public void testAdd() {
		int itemsCount = (int) Math.pow(10, 5);
		// !!! 10^7 - out of memory, time: ~1600s
		// 10^6.5 - time: ~12s

		tree = new RBTree<Integer>();

		int i = 0;
		for (; i < itemsCount;) {
			// generate one item
			int key = (int) (Math.random() * maxNum);
			// IntegerNodeKey treeKey = new IntegerNodeKey(key);
			IntegerNodeValue treeValue = new IntegerNodeValue(key);
			// insert generated item to RB tree
			boolean iserted = tree.insert(new RBNode<Integer>(treeValue) {

				@Override
				public Integer getKey() {
					return (Integer) value.getNodeValue();
				}
//
//				@Override
//				public void setKey(Integer key) {
//					new IntegerNodeValue(key);
//				}
			});
			if (iserted) {
				i++;
			}
		}

		RBTreeCheckProperties<Integer> checker = new RBTreeCheckProperties<>();
		boolean checked = checker.checkProperties(tree);
		
		Assert.assertTrue(checked);
		Assert.assertEquals(i, tree.size());
	}
	
	@Test
	public void testAddAndFind() {
		int itemsCount = (int) Math.pow(10, 5);
		// !!! 10^7 - out of memory, time: ~1600s
		// 10^6.5 - time: ~12s

		tree = new RBTree<Integer>();

		int i = 0;
		for (; i < itemsCount;) {
			// generate one item
			int key = (int) (Math.random() * maxNum);
			// IntegerNodeKey treeKey = new IntegerNodeKey(key);
			IntegerNodeValue treeValue = new IntegerNodeValue(key);
			// insert generated item to RB tree
			RBNode<Integer> item = new RBNode<Integer>(treeValue) {

				@Override
				public Integer getKey() {
					return (Integer) value.getNodeValue();
				}
//
//				@Override
//				public void setKey(Integer key) {
//					new IntegerNodeValue(key);
//				}
			};
			boolean iserted = tree.insert(item);
			if (iserted) {
				i++;
				RBNode<Integer> found = tree.find(key);
				Assert.assertEquals(item, found);
			}
		}

		RBTreeCheckProperties<Integer> checker = new RBTreeCheckProperties<>();
		boolean checked = checker.checkProperties(tree);
		
		Assert.assertTrue(checked);
		Assert.assertEquals(i, tree.size());
	}

	@Test
	public void testDel() {
		this.testAdd();

		int itemsCount = (int) Math.pow(10, 3);
		// !!! 10^7 - out of memory, time: ~1600s
		// 10^6.5 - time: ~12s

		for (int i = 0; i < itemsCount;) {
			// generate one item key
			int key = (int) (Math.random() * maxNum);
			// try to find the item
			RBNode<Integer> item = tree.find(key);
			//RBNode<Integer> parent = item.getParent();
			// try to delete item
			if (item != null) {
				tree.delete(item);
				i++;
				//System.out.print("deleted item with key " + item.getKey());
				//System.out.println("i is: " + i);
				RBTreeCheckProperties<Integer> checker = new RBTreeCheckProperties<>();
				boolean c = checker.checkProperties(tree);
				//System.out.println(c);
//				if (!c) {
//					System.out.println();
//				}
				Assert.assertTrue(c);
			}
		}

		RBTreeCheckProperties<Integer> checker = new RBTreeCheckProperties<>();
		boolean checked = checker.checkProperties(tree);

		Assert.assertTrue(checked);
	}

	private class RBNodeItemByEan extends rb.RBNode<Integer> {
		protected Product value;

		public RBNodeItemByEan(Product value) {
			super(value);
		}

		@Override
		public Integer getKey() {
			return value.getNodeValue();
		}

//		@Override
//		public void setKey(Integer key) {
//			value.ean = key;
//		}
	}

	private class Product implements NodeValue {
		private int ean;

		public Product(int ean) {
			super();
			this.ean = ean;
		}

		@Override
		public Integer getNodeValue() {
			return ean;
		}

	}
	
	@Test
	public void testOther(){
		String a = "aaba";
		String b = "aaab";
		
		System.out.println(a.compareTo(b));
	}
}
