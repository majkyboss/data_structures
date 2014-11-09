package tests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

public class TestDateFormat {

	@Test
	public void test() {
		DateFormat shortDf = DateFormat.getDateInstance(DateFormat.SHORT);

		DateFormat mediumDf = DateFormat.getDateInstance(DateFormat.MEDIUM);
		DateFormat longDf = DateFormat.getDateInstance(DateFormat.LONG);
		DateFormat fullDf = DateFormat.getDateInstance(DateFormat.FULL);
		System.out.println(shortDf.format(new Date()));
		System.out.println(mediumDf.format(new Date()));
		System.out.println(longDf.format(new Date()));
		System.out.println(fullDf.format(new Date()));

		// parsing
		try {
			Date date = shortDf.parse("22.12.2014");
			System.out.println(date);
			
			date = shortDf.parse("12/12/2006");
			System.out.println(date);
		} catch (ParseException e) {
		}
	}

}
