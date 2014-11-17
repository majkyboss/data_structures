package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import core.Db;
import core.StorageDatabase;

public class MainWin extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWin frame = new MainWin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;
	private StorageDatabase storage;

	/**
	 * Create the frame.
	 */
	public MainWin() {
		// data init
		storage = new Db();
		// TODO delete simulation state
		// this.storage = DatabaseStorageSimulator.getInstance();
		final JPanel function1 = new Products(storage);
		final JPanel function2 = new ProductsCount(storage);
		final JPanel function3 = new OneProduct(storage);
		final JPanel function4 = new AddProduct(storage);
		final JPanel function5 = new OneClient(storage);
		final JPanel function9 = new Clients(storage);
		final JPanel function10 = new TransportsLive(storage);
		final JPanel function11 = new ArrivedProducts(storage, true);
		final JPanel function12 = new ArrivedProducts(storage, false);
		final JPanel function13 = new ProductsNumDays(storage);
		final JPanel function17 = new ProductsValue(storage);
		// end data init

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 429);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnProducts = new JMenu("Products");
		menuBar.add(mnProducts);

		JMenu mnWarehouses = new JMenu("Warehouses");
		menuBar.add(mnWarehouses);

		JMenu mnClients = new JMenu("Clients");
		menuBar.add(mnClients);

		JMenu mnTransport = new JMenu("Transport");
		menuBar.add(mnTransport);

		JMenu mnAll = new JMenu("All");
		menuBar.add(mnAll);
		// initMenu(mnAll);
		JMenuItem menuItem = new JMenuItem("1 Search products by date");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function1);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("1 Search products by date");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function1);
			}
		});
		mnProducts.add(menuItem);
		menuItem = new JMenuItem("2 Search products count");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function2);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("2 Search products count");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function2);
			}
		});
		mnProducts.add(menuItem);
		menuItem = new JMenuItem("3 Search product by product num.");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function3);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("3 Search product by product num.");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function3);
			}
		});
		mnProducts.add(menuItem);
		menuItem = new JMenuItem("4 Add product");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function4);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("4 Add product");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function4);
			}
		});
		mnProducts.add(menuItem);
		menuItem = new JMenuItem("5 Search client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function5);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("5 Search client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function5);
			}
		});
		mnClients.add(menuItem);
		menuItem = new JMenuItem("6 Make transport to warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new TransportStart(storage, false));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("6 Make transport to warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new TransportStart(storage, false));
			}
		});
		mnTransport.add(menuItem);
		menuItem = new JMenuItem("7 Make transport to client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new TransportStart(storage, true));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("7 Make transport to client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new TransportStart(storage, true));
			}
		});
		mnTransport.add(menuItem);
		menuItem = new JMenuItem("8 End transport");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new TransportEnd(storage));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("8 End transport");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new TransportEnd(storage));
			}
		});
		mnTransport.add(menuItem);
		menuItem = new JMenuItem("9 Get clients of warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function9);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("9 Get clients of warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function9);
			}
		});
		mnWarehouses.add(menuItem);
		menuItem = new JMenuItem("10 Get live transports");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function10);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("10 Get live transports");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function10);
			}
		});
		mnWarehouses.add(menuItem);
		menuItem = new JMenuItem("11 Get arrived products to client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function11);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("11 Get arrived products to client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function11);
			}
		});
		mnClients.add(menuItem);
		menuItem = new JMenuItem("11 Get arrived products to client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function11);
			}
		});
		mnTransport.add(menuItem);
		menuItem = new JMenuItem("12 Get arrived products to warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function12);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("12 Get arrived products to warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function12);
			}
		});
		mnWarehouses.add(menuItem);
		menuItem = new JMenuItem("12 Get arrived products to warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function12);
			}
		});
		mnTransport.add(menuItem);
		menuItem = new JMenuItem("13 Get products by date + n");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function13);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("13 Get products by date + n");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function13);
			}
		});
		mnProducts.add(menuItem);
		menuItem = new JMenuItem("13 Get products by date + n");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function13);
			}
		});
		mnWarehouses.add(menuItem);
		menuItem = new JMenuItem("14 Add warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new AddProductPlace(storage, false));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("14 Add warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new AddProductPlace(storage, false));
			}
		});
		mnWarehouses.add(menuItem);
		menuItem = new JMenuItem("15 Add client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new AddProductPlace(storage, true));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("15 Add client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new AddProductPlace(storage, true));
			}
		});
		mnClients.add(menuItem);
		menuItem = new JMenuItem("16 Delete product");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new DeleteProduct(storage));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("16 Delete product");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new DeleteProduct(storage));
			}
		});
		mnProducts.add(menuItem);
		menuItem = new JMenuItem("17 Get products value");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function17);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("17 Get products value");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function17);
			}
		});
		mnWarehouses.add(menuItem);
		menuItem = new JMenuItem("18 Delete warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new DeletePlaces(storage, false));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("18 Delete warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new DeletePlaces(storage, false));
			}
		});
		mnWarehouses.add(menuItem);
		menuItem = new JMenuItem("19 Delete client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new DeletePlaces(storage, true));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("19 Delete client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new DeletePlaces(storage, true));
			}
		});
		mnClients.add(menuItem);
		// -------------------------------------- my own functions
		menuItem = new JMenuItem("Get warehouses");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new WareHousesView(storage));
			}
		});
		mnWarehouses.add(menuItem);
		menuItem = new JMenuItem("Get warehouses");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new WareHousesView(storage));
			}
		});
		mnAll.add(menuItem);

		menuItem = new JMenuItem("Get all products");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ProductsView(storage));
			}
		});
		mnProducts.add(menuItem);
		menuItem = new JMenuItem("Get all products");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ProductsView(storage));
			}
		});
		mnAll.add(menuItem);
		
		menuItem = new JMenuItem("Get all clients");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ClientsView(storage));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("Get all clients");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ClientsView(storage));
			}
		});
		mnClients.add(menuItem);
		

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}

	// private void initMenu(JMenu menu) {
	//
	// }

	private void showContent(JPanel component) {
		contentPane.removeAll();
		contentPane.add(component);
		contentPane.revalidate();
		contentPane.repaint();
	}
}
