package it.alessio.tastierino.persistance;

import static org.junit.Assert.assertEquals;
import it.alessio.eliminacode.common.model.HistoryLineJPA;
import it.alessio.eliminacode.common.persistance.JPARepository;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class JpaRepositoryTest {

	private JPARepository r;
	@Before
	public void setUp(){
		r = new JPARepository();
	}
	
	
//	@Test
//	public void countTest(){
//		GregorianCalendar calendar = new GregorianCalendar();
//		calendar.setTimeInMillis(System.currentTimeMillis());
//		Date now = calendar.getTime();
//		int count = r.countHistoryLinesByDate(now);
//		assertEquals(count,0);
//		
//		HistoryLineJPA line = new HistoryLineJPA();
//		line.setId(123);
//		calendar.set(2013, 12, 13);
//		
//		line.setTimestamp(calendar.getTime());
//		r.persistHistoryLine(line);
//		count = r.countHistoryLinesByDate(calendar.getTime());
//		assertEquals(1,0);
//	}
	
	@Test
	public void persistTest(){
		HistoryLineJPA line = new HistoryLineJPA();
		GregorianCalendar calendar = new GregorianCalendar();
//		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(2011, 11, 24);
//		line.setDayMonthYear(calendar.getTime());
		line.setTimestamp(calendar.getTime());
		line.setDay(calendar.get(GregorianCalendar.DAY_OF_MONTH));
		line.setMonth(calendar.get(GregorianCalendar.MONTH));
		int year = calendar.get(GregorianCalendar.YEAR);
//		line.setAnno(calendar.get(GregorianCalendar.YEAR));
		line.setAnno(year);

		r.persistHistoryLine(line);
	}
	
	@Test
	public void retrieveAllDatesFromHistoryLinesTest(){
		List<Date> dd = r.retrieveAllDatesFromHistoryLines();
		for(Date d : dd){
			System.out.println(d);
		}
	}
//	@Test
	public void retrieveAllDatesTest(){
		List<HistoryLineJPA> lines = r.retrieveAllHistoryLines();
		for(HistoryLineJPA line : lines){
			System.out.println(line);
		}
	}
}
