package rb;

import core.NodeValue;

/**
 * Class represents Red-Black tree structure. Works only with Red-Black nodes {@link RBNode<T>}. <br>
 * T is key type
 * 
 * @author Banik
 */
public class RBTree<T extends Comparable<T>> {
	protected static final int INDENT_STEP = 6;

	@SuppressWarnings("rawtypes")
	private static void printHelper(RBNode n, int indent) {
		if (n == null) {
			System.out.print("<empty tree>");
			return;
		}
		if (n.getRightChild() != null) {
			printHelper(n.getRightChild(), indent + INDENT_STEP);
		}
		for (int i = 0; i < indent; i++)
			System.out.print(" ");
		if (n.getColor() == RBNode.COLOR_NODE_BLACK)
			System.out.println(n);
		else
			System.out.println("<" + n + ">");
		if (n.getLeftChild() != null) {
			printHelper(n.getLeftChild(), indent + INDENT_STEP);
		}
	}

	protected RBNode<T> root = null;

	/**
	 * 
	 * <pre>
	 *       6
	 *     4___7
	 *   2___5
	 * 1___3
	 * topOfRotation - 6
	 * centerOfRotation - 4
	 *       4
	 *   2_______6
	 * 1___3   5___7
	 * 
	 * In literature the same operation is RR(6) but
	 * they call it rotate around node 6
	 * <a href="http://forrestyu.net/art/red-black-tree-tutorial/#rotation">http://forrestyu.net/art/red-black-tree-tutorial/#rotation</a>
	 * 
	 * <pre>
	 * 
	 * @param topOfRotation
	 */
	private void rightRotation(RBNode<T> topOfRotation) {
		RBNode<T> centerOfRotaion = topOfRotation.getLeftChild();
		centerOfRotaion.setParent(topOfRotation.getParent());
		if (topOfRotation.getParent() != null) {
			RBNode<T> yParent = ((RBNode<T>) topOfRotation.getParent());
			if (yParent != null && topOfRotation.equals(yParent.getLeftChild())) {
				yParent.setLeftChild(centerOfRotaion);
			} else {
				yParent.setRightChild(centerOfRotaion);
			}
		} else {
			root = centerOfRotaion;
		}
		topOfRotation.setParent(centerOfRotaion);
		topOfRotation.setLeftChild(centerOfRotaion.getRightChild());
		if (centerOfRotaion.getRightChild() != null)
			centerOfRotaion.getRightChild().setParent(topOfRotation);
		centerOfRotaion.setRightChild(topOfRotation);
	}

	/**
	 * 
	 * <pre>
	 *   2
	 * 1___4
	 *   3___6
	 *     5___7
	 * topOfRotation - 2
	 * centerOfRotation - 4
	 *       4
	 *   2_______6
	 * 1___3   5___7
	 * 
	 * In literature the same operation is RR(6) but
	 * they call it rotate around node 6
	 * <a href="http://forrestyu.net/art/red-black-tree-tutorial/#rotation">http://forrestyu.net/art/red-black-tree-tutorial/#rotation</a>
	 * 
	 * <pre>
	 * 
	 * @param topOfRotation
	 */
	private void leftRotation(RBNode<T> topOfRotation/* topOfRotation */) {
		RBNode<T> centerOfRotaion = topOfRotation.getRightChild();
		centerOfRotaion.setParent(topOfRotation.getParent());
		if (topOfRotation.getParent() != null) {
			RBNode<T> yParent = ((RBNode<T>) topOfRotation.getParent());
			if (yParent != null && topOfRotation.equals(yParent.getRightChild())) {
				yParent.setRightChild(centerOfRotaion);
			} else {
				yParent.setLeftChild(centerOfRotaion);
			}
		} else {
			root = centerOfRotaion;
		}
		topOfRotation.setParent(centerOfRotaion);
		topOfRotation.setRightChild(centerOfRotaion.getLeftChild());
		if (centerOfRotaion.getLeftChild() != null) {
			centerOfRotaion.getLeftChild().setParent(topOfRotation);
		}
		centerOfRotaion.setLeftChild(topOfRotation);
	}

	public boolean insert(RBNode<T> newNode) {
		// if item with same key is already inserted then do not insert the item
		RBNode<T> foundItem = find(newNode.getKey());
		if (foundItem != null) {
			return false;
		}

		RBNode<T> x = root;
		RBNode<T> parent = null;
		while (x != null) {
			parent = x;
			if (newNode.compareTo(x) < 0) {
				x = (RBNode<T>) x.getLeftChild();
			} else {
				x = (RBNode<T>) x.getRightChild();
			}
		}
		newNode.setParent(parent);
		if (parent == null) {
			root = newNode;
		} else if (newNode.compareTo(parent) < 0) {
			parent.setLeftChild(newNode);
		} else {
			parent.setRightChild(newNode);
		}
		newNode.setLeftChild(null);
		newNode.setRightChild(null);
		newNode.setColor(RBNode.COLOR_NODE_RED);
		insertFixUp(newNode);
		// System.out.print("");
		return true;
	}

	private void insertFixUp(RBNode<T> z) {
		//@f:off
//		while color[p[z]] = RED
//			    do if p[z] = left[p[p[z]]]
//			          then y <- right[p[p[z]]]
//			                  if color[y] = RED
//			                      then color[p[z]] <- BLACK  // Case 1
//			                              color[y] <- BLACK       // Case 1
//			                              color[p[p[z]]] <- RED   // Case 1
//			                              z <- p[p[z]]                    // Case 1
//                                else if z = right[p[z]]  // color[y] != RED
//                                        then z <- p[z]                           // Case 2
//                                                LEFT-ROTATE(T, z)      // Case 2
//                                        color[p[z]] <- BLACK            // Case 3 // !!! p[z] based on actual changed z in case 2
//                                        color[p[p[z]]] <- RED             // Case 3
//                                        RIGHT-ROTATE(T, p[p[z]])  // Case 3
//                    else (if p[z] = right[p[p[z]]])(same as 10-14
//                                     with “right” and “left” exchanged)
//      color[root[T ]] <- BLACK
		//@f:on
		// @f:on

		RBNode<T> pZ = (RBNode<T>) z.getParent();
		while (pZ != null && pZ.getColor() == RBNode.COLOR_NODE_RED) {
			if (pZ.equals(root)) {
				pZ.setColor(RBNode.COLOR_NODE_BLACK);
			}

			RBNode<T> ppZ = (RBNode<T>) z.getParent().getParent();
			if (ppZ != null) { // added for null object checking. both branches
								// had same condition
				if (pZ.equals(ppZ.getLeftChild())) {
					RBNode<T> y = ppZ.getRightChild();
					if (y != null && y.getColor() == RBNode.COLOR_NODE_RED) {
						pZ.setColor(RBNode.COLOR_NODE_BLACK);
						y.setColor(RBNode.COLOR_NODE_BLACK);
						ppZ.setColor(RBNode.COLOR_NODE_RED);
						z = ppZ;
					} else {
						if (z.equals(pZ.getRightChild())) {
							z = pZ;
							leftRotation(z);
							pZ = (RBNode<T>) z.getParent();
						}
						pZ.setColor(RBNode.COLOR_NODE_BLACK);
						ppZ.setColor(RBNode.COLOR_NODE_RED);
						rightRotation(ppZ);
					}
				} else if (pZ.equals(ppZ.getRightChild())) {
					RBNode<T> y = ppZ.getLeftChild();
					if (y != null && y.getColor() == RBNode.COLOR_NODE_RED) {
						pZ.setColor(RBNode.COLOR_NODE_BLACK);
						y.setColor(RBNode.COLOR_NODE_BLACK);
						ppZ.setColor(RBNode.COLOR_NODE_RED);
						z = ppZ;
					} else {
						if (z.equals(pZ.getLeftChild())) {
							z = pZ;
							rightRotation(z);
							pZ = (RBNode<T>) z.getParent();
						}
						pZ.setColor(RBNode.COLOR_NODE_BLACK);
						ppZ.setColor(RBNode.COLOR_NODE_RED);
						leftRotation(ppZ);
					}
				}
			}
			pZ = (RBNode<T>) z.getParent();

		}
		root.setColor(RBNode.COLOR_NODE_BLACK);
	}

	public RBNode<T> delete(RBNode<T> z) {
		RBNode<T> leftChild = z.getLeftChild();
		RBNode<T> rightChild = z.getRightChild();
		RBNode<T> zParent = z.getParent();

		if (leftChild == null && rightChild == null) { // 1. if node is leaf
			// if zParent is null then z was root node
			if (zParent == null) {
				root = null;
				z.setParent(null);
			} else {
				boolean zWasLeft = z.equals(zParent.getLeftChild());

				zParent.deleteChild(z);
				z.setParent(null);
				if (z.getColor() == RBNode.COLOR_NODE_BLACK) { // 1.1 if leaf was black fix up the tree

					deleteFixUp(z, zParent, zWasLeft);
				}
			}
			return z;

		} else if (leftChild != null && rightChild != null) { // 3. if the z node has two children
			RBNode<T> successor = treeSuccessor(z);

			// replaceNode(z, successor);
			// replace z node by successor
			NodeValue zValue = z.getValue();
			// T zKey = z.getKey();
			// int zColor = z.getColor();
			z.setValue(successor.getValue());
			// z.setColor(successor.getColor());
			successor.setValue(zValue);
			// successor.setColor(zColor);

			// RBNode<T> successorRightChild = successor.getRightChild();

			RBNode<T> returnObject = delete(successor);

			// set color of successor child
			// int oldSuccessorColor = successor.getColor();
			// if (successorRightChild != null) {
			// successorRightChild.setColor(successor.getColor());
			// successor.setColor(z.getColor());
			// }

			// maybe we do not need this
			// because we changed the color of successor child to successor color
			// if (oldSuccessorColor == RBNode.COLOR_NODE_BLACK) {
			// boolean zWasLeft = (zParent == null) ? false : z.equals(zParent.getLeftChild());
			//
			// deleteFixUp(z, zParent, zWasLeft);
			// }
			return returnObject;

		} else {/* if (leftChild != null || rightChild != null) */// 2. if node has only one child
			RBNode<T> child = null;
			if (leftChild != null) { // if node has only left child
				child = leftChild;
				z.setLeftChild(null);
			} else { // if node has only right child
				child = rightChild;
				z.setRightChild(null);
			}

			if (zParent == null) {
				root = child;
			} else if (zParent.getLeftChild().equals(z)) { // if z was left child
				zParent.setLeftChild(child);
			} else { /* if (zParent.getRightChild().equals(z)) */// if z was right child
				zParent.setRightChild(child);
			}

			child.setParent(zParent);
			z.setParent(null);

			child.setColor(z.getColor());

			return z;
		}
	}

	private RBNode<T> treeSuccessor(RBNode<T> node) {

		RBNode<T> succ;
		if ((succ = node.getRightChild()) != null) {
			// ak ma vrchol pravy podstrom tak najdeme minimum v tomto podstrome
			while (succ.getLeftChild() != null) {
				succ = succ.getLeftChild();
			}
		} else {
			// ak vrchol nema pravy podstrom tak hladame najblizsieho rodica
			// (najblizsieho podla hodnoty kluca)
			// prechadzame rodicov az do kym nejaky rodic vrcholu "node" je lavy
			// syn
			RBNode<T> x = node;
			succ = (RBNode<T>) x.getParent();
			while (succ != null && x.equals(succ.getRightChild())) {
				x = succ;
				succ = (RBNode<T>) x.getParent();
			}

			// // for version
			// for (succ = (RBNode<T>) node.getParent();
			// node.equals(succ.getRightChild()); succ = (RBNode<T>)
			// succ.getParent()) {
			// node = succ;
			// }
		}

		return succ;
	}

	private void deleteFixUp(RBNode<T> z, RBNode<T> zParent, boolean zWasLeft) {
		while (zParent != null) { // repeat until the checking will grow up to the root of tree

			if (zWasLeft) { // if z was left child of its parent
				RBNode<T> zBrother = zParent.getRightChild();
				RBNode<T> broLeftChild = zBrother.getLeftChild();
				RBNode<T> broRightChild = zBrother.getRightChild();

				if (zBrother.getColor() == RBNode.COLOR_NODE_BLACK) { // if brother of z node has black color
					if ((broLeftChild == null || broLeftChild.getColor() == RBNode.COLOR_NODE_BLACK) && (broRightChild == null || broRightChild.getColor() == RBNode.COLOR_NODE_BLACK)) {
						// case 1 // and if broher's children is black too
						zBrother.setColor(RBNode.COLOR_NODE_RED);
						int zParentOldColor = zParent.getColor();
						zParent.setColor(RBNode.COLOR_NODE_BLACK);

						if (zParentOldColor == RBNode.COLOR_NODE_BLACK) { // if color of parent was already black
							// continue to higher level of tree
							z = zParent;
							zParent = zParent.getParent();
							if (z.equals(root)) {
								continue;
							}
							if (zParent == null || z == null || zParent.getLeftChild() == null || zParent.getRightChild() == null) {
								System.out.println("");
							}
							if (z.equals(zParent.getLeftChild())) {
								zBrother = zParent.getRightChild();
								zWasLeft = true;
							} else {
								zBrother = zParent.getLeftChild();
								zWasLeft = false;
							}
							continue;
						} else {
							break;
						}
					} else { // if both brother's children are not black
						if (broRightChild != null && broRightChild.getColor() == RBNode.COLOR_NODE_RED) { // case 2 // and if brother's right child is red
							broRightChild.setColor(RBNode.COLOR_NODE_BLACK);
							zBrother.setColor(zParent.getColor());
							zParent.setColor(RBNode.COLOR_NODE_BLACK);
							leftRotation(zParent);
							break;
						} else if ((broRightChild == null || broRightChild.getColor() == RBNode.COLOR_NODE_BLACK) && (broLeftChild.getColor() == RBNode.COLOR_NODE_RED)) {
							// case 3 // and if brother's right child is black and left child is red broRightChild may be null because null nodes of RB tree are
							// black "nil"
							zBrother.setColor(RBNode.COLOR_NODE_RED);
							broLeftChild.setColor(RBNode.COLOR_NODE_BLACK);
							rightRotation(zBrother);
							// go to case 2
							// TODO modify this - create consequence
							zParent = zBrother.getParent().getParent();
							continue;
						}
					}
				} else if (zBrother.getColor() == RBNode.COLOR_NODE_RED) { // case 4 // if brother of z node is red
					@SuppressWarnings("unused")
					boolean shouldBeTrue = zParent.getColor() == RBNode.COLOR_NODE_BLACK;
					zParent.setColor(RBNode.COLOR_NODE_RED);
					zBrother.setColor(RBNode.COLOR_NODE_BLACK);
					leftRotation(zParent);
					// continue to higher level of tree
					// z = zParent;
					// zParent = zParent.getParent();
					// if (z.equals(zParent.getLeftChild())) {
					// zBrother = zParent.getRightChild();
					// zWasLeft = true;
					// } else {
					// zBrother = zParent.getLeftChild();
					// zWasLeft = false;
					// }
					continue;
				}

			} else if (!zWasLeft) {
				RBNode<T> zBrother = zParent.getLeftChild();
				RBNode<T> broLeftChild = zBrother.getLeftChild();
				RBNode<T> broRightChild = zBrother.getRightChild();

				if (zBrother.getColor() == RBNode.COLOR_NODE_BLACK) { // if brother of z node has black color
					if ((broLeftChild == null || broLeftChild.getColor() == RBNode.COLOR_NODE_BLACK) && (broRightChild == null || broRightChild.getColor() == RBNode.COLOR_NODE_BLACK)) {
						// case 1 // and if broher's children is black too
						zBrother.setColor(RBNode.COLOR_NODE_RED);
						int zParentOldColor = zParent.getColor();
						zParent.setColor(RBNode.COLOR_NODE_BLACK);

						if (zParentOldColor == RBNode.COLOR_NODE_BLACK) { // if color of parent was already black
							// continue to higher level of tree
							z = zParent;
							zParent = zParent.getParent();
							if (z.equals(root)) {
								continue;
							}
							if (zParent == null || z == null || zParent.getLeftChild() == null || zParent.getRightChild() == null) {
								System.out.println("");
							}
							if (z.equals(zParent.getLeftChild())) {
								zBrother = zParent.getRightChild();
								zWasLeft = true;
							} else {
								zBrother = zParent.getLeftChild();
								zWasLeft = false;
							}
							continue;
						} else {
							break;
						}
					} else // if both brother's children are not black
					if (broLeftChild != null && broLeftChild.getColor() == RBNode.COLOR_NODE_RED) { // case 2 // and if brother's left child is red
						broLeftChild.setColor(RBNode.COLOR_NODE_BLACK);
						zBrother.setColor(zParent.getColor());
						zParent.setColor(RBNode.COLOR_NODE_BLACK);
						rightRotation(zParent);
						break;
					} else if ((broLeftChild == null || broLeftChild.getColor() == RBNode.COLOR_NODE_BLACK) && broRightChild.getColor() == RBNode.COLOR_NODE_RED) {
						// case 3 // and if brother's left child is black and right child is red broRightChild may be null because null nodes of RB tree are
						// black "nil"
						zBrother.setColor(RBNode.COLOR_NODE_RED);
						broRightChild.setColor(RBNode.COLOR_NODE_BLACK);
						leftRotation(zBrother);
						// go to case 2
						// modify this - create consequence
						zParent = zBrother.getParent().getParent();
						continue;
					}
				} else if (zBrother.getColor() == RBNode.COLOR_NODE_RED) { // case 4 // if brother of z node is red
					@SuppressWarnings("unused")
					boolean shouldBeTrue = zParent.getColor() == RBNode.COLOR_NODE_BLACK;
					zParent.setColor(RBNode.COLOR_NODE_RED);
					zBrother.setColor(RBNode.COLOR_NODE_BLACK);
					rightRotation(zParent);
					// continue to higher level of tree
					// z = zParent;
					// zParent = zParent.getParent();
					// if (z.equals(zParent.getLeftChild())) {
					// zBrother = zParent.getRightChild();
					// zWasLeft = true;
					// } else {
					// zBrother = zParent.getLeftChild();
					// zWasLeft = false;
					// }
					continue;
				}
			}
		}

	}

	public RBNode<T> find(T key) {
		RBNode<T> x = root;

		while (x != null && x.compareTo(key) != 0) {
			if (x.compareTo(key) > 0) {
				x = x.getLeftChild();
			} else {
				x = x.getRightChild();
			}
		}

		return x;
	}

	public int size() {
		return size(root);
	}

	private int size(RBNode<T> node) {
		int size = 0;
		if (node != null) {
			size++;
			size += size(node.getLeftChild());
			size += size(node.getRightChild());
		}
		return size;
	}

	public void print() {
		inOrderTraverse(root);
	}

	public void inOrderTraverse(RBNode<T> root) {
		if (root != null) {
			inOrderTraverse(root.getLeftChild());
			System.out.println("  " + root + " " + (root.getColor() == RBNode.COLOR_NODE_BLACK ? "Black" : root.getColor() == RBNode.COLOR_NODE_RED ? "Red" : "") + (root.equals(this.root) ? " (root)" : ""));
			inOrderTraverse(root.getRightChild());
		}
	}

	public void printBetter() {
		printHelper(root, 0);
	}

	public RBNode<T> getRoot() {
		return root;
	}
}
