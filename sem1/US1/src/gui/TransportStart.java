package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.StorageDatabase;

@SuppressWarnings("serial")
public class TransportStart extends JPanel {
	private JTextField fieldProductNum;
	private StorageDatabase database;
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
	private JTextField fieldExpectedDate;
	private JTextField fieldPlaceTo;

	/**
	 * Create the panel.
	 */
	public TransportStart(StorageDatabase db, final boolean toClient) {
		this.database = db;
		setLayout(null);

		JLabel lblEanCode = new JLabel("Product Number:");
		lblEanCode.setBounds(10, 14, 104, 14);
		add(lblEanCode);

		fieldProductNum = new JTextField();
		fieldProductNum.setText("1");
		fieldProductNum.setBounds(132, 11, 173, 20);
		add(fieldProductNum);
		fieldProductNum.setColumns(10);

		JButton btnSearch = new JButton("Make Transport");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Date expectedDate = null;
					if (!fieldExpectedDate.getText().trim().isEmpty()) {
						expectedDate = shortDateFormat.parse(fieldExpectedDate.getText());
					}
					int productNumber = Integer.parseInt(fieldProductNum.getText());

					boolean transportMade = false;
					if (!toClient) {
						int wareHoseId = Integer.parseInt(fieldPlaceTo.getText());
						transportMade = database.makeTransportToWareHouse(productNumber, wareHoseId, expectedDate);
					} else {
						String clientId = fieldPlaceTo.getText();
						transportMade = database.makeTransportToClient(productNumber, clientId, expectedDate);
					}

					if (transportMade) {
						JOptionPane.showMessageDialog(getParent(), "transport was created");
					} else {
						JOptionPane.showMessageDialog(getParent(), "transport was not created", "Error", JOptionPane.ERROR_MESSAGE);
					}

				} catch (
						ParseException
						| NumberFormatException e) {
					System.err.println(e.getMessage());
				}

			}
		});
		btnSearch.setBounds(298, 99, 142, 23);
		add(btnSearch);

		fieldExpectedDate = new JTextField(shortDateFormat.format(new Date()));
		fieldExpectedDate.setBounds(132, 73, 86, 20);
		add(fieldExpectedDate);
		fieldExpectedDate.setColumns(10);

		JLabel lblExpectedDate = new JLabel("Expected date:");
		lblExpectedDate.setBounds(10, 76, 90, 14);
		add(lblExpectedDate);

		fieldPlaceTo = new JTextField();
		fieldPlaceTo.setText("1");
		fieldPlaceTo.setBounds(219, 43, 86, 20);
		add(fieldPlaceTo);
		fieldPlaceTo.setColumns(10);

		JLabel lblToPlace = new JLabel();
		if (toClient) {
			lblToPlace.setText("To client (id-string):");
		} else {
			lblToPlace.setText("To Warehouse (id-integer):");
		}
		lblToPlace.setBounds(10, 45, 185, 14);
		add(lblToPlace);
	}

}
