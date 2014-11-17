package gui;

import gui.tables.ClientsModel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.StorageDatabase;
import core.data.Client;
import core.data.WareHouse;

public class ClientsView extends JPanel {
	private StorageDatabase database;

	/**
	 * Create the panel.
	 */
	public ClientsView(StorageDatabase db) {
		this.database = db;
		setLayout(null);

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Client> items = database.getAllClients();
				openFoundItems(items);
			}
		});
		btnLoad.setBounds(10, 11, 89, 23);
		add(btnLoad);
	}

	private void openFoundItems(List<Client> items) {
		TableView tableView = new TableView(this);
		tableView.updateTable(new ClientsModel(items));

		Container c = getParent();
		c.removeAll();
		c.add(tableView);
		c.revalidate();
		c.repaint();
	}
}
