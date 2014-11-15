package core.data;

import rb.RBNode;
import rb.RBTree;
import core.ClientNode;
import core.EanNode;
import core.NodeValue;

public class WareHouse extends ProductPlace implements NodeValue {
	private int id;
	private RBTree<String> storedByEan;
	// private RBTree<Integer> dispatchedByPN;
	private RBTree<String> clientsById;

	public WareHouse() {
		super();
		this.id = IdCounter.getNextId();
		this.storedByEan = new RBTree<String>();
		// this.dispatchedByPN = new RBTree<Integer>();
		this.clientsById = new RBTree<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public Object getNodeValue() {
		return this;
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
			// RBTree<Date> itemsByDate = ((EanNode) item).getNodeValue();
			// the right command should be nodeToAdd.getValue().getNodeValue()
			// but because the EanNode implements NodeValue interface I can skip
			// it
			// TODO try to think to implement NodeValue into RBNode (not as an
			// attribute)
			retVal = ((EanNode) item).addProduct(product);
		}

		return retVal;
	}

	public RBTree<String> getTreeByEan() {
		return storedByEan;
	}

	public Client findClient(String id) {
		RBNode<String> clientNode = clientsById.find(id);
		if (clientNode != null && clientNode instanceof ClientNode) {
			NodeValue client = clientNode.getValue();
			if (client != null && client instanceof Client) {
				return (Client) client;
			}
		}

		return null;
	}
}
