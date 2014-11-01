package core;

import rb.RBTree;

public class Db {
	private int id;
	private RBTree warehouses;
	public Db() {
		this.id = IdCounter.getNextId();
		this.warehouses = new RBTree();
	}

	
}
