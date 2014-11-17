package gui.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.data.ProductValueItem;

public class ProductsValueModel extends AbstractTableModel {
	private List<ProductValueItem> items;
	private String[] colNames = new String[] { "EAN", "count", "value" };

	public ProductsValueModel(List<ProductValueItem> items) {
		super();
		this.items = items;
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}
	
	@Override
	public String getColumnName(int arg0) {
		return colNames[arg0];
	}

	@Override
	public int getRowCount() {
		return items.size() + 1;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object ret = null;
		if (row >= items.size()) {
			switch (col) {
			case 0:
				ret = "";
				break;
			case 1:
				ret = "Summary:";
				break;
			case 2:
				double value = 0.0;
				for (ProductValueItem item : items) {
					value += item.getValue();
				}
				ret = value;
				break;
			default:
				break;
			}
		} else {
			ProductValueItem p = items.get(row);
			switch (col) {
			case 0:
				ret = p.getEan();
				break;
			case 1:
				ret = p.getCount();
				break;
			case 2:
				ret = p.getValue();
				break;
			default:
				break;
			}
		}

		return ret;
	}

}
