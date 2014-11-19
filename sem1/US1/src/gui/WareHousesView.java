package gui;

import gui.tables.WarehousesModel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.StorageDatabase;
import core.data.WareHouse;

@SuppressWarnings("serial")
public class WareHousesView extends JPanel {
	private StorageDatabase database;

	/**
	 * Create the panel.
	 */
	public WareHousesView(StorageDatabase db) {
		this.database = db;
		setLayout(null);

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<WareHouse> items = database.getWarehouses();
				loadWarehouses(items);
			}
		});
		btnLoad.setBounds(10, 11, 89, 23);
		add(btnLoad);
	}

	private void loadWarehouses(List<WareHouse> items) {
		TableView tableView = new TableView(this);
		tableView.updateTable(new WarehousesModel(items));

		Container c = getParent();
		c.removeAll();
		c.add(tableView);
		c.revalidate();
		c.repaint();
	}
}
