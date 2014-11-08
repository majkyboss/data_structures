package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Db;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchProducts extends JPanel {
	private JTextField fieldEan;
	private JTextField fieldWH;
	private JTextField fieldCount;
	private JTextField textField;
	private JTextField textField_1;
	private Db database;

	/**
	 * Create the panel.
	 */
	public SearchProducts(Db databse) {
		this.database = databse;
		setLayout(null);

		JLabel lblEanCode = new JLabel("Enter EAN code:");
		lblEanCode.setBounds(10, 14, 86, 14);
		add(lblEanCode);

		fieldEan = new JTextField();
		fieldEan.setBounds(106, 11, 185, 20);
		add(fieldEan);
		fieldEan.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO: keep return object
				String ean = fieldEan.getText();
				
				database.searchProducts(Ean, dateFrom, dateTo, count, wareHouseId);
			}
		});
		btnSearch.setBounds(354, 66, 89, 23);
		add(btnSearch);

		JLabel lblWH = new JLabel("Warehouse:");
		lblWH.setBounds(10, 69, 86, 14);
		add(lblWH);

		fieldWH = new JTextField();
		fieldWH.setColumns(10);
		fieldWH.setBounds(106, 66, 120, 20);
		add(fieldWH);
		
		JLabel lblCount = new JLabel("Count:");
		lblCount.setBounds(301, 14, 46, 14);
		add(lblCount);
		
		fieldCount = new JTextField();
		fieldCount.setBounds(357, 11, 86, 20);
		add(fieldCount);
		fieldCount.setColumns(10);
		
		JLabel lblDateFrom = new JLabel("Date from:");
		lblDateFrom.setBounds(10, 42, 63, 14);
		add(lblDateFrom);
		
		textField = new JTextField();
		textField.setBounds(83, 39, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblDateTo = new JLabel("Date to:");
		lblDateTo.setBounds(179, 42, 46, 14);
		add(lblDateTo);
		
		textField_1 = new JTextField();
		textField_1.setBounds(235, 39, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);

	}
}
