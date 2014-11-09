package gui;

import gui.tables.ProductsModel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Product;
import core.StorageDatabase;

public class ArrivedProducts extends JPanel {
	private JTextField fieldWH;
	private StorageDatabase database;
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

	/**
	 * Create the panel.
	 */
	public ArrivedProducts(StorageDatabase db) {
		this.database = db;
		setLayout(null);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO createing started, did not finished! 

//				String ean = fieldEan.getText();
//
//				try {
//					Date dateFrom = null;
//					if (!fieldDateFrom.getText().trim().isEmpty()) {
//						dateFrom = shortDateFormat.parse(fieldDateFrom.getText());
//					}
//					Date dateTo = null;
//					if (!fieldDateTo.getText().trim().isEmpty()) {
//						dateTo = shortDateFormat.parse(fieldDateTo.getText());
//					}
//					int count = Integer.parseInt(fieldCount.getText());
//					int wareHouseId = Integer.parseInt(fieldWH.getText());
//
//					List<Product> foundProducts = database.searchProducts(ean, dateFrom, dateTo, count, wareHouseId);
//					if (foundProducts.isEmpty()) {
//						// show warning dialog: no items found
//						JOptionPane.showMessageDialog(getParent(), "No item was found.", "No items", JOptionPane.WARNING_MESSAGE);
//					}
//					openFoundItems(foundProducts);
//
//				} catch (
//						ParseException
//						| NumberFormatException e) {
//					System.err.println(e.getMessage());
//				}

			}
		});
		btnSearch.setBounds(354, 66, 89, 23);
		add(btnSearch);

		JLabel lblWH = new JLabel("Warehouse:");
		lblWH.setBounds(10, 69, 86, 14);
		add(lblWH);

		fieldWH = new JTextField();
		fieldWH.setText("1");
		fieldWH.setColumns(10);
		fieldWH.setBounds(93, 67, 120, 20);
		add(fieldWH);

	}

	private void openFoundItems(List<Product> items) {
		TableView tableView = new TableView(this);
		tableView.updateTable(new ProductsModel(items));

		Container c = getParent();
		c.removeAll();
		c.add(tableView);
		c.revalidate();
		c.repaint();
	}
}
