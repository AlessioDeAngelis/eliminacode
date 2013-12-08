package it.alessio.eliminacode.controller;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.model.TastierinoModel;
import it.alessio.eliminacode.common.persistance.Repository;
import it.alessio.eliminacode.common.sound.MusicPlayer;
import it.alessio.tabellone.view.TabelloneView;
import it.alessio.tastierino.controller.listeners.NextButtonListener;
import it.alessio.tastierino.controller.listeners.NumButtonListener;
import it.alessio.tastierino.controller.listeners.NumberButtonListener;
import it.alessio.tastierino.controller.listeners.OnOffButtonListener;
import it.alessio.tastierino.controller.listeners.ServiceButtonListener;
import it.alessio.tastierino.view.TastierinoView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Saves the model (services and machines) in the db, in order to let the other components to have data to start from
 * */
public class StartUpController {
	private Repository repository;

	private Properties properties;

	public StartUpController() {

	}
	
	public void initialize(){
		loadProperties();
		this.repository = new Repository();
		persistServices();
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
			service = this.repository.findOrCreateService(service);
			System.out.println(service + " STORED SUCCESSFULLY");
		}
	}


}
