package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import core.CSVHandler;
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
		storage = getNewDbInstance();

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

		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);

		// initMenu(mnAll);
		JMenuItem menuItem = new JMenuItem("1 Search products by date");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new Products(storage));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("1 Search products by date");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new Products(storage));
			}
		});
		mnProducts.add(menuItem);
		menuItem = new JMenuItem("2 Search products count");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ProductsCount(storage));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("2 Search products count");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ProductsCount(storage));
			}
		});
		mnProducts.add(menuItem);
		menuItem = new JMenuItem("3 Search product by product num.");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new OneProduct(storage));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("3 Search product by product num.");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new OneProduct(storage));
			}
		});
		mnProducts.add(menuItem);
		menuItem = new JMenuItem("4 Add product");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new AddProduct(storage));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("4 Add product");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new AddProduct(storage));
			}
		});
		mnProducts.add(menuItem);
		menuItem = new JMenuItem("5 Search client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new OneClient(storage));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("5 Search client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new OneClient(storage));
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
				showContent(new Clients(storage));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("9 Get clients of warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new Clients(storage));
			}
		});
		mnWarehouses.add(menuItem);
		menuItem = new JMenuItem("10 Get live transports");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new TransportsLive(storage));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("10 Get live transports");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new TransportsLive(storage));
			}
		});
		mnWarehouses.add(menuItem);
		menuItem = new JMenuItem("11 Get arrived products to client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ArrivedProducts(storage, true));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("11 Get arrived products to client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ArrivedProducts(storage, true));
			}
		});
		mnClients.add(menuItem);
		menuItem = new JMenuItem("11 Get arrived products to client");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ArrivedProducts(storage, true));
			}
		});
		mnTransport.add(menuItem);
		menuItem = new JMenuItem("12 Get arrived products to warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ArrivedProducts(storage, false));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("12 Get arrived products to warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ArrivedProducts(storage, false));
			}
		});
		mnWarehouses.add(menuItem);
		menuItem = new JMenuItem("12 Get arrived products to warehouse");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ArrivedProducts(storage, false));
			}
		});
		mnTransport.add(menuItem);
		menuItem = new JMenuItem("13 Get products by date + n");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ProductsNumDays(storage));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("13 Get products by date + n");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ProductsNumDays(storage));
			}
		});
		mnProducts.add(menuItem);
		menuItem = new JMenuItem("13 Get products by date + n");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ProductsNumDays(storage));
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
				showContent(new ProductsValue(storage));
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("17 Get products value");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(new ProductsValue(storage));
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

		menuItem = new JMenuItem("Init DB");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// showContent(new ClientsView(storage));
				storage = getNewDbInstance();
				if (storage instanceof Db) {
					((Db) storage).initDB();
					JOptionPane.showMessageDialog(getParent(), "Database initialized.", "Setting DB", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		mnTools.add(menuItem);

		menuItem = new JMenuItem("Load Db");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// showContent(new ClientsView(storage));
				String dirPath = ".";
				try {
					new CSVHandler(storage).load_csv(dirPath);
					JOptionPane.showMessageDialog(getParent(), "Database loaded.", "Loading DB", JOptionPane.PLAIN_MESSAGE);
				} catch (
						HeadlessException
						| IOException
						| ParseException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(getParent(), "Database has not loaded.", "Loading DB", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mnTools.add(menuItem);
		menuItem = new JMenuItem("Save DB");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// showContent(new ClientsView(storage));
				String dirPath = ".";
				try {
					new CSVHandler(storage).save_csv(dirPath);
					JOptionPane.showMessageDialog(getParent(), "Database saved.", "Save DB", JOptionPane.PLAIN_MESSAGE);
				} catch (
						HeadlessException
						| IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(getParent(), "Database has not saved.", "Save DB", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mnTools.add(menuItem);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}

	private StorageDatabase getNewDbInstance() {
		StorageDatabase db = null;
		db = new Db();
		// simulation state
		// this.storage = DatabaseStorageSimulator.getInstance();
		return db;
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
