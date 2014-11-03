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
			int value = Integer.parseInt(args[1]);
			RBNode<Integer> node = new IntegerNode(key, new IntegerNodeValue(value));
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
			key = Integer.parseInt(args[1]);
			value = Integer.parseInt(args[1]);
			RBNode<Integer> nodeToDel = tree.find(key);
			if (nodeToDel!=null) {
				tree.delete(nodeToDel);
				System.out.println(nodeToDel.toString() + " deleted");
			} else {
				System.out.println("node is not in the tree, key: " + key);
			}
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
		parse("add 10");
		parse("add 20");
		parse("add 30");
		parse("add 40");
		parse("add 50");
		parse("add 60");
		parse("add 70");
		parse("add 80");
		parse("add 90");
		parse("add 100");
		parse("add 110");
		parse("add 120");
	}
	
	public class IntegerNode extends RBNode<Integer>{
		private int key;
		
		public IntegerNode(Integer key, NodeValue value) {
			super(value);
			this.key = key;
		}

		@Override
		public Integer getKey() {
			return key;
		}
		
	}
}
