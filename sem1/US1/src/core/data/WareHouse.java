package core.data;

import java.util.Date;

import rb.RBNode;
import rb.RBTree;
import core.ClientNode;
import core.EanNode;
import core.TransportNode;

public class WareHouse extends ProductPlace {
	private int id;
	private RBTree<String> storedByEan;
	private RBTree<Integer> dispatchedByPN;
	private RBTree<String> clientsById;

	public WareHouse() {
		super();
		this.id = IdCounter.getNextId();
		this.storedByEan = new RBTree<String>();
		this.dispatchedByPN = new RBTree<Integer>();
		this.clientsById = new RBTree<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean addProduct(Product product) {
		// 1. try to find ean item
		// 2. if does not exist create new item
		//
		// 3. add product to item

		boolean retVal = false;

		// add product to exist ean group - add one lie in db
		RBNode<String> item = storedByEan.find(product.getEan());

		if (item == null) {
			// if the ean is not in db
			// insert new ean node
			EanNode newNode = new EanNode(product.getEan());

			item = newNode;
		}

		storedByEan.insert(item);

		if (item instanceof EanNode) {
			retVal = ((EanNode) item).addProduct(product);
		}
		if (retVal) {
			product.setEanTree(storedByEan);
		}

		return retVal;
	}

	public Client getClient(String id) {
		RBNode<String> clientNode = clientsById.find(id);
		if (clientNode != null && clientNode instanceof ClientNode) {
			Object client = clientNode.getValue();
			if (client != null && client instanceof Client) {
				return (Client) client;
			}
		}

		return null;
	}

	public boolean addClient(Client client) {
		return clientsById.insert(new ClientNode(client));
	}
	
	public RBTree<String> getStoredItemsByEan() {
		return storedByEan;
	}

	public RBTree<String> getClientsById() {
		return clientsById;
	}

	public RBTree<Integer> getDispatchedByPN() {
		return dispatchedByPN;
	}

	public TransportProduct getDispatchedItem(int productNumber) {
		RBNode<Integer> transpNode = dispatchedByPN.find(productNumber);
		if (transpNode != null && transpNode instanceof TransportNode) {
			return ((TransportNode) transpNode).getValue();
		}
		return null;
	}
}
