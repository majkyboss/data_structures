package core;

import rb.RBNode;
import core.data.WareHouse;

public class WareHouseNode extends RBNode<Integer> {

	public WareHouseNode(WareHouse wh) {
		super();
		value = wh;
	}

	@Override
	public Integer getKey() {
		return ((WareHouse) value).getId();
	}
}
