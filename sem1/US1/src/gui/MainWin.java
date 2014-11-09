package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import core.Db;
import core.DatabaseStorageSimulator;
import core.StorageDatabase;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		this.storage = DatabaseStorageSimulator.getInstance();
		final JPanel function1 = new SearchProducts(storage);
		final JPanel function2 = new SearchCount(storage);
		final JPanel function3 = new SearchOneProduct(storage);
		final JPanel function4 = new AddProduct(storage);
		// end data init

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 429);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAll = new JMenu("All");
		menuBar.add(mnAll);
		// initMenu(mnAll);
		JMenuItem menuItem = new JMenuItem("1");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function1);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("2");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function2);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("3");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function3);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("4");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showContent(function4);
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("5");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("6");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("7");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("8");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("9");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("10");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("11");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("12");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("13");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("14");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("15");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("16");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("17");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("18");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);
		menuItem = new JMenuItem("19");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAll.add(menuItem);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}

	private void initMenu(JMenu menu) {

	}

	private void showContent(JPanel component) {
		contentPane.removeAll();
		contentPane.add(component);
		contentPane.revalidate();
		contentPane.repaint();
	}
}
