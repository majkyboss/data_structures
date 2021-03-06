package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.StorageDatabase;

@SuppressWarnings("serial")
public class DeleteProduct extends JPanel {
	private JTextField fieldProductNum;
	private StorageDatabase database;

	/**
	 * Create the panel.
	 */
	public DeleteProduct(StorageDatabase db) {
		this.database = db;
		setLayout(null);

		JLabel lblProductNum = new JLabel("Product Number:");
		lblProductNum.setBounds(10, 14, 109, 14);
		add(lblProductNum);

		fieldProductNum = new JTextField();
		fieldProductNum.setBounds(131, 11, 173, 20);
		add(fieldProductNum);
		fieldProductNum.setColumns(10);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					int productNum = Integer.parseInt(fieldProductNum.getText());

					boolean deleted = database.deleteProduct(productNum);
					if (deleted) {
						JOptionPane.showMessageDialog(getParent(), "item (product number:" + productNum + ") was deleted");
					} else {
						JOptionPane.showMessageDialog(getParent(), "item was not deleted", "Delete error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					System.err.println(e.getMessage());
				}

			}
		});
		btnDelete.setBounds(349, 43, 89, 23);
		add(btnDelete);
	}

}
