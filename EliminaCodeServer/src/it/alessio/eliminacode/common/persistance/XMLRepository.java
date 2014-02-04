package it.alessio.eliminacode.common.persistance;

import it.alessio.eliminacode.common.model.HistoryLine;
import it.alessio.eliminacode.common.model.ItalianEnglishMonthConverter;
import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private String historyFilePath;

	public XMLRepository() {
		this.machinesFilePath = "data/xml/machines.xml";
		this.servicesFilePath = "data/xml/services.xml";
		this.historyFilePath = "data/xml/history.xml";
	}

	public void createXmlFile(String rootElementName, String dirPath, String fileName) {
		// creates the dirs
		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		// creates the xml file with the given root element
		Element rootElement = new Element(rootElementName);
		Document doc = new Document();
		doc.setRootElement(rootElement);
		XMLOutputter outputter = new XMLOutputter();
		try {
			outputter.output(doc, new FileWriter(dirPath + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * I will persist only the id, the currentNumber and the current service
	 * */
	public void persistMachine(Machine machine) {
		try {
			// Element machinesElement = new Element("machines");
			// Document doc = new Document();
			// doc.setRootElement(machinesElement);
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(machinesFilePath);
			Document doc = (Document) builder.build(xmlFile);

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

			// System.out.println("Machine Saved in " + machinesFilePath);
		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Id, name, color, currentNumber will be persisted
	 * */
	public void persistService(Service service) {
		try {
			Element servicesElement = new Element("services");
			// Document doc = new Document();
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(servicesFilePath);

			Document doc = (Document) builder.build(xmlFile);
			// doc.setRootElement(servicesElement);
			servicesElement = doc.getRootElement();
			Element serviceElement = new Element("service");
			serviceElement.addContent(new Element("id").setText("" + service.getId()));
			serviceElement.addContent(new Element("name").setText("" + service.getName()));
			serviceElement.addContent(new Element("color").setText("" + service.getColor()));
			serviceElement.addContent(new Element("current_number").setText("" + service.getCurrentNumber()));

			doc.getRootElement().addContent(serviceElement);

			new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(servicesFilePath));

			// System.out.println("Service Saved in " + servicesFilePath);
		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Id, name, color, currentNumber will be persisted
	 * */
	public void persistHistoryLine(HistoryLine historyLine) {
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(historyFilePath);

			Document doc = (Document) builder.build(xmlFile);
			Element lineElement = new Element("line");
			lineElement.addContent(new Element("id").setText("" + historyLine.getId()));
			lineElement.addContent(new Element("service").setText("" + historyLine.getServiceId()));
			lineElement.addContent(new Element("machine").setText("" + historyLine.getMachineId()));
			lineElement.addContent(new Element("timestamp").setText("" + historyLine.getTimestamp()));
			lineElement.addContent(new Element("year").setText("" + historyLine.getYear()));
			lineElement.addContent(new Element("month").setText("" + historyLine.getMonth()));
			lineElement.addContent(new Element("day").setText("" + historyLine.getDay()));

			doc.getRootElement().addContent(lineElement);

			new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(historyFilePath));

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Id, name, color, currentNumber will be persisted
	 * */
	public void persistServiceBackup(Service service) {
		try {
			Element servicesElement = new Element("services");
			// Document doc = new Document();
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(servicesFilePath);

			Document doc = (Document) builder.build(xmlFile);
			doc.setRootElement(servicesElement);

			Element serviceElement = new Element("service");
			serviceElement.addContent(new Element("id").setText("" + service.getId()));
			serviceElement.addContent(new Element("name").setText("" + service.getName()));
			serviceElement.addContent(new Element("color").setText("" + service.getColor()));
			serviceElement.addContent(new Element("current_number").setText("" + service.getCurrentNumber()));

			doc.getRootElement().addContent(serviceElement);

			new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(servicesFilePath));

			// System.out.println("Service Saved in " + servicesFilePath);
		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException e) {
			e.printStackTrace();
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
			XPathExpression xp = xpfac.compile("//services/service", Filters.element());
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

			// System.out.println("Service updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return services;
	}

	public List<Machine> findAllMachines() {
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
				String currentNumber = machineElement.getChildText("current_number");
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
			XPathExpression xp = xpfac.compile("//services/service[id='" + service.getId() + "']", Filters.element());
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

			// System.out.println("Service updated!");
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

			// System.out.println("Machine updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}

	public Machine findMachineById(int machineId) {
		Machine machine = null;
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(machinesFilePath);

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			// use xpath to find the node
			XPathFactory xpfac = XPathFactory.instance();
			XPathExpression xp = xpfac.compile("//machines/machine[id='" + machineId + "']", Filters.element());
			List<Element> elements = xp.evaluate(rootNode);
			// update the node, in particular the current number and service
			for (Element machineElement : elements) {
				machine = new Machine();
				String id = machineElement.getChildText("id");
				String currentNumber = machineElement.getChildText("current_number");
				String currentServiceId = machineElement.getChildText("current_service");

				machine.setActive(false);
				machine.setId(Integer.parseInt(id));
				machine.setNumberYouAreServing(Integer.parseInt(currentNumber));
				machine.setServiceId(Integer.parseInt(currentServiceId));
				System.out.println("Machine found " + machine.toString());

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
		return machine;

	}

	public Service findServiceById(int serviceId) {
		Service service = null;
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(servicesFilePath);

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			// use xpath to find the node
			XPathFactory xpfac = XPathFactory.instance();
			XPathExpression xp = xpfac.compile("//services/service[id = '" + serviceId + "']", Filters.element());
			List<Element> elements = xp.evaluate(rootNode);
			// update the node
			for (Element serviceElement : elements) {
				service = new Service();
				String id = serviceElement.getChildText("id");
				String name = serviceElement.getChildText("name");
				String color = serviceElement.getChildText("color");
				String currentNumber = serviceElement.getChildText("current_number");

				service.setId(Integer.parseInt(id));
				service.setName(name);
				service.setColor(color);
				service.setCurrentNumber(currentNumber);
				System.out.println("Service found " + service.toString());
			}

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(servicesFilePath));

			// xmlOutput.output(doc, System.out);

		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return service;
	}

	public Set<String> retrieveAllDatesFromHistoryLines() {
		Set<String> lines = new HashSet<String>();
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(historyFilePath);

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			// use xpath to find the node
			XPathFactory xpfac = XPathFactory.instance();
			XPathExpression xp = xpfac.compile("//history/line", Filters.element());
			List<Element> elements = xp.evaluate(rootNode);
			// update the node

			for (Element serviceElement : elements) {
				String timestamp = serviceElement.getChildText("timestamp");
				String[] dayMonthYear = timestamp.split(" ");
				String weekDay = dayMonthYear[0];
				String month = ItalianEnglishMonthConverter.english2italianMonth(dayMonthYear[1],"english");
				String day = dayMonthYear[2];
				String year = dayMonthYear[5];
				lines.add(weekDay + "-" + day + "-"+ month + "-" + year);
			}

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(historyFilePath));

			// xmlOutput.output(doc, System.out);

		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public List<HistoryLine> retrieveHistoryLinesByDate(String day, String month, String year) {
		List<HistoryLine> lines = new ArrayList<HistoryLine>();
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(historyFilePath);

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			// use xpath to find the node
			XPathFactory xpfac = XPathFactory.instance();
			XPathExpression xp = xpfac.compile("//history/line[day='" + day + "' and month ='" + month + "' and year='"
					+ year + "']", Filters.element());
			List<Element> elements = xp.evaluate(rootNode);
			// update the node

			for (Element lineElement : elements) {
				HistoryLine line = new HistoryLine();
				String timestamp = lineElement.getChildText("timestamp");
				String id = lineElement.getChildText("id");
				String serviceId = lineElement.getChildText("service");
				String machineId = lineElement.getChildText("machine");

				line.setId(Long.parseLong(id));
				line.setServiceId(Integer.parseInt(serviceId));
				line.setMachineId(Integer.parseInt(machineId));
				line.setDay(day);
				line.setMonth(month);
				line.setYear(year);
				line.setTimestamp(timestamp);

				lines.add(line);
			}

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(historyFilePath));

			// xmlOutput.output(doc, System.out);

		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
