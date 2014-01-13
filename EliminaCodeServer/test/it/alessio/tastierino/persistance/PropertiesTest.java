package it.alessio.tastierino.persistance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class PropertiesTest {
	@Test
	public void propTest() throws IOException {
		Properties properties = new Properties();
		FileWriter fileWriter = null;
		try {
			FileInputStream fis = new FileInputStream("data/config2.txt");
			fileWriter = new FileWriter("data/config2.txt", true);
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String value = properties.getProperty("prova");
		System.out.println(value);
		properties.setProperty("prova", "ciao");
		value = properties.getProperty("prova");
		System.out.println( value );
		properties.store(fileWriter, "");

	}
}
