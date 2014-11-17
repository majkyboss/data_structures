package gui;

import gui.tables.WarehousesModel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.StorageDatabase;
import core.data.WareHouse;

public class WareHousesView extends JPanel {
	private StorageDatabase database;
	private JTextField fieldName;
	private JTextField fieldId;
	private JTextField fieldAddress;
	private JTextField fieldWareHouse;

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

		// JButton btnAdd = new JButton("Add place");
		// btnAdd.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		//
		// String name = fieldName.getText();
		// String address = fieldAddress.getText();
		//
		// try {
		// boolean added = false;
		//
		// int id = Integer.parseInt(fieldId.getText());
		// WareHouse wh = new WareHouse();
		// wh.setId(id);
		// wh.setAddress(address);
		// wh.setName(name);
		// added = database.addWarehouse(wh);
		//
		// if (added) {
		// JOptionPane.showMessageDialog(getParent(), "Waehouse (id:" +
		// wh.getId() + ") was added");
		// } else {
		// JOptionPane.showMessageDialog(getParent(), "Warehouse was not added",
		// "Insert error", JOptionPane.ERROR_MESSAGE);
		// }
		//
		// } catch (NumberFormatException e) {
		// System.err.println(e.getMessage());
		// }
		//
		// }
		// });
		// btnAdd.setBounds(340, 65, 98, 23);
		// add(btnAdd);
		//
		// JLabel lblName = new JLabel("Name:");
		// lblName.setBounds(10, 14, 46, 14);
		// add(lblName);
		//
		// fieldName = new JTextField();
		// fieldName.setBounds(71, 11, 86, 20);
		// add(fieldName);
		// fieldName.setColumns(10);
		//
		// JLabel lblId = new JLabel("Id:");
		// lblId.setBounds(10, 42, 46, 14);
		// add(lblId);
		//
		// fieldId = new JTextField();
		// fieldId.setBounds(71, 39, 86, 20);
		// add(fieldId);
		// fieldId.setColumns(10);
		//
		// JLabel lblAddress = new JLabel("Address:");
		// lblAddress.setBounds(10, 69, 51, 14);
		// add(lblAddress);
		//
		// fieldAddress = new JTextField();
		// fieldAddress.setBounds(71, 67, 222, 20);
		// add(fieldAddress);
		// fieldAddress.setColumns(10);
		//
		// JLabel lblWarehouse = new JLabel("Warehouse:");
		// lblWarehouse.setBounds(202, 13, 77, 16);
		// add(lblWarehouse);
		//
		// fieldWareHouse = new JTextField();
		// fieldWareHouse.setBounds(289, 11, 114, 20);
		// add(fieldWareHouse);
		// fieldWareHouse.setColumns(10);
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
