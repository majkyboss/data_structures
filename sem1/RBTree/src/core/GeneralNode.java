package core;

import java.util.ArrayList;

public class GeneralNode implements Node {
	protected ArrayList<Node> nodes;
	private Node parent;
	private Object key;
	private Object value;
	
	public GeneralNode() {
		nodes = new ArrayList<>();
	}
	
	public GeneralNode(Object key, Object value){
		nodes = new ArrayList<>();
		this.key = key;
		this.value = value;
	}

	@Override
	public ArrayList<Node> getChildren() {
		return nodes;
	}

	@Override
	public Node getChild(int nodeNumber) throws IndexOutOfBoundsException {
		if (nodeNumber > nodes.size()) {
			throw new IndexOutOfBoundsException("Tree node has less children than entered parameter");
		}
		return nodes.get(nodeNumber);
	}

	@Override
	public Node getParent() {
		return parent;
	}

	public Object getKey() {
		return key;
	}

	@Override
	public Object getValue() {
		return value;
	}

}
