package core;

import rb.RBNode;
import rb.RBTree;

public class Db {
	private int id;
	private RBTree<Integer> warehouses;
	public Db() {
		this.id = IdCounter.getNextId();
		this.warehouses = new RBTree<>();
	}

	public void addItem(int whId, Product product) {
		RBNode<Integer> whNode = warehouses.find(whId);

	}
}
