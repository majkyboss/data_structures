package rb;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Implemented according to <a href=
 * "http://n00tc0d3r.blogspot.sk/2013/08/implement-iterator-for-binarytree-i-in.html"
 * >link (author: Sophie)</a>
 * 
 * @author Banik
 * 
 * @param <T>
 */
public class InOrderBinaryTreeIterator<T extends Comparable<T>> implements
		Iterator<RBNode<T>> {
	Stack<RBNode<T>> stack = new Stack<RBNode<T>>();
	T startKey = null;
	T stopKey = null;

	/** Constructor */
	public InOrderBinaryTreeIterator(RBNode<T> root, T startKey) {
		this.startKey = startKey;
		pushLeftChildren(root);
		
		// this.stopKey = stopKey;
	}

	/** Push node cur and all of its left children into stack */
	private void pushLeftChildren(RBNode<T> cur) {
		while (cur != null) {
			stack.push(cur);
			if (startKey != null && cur.getKey().equals(startKey)) {
				break;
			}
			cur = cur.getLeftChild();
		}
	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}

	public RBNode<T> next() {
		if (!hasNext()) {
			throw new NoSuchElementException("All nodes have been visited!");
		}

		RBNode<T> res = stack.pop();
//		if (res.getRightChild()==null && res.getParent()!=null) {
//			res = res.getParent();
//		}
		pushLeftChildren(res.getRightChild());

		return res;
	}

	public void remove() {
		throw new UnsupportedOperationException("remove() is not supported.");
	}
}