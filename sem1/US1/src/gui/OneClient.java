package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.StorageDatabase;
import core.data.Client;

public class OneClient extends JPanel {
	private JTextField fieldClientId;
	private JTextField fieldWH;
	private StorageDatabase database;
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

	/**
	 * Create the panel.
	 */
	public OneClient(StorageDatabase db) {
		this.database = db;
		setLayout(null);

		JLabel lblEanCode = new JLabel("Client ID:");
		lblEanCode.setBounds(10, 14, 90, 14);
		add(lblEanCode);

		fieldClientId = new JTextField();
		fieldClientId.setBounds(93, 11, 120, 20);
		add(fieldClientId);
		fieldClientId.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String clientId = fieldClientId.getText();

				try {

					int wareHouseId = Integer.parseInt(fieldWH.getText());

					Client client = database.searchClient(clientId, wareHouseId);
					if (client == null) {
						// show warning dialog: no items found
						JOptionPane.showMessageDialog(getParent(), "No item was found.", "No items", JOptionPane.WARNING_MESSAGE);
					}
					showClient(client);

				} catch (NumberFormatException e) {
					System.err.println(e.getMessage());
				}

			}
		});
		btnSearch.setBounds(354, 66, 89, 23);
		add(btnSearch);

		JLabel lblWH = new JLabel("Warehouse:");
		lblWH.setBounds(10, 69, 86, 14);
		add(lblWH);

		fieldWH = new JTextField();
		fieldWH.setText("1");
		fieldWH.setColumns(10);
		fieldWH.setBounds(93, 67, 120, 20);
		add(fieldWH);
	}

	private void showClient(Client client) {
		// show window with clinet info
		JDialog clientInfo = new PlaceInfo(client, false);
		clientInfo.setModal(true);
		clientInfo.setVisible(true);
	}
}
