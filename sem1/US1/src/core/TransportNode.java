package core;

import rb.RBNode;
import core.data.TransportProduct;

public class TransportNode extends RBNode<Integer> {

	public TransportNode(TransportProduct transport) {
		super();
		value = transport;
	}

	@Override
	public Integer getKey() {
		return ((TransportProduct) value).getPN();
	}

	@Override
	public TransportProduct getValue() {
		return (TransportProduct) super.getValue();
	}

}
