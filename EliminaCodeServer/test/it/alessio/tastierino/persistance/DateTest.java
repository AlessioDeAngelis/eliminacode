package it.alessio.tastierino.persistance;

import java.util.GregorianCalendar;

public class DateTest {
	public static void main(String[] args) {
		// Date date = new Date(System.get);
//		try {
//			Date date = (Date) DateFormat.getInstance().parse(""+System.currentTimeMillis());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		 GregorianCalendar gc = new GregorianCalendar();
		 gc.setTimeInMillis(System.currentTimeMillis());
		 gc.getTime();
		 System.out.println(gc.getTime());
	}
}
