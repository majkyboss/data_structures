package rb;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * <a href=
 * "http://n00tc0d3r.blogspot.sk/2013/08/implement-iterator-for-binarytree-i-in.html"
 * >source web page link</a>
 * 
 * @author Sophie
 * 
 * @param <T>
 */
public class InOrderBinaryTreeIterator<T extends Comparable<T>> implements
		Iterator<RBNode<T>> {
	Stack<RBNode<T>> stack = new Stack<RBNode<T>>();

	/** Push node cur and all of its left children into stack */
	private void pushLeftChildren(RBNode<T> cur) {
		while (cur != null) {
			stack.push(cur);
			cur = cur.getLeftChild();
		}
	}

	/** Constructor */
	public InOrderBinaryTreeIterator(RBNode<T> root) {
		pushLeftChildren(root);
	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}

	public RBNode<T> next() {
		if (!hasNext()) {
			throw new NoSuchElementException("All nodes have been visited!");
		}

		RBNode<T> res = stack.pop();
		pushLeftChildren(res.getRightChild());

		return res;
	}

	public void remove() {
		throw new UnsupportedOperationException("remove() is not supported.");
	}
}