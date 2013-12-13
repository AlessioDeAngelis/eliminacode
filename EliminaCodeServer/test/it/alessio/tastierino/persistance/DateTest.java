package it.alessio.tastierino.persistance;

import static org.junit.Assert.assertEquals;
import it.alessio.eliminacode.common.model.HistoryLine;
import it.alessio.eliminacode.common.persistance.JDBCRepository;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DateTest {

	private JDBCRepository r;
	@Before
	public void setUp(){
		r = new JDBCRepository();
	}
	
//	@Test
	public void createTableTest(){
		r.createHistoryLinesTable();
	}
	
	
	
	@Test
	public void countTest(){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(System.currentTimeMillis());
		Date now = calendar.getTime();
		int count = r.countHistoryLinesByDate(now);
		assertEquals(count,0);
		
		HistoryLine line = new HistoryLine();
		line.setId(123);
		calendar.set(2013, 12, 13);
		
		line.setTimestamp(calendar.getTime());
		r.persistHistoryLine(line);
		count = r.countHistoryLinesByDate(calendar.getTime());
		assertEquals(1,0);
	}
	
	@Test
	public void retrieveAllDatesTest(){
		List<HistoryLine> lines = r.findAllHistoryLines();
		for(HistoryLine line : lines){
			System.out.println(line);
		}
	}
}
