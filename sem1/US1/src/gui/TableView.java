package gui;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;

import core.Product;

import java.awt.Color;
import java.util.List;

public class TableView extends JPanel {
	private JTable table;
	private TableModel model;
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
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainContent.add(table);

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

	public void updateTable(TableModel model) {
		table.setModel(model);
		this.revalidate();
		this.repaint();
	}
}