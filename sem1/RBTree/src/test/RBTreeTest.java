package test;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import rb.RBNode;
import rb.RBTree;
import rb.RBTreeCheckProperties;

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
			// insert generated item to RB tree
			RBNode<Integer> node = new IntegerNode(key);
			boolean iserted = tree.insert(node);
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
			// insert generated item to RB tree
			RBNode<Integer> item = new IntegerNode(key);
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

		System.out.println("longer test started: testDel()");

		int itemsCount = (int) Math.pow(10, 5);
		// !!! 10^7 - out of memory
		// 10^5 - time: ~16s
		// 10^6 - time: ~4853s

		LinkedList<Integer> addedItems = new LinkedList<>();

		int i = 0;
		for (; i < itemsCount;) {
			// generate one item
			int key = (int) (Math.random() * maxNum);
			// insert generated item to RB tree
			RBNode<Integer> node = new IntegerNode(key);
			boolean iserted = tree.insert(node);
			if (iserted) {
				i++;
				addedItems.add(key);
			}
		}

		for (i = 0; i < itemsCount;) {
			// generate one item key
			int index = (int) (Math.random() * addedItems.size());
			int key = addedItems.get(index);
			addedItems.remove(index);
			// int key = (int) (Math.random() * maxNum);
			// try to find the item
			RBNode<Integer> item = tree.find(key);
			// RBNode<Integer> parent = item.getParent();
			// try to delete item
			if (item != null) {
				tree.delete(item);
				i++;
				// if (i % 100 == 0) {
				// System.out.print("deleted item with key " + item.getKey());
				// System.out.println("i is: " + i);
				// }

				RBTreeCheckProperties<Integer> checker = new RBTreeCheckProperties<>();
				if (i % 1000 == 0) {
					boolean c = checker.checkProperties(tree);
					Assert.assertTrue(c);
					// System.out.println(i + " " + c);
				}
				// if (!c) {
				// System.out.println();
				// }

			}
		}

		RBTreeCheckProperties<Integer> checker = new RBTreeCheckProperties<>();
		boolean checked = checker.checkProperties(tree);

		Assert.assertTrue(checked);
		System.out.println("longer test ended: testDel()");
	}

	// testing data classes
	// private class RBNodeItemByEan extends rb.RBNode<Integer> {
	// protected Product value;
	//
	// public RBNodeItemByEan(Product value) {
	// super(value);
	// }
	//
	// @Override
	// public Integer getKey() {
	// return value.getNodeValue();
	// }
	//
	// // @Override
	// // public void setKey(Integer key) {
	// // value.ean = key;
	// // }
	// }
	//
	// private class Product implements NodeValue {
	// private int ean;
	//
	// public Product(int ean) {
	// super();
	// this.ean = ean;
	// }
	//
	// @Override
	// public Integer getNodeValue() {
	// return ean;
	// }
	//
	// }

	@Test
	public void testGradually() {
		int itemsCount = (int) Math.pow(10, 5);

		LinkedList<Integer> addedItems = new LinkedList<>();

		int i = 0;
		for (; i < itemsCount;) {
			// generate one item
			int key = i;

			// insert generated item to RB tree
			RBNode<Integer> node = new IntegerNode(key);
			boolean iserted = tree.insert(node);
			if (iserted) {
				i++;
				addedItems.add(key);
			}
		}

		for (i = 0; i < itemsCount;) {
			// generate one item key
			int index = 0;
			int key = addedItems.get(index);
			addedItems.remove(index);
			// int key = (int) (Math.random() * maxNum);
			// try to find the item
			RBNode<Integer> item = tree.find(key);
			// try to delete item
			if (item != null) {
				tree.delete(item);
				i++;

				RBTreeCheckProperties<Integer> checker = new RBTreeCheckProperties<>();
				if (i % 1000 == 0) {
					boolean c = checker.checkProperties(tree);
					Assert.assertTrue(c);
				}
			}
		}

		RBTreeCheckProperties<Integer> checker = new RBTreeCheckProperties<>();
		boolean checked = checker.checkProperties(tree);

		Assert.assertTrue(checked);
	}
}
