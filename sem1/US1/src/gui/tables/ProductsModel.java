package gui.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.data.Product;

public class ProductsModel extends AbstractTableModel {
	private List<Product> items;

	public ProductsModel(List<Product> items) {
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
		Product p = items.get(row);
		Object ret = null;
		switch (col) {
		case 0:
			ret = p.getEan();
			break;
		case 1:
			ret = p.getName();
			break;
		case 2:
			ret = p.getProductionDate();
			break;
		case 3:
			ret = p.getMinDate();
			break;
		case 4:
			ret = p.getProductNumber();
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
