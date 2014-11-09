package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import core.Client;
import core.ProductPlace;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlaceInfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fieldName;
	private JTextField fieldID;
	private JTextField fieldAddress;
	private JTextField fieldWareHouse;
	private ProductPlace place;

	/**
	 * Create the dialog.
	 */
	public PlaceInfo(ProductPlace place, boolean editable) {
		this.place = place;
		setTitle("Place info");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Name:");
			lblName.setBounds(10, 11, 46, 14);
			contentPanel.add(lblName);
		}
		{
			fieldName = new JTextField(place.getName());
			fieldName.setEditable(editable);
			fieldName.setBounds(88, 8, 86, 20);
			contentPanel.add(fieldName);
			fieldName.setColumns(10);
		}
		{
			fieldID = new JTextField(place.getId() + "");
			fieldID.setEditable(editable);
			fieldID.setBounds(88, 39, 86, 20);
			contentPanel.add(fieldID);
			fieldID.setColumns(10);
		}
		{
			fieldAddress = new JTextField(place.getAddress());
			fieldAddress.setEditable(editable);
			fieldAddress.setBounds(88, 70, 86, 20);
			contentPanel.add(fieldAddress);
			fieldAddress.setColumns(10);
		}
		{
			if (place instanceof Client) {
				JLabel lblWarehouse = new JLabel("Warehouse:");
				lblWarehouse.setBounds(10, 104, 68, 14);
				contentPanel.add(lblWarehouse);

				String s = "1";// ((Client) place).getWarehouse().getId() + "";
				fieldWareHouse = new JTextField(s);
				fieldWareHouse.setEditable(editable);
				fieldWareHouse.setBounds(88, 101, 86, 20);
				contentPanel.add(fieldWareHouse);
				fieldWareHouse.setColumns(10);
			}

		}
		{
			JLabel lblId = new JLabel("Id:");
			lblId.setBounds(10, 42, 46, 14);
			contentPanel.add(lblId);
		}
		{
			JLabel lblAddress = new JLabel("Address:");
			lblAddress.setBounds(10, 73, 66, 14);
			contentPanel.add(lblAddress);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

}
