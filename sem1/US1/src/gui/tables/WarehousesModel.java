package gui.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.data.WareHouse;

public class WarehousesModel extends AbstractTableModel {
	private List<WareHouse> items;

	public WarehousesModel(List<WareHouse> items) {
		super();
		this.items = items;
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return items.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		WareHouse wh = items.get(row);
		Object ret = null;
		switch (col) {
		case 0:
			ret = wh.getId();
			break;
		case 1:
			ret = wh.getName();
			break;
		case 2:
			ret = wh.getAddress();
			break;
		case 3:
			ret = wh.getStoredItemsByEan().size();
			break;
		case 4:
			ret = wh.getArrivedItems().size();
			break;
		case 5:
			ret = wh.getClientsById().size();
			break;
		default:
			break;
		}

		return ret;
	}

}
