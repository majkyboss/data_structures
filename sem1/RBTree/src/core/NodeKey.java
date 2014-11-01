package core;

public interface NodeKey extends Comparable<NodeKey> {

	/**
	 * used same as toString() ... most probably should to remove 
	 * @return
	 */
	Object getKeyValue();
}
