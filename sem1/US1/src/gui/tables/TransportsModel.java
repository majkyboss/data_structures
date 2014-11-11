package gui.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.data.TransportProduct;

public class TransportsModel extends AbstractTableModel {
	List<TransportProduct> items;
	String[] columns = new String[] { "product id", "from", "to", "date dispatched", "date expected", "car ecv", "date arrived" };

	public TransportsModel(List<TransportProduct> items) {
		super();
		this.items = items;
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
		return items.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		TransportProduct p = items.get(row);
		Object ret = null;
		switch (col) {
		case 0:
			ret = p.getProductId();
			break;
		case 1:
			ret = p.getDeparture();
			break;
		case 2:
			ret = p.getDestination();
			break;
		case 3:
			ret = p.getDispatchedDate();
			break;
		case 4:
			ret = p.getExpectedDate();
			break;
		case 5:
			ret = p.getCarEcv();
			break;
		case 6:
			ret = p.getArrivedDate();
			break;
		default:
			break;
		}

		return ret;
	}

}
