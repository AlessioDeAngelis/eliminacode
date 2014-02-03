package it.alessio.tastierino.persistance;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.persistance.XMLRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XmlTest {
	public static void main(String[] args) {
		xpath();
	}
	
	public static void write(){
		try {
			Machine machine = new Machine();
			machine.setChiave(1);
			machine.setId(0);
			machine.setActive(true);
			machine.setServiceId(3);
			machine.setNumberYouAreServing(33);

			Element machinesElement = new Element("machines");
			Document doc = new Document();
			doc.setRootElement(machinesElement);

			Element machineElement = new Element("machine");
			machineElement.addContent(new Element("id").setText("" + machine.getId()));
			machineElement.addContent(new Element("number_serving").setText("" + machine.getNumberYouAreServing()));
			machineElement.addContent(new Element("service_id").setText("" + machine.getServiceId()));

			doc.getRootElement().addContent(machineElement);

			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("data/xml/machines.xml"));

			System.out.println("File Saved!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
	}
	
	public static void update(){
		  try {
			  
				SAXBuilder builder = new SAXBuilder();
				File xmlFile = new File("data/xml/machines.xml");
		 
				Document doc = (Document) builder.build(xmlFile);
				Element rootNode = doc.getRootElement();
		 
				// update staff id attribute
				Element machineElement = rootNode.getChild("machine");
				machineElement.getChild("number_serving").setText("50");			
		 
				XMLOutputter xmlOutput = new XMLOutputter();
		 
				// display nice nice
				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(doc, new FileWriter("data/xml/machines.xml"));
		 
				// xmlOutput.output(doc, System.out);
		 
				System.out.println("File updated!");
			  } catch (IOException io) {
				io.printStackTrace();
			  } catch (JDOMException e) {
				e.printStackTrace();
			  }
	}
	
	public static void xpath(){
		Machine machine = new Machine();
		machine.setChiave(1);
		machine.setId(0);
		machine.setActive(true);
		machine.setServiceId(3);
		machine.setNumberYouAreServing(33);
		XMLRepository repo = new XMLRepository();
		repo.updateMachine(machine);
	}
}
