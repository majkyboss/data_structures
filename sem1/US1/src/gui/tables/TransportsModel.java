package gui.tables;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.data.Client;
import core.data.ProductPlace;
import core.data.TransportProduct;
import core.data.WareHouse;

@SuppressWarnings("serial")
public class TransportsModel extends AbstractTableModel {
	private List<TransportProduct> items;
	private String[] columns = new String[] { "product id", "from", "to", "date dispatched", "date expected", "car ecv", "date arrived" };
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

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
		String sDate = "";
		Date date = null;
		switch (col) {
		case 0:
			ret = p.getPN();
			break;
		case 1:
			String departure = "";
			ProductPlace dep = p.getDeparture();
			if (dep instanceof Client) {
				departure = "client " + ((Client) dep).getId();
			} else if (dep instanceof WareHouse) {
				departure = "warehouse " + ((WareHouse) dep).getId();
			}
			ret = departure;
			break;
		case 2:
			String destination = "";
			ProductPlace dest = p.getDeparture();
			if (dest instanceof Client) {
				destination = "client " + ((Client) dest).getId();
			} else if (dest instanceof WareHouse) {
				destination = "warehouse " + ((WareHouse) dest).getId();
			}
			ret = destination;
			break;
		case 3:
			date = p.getDispatchedDate();
			if (date != null) {
				sDate = shortDateFormat.format(date);
			}
			ret = sDate;
			break;
		case 4:
			date = p.getExpectedDate();
			if (date != null) {
				sDate = shortDateFormat.format(date);
			}
			ret = sDate;
			break;
		case 5:
			ret = p.getCarEcv();
			break;
		case 6:
			date = p.getArrivedDate();
			if (date != null) {
				sDate = shortDateFormat.format(date);
			}
			ret = sDate;
			break;
		default:
			break;
		}

		return ret;
	}

}
