package it.alessio.eliminacode.controller;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.persistance.XMLRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Saves the model (services and machines) in the db, in order to let the other components to have data to start from
 * */
public class StartUpController {
	private XMLRepository xmlRepository;
	private Properties properties;

	public StartUpController() {

	}
	
	public void initialize(){
		loadProperties();
		this.xmlRepository = new XMLRepository();
		createXmlFiles();
		insertServices();
		insertMachines();
	}
	
	
	private void createXmlFiles(){
		String dbFolderPath = this.properties.getProperty("db_folder_path");
		this.xmlRepository.createXmlFile("services", dbFolderPath, "services.xml");//TODO: not hardcode paths
		this.xmlRepository.createXmlFile("machines", dbFolderPath, "machines.xml");
		this.xmlRepository.createXmlFile("history", dbFolderPath, "history.xml");
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

	private void insertServices() {
		String numServiceString = properties.getProperty("numero_servizi");
		int numServices = 4;
		if (numServiceString != null && !numServiceString.equals("")) {
			numServices = Integer.parseInt(numServiceString);
		}
		for (int i = 0; i < numServices; i++) {
			Service service = new Service(i, properties.getProperty("nome_servizio" + (i + 1)), "0",
					properties.getProperty("colore_servizio" + (i + 1)));
			Service returnedService = this.xmlRepository.findServiceById(service.getId());
			if(returnedService == null){
				this.xmlRepository.persistService(service);
//				System.out.println(service + " STORED SUCCESSFULLY");
			}
			
		}
	}
	
	/**
	 * Inserts new machines in the database
	 * */
	private void insertMachines(){
		String numberOfMachinesString  = properties.getProperty("numero_tastierini");
		int numMachines = 4;
		if (numberOfMachinesString != null && !numberOfMachinesString.equals("")) {
			numMachines = Integer.parseInt(numberOfMachinesString);
		}
		
		for(int i = 0; i < numMachines; i++){
			Machine machine = new Machine();
			machine.setChiave(i);
			machine.setId(i);
			machine.setNumberYouAreServing(0);
			machine.setServiceId(0);
			System.out.println("Inserting " + machine);
			this.xmlRepository.persistMachine(machine);
		}
	}


}
