package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class TableView extends JPanel {
	private JTable table;
	private JPanel history;

	/**
	 * Create the panel.
	 */
	public TableView(JPanel lastPanel) {
		this.history = lastPanel;
		setLayout(new BorderLayout(0, 0));

		JPanel mainContent = new JPanel();
		add(mainContent, BorderLayout.CENTER);
		mainContent.setLayout(new BorderLayout(2, 2));

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, }, new String[] { "New column", "New column", "New column" }));
		// mainContent.add(table, BorderLayout.SOUTH);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));

		JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(350, 270));
		mainContent.add(scrollPane, BorderLayout.CENTER);

		if (lastPanel != null) {

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

	public void updateTable(TableModel model) {
		table.setModel(model);
		this.revalidate();
		this.repaint();
	}
}
