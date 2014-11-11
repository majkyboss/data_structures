package gui;

import gui.tables.ProductsModel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.StorageDatabase;
import core.data.Product;

public class OneProduct extends JPanel {
	private JTextField fieldProductNum;
	private StorageDatabase database;
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

	/**
	 * Create the panel.
	 */
	public OneProduct(StorageDatabase db) {
		this.database = db;
		setLayout(null);

		JLabel lblEanCode = new JLabel("Product Number:");
		lblEanCode.setBounds(10, 14, 109, 14);
		add(lblEanCode);

		fieldProductNum = new JTextField();
		fieldProductNum.setBounds(131, 11, 173, 20);
		add(fieldProductNum);
		fieldProductNum.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					int productNum = Integer.parseInt(fieldProductNum.getText());

					Product product = database.searchProduct(productNum);
					openFoundItem(product);

				} catch (NumberFormatException e) {
					System.err.println(e.getMessage());
				}

			}
		});
		btnSearch.setBounds(354, 66, 89, 23);
		add(btnSearch);
	}

	private void openFoundItem(Product product) {
		// TODO !!! required create separate window for product info
		LinkedList<Product> items = new LinkedList<>();
		items.add(product);
		TableView tableView = new TableView(this);
		tableView.updateTable(new ProductsModel(items));

		Container c = getParent();
		c.removeAll();
		c.add(tableView);
		c.revalidate();
		c.repaint();
	}

}
