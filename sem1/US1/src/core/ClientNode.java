package core;

import core.data.Client;
import rb.RBNode;

public class ClientNode extends RBNode<String> {

	public ClientNode(Client clientValue) {
		super();
		setValue(clientValue);
	}

	@Override
	public String getKey() {
		return ((Client) getValue()).getId();
	}

}
