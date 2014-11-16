package core;

import core.data.TransportProduct;
import rb.RBNode;

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
