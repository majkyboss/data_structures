package gui.tables;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.data.Product;

@SuppressWarnings("serial")
public class ProductsModel extends AbstractTableModel {
	private List<Product> items;
	private String[] columns = new String[] { "ean code", "name", "product num.", "production date", "min. date", "cost" };
	private DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

	public ProductsModel(List<Product> items) {
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
		Product p = items.get(row);
		Object ret = null;
		String sDate = "";
		Date date = null;
		switch (col) {
		case 0:
			ret = p.getEan();
			break;
		case 1:
			ret = p.getName();
			break;
		case 2:
			ret = p.getProductNumber();
			break;
		case 3:
			date = p.getProductionDate();
			if (date != null) {
				sDate = shortDateFormat.format(date);
			}
			ret = sDate;
			break;
		case 4:
			date = p.getMinDate();
			if (date != null) {
				sDate = shortDateFormat.format(date);
			}
			ret = sDate;
			break;
		case 5:
			ret = p.getCost();
			break;
		default:
			break;
		}

		return ret;
	}

}
