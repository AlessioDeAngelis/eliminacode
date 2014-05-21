package it.alessio.eliminacode.common.persistance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BatchFileManager {
	public void createTastierinoBatchFile(int number) {
		try {
			 
			String content = "java -jar tastierino.jar " +number;
 
			File file = new File("tastierino_"+number+".bat");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
