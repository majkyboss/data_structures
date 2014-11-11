package gui;

import gui.tables.TransportsModel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.StorageDatabase;
import core.data.TransportProduct;

public class ArrivedProducts extends JPanel {
	private JTextField fieldWH;
	private StorageDatabase database;
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
	private JTextField fieldPlaceTo;

	/**
	 * Create the panel.
	 */
	public ArrivedProducts(StorageDatabase db, final boolean toClient) {
		this.database = db;
		setLayout(null);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					int wareHouseFromId = Integer.parseInt(fieldWH.getText());

					List<TransportProduct> foundProducts = new LinkedList<>();
					if (!toClient) {
						int wareHouseToId = Integer.parseInt(fieldPlaceTo.getText());
						foundProducts = database.showArrivedProductsInWareHouse(wareHouseFromId, wareHouseToId);
					} else {
						String clientId = fieldPlaceTo.getText();
						foundProducts = database.showArrivedProductsInClinet(wareHouseFromId, clientId);
					}

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
		lblWH.setBounds(12, 45, 86, 14);
		add(lblWH);

		fieldWH = new JTextField();
		fieldWH.setText("1");
		fieldWH.setColumns(10);
		fieldWH.setBounds(95, 43, 120, 20);
		add(fieldWH);

		JLabel lblToPlace = new JLabel();
		lblToPlace.setText("s");
		if (toClient) {
			lblToPlace.setText("To client (id-string):");
		} else {
			lblToPlace.setText("To Warehouse (id-integer):");
		}
		lblToPlace.setBounds(12, 16, 152, 14);
		add(lblToPlace);

		fieldPlaceTo = new JTextField();
		fieldPlaceTo.setText("1");
		fieldPlaceTo.setColumns(10);
		fieldPlaceTo.setBounds(176, 13, 86, 20);
		add(fieldPlaceTo);

	}

	private void openFoundItems(List<TransportProduct> items) {
		TableView tableView = new TableView(this);
		tableView.updateTable(new TransportsModel(items));

		Container c = getParent();
		c.removeAll();
		c.add(tableView);
		c.revalidate();
		c.repaint();
	}
}
