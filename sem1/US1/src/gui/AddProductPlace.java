package gui;

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

import core.Client;
import core.Product;
import core.StorageDatabase;
import core.WareHouse;

public class AddProductPlace extends JPanel {
	private StorageDatabase database;
	private JTextField fieldName;
	private JTextField fieldId;
	private JTextField fieldAddress;
	private JTextField fieldWareHouse;

	/**
	 * Create the panel.
	 */
	public AddProductPlace(StorageDatabase db, final boolean isClient) {
		this.database = db;
		setLayout(null);

		JButton btnAdd = new JButton("Add place");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String name = fieldName.getText();
				String address = fieldAddress.getText();

				try {
					boolean added = false;
					if (isClient) {
						String id = fieldId.getText();
						int whId = Integer.parseInt(fieldWareHouse.getText());
						Client c = new Client();
						c.setName(name);
						c.setAddress(address);
						c.setId(id);
						added = database.addClient(c, whId);

						if (added) {
							JOptionPane.showMessageDialog(getParent(), "Client (id:" + c.getId() + ") was added");
						} else {
							JOptionPane.showMessageDialog(getParent(), "item was not added", "Insert error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						int id = Integer.parseInt(fieldId.getText());
						WareHouse wh = new WareHouse();
						wh.setId(id);
						wh.setAddress(address);
						wh.setName(name);
						added = database.addWarehouse(wh);

						if (added) {
							JOptionPane.showMessageDialog(getParent(), "Waehouse (id:" + wh.getId() + ") was added");
						} else {
							JOptionPane.showMessageDialog(getParent(), "Warehouse was not added", "Insert error", JOptionPane.ERROR_MESSAGE);
						}
					}

				} catch (NumberFormatException e) {
					System.err.println(e.getMessage());
				}

			}
		});
		btnAdd.setBounds(340, 65, 98, 23);
		add(btnAdd);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 14, 46, 14);
		add(lblName);

		fieldName = new JTextField();
		fieldName.setBounds(71, 11, 86, 20);
		add(fieldName);
		fieldName.setColumns(10);

		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 42, 46, 14);
		add(lblId);

		fieldId = new JTextField();
		fieldId.setBounds(71, 39, 86, 20);
		add(fieldId);
		fieldId.setColumns(10);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 69, 51, 14);
		add(lblAddress);

		fieldAddress = new JTextField();
		fieldAddress.setBounds(71, 67, 222, 20);
		add(fieldAddress);
		fieldAddress.setColumns(10);

		JLabel lblWarehouse = new JLabel("Warehouse:");
		if (!isClient) {
			lblWarehouse.setVisible(false);
		}
		lblWarehouse.setBounds(202, 13, 77, 16);
		add(lblWarehouse);

		fieldWareHouse = new JTextField();
		if (!isClient) {
			fieldWareHouse.setVisible(false);
		}
		fieldWareHouse.setBounds(289, 11, 114, 20);
		add(fieldWareHouse);
		fieldWareHouse.setColumns(10);
	}
}
