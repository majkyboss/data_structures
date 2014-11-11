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

public class TransportEnd extends JPanel {
	private JTextField fieldProductNum;
	private JTextField fieldDateFrom;
	private StorageDatabase database;
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

	/**
	 * Create the panel.
	 */
	public TransportEnd(StorageDatabase db) {
		this.database = db;
		setLayout(null);

		JLabel lblProductNum = new JLabel("Product number:");
		lblProductNum.setBounds(10, 14, 96, 14);
		add(lblProductNum);

		fieldProductNum = new JTextField();
		fieldProductNum.setBounds(118, 11, 173, 20);
		add(fieldProductNum);
		fieldProductNum.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Date arrivalDate = shortDateFormat.parse(fieldDateFrom.getText());

					int productNum = Integer.parseInt(fieldProductNum.getText());

					boolean transportEnded = database.endTransport(productNum, arrivalDate);
					if (!transportEnded) {
						JOptionPane.showMessageDialog(getParent(), "Transport could not be ended.", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(getParent(), "Transport was succesfuly ended.", "Transport ended", JOptionPane.PLAIN_MESSAGE);
					}

				} catch (
						ParseException
						| NumberFormatException e) {
					System.err.println(e.getMessage());
				}

			}
		});
		btnSearch.setBounds(354, 66, 89, 23);
		add(btnSearch);

		JLabel lblDateFrom = new JLabel("Arrival date:");
		lblDateFrom.setBounds(10, 42, 96, 14);
		add(lblDateFrom);

		fieldDateFrom = new JTextField(shortDateFormat.format(new Date()));
		fieldDateFrom.setBounds(118, 39, 86, 20);
		add(fieldDateFrom);
		fieldDateFrom.setColumns(10);
	}

}
