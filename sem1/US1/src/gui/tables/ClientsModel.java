package gui.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.data.Client;

public class ClientsModel extends AbstractTableModel {
	private List<Client> clients;

	public ClientsModel(List<Client> clients) {
		super();
		this.clients = clients;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return clients.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Client p = clients.get(row);
		Object ret = null;
		switch (col) {
		case 0:
			ret = p.getId();
			break;
		case 1:
			ret = p.getName();
			break;
		case 2:
			ret = p.getAddress();
			break;
		default:
			break;
		}

		return ret;
	}

}
