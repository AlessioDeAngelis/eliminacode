package it.alessio.eliminacode.controller;

import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.persistance.JDBCRepository;
import it.alessio.eliminacode.common.persistance.JPARepository;
import it.alessio.eliminacode.common.persistance.XMLRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Saves the model (services and machines) in the db, in order to let the other components to have data to start from
 * */
public class StartUpController {
//	private JDBCRepository repository;
	private XMLRepository repo;
	private JPARepository jpaRepository;
	private Properties properties;

	public StartUpController() {

	}
	
	public void initialize(){
		loadProperties();
//		this.repository = new JDBCRepository();
		this.repo = new XMLRepository();
		createTables();
		createXmlFiles();
		persistServices();
	}
	
	private void createDB(){
		//uncommented because I don't need them in xml
//		this.repository.createEliminacodeDB("eliminacode");
//		this.repository.createEliminacodeDB("eliminacodeJPA");

	}
	
	private void createXmlFiles(){
		this.repo.createXmlFile("services", "data/xml/", "services.xml");//TODO: not hardcode paths
		this.repo.createXmlFile("machines", "data/xml/", "machines.xml");
		this.repo.createXmlFile("history", "data/xml/", "history.xml");
	}

	/**
	 * this could become create xml files and directories
	 * */
	private void createTables() {
//		this.repository.createServiceTable();
//		this.repository.createMachineTable();
	}

	private void loadProperties() {
		this.properties = new Properties();
		try {
			FileInputStream fis = new FileInputStream("data/config.txt");
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void persistServices() {
		String numServiceString = properties.getProperty("numero_servizi");
		int numServices = 4;
		if (numServiceString != null && !numServiceString.equals("")) {
			numServices = Integer.parseInt(numServiceString);
		}
		for (int i = 0; i < numServices; i++) {
			Service service = new Service(i, properties.getProperty("nome_servizio" + (i + 1)), "0",
					properties.getProperty("colore_servizio" + (i + 1)));
			Service returnedService = this.repo.findServiceById(service.getId());
			if(returnedService == null){
				this.repo.persistService(service);
//				System.out.println(service + " STORED SUCCESSFULLY");
			}
			
		}
	}


}
