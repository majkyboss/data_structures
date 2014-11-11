package rb;

/**
 * Inteface ensures the opportunity for comparing with same object
 * 
 * @author Banik
 * @param <T>
 */
public interface NodeCompare<T extends Comparable<T>> extends Comparable<T> {
	int compareTo(NodeCompare<T> node);

	// @Override
	// int compareTo(T key);
	T getKey();
}
