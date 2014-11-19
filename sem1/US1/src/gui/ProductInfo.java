package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import core.data.Client;
import core.data.Product;
import core.data.ProductPlace;
import core.data.TransportProduct;
import core.data.WareHouse;

@SuppressWarnings("serial")
public class ProductInfo extends JPanel {
	private JTextField fieldEan;
	private JTextField fieldWH;
	private JTextField fieldProductionDate;
	private JTextField fieldMinDate;
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
	private JTextField fieldName;
	private JTextField fieldProductionNumber;
	private JTextField fieldCost;

	private JPanel history;
	private JTextField fieldFrom;
	private JTextField fieldTo;
	private JTextField fieldDispatched;
	private JTextField fieldExpected;

	/**
	 * Create the panel.
	 */
	public ProductInfo(JPanel lastPanel, Product product) {

		// --------------------------------

		this.history = lastPanel;
		setLayout(new BorderLayout(0, 0));

		JPanel mainContent = new JPanel();
		add(mainContent, BorderLayout.CENTER);
		mainContent.setLayout(null);

		{
			JLabel lblEanCode = new JLabel("EAN code:");
			lblEanCode.setBounds(10, 39, 90, 14);
			mainContent.add(lblEanCode);

			fieldEan = new JTextField(product.getEan());
			fieldEan.setEditable(false);
			fieldEan.setBounds(118, 36, 173, 20);
			mainContent.add(fieldEan);
			fieldEan.setColumns(10);

			JLabel lblWH = new JLabel("Warehouse:");
			lblWH.setBounds(10, 178, 86, 14);
			mainContent.add(lblWH);

			fieldWH = new JTextField(product.getCurrentPlace() != null ? product.getCurrentPlace().getId() + "" : "");
			fieldWH.setEditable(false);
			fieldWH.setColumns(10);
			fieldWH.setBounds(93, 176, 120, 20);
			mainContent.add(fieldWH);

			JLabel lblDateFrom = new JLabel("Production date:");
			lblDateFrom.setBounds(10, 67, 107, 14);
			mainContent.add(lblDateFrom);

			fieldProductionDate = new JTextField(shortDateFormat.format(product.getProductionDate()));
			fieldProductionDate.setEditable(false);
			fieldProductionDate.setBounds(164, 64, 86, 20);
			mainContent.add(fieldProductionDate);
			fieldProductionDate.setColumns(10);

			JLabel lblDateTo = new JLabel("Minimum durability date:");
			lblDateTo.setBounds(10, 95, 142, 14);
			mainContent.add(lblDateTo);

			fieldMinDate = new JTextField(shortDateFormat.format(product.getMinDate()));
			fieldMinDate.setEditable(false);
			fieldMinDate.setBounds(164, 92, 86, 20);
			mainContent.add(fieldMinDate);
			fieldMinDate.setColumns(10);

			JLabel lblName = new JLabel("Name:");
			lblName.setBounds(10, 11, 46, 14);
			mainContent.add(lblName);

			JLabel lblCost = new JLabel("Cost:");
			lblCost.setBounds(10, 151, 46, 14);
			mainContent.add(lblCost);

			fieldName = new JTextField(product.getName());
			fieldName.setEditable(false);
			fieldName.setBounds(66, 8, 225, 20);
			mainContent.add(fieldName);
			fieldName.setColumns(10);

			JLabel lblProductionNumber = new JLabel("Production number:");
			lblProductionNumber.setBounds(10, 123, 120, 14);
			mainContent.add(lblProductionNumber);

			fieldProductionNumber = new JTextField(product.getProductNumber());
			fieldProductionNumber.setEditable(false);
			fieldProductionNumber.setBounds(163, 120, 151, 20);
			mainContent.add(fieldProductionNumber);
			fieldProductionNumber.setColumns(10);

			fieldCost = new JTextField(product.getCost() + "");
			fieldCost.setEditable(false);
			fieldCost.setBounds(66, 148, 86, 20);
			mainContent.add(fieldCost);
			fieldCost.setColumns(10);

			TransportProduct transport = product.getTransport();
			if (transport != null) {

				String departureId = "";
				ProductPlace departure = transport.getDeparture();
				if (departure instanceof Client) {
					departureId = ((Client) departure).getId();
				} else if (departure instanceof WareHouse) {
					departureId = ((WareHouse) departure).getId() + "";
				}
				StringBuilder typeTo = new StringBuilder(" to ");
				String destinationId = "";
				ProductPlace destination = transport.getDestination();
				if (destination instanceof Client) {
					destinationId = ((Client) destination).getId();
					typeTo.append("Client");
				} else if (destination instanceof WareHouse) {
					destinationId = ((WareHouse) destination).getId() + "";
					typeTo.append("Warehouse");
				}

				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Product export" + typeTo.toString(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel.setBounds(10, 203, 430, 80);
				mainContent.add(panel);
				panel.setLayout(null);

				JLabel lblFrom = new JLabel("From:");
				lblFrom.setBounds(10, 22, 46, 14);
				panel.add(lblFrom);

				JLabel lblTo = new JLabel("To:");
				lblTo.setBounds(10, 47, 46, 14);
				panel.add(lblTo);

				fieldFrom = new JTextField(departureId);
				fieldFrom.setEditable(false);
				fieldFrom.setBounds(66, 19, 86, 20);
				panel.add(fieldFrom);
				fieldFrom.setColumns(10);

				fieldTo = new JTextField(destinationId);
				fieldTo.setEditable(false);
				fieldTo.setBounds(66, 44, 86, 20);
				panel.add(fieldTo);
				fieldTo.setColumns(10);

				JLabel lblDispacthedDate = new JLabel("Dispacthed date:");
				lblDispacthedDate.setBounds(162, 22, 86, 14);
				panel.add(lblDispacthedDate);

				JLabel lblExpectedDate = new JLabel("Expected date:");
				lblExpectedDate.setBounds(162, 47, 86, 14);
				panel.add(lblExpectedDate);

				fieldDispatched = new JTextField(shortDateFormat.format(transport.getDispatchedDate()));
				fieldDispatched.setEditable(false);
				fieldDispatched.setBounds(258, 19, 86, 20);
				panel.add(fieldDispatched);
				fieldDispatched.setColumns(10);

				fieldExpected = new JTextField(shortDateFormat.format(transport.getExpectedDate()));
				fieldExpected.setEditable(false);
				fieldExpected.setBounds(258, 44, 86, 20);
				panel.add(fieldExpected);
				fieldExpected.setColumns(10);

			}

		}

		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Container parent = getParent();
				parent.removeAll();
				parent.add(history);
				parent.revalidate();
				parent.repaint();
			}
		});
		topPanel.add(btnBack);
	}
}
