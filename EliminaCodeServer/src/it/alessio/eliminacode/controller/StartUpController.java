package it.alessio.eliminacode.controller;

import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.persistance.JDBCRepository;
import it.alessio.eliminacode.common.persistance.JPARepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Saves the model (services and machines) in the db, in order to let the other components to have data to start from
 * */
public class StartUpController {
	private JDBCRepository repository;
	private JPARepository jpaRepository;
	private Properties properties;

	public StartUpController() {

	}
	
	public void initialize(){
		loadProperties();
		this.repository = new JDBCRepository();
		createTables();
		persistServices();
	}
	
	private void createDB(){
		this.repository.createEliminacodeDB("eliminacode");
		this.repository.createEliminacodeDB("eliminacodeJPA");

	}

	private void createTables() {
		this.repository.createServiceTable();
		this.repository.createMachineTable();
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
			Service returnedService = this.repository.findServiceById(service.getId());
			if(returnedService==null){
				this.repository.persistService(service);;
			}
			System.out.println(service + " STORED SUCCESSFULLY");
		}
	}


}
