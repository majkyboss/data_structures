package rb;

public interface ItemWithKey<T> extends Comparable<T>{
	T getKey();
}