package core.data.csv.mapping;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;

import core.data.Product;

public class ProductCSVStrategy extends ColumnPositionMappingStrategy<Product> {
	@Override
	public Product createBean() throws InstantiationException, IllegalAccessException {
		return new Product();
	}

	@Override
	public PropertyDescriptor findDescriptor(int col) throws IntrospectionException {
		PropertyDescriptor pd = null;
		// if (header[col].equals("minDate")) {
		// pd = new PropertyDescriptor(header[col], Product.class, "getMinDate",
		// "parseMinDate");
		// } else if (header[col].equals("productionDate")) {
		// // pd = new PropertyDescriptor(header[col], Product.class,
		// // "getProductionDate", s);
		// Method writeMethod;
		// try {
		// writeMethod = Product.class.getMethod("parseProdDate", new Class[] {
		// String.class });
		// Method readMethod = Product.class.getMethod("getProductionDate",
		// null);
		// pd = new PropertyDescriptor(header[col], readMethod, writeMethod);
		// } catch (
		// NoSuchMethodException
		// | SecurityException e) {
		// e.printStackTrace();
		// }
		//
		// } else {
		pd = new PropertyDescriptor(header[col], Product.class);
		// }
		return pd;
	}

	@Override
	protected PropertyDescriptor findDescriptor(String name) throws IntrospectionException {
		int col = getColumnIndex(name);
		return findDescriptor(col);
	}

	@Override
	public void captureHeader(CSVReader reader) throws IOException {
		header = reader.readNext();
	}

	@Override
	public Integer getColumnIndex(String name) {
		for (int i = 0; i < header.length; i++) {
			if (name.equals(header[i])) {
				return i;
			}
		}
		return 0;
	}

	@Override
	protected String getColumnName(int col) {
		return header[col];
	}
}
