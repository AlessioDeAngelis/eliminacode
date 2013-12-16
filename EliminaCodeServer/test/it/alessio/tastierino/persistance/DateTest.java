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
	}
}
