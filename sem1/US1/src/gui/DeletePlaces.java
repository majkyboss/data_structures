package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.StorageDatabase;

public class DeletePlaces extends JPanel {
	private JTextField fieldPlaceForDel;
	private StorageDatabase database;
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
	private JTextField fieldWareHouse;

	/**
	 * Create the panel.
	 */
	public DeletePlaces(StorageDatabase db, final boolean isClient) {
		this.database = db;
		setLayout(null);

		JLabel lblPlaceForDel = new JLabel("Place for delete:");
		lblPlaceForDel.setBounds(10, 14, 109, 14);
		add(lblPlaceForDel);

		fieldPlaceForDel = new JTextField();
		fieldPlaceForDel.setBounds(131, 11, 173, 20);
		add(fieldPlaceForDel);
		fieldPlaceForDel.setColumns(10);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					boolean deleted = false;
					String textToMsg = "";
					if (isClient) {
						String clientId = fieldPlaceForDel.getText();
						int warehouseId = Integer.parseInt(fieldWareHouse.getText());
						deleted = database.removeClient(clientId, warehouseId);
						textToMsg = "client (id:" + clientId + ") was deleted";
					} else {
						int wareHouseForDelId = Integer.parseInt(fieldPlaceForDel.getText());
						int warehouseToMoveId = Integer.parseInt(fieldWareHouse.getText());
						deleted = database.removeWarehouse(wareHouseForDelId, warehouseToMoveId);
						textToMsg = "warehouse (id:" + wareHouseForDelId + ") was deleted and all data was moved to: " + warehouseToMoveId;
					}

					if (deleted) {
						JOptionPane.showMessageDialog(getParent(), textToMsg);
					} else {
						JOptionPane.showMessageDialog(getParent(), textToMsg, "Delete error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					System.err.println(e.getMessage());
				}

			}
		});
		btnDelete.setBounds(349, 43, 89, 23);
		add(btnDelete);

		fieldWareHouse = new JTextField();
		fieldWareHouse.setBounds(131, 44, 114, 20);
		add(fieldWareHouse);
		fieldWareHouse.setColumns(10);

		JLabel lblWarehouse = new JLabel("Warehouse:");
		lblWarehouse.setBounds(10, 46, 80, 16);
		add(lblWarehouse);
	}

}