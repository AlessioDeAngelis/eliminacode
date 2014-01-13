package it.alessio.eliminacode.common.persistance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextRepository {
	public void writeToFile(String fileName, String text) {
		try {
			// FileReader reads text files in the default encoding.
			FileWriter fileWriter = new FileWriter(fileName, false);

			// Always wrap FileReader in BufferedReader.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(text);
			// Always close files.
			bufferedWriter.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

	public String readFromFile(String fileName) {

		// This will reference one line at a time
		String text = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			text = bufferedReader.readLine();
			if (text == null) {
				System.err.println("File is empty!");
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		return text;
	}
}
