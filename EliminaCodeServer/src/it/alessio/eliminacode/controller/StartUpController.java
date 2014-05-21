package it.alessio.eliminacode.controller;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.persistance.BatchFileManager;
import it.alessio.eliminacode.common.persistance.SecretKeyRepository;
import it.alessio.eliminacode.common.persistance.XMLRepository;
import it.alessio.eliminacode.security.EncryptorManager;
import it.alessio.eliminacode.security.MacManager;
import it.alessio.eliminacode.security.SecretKeyEntity;
import it.alessio.eliminacode.view.StartupView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Saves the model (services and machines) in the db, in order to let the other
 * components to have data to start from
 * */
public class StartUpController {
	private XMLRepository xmlRepository;
	private Properties properties;
	private StartupView view;
	private SecretKeyRepository secretKeyRepository;
	private String inputSerialCode;
	private BatchFileManager batchFileManager;

	public StartUpController() {
		this.inputSerialCode = "";
		this.batchFileManager = new BatchFileManager();
		this.secretKeyRepository = new SecretKeyRepository();
		this.view = new StartupView(this);
		this.view.initialize();
		this.view.addListeners();
	}

	public void startTheSetupOperation() {
		loadProperties();
		this.xmlRepository = new XMLRepository();
		createXmlFiles();
		insertServices();
		insertMachines();
	}

	private void createXmlFiles() {
		String dbFolderPath = this.properties.getProperty("db_folder_path");
		this.xmlRepository.createXmlFile("services", dbFolderPath, "services.xml");// TODO:
																					// not
																					// hardcode
																					// paths
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
			if (returnedService == null) {
				this.xmlRepository.persistService(service);
				// System.out.println(service + " STORED SUCCESSFULLY");
			}

		}
	}

	/**
	 * Inserts new machines in the database
	 * */
	private void insertMachines() {
		String numberOfMachinesString = properties.getProperty("numero_tastierini");
		int numMachines = 4;
		if (numberOfMachinesString != null && !numberOfMachinesString.equals("")) {
			numMachines = Integer.parseInt(numberOfMachinesString);
		}

		for (int i = 0; i < numMachines; i++) {
			Machine machine = new Machine();
			machine.setChiave(i);
			machine.setId(i);
			machine.setNumberYouAreServing(0);
			machine.setServiceId(0);
			System.out.println("Inserting " + machine);
			//persist machine in the xml file
			this.xmlRepository.persistMachine(machine);
			//creates a batch file for the machine
			this.batchFileManager.createTastierinoBatchFile(i);
		}
	}

	/**
	 * Per generare i codici e proteggersi dalle copie si fa così: si prende
	 * l'indirizzo mac della macchina e si cripta. Questo è il codice di
	 * attivazione che verrà fornito all'utente. L'utente dovrà contattare
	 * l'amministratore e comunicare il codice di attivazione. Riceverà un
	 * codice seriale, che altro non è che il codice di attivazione criptato
	 * nuovamente. 
	 * **/
	public void generateActivationCode() {
		String macAddress = MacManager.findMac();
		System.out.println("Mac Address: " + macAddress);
		EncryptorManager encMan = new EncryptorManager();
		String activationCode = encMan.generateActivationCodeFromMac(macAddress);
		String serialCode = encMan.generateSerialKey(activationCode);
		// in the db persist the serial
		SecretKeyEntity key = new SecretKeyEntity();
		key.setActivationCode(activationCode);
		key.setSerialCode(serialCode);
		System.out.println("generated serial code: " + serialCode);
		this.secretKeyRepository.persistSecretKey(key); // TODO: uncomment it if
														// it is not a demo
		// notify the view
		this.view.updateGenerateCodeTextField(activationCode);
	}

	public void validateSerialCode() {
		SecretKeyEntity key = this.secretKeyRepository.findSecretKey();
		String correctStoredSerial = key.getSerialCode();
		if (correctStoredSerial.equals(this.inputSerialCode)) {
			this.view.updateValidationResultTextField("IL CODICE SERIALE INSERITO E' CORRETTO, ATTENDERE QUALCHE SECONDO PER FAVORE");			
			startTheSetupOperation();
			this.view.updateValidationResultTextField("SETUP COMPLETATO, PUOI USARE L'APPLICAZIONE");
		} else {
			this.view.updateValidationResultTextField("IL CODICE SERIALE INSERITO NON E' CORRETTO");
		}
	}

	public void modifyInputSerialCode(String inputSerialCode) {
		this.inputSerialCode = inputSerialCode;
		System.out.println(this.inputSerialCode);
	}

}
