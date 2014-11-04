package rb;

/**
 * Class represents Red-Black tree structure. Works only with Red-Black nodes
 * {@link RBNode<T>}. <br>
 * T is key type
 * 
 * @author Banik
 */
public class RBTree<T extends Comparable<T>> {
	protected static final int INDENT_STEP = 6;

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
	//TODO repair delete - fix up cycle do not stop

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

	private void leftRotation(RBNode<T> topOfRotation/* topOfRotation */) {
		RBNode<T> centerOfRotaion = topOfRotation.getRightChild();
		centerOfRotaion.setParent(topOfRotation.getParent());
		if (topOfRotation.getParent() != null) {
			RBNode<T> yParent = ((RBNode<T>) topOfRotation.getParent());
			if (yParent != null
					&& topOfRotation.equals(yParent.getRightChild())) {
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

	public void delete(RBNode<T> z) {
		// if left[z] = nil or right[z] = nil
		// then y ← z
		// else y ← BST-Successor(z)
		// //✄ y is the node that’s actually removed.
		// //✄ Here y does not have two children.
		// if left[y] 6= nil
		// then x ← left[y]
		// else x ← right[y]
		// //✄ x is the node that’s moving to y’s position.
		// if x 6= nil then p[x] ← p[y]
		// //✄ p[x] is reset If x isn’t NIL.
		// //✄ Resetting is unnecessary if x is NIL.
		// if p[y] = nil then root[T] ← x
		// //✄ If y is the root, then x becomes the root.
		// //✄ Otherwise, do the following.
		// else if y = left[p[y]]
		// then left[p[y]] ← x
		// //✄ If y is the left child of its parent, then
		// //✄ Set the parent’s left child to x.
		// else right[p[y]] ← x
		// //✄ If y is the right child of its parent, then
		// //✄ Set the parent’s right child to x.
		// if y 6= z then
		// { key[z] ← key[y]
		// Move other data from y to z }
		// return (y)

		RBNode<T> y;
		if (z.getLeftChild() == null || z.getRightChild() == null)
			y = z;
		else
			// if z node has some child then the successor will be from one of
			// the subtree
			y = treeSuccessor(z);

		// y is the node that is actually removing
		// now the y node does not have two children

		RBNode<T> x;
		boolean xIsLeftChild = false;
		boolean xIsRightChild = false;
		if (y.getLeftChild() != null) {
			x = y.getLeftChild();
		} else {
			x = y.getRightChild();
		}
		// x node is the node that is moving to y's position
		RBNode<T> yP;
		// pre zachovanie ref na rodica vymazavaneho prvku
		RBNode<T> xP = null;
		if ((yP = (RBNode<T>) y.getParent()) == null)
			// if yP is null then y was root and the its child will be new root
			root = x;
		else {
			// otherwise do following

			xP = yP;
			if (x != null)
				// xP is reseting if x is not null
				x.setParent(yP);

			if (y.equals(yP.getLeftChild())) {
				// if y is left child of its parent set the x as a new left
				// child of y's parent
				yP.setLeftChild(x);

				xIsLeftChild = true;
				xIsRightChild = false;
			} else {
				// otherwise set the x as a right child
				yP.setRightChild(x);

				xIsLeftChild = false;
				xIsRightChild = true;
			}
		}

		if (!y.equals(z)) {
			//@f:off
//			y->left = z->left;
//			y->right = z->right;
//			y->parent = z->parent;
//			y->color = z->color;
//			z->left->parent = z->right->parent = y;
//			if (z == z->parent->left)
//			    z->parent->left = y; 
//			else
//			    z->parent->right = y;
			//@f:on
			y.setLeftChild(z.getLeftChild());
			y.setRightChild(z.getRightChild());
			y.setParent(z.getParent());
			// do not copy color
			// y.setColor(z.getColor());
			if (z.getLeftChild() != null)
				z.getLeftChild().setParent(y);
			if (z.getRightChild() != null)
				z.getRightChild().setParent(y);
			RBNode<T> zP = (RBNode<T>) z.getParent();
			// set new references for parent of z item (entered item)
			if (zP != null) {
				if (z.equals(zP.getLeftChild())) {
					zP.setLeftChild(y);
				} else {
					zP.setRightChild(y);
				}
			} else {
				// if parent of z was null z was root, so set root to new moved
				// item
				root = y;
			}
		}

		// as a deleted node is meant successor
		if (y.getColor() == RBNode.COLOR_NODE_BLACK) {
			deleteFixUp(x, xP, xIsLeftChild, xIsRightChild);
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

	private void deleteFixUp(RBNode<T> x, RBNode<T> xP, boolean isLeftChild,
			boolean isRightChild) {
		RBNode<T> xp_backup = xP;
		int xColor = RBNode.COLOR_NODE_BLACK;

		// kym xP nieje null a teda do kym x nieje root (root nema parenta)
		while (xP != null && xColor == RBNode.COLOR_NODE_BLACK) {
			if (x != null && x.equals(root)) {
				break;
			}

			// set which child the x is (if the x is not null)
			if (x != null && xP != null) {
				xP = (RBNode<T>) x.getParent();
				if (x.equals(xP.getLeftChild())) {
					isLeftChild = true;
					isRightChild = false;
				} else if (x.equals(xP.getRightChild())) {
					isRightChild = true;
					isLeftChild = false;
				}
			}

			// RBNode<T> w = (isLeftChild ? xP.getRightChild() : (isRightChild ?
			// xP.getLeftChild() : null));
			RBNode<T> w = null;
			if (isLeftChild) {
				w = xP.getRightChild();
				if (w != null) {
					if (w.getColor() == RBNode.COLOR_NODE_BLACK) {
						RBNode<T> wLc = w.getLeftChild();
						RBNode<T> wRc = w.getRightChild();

						if (((wLc != null && wLc.getColor() == RBNode.COLOR_NODE_BLACK) || wLc == null)
								&& ((wRc != null && wRc.getColor() == RBNode.COLOR_NODE_BLACK) || wRc == null)) {
							// case 1
							// if left and right child of black brother of x
							// is BLACK
							w.setColor(RBNode.COLOR_NODE_RED);
							if (xP.getColor() == RBNode.COLOR_NODE_RED) {
								x = root;
							} else {
								// if parent had a black color, go up
								x = xP;
							}
							xP.setColor(RBNode.COLOR_NODE_BLACK);
							// set color mark
							xColor = xP.getColor();// //test commit
							continue;
						}

						if (wRc != null
								&& wRc.getColor() == RBNode.COLOR_NODE_BLACK
								&& wLc != null
								&& wLc.getColor() == RBNode.COLOR_NODE_RED) {
							// case 3 -> case 2
							w.setColor(RBNode.COLOR_NODE_RED);
							wLc.setColor(RBNode.COLOR_NODE_BLACK);
							rightRotation(w);
						}

						if (wRc != null
								&& wRc.getColor() == RBNode.COLOR_NODE_RED) {
							// case 2
							wRc.setColor(RBNode.COLOR_NODE_BLACK);
							w.setColor(xP.getColor());
							xP.setColor(RBNode.COLOR_NODE_BLACK);
							leftRotation(xP);
							x = root;
						}
					} else if (w.getColor() == RBNode.COLOR_NODE_RED) {
						// case 4
						assert xP.getColor() == RBNode.COLOR_NODE_BLACK; // testovanie
																			// predpokladanych
																			// hodnot
						xP.setColor(RBNode.COLOR_NODE_RED);
						leftRotation(xP);

					}

				}
			} else if (isRightChild) {
				w = xP.getLeftChild();
				if (w != null) {
					if (w.getColor() == RBNode.COLOR_NODE_BLACK) {
						RBNode<T> wLc = w.getLeftChild();
						RBNode<T> wRc = w.getRightChild();

						if (((wLc != null && wLc.getColor() == RBNode.COLOR_NODE_BLACK) || wLc == null)
								&& ((wRc != null && wRc.getColor() == RBNode.COLOR_NODE_BLACK) || wRc == null)) {
							// case 1
							// if left and right child of black brother of x
							// is BLACK
							w.setColor(RBNode.COLOR_NODE_RED);
							if (xP.getColor() == RBNode.COLOR_NODE_RED) {
								x = root;
							} else {
								// if parent had a black color, go up
								x = xP;
							}
							xP.setColor(RBNode.COLOR_NODE_BLACK);
							// set color mark
							xColor = xP.getColor();
							continue;
						}

						if (wLc != null
								&& wLc.getColor() == RBNode.COLOR_NODE_BLACK
								&& wRc != null
								&& wRc.getColor() == RBNode.COLOR_NODE_RED) {
							// case 3 -> case 2
							w.setColor(RBNode.COLOR_NODE_RED);
							wRc.setColor(RBNode.COLOR_NODE_BLACK);
							leftRotation(w);
						}

						if (wLc != null
								&& wLc.getColor() == RBNode.COLOR_NODE_RED) {
							// case 2
							wLc.setColor(RBNode.COLOR_NODE_BLACK);
							w.setColor(xP.getColor());
							xP.setColor(RBNode.COLOR_NODE_BLACK);
							rightRotation(xP);
							x = root;
						}
					} else if (w.getColor() == RBNode.COLOR_NODE_RED) {
						// case 4
						assert xP.getColor() == RBNode.COLOR_NODE_BLACK; // testovanie
																			// predpokladanych
																			// hodnot
						xP.setColor(RBNode.COLOR_NODE_RED);
						rightRotation(xP);

					}

				}
			}
		}

		// we are out of while cycle if the x is root

		// System.out.println("fixed up");

		// while (x!=null && !x.equals(root) && x.getColor() ==
		// RBNode.COLOR_NODE_BLACK) {
		// RBNode xP = (RBNode) x.getParent();
		// if (x.equals(xP.getLeftChild())) {
		// RBNode w = xP.getRightChild();
		// if (w.getColor() == RBNode.COLOR_NODE_BLACK) { // case 4
		// xP.setColor(RBNode.COLOR_NODE_RED); // case 4
		// leftRotation(xP); // case 4
		// w = xP.getRightChild(); // case 4
		// }
		// if (w.getLeftChild().getColor() == RBNode.COLOR_NODE_BLACK &&
		// w.getRightChild().getColor() == RBNode.COLOR_NODE_BLACK) {
		// w.setColor(RBNode.COLOR_NODE_RED); // case 1
		// x = xP; // case 1
		// } else {
		// if (w.getRightChild().getColor() == RBNode.COLOR_NODE_BLACK) {
		// w.getLeftChild().setColor(RBNode.COLOR_NODE_BLACK); // case
		// // 3
		// w.setColor(RBNode.COLOR_NODE_RED); // case 3
		// rightRotation(w); // case 3
		// w = xP.getRightChild(); // case 3
		// }
		// w.setColor(xP.getColor()); // case 2
		// xP.setColor(RBNode.COLOR_NODE_BLACK); // case 2
		// w.getRightChild().setColor(RBNode.COLOR_NODE_BLACK); // case
		// // 2
		// leftRotation(xP); // case 2
		// x = root; // case 2
		// }
		// } else {
		// RBNode w = xP.getLeftChild();
		// if (w.getColor() == RBNode.COLOR_NODE_BLACK) {
		// xP.setColor(RBNode.COLOR_NODE_RED);
		// rightRotation(xP);
		// w = xP.getLeftChild();
		// }
		// if (w.getLeftChild().getColor() == RBNode.COLOR_NODE_BLACK &&
		// w.getRightChild().getColor() == RBNode.COLOR_NODE_BLACK) {
		// w.setColor(RBNode.COLOR_NODE_RED);
		// x = xP;
		// } else {
		// if (w.getLeftChild().getColor() == RBNode.COLOR_NODE_BLACK) {
		// w.getRightChild().setColor(RBNode.COLOR_NODE_BLACK);
		// w.setColor(RBNode.COLOR_NODE_RED);
		// leftRotation(w);
		// w = xP.getLeftChild();
		// }
		// w.setColor(xP.getColor());
		// xP.setColor(RBNode.COLOR_NODE_BLACK);
		// w.getLeftChild().setColor(RBNode.COLOR_NODE_BLACK);
		// rightRotation(xP);
		// x = root;
		// }
		// }
		// }
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
			System.out.println("  "
					+ root
					+ " "
					+ (root.getColor() == RBNode.COLOR_NODE_BLACK ? "Black"
							: root.getColor() == RBNode.COLOR_NODE_RED ? "Red"
									: "")
					+ (root.equals(this.root) ? " (root)" : ""));
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
