package gui;

import gui.tables.ProductsModel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.StorageDatabase;
import core.data.Product;

@SuppressWarnings("serial")
public class ProductsView extends JPanel {
	private StorageDatabase database;

	/**
	 * Create the panel.
	 */
	public ProductsView(StorageDatabase db) {
		this.database = db;
		setLayout(null);

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Product> items = database.getAllProducts();
				loadProducts(items);
			}
		});
		btnLoad.setBounds(10, 11, 89, 23);
		add(btnLoad);
	}

	private void loadProducts(List<Product> items) {
		TableView tableView = new TableView(this);
		tableView.updateTable(new ProductsModel(items));

		Container c = getParent();
		c.removeAll();
		c.add(tableView);
		c.revalidate();
		c.repaint();
	}
}
