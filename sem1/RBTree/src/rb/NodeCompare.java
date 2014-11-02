package rb;

public interface NodeCompare<T extends Comparable<T>> extends Comparable<T>{
	int compareTo(NodeCompare<T> node);
	//@Override
	//int compareTo(T key);
	T getKey();
}
