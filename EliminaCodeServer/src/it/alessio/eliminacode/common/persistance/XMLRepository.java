package it.alessio.eliminacode.common.persistance;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

public class XMLRepository {
	private String machinesFilePath;
	private String servicesFilePath;

	public XMLRepository() {
		this.machinesFilePath = "data/xml/machines.xml";
		this.servicesFilePath = "data/xml/services.xml";
	}

	/**
	 * I will persist only the id, the currentNumber and the current service
	 * */
	public void persistMachine(Machine machine) {
		try {
			Element machinesElement = new Element("machines");
			Document doc = new Document();
			doc.setRootElement(machinesElement);

			Element machineElement = new Element("machine");
			machineElement.addContent(new Element("id").setText("" + machine.getId()));
			machineElement.addContent(new Element("current_number").setText("" + machine.getNumberYouAreServing()));
			machineElement.addContent(new Element("current_service").setText("" + machine.getServiceId()));

			doc.getRootElement().addContent(machineElement);

			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(machinesFilePath));

			System.out.println("Machine Saved in " + machinesFilePath);
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
	}

	/**
	 * Id, name, color, currentNumber will be persisted
	 * */
	public void persistService(Service service) {
		try {
			Element servicesElement = new Element("services");
			Document doc = new Document();
			doc.setRootElement(servicesElement);

			Element serviceElement = new Element("service");
			serviceElement.addContent(new Element("id").setText("" + service.getId()));
			serviceElement.addContent(new Element("name").setText("" + service.getName()));
			serviceElement.addContent(new Element("color").setText("" + service.getColor()));
			serviceElement.addContent(new Element("current_number").setText("" + service.getCurrentNumber()));

			doc.getRootElement().addContent(serviceElement);

			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(servicesFilePath));

			System.out.println("Service Saved in " + servicesFilePath);
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
	}
	

	public List<Service> findAllServices() {
		List<Service> services = new ArrayList<Service>();
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(servicesFilePath);

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			// use xpath to find the node
			XPathFactory xpfac = XPathFactory.instance();
			XPathExpression xp = xpfac.compile("//services/service",
					Filters.element());
			List<Element> elements = xp.evaluate(rootNode);
			// update the node
			for (Element serviceElement : elements) {
				Service service = new Service();
				String id = serviceElement.getChildText("id");
				String name = serviceElement.getChildText("name");
				String color = serviceElement.getChildText("color");
				String currentNumber = serviceElement.getChildText("current_number");
				
				service.setId(Integer.parseInt(id));
				service.setName(name);
				service.setColor(color);
				service.setCurrentNumber(currentNumber);
				
				services.add(service);
			}

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(servicesFilePath));

			// xmlOutput.output(doc, System.out);

			System.out.println("Service updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return services;
	}
	
	public List<Machine> findAllMachines(){
		List<Machine> machines = new ArrayList<Machine>();
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(machinesFilePath);

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			// use xpath to find the node
			XPathFactory xpfac = XPathFactory.instance();
			XPathExpression xp = xpfac.compile("//machines/machine", Filters.element());
			List<Element> elements = xp.evaluate(rootNode);
			// update the node, in particular the current number and service
			for (Element machineElement : elements) {
				Machine machine = new Machine();
				String id = machineElement.getChildText("id");
				String currentNumber = machineElement.getChildText("currentNumber");
				String currentServiceId = machineElement.getChildText("current_service");
				
				machine.setActive(false);
				machine.setId(Integer.parseInt(id));
				machine.setNumberYouAreServing(Integer.parseInt(currentNumber));
				machine.setServiceId(Integer.parseInt(currentServiceId));
				
				machines.add(machine);			
			}

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(machinesFilePath));

			// xmlOutput.output(doc, System.out);
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return machines;
	}

	public void updateService(Service service) {
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(servicesFilePath);

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			// use xpath to find the node
			XPathFactory xpfac = XPathFactory.instance();
			XPathExpression xp = xpfac.compile("//services/service[id='" + service.getId() + "']",
					Filters.element());
			List<Element> elements = xp.evaluate(rootNode);
			// update the node
			for (Element serviceElement : elements) {
				serviceElement.getChild("current_number").setText("" + service.getCurrentNumber());
			}

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(servicesFilePath));

			// xmlOutput.output(doc, System.out);

			System.out.println("Service updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}

	public void updateMachine(Machine machine) {
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(machinesFilePath);

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			// use xpath to find the node
			XPathFactory xpfac = XPathFactory.instance();
			XPathExpression xp = xpfac.compile("//machines/machine[id='" + machine.getId() + "']", Filters.element());
			List<Element> elements = xp.evaluate(rootNode);
			// update the node, in particular the current number and service
			for (Element machineElement : elements) {
				machineElement.getChild("current_number").setText("" + machine.getNumberYouAreServing());
				machineElement.getChild("current_service").setText("" + machine.getServiceId());
			}

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(machinesFilePath));

			// xmlOutput.output(doc, System.out);

			System.out.println("Machine updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}
}
