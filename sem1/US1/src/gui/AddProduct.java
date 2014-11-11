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
import core.data.Product;

public class AddProduct extends JPanel {
	private JTextField fieldEan;
	private JTextField fieldWH;
	private JTextField fieldProductionDate;
	private JTextField fieldMinDate;
	private StorageDatabase database;
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
	private JTextField fieldName;
	private JTextField fieldProductionNumber;
	private JTextField fieldCost;

	/**
	 * Create the panel.
	 */
	public AddProduct(StorageDatabase db) {
		this.database = db;
		setLayout(null);

		JLabel lblEanCode = new JLabel("Enter EAN code:");
		lblEanCode.setBounds(10, 39, 90, 14);
		add(lblEanCode);

		fieldEan = new JTextField();
		fieldEan.setBounds(118, 36, 173, 20);
		add(fieldEan);
		fieldEan.setColumns(10);

		JButton btnAdd = new JButton("Add product");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String ean = fieldEan.getText();
				String name = fieldName.getText();

				try {

					Date productionDate = shortDateFormat.parse(fieldProductionDate.getText());
					Date minDate = shortDateFormat.parse(fieldMinDate.getText());
					int productionNumber = Integer.parseInt(fieldProductionNumber.getText());
					double cost = Double.parseDouble(fieldCost.getText());
					int wareHouseId = Integer.parseInt(fieldWH.getText());

					Product p = new Product();
					p.setEan(ean);
					p.setName(name);
					p.setProductionDate(productionDate);
					p.setMinDate(minDate);
					p.setProductNumber(productionNumber);
					p.setCost(cost);

					boolean added = database.addProduct(wareHouseId, p);

					if (added) {
						JOptionPane.showMessageDialog(getParent(), "item (product number:" + p.getProductNumber() + ") was added");
					} else {
						JOptionPane.showMessageDialog(getParent(), "item was not added", "Insert error", JOptionPane.ERROR_MESSAGE);
					}

				} catch (
						ParseException
						| NumberFormatException e) {
					System.err.println(e.getMessage());
				}

			}
		});
		btnAdd.setBounds(342, 174, 98, 23);
		add(btnAdd);

		JLabel lblWH = new JLabel("Warehouse:");
		lblWH.setBounds(10, 178, 86, 14);
		add(lblWH);

		fieldWH = new JTextField();
		fieldWH.setText("1");
		fieldWH.setColumns(10);
		fieldWH.setBounds(93, 176, 120, 20);
		add(fieldWH);

		JLabel lblDateFrom = new JLabel("Production date:");
		lblDateFrom.setBounds(10, 67, 107, 14);
		add(lblDateFrom);

		fieldProductionDate = new JTextField(shortDateFormat.format(new Date()));
		fieldProductionDate.setBounds(164, 64, 86, 20);
		add(fieldProductionDate);
		fieldProductionDate.setColumns(10);

		JLabel lblDateTo = new JLabel("Minimum durability date:");
		lblDateTo.setBounds(10, 95, 142, 14);
		add(lblDateTo);

		fieldMinDate = new JTextField(shortDateFormat.format(new Date()));
		fieldMinDate.setBounds(164, 92, 86, 20);
		add(fieldMinDate);
		fieldMinDate.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 11, 46, 14);
		add(lblName);

		JLabel lblCost = new JLabel("Cost:");
		lblCost.setBounds(10, 151, 46, 14);
		add(lblCost);

		fieldName = new JTextField();
		fieldName.setBounds(66, 8, 225, 20);
		add(fieldName);
		fieldName.setColumns(10);

		JLabel lblProductionNumber = new JLabel("Production number:");
		lblProductionNumber.setBounds(10, 123, 120, 14);
		add(lblProductionNumber);

		fieldProductionNumber = new JTextField();
		fieldProductionNumber.setText("1");
		fieldProductionNumber.setBounds(163, 120, 151, 20);
		add(fieldProductionNumber);
		fieldProductionNumber.setColumns(10);

		fieldCost = new JTextField();
		fieldCost.setText("1");
		fieldCost.setBounds(66, 148, 86, 20);
		add(fieldCost);
		fieldCost.setColumns(10);
	}
}
