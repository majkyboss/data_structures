package gui.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.Product;
import core.ProductTransport;

public class TransportsModel extends AbstractTableModel {
	List<ProductTransport> items;
	
	public TransportsModel(List<ProductTransport> items) {
		super();
		this.items = items;
	}

	@Override
	public int getColumnCount() {
		//product id
		//from
		//to
		//date dispatched
		//date expected
		//car ecv
		return 6;
	}

	@Override
	public int getRowCount() {
		return items.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		ProductTransport p = items.get(row);
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
		default:
			break;
		}

		return ret;
	}

}
