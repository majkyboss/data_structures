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

import core.StorageDatabase;
import core.data.Product;

public class Products extends JPanel {
	private JTextField fieldEan;
	private JTextField fieldWH;
	private JTextField fieldCount;
	private JTextField fieldDateFrom;
	private JTextField fieldDateTo;
	private StorageDatabase database;
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

	/**
	 * Create the panel.
	 */
	public Products(StorageDatabase db) {
		this.database = db;
		setLayout(null);

		JLabel lblEanCode = new JLabel("Enter EAN code:");
		lblEanCode.setBounds(10, 14, 90, 14);
		add(lblEanCode);

		fieldEan = new JTextField();
		fieldEan.setBounds(118, 11, 173, 20);
		add(fieldEan);
		fieldEan.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String ean = fieldEan.getText();

				try {
					Date dateFrom = null;
					if (!fieldDateFrom.getText().trim().isEmpty()) {
						dateFrom = shortDateFormat.parse(fieldDateFrom.getText());
					}
					Date dateTo = null;
					if (!fieldDateTo.getText().trim().isEmpty()) {
						dateTo = shortDateFormat.parse(fieldDateTo.getText());
					}
					int count = Integer.parseInt(fieldCount.getText());
					int wareHouseId = Integer.parseInt(fieldWH.getText());

					List<Product> foundProducts = database.searchProducts(ean, dateFrom, dateTo, count, wareHouseId);
					if (foundProducts.isEmpty()) {
						// show warning dialog: no items found
						JOptionPane.showMessageDialog(getParent(), "No item was found.", "No items", JOptionPane.WARNING_MESSAGE);
					}
					openFoundItems(foundProducts);

				} catch (
						ParseException
						| NumberFormatException e) {
					System.err.println(e.getMessage());
				}

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

		JLabel lblCount = new JLabel("Count:");
		lblCount.setBounds(301, 14, 46, 14);
		add(lblCount);

		fieldCount = new JTextField();
		fieldCount.setText("1");
		fieldCount.setBounds(357, 11, 86, 20);
		add(fieldCount);
		fieldCount.setColumns(10);

		JLabel lblDateFrom = new JLabel("Date from:");
		lblDateFrom.setBounds(10, 42, 63, 14);
		add(lblDateFrom);

		fieldDateFrom = new JTextField(shortDateFormat.format(new Date()));
		fieldDateFrom.setBounds(83, 39, 86, 20);
		add(fieldDateFrom);
		fieldDateFrom.setColumns(10);

		JLabel lblDateTo = new JLabel("Date to:");
		lblDateTo.setBounds(179, 42, 46, 14);
		add(lblDateTo);

		fieldDateTo = new JTextField(shortDateFormat.format(new Date()));
		fieldDateTo.setBounds(235, 39, 86, 20);
		add(fieldDateTo);
		fieldDateTo.setColumns(10);

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
