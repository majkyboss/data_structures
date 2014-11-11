package gui;

import gui.tables.TransportsModel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.StorageDatabase;
import core.TransportProduct;

public class TransportsLive extends JPanel {
	private JTextField fieldWH;
	private StorageDatabase database;
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

	/**
	 * Create the panel.
	 */
	public TransportsLive(StorageDatabase db) {
		this.database = db;
		setLayout(null);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int wareHouseId = Integer.parseInt(fieldWH.getText());

					List<TransportProduct> foundTransports = database.getLiveTransport(wareHouseId);
					if (foundTransports.isEmpty()) {
						// show warning dialog: no items found
						JOptionPane.showMessageDialog(getParent(), "No item was found.", "No items", JOptionPane.WARNING_MESSAGE);
					}
					openFoundItems(foundTransports);

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

	private void openFoundItems(List<TransportProduct> foundTransports) {
		TableView tableView = new TableView(this);
		tableView.updateTable(new TransportsModel(foundTransports));

		Container c = getParent();
		c.removeAll();
		c.add(tableView);
		c.revalidate();
		c.repaint();

	}

}
