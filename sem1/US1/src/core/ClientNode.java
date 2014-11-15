package core;

import rb.RBNode;
import core.data.Client;

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
