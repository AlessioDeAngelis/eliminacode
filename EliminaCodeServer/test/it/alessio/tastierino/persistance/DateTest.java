package it.alessio.tastierino.persistance;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTest {
	public static void main(String[] args) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeInMillis(System.currentTimeMillis());
		Date date = c.getTime();
		c.get(GregorianCalendar.MONTH);
		System.out.println(date.toString());
		String[] dmy = date.toString().split(" ");
		String day = dmy[0];
		String month = dmy[1];
		String year = dmy[2];
		System.out.println(day+"-"+month + "-" + year);

	}
}
