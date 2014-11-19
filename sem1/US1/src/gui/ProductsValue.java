package gui;

import gui.tables.ProductsValueModel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.StorageDatabase;
import core.data.ProductValueItem;

@SuppressWarnings("serial")
public class ProductsValue extends JPanel {
	private JTextField fieldWH;
	private StorageDatabase database;

	/**
	 * Create the panel.
	 */
	public ProductsValue(StorageDatabase db) {
		this.database = db;
		setLayout(null);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					int wareHouseId = Integer.parseInt(fieldWH.getText());

					List<ProductValueItem> foundProducts = database.getProductsValue(wareHouseId);
					if (foundProducts.isEmpty()) {
						// show warning dialog: no items found
						JOptionPane.showMessageDialog(getParent(), "No item was found.", "No items", JOptionPane.WARNING_MESSAGE);
					}
					openFoundItems(foundProducts);

				} catch (NumberFormatException e) {
					System.err.println(e.getMessage());
				}

			}
		});
		btnSearch.setBounds(354, 66, 89, 23);
		add(btnSearch);

		JLabel lblWH = new JLabel("Warehouse:");
		lblWH.setBounds(10, 13, 86, 14);
		add(lblWH);

		fieldWH = new JTextField();
		fieldWH.setText("1");
		fieldWH.setColumns(10);
		fieldWH.setBounds(93, 11, 120, 20);
		add(fieldWH);

	}

	private void openFoundItems(List<ProductValueItem> items) {
		TableView tableView = new TableView(this);
		tableView.updateTable(new ProductsValueModel(items));

		Container c = getParent();
		c.removeAll();
		c.add(tableView);
		c.revalidate();
		c.repaint();
	}
}
