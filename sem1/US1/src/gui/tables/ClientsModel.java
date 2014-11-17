package gui.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.data.Client;

public class ClientsModel extends AbstractTableModel {
	private List<Client> clients;
	private String[] columns = new String[] { "client id", "name", "address",};

	public ClientsModel(List<Client> clients) {
		super();
		this.clients = clients;
	}

	@Override
	public String getColumnName(int arg0) {
		return columns[arg0];
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return clients.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Client c = clients.get(row);
		Object ret = null;
		switch (col) {
		case 0:
			ret = c.getId();
			break;
		case 1:
			ret = c.getName();
			break;
		case 2:
			ret = c.getAddress();
			break;
		default:
			break;
		}

		return ret;
	}

}
