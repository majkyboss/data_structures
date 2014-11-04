package core;

import rb.RBNode;
import rb.RBTree;

public class Db {
	private int id;
	private RBTree<Integer> warehouses;
	private RBTree<Integer> itemsByProductNumber;

	public Db() {
		this.id = IdCounter.getNextId();
		this.warehouses = new RBTree<>();
		this.itemsByProductNumber = new RBTree<Integer>();
	}

	public void addItem(int whId, Product product) {
		RBNode<Integer> whNode = warehouses.find(whId);

	}

	public void addWareHouse(WareHouse warehouse) {
		boolean inserted = warehouses.insert(new RBNode<Integer>(warehouse) {

			@Override
			public Integer getKey() {
				return ((WareHouse) value).getId();
			}
		});
	}
}
