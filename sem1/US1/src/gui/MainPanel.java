package gui;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MainPanel extends JPanel {
	private JTextField fieldEan;
	private JTextField fieldWH;

	/**
	 * Create the panel.
	 */
	public MainPanel() {
		setLayout(null);
		
		JLabel lblEanCode = new JLabel("Enter EAN code:");
		lblEanCode.setBounds(90, 148, 86, 14);
		add(lblEanCode);
		
		fieldEan = new JTextField();
		fieldEan.setBounds(186, 145, 86, 20);
		add(fieldEan);
		fieldEan.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(282, 144, 89, 23);
		add(btnSearch);
		
		JLabel lblWH = new JLabel("Warehouse:");
		lblWH.setBounds(90, 118, 86, 14);
		add(lblWH);
		
		fieldWH = new JTextField();
		fieldWH.setColumns(10);
		fieldWH.setBounds(186, 115, 86, 20);
		add(fieldWH);

	}
}
