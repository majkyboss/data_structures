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

public class ProductsNumDays extends JPanel {
	private JTextField fieldWH;
	private JTextField fieldDateFrom;
	private JTextField fieldDays;
	private StorageDatabase database;
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

	/**
	 * Create the panel.
	 */
	public ProductsNumDays(StorageDatabase db) {
		this.database = db;
		setLayout(null);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Date dateFrom = shortDateFormat.parse(fieldDateFrom.getText());

					int daysUntilDate = Integer.parseInt(fieldDays.getText());
					int wareHouseId = Integer.parseInt(fieldWH.getText());

					List<Product> foundProducts = database.searchProducts(dateFrom, daysUntilDate, wareHouseId);
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
		lblWH.setBounds(10, 41, 86, 14);
		add(lblWH);

		fieldWH = new JTextField();
		fieldWH.setText("1");
		fieldWH.setColumns(10);
		fieldWH.setBounds(93, 39, 120, 20);
		add(fieldWH);

		JLabel lblDateFrom = new JLabel("Date from:");
		lblDateFrom.setBounds(10, 14, 63, 14);
		add(lblDateFrom);

		fieldDateFrom = new JTextField(shortDateFormat.format(new Date()));
		fieldDateFrom.setBounds(83, 11, 86, 20);
		add(fieldDateFrom);
		fieldDateFrom.setColumns(10);

		JLabel lblDays = new JLabel("Days:");
		lblDays.setBounds(179, 14, 46, 14);
		add(lblDays);

		fieldDays = new JTextField();
		fieldDays.setText("1");
		fieldDays.setBounds(235, 11, 86, 20);
		add(fieldDays);
		fieldDays.setColumns(10);

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
