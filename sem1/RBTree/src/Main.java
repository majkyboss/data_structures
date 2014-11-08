import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import rb.RBNode;
import rb.RBTree;
import rb.RBTreeCheckProperties;
import core.IntegerNodeValue;
import core.NodeValue;

public class Main {

	public static void main(String[] args) {
		Main app = new Main();
		app.init();
		app.start();
	}

	private RBTree<Integer> tree = new RBTree<Integer>();
	private boolean started = false;

	public void start() {
		String s = "Hello\n";
		started = true;

		// DisplayBinaryTree frame = new DisplayBinaryTree(tree);
		// frame.setSize(300, 300);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setVisible(true);
		// frame.setAlwaysOnTop(true);

		while (started) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print(s);
			System.out.print("/$");
			try {
				s = br.readLine();
				parse(s);
				s = "";
			} catch (IOException e) {
				e.printStackTrace(System.out);
			}
		}
	}

	private void parse(String s) {
		String[] args;
		args = s.split(" ");

		switch (args[0]) {
		case "create":
			tree = new RBTree<Integer>();
			System.out.println("created");
			break;
		case "add":
			if (args.length < 2) {
				System.out.println("Error");
				break;
			}
			int key = Integer.parseInt(args[1]);
			int value = key;
			RBNode<Integer> node = new IntegerNode(new IntegerNodeValue(value));
			if (tree != null) {
				if(tree.insert(node)){
					System.out.println("added " + key);
				} else {
					System.out.println("item with this key is already inserted");
				}
			}
			break;
		case "del":
			if (args.length < 2) {
				System.out.println("Error");
				break;
			}
			RBNode<Integer> nodeToDel = null;
			try {
				key = Integer.parseInt(args[1]);
				value = Integer.parseInt(args[1]);
				nodeToDel = tree.find(key);
			} catch (NumberFormatException e) {
				key = 0;
			}
			if (nodeToDel!=null) {
				RBNode<Integer> deletedNode = tree.delete(nodeToDel);
				System.out.println(deletedNode.toString() + " deleted");
			} else {
				System.out.println("node is not in the tree, key: " + key);
			}
			parse("check");
			break;
		case "reset":
			tree = new RBTree<Integer>();
			System.out.println("tree reset");
			break;
		case "print":
			// tree.print();
			tree.printBetter();
			break;
		case "check":
			RBTreeCheckProperties<Integer> checker = new RBTreeCheckProperties<>();
			boolean test = checker.checkProperties(tree);
			if (test)
				System.out.println("All right!");
			else
				System.out.println("Something bad with tree");
			break;
		case "size":
			System.out.println("Tree size: " + tree.size());
			break;
		case "exit":
			started = false;
			break;
		default:
			break;
		}
	}

	private void init() {
//		parse("add 10");
//		parse("add 20");
//		parse("add 30");
//		parse("add 40");
//		parse("add 50");
//		parse("add 60");
//		parse("add 70");
//		parse("add 80");
//		parse("add 90");
//		parse("add 100");
//		parse("add 110");
//		parse("add 120");
		
		int maxNum = (int) Math.pow(10, 10);
		int itemsCount = (int) Math.pow(10, 3);
		int i = 0;
		for (; i < itemsCount;) {
			// generate one item
			int key = (int) (Math.random() * maxNum);
			// IntegerNodeKey treeKey = new IntegerNodeKey(key);
			IntegerNodeValue treeValue = new IntegerNodeValue(key);
			// insert generated item to RB tree
			boolean iserted = tree.insert(new RBNode<Integer>(treeValue) {

				@Override
				public Integer getKey() {
					return (Integer) value.getNodeValue();
				}
//
//				@Override
//				public void setKey(Integer key) {
//					new IntegerNodeValue(key);
//				}
			});
			if (iserted) {
				i++;
			}
		}
	}
	
	public class IntegerNode extends RBNode<Integer>{
//		private int key;
		
		public IntegerNode(IntegerNodeValue value) {
			super(value);
//			this.key = key;
		}

		@Override
		public Integer getKey() {
			return (Integer) value.getNodeValue();
		}
		
	}
}
