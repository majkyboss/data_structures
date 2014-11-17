package tests;

import static org.junit.Assert.*;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import core.data.Product;
import core.data.csv.mapping.ProductCSVStrategy;

public class TestCSV {

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void test() throws Exception {

		CsvToBean csv = new CsvToBean();

		String csvFilename = "data.csv";
		CSVReader csvReader = new CSVReader(new FileReader(csvFilename));

		// Set column mapping strategy
		List list = csv.parse(setColumMapping(csvReader), csvReader);

		int sum = 0;
		for (Object object : list) {
			Employee employee = (Employee) object;
			System.out.println(employee);
			sum += employee.getAge();
		}

		System.out.println("age sum: " + sum);

		assertTrue(true);
		// fail("Not yet implemented");
	}

	private ColumnPositionMappingStrategy<Employee> setColumMapping(CSVReader csvReader) throws IOException {
		ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<Employee>() {

			@Override
			public Employee createBean() throws InstantiationException, IllegalAccessException {
				// just creating empty object
				return new Employee();
			}

			@Override
			public PropertyDescriptor findDescriptor(int col) throws IntrospectionException {
				// find the name of property in class
				// ! class has to have methods for setting and getting value in
				// format setPropertyName() and getPropertyName()
				PropertyDescriptor pd = new PropertyDescriptor(header[col], Employee.class);
				return pd;
			}

			@Override
			protected PropertyDescriptor findDescriptor(String name) throws IntrospectionException {
				int col = getColumnIndex(name);
				return findDescriptor(col);
			}

			@Override
			public void captureHeader(CSVReader reader) throws IOException {
				// not sure if this will work everytime
				// read first line of file, resp. reader
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

		};
		return strategy;
	}

	@Test
	public void testLoadProduct() throws IOException {
		// Build reader instance
		CSVReader reader = new CSVReader(new FileReader("product.csv"), ',', '"', 1);

		// Read all rows at once
		List<String[]> allRows = reader.readAll();

		// Read CSV line by line and use the string array as you want
		for (String[] row : allRows) {
			System.out.println(Arrays.toString(row));
		}


		System.out.println("age sum: ");
	}
}
