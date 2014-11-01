package core;

import rb.RBTree;

public class WareHouse implements NodeKey {
	private String name;
	private int id;
	private Address address;
	private RBTree stored;
	private RBTree dispatched;

	public int getId() {
		return id;
	}

	@Override
	public int compareTo(NodeKey arg0) {
		if (arg0 instanceof WareHouse) {
			if (this.id < ((WareHouse) arg0).getId()) {
				return -1;
			} else if (this.id > ((WareHouse) arg0).getId()) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public Object getKeyValue() {
		return id;
	}
}
