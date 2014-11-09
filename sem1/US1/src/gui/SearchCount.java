package gui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Product;
import core.StorageDatabase;

public class SearchCount extends JPanel {
	private JTextField fieldEan;
	private JTextField fieldWH;
	private StorageDatabase database;
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

	/**
	 * Create the panel.
	 */
	public SearchCount(StorageDatabase db) {
		this.database = db;
		setLayout(null);

		JLabel lblEanCode = new JLabel("Enter EAN code:");
		lblEanCode.setBounds(10, 14, 90, 14);
		add(lblEanCode);

		fieldEan = new JTextField();
		fieldEan.setBounds(118, 11, 173, 20);
		add(fieldEan);
		fieldEan.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String ean = fieldEan.getText();

				try {

					int wareHouseId = Integer.parseInt(fieldWH.getText());

					int count = database.searchCount(ean, wareHouseId);
					showCountInfo(count);

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

	private void showCountInfo(int count) {
		JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Number of found items: " + count, "Found items", JOptionPane.PLAIN_MESSAGE);
	}

}
