package it.alessio.tabellone.controller;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.model.TastierinoModel;
import it.alessio.eliminacode.common.model.comparator.MachineComparator;
import it.alessio.eliminacode.common.persistance.XMLRepository;
import it.alessio.eliminacode.common.sound.MusicPlayer;
import it.alessio.tabellone.news.FeedController;
import it.alessio.tabellone.news.FeedMessage;
import it.alessio.tabellone.view.TabelloneView;
import it.alessio.tastierino.view.TastierinoView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * The Controller coordinates interactions between the View and Model Of the
 * TABELLONE
 * */
public class TabelloneController {
	private TastierinoModel model;
	private TabelloneView tabelloneView;
	private XMLRepository xmlRepository;
	private Map<Integer, TastierinoView> machineId2TastierinoView;
	private Properties properties;
	private FeedController feedController;
	private boolean isInternetAvailable = true;

	public TabelloneController() {
		loadProperties();
		this.model = new TastierinoModel();
		this.xmlRepository = new XMLRepository();
		String rssUrl = properties.getProperty("rss_url");
		try {
			this.feedController = new FeedController(rssUrl);
			this.feedController.initializeFeed();
		} catch (Exception e) {
			isInternetAvailable = false;
		}
		loadServices();
		groupService2machines();
		this.tabelloneView = new TabelloneView(model, properties, this);
		// this.tabelloneView.updateViewText();
		// Singleton.setTabelloneController(this);

	}

	public void mainLoop() {
		this.tabelloneView.orderPanels();
		this.tabelloneView.playVideo(properties.getProperty("video_path"));

		int timeLeftToUpdateFeed = 4;
		Map<Integer, Integer> service2lastNumberCalled = new HashMap<Integer, Integer>();
		List<Service> services = xmlRepository.findAllServices();
		for (Service service : services) {
			// if(Integer.parseInt(service.getCurrentNumber())){
			service2lastNumberCalled.put(service.getId(), Integer.parseInt(service.getCurrentNumber()));
			// }
		}

		while (true) {
			try {
				services = xmlRepository.findAllServices();
				for (Service service : services) {
					int currentNumber = Integer.parseInt(service.getCurrentNumber());
					int prevNumber = service2lastNumberCalled.get(service.getId());
					if (currentNumber != prevNumber) {
						// play sound once
						MusicPlayer.playSound("data/sounds/coin.wav");
						break;
					}
				}
				FeedMessage feedMessage = new FeedMessage();
				if (isInternetAvailable) {
					feedMessage = this.feedController.giveNextMessage();
				} else { // no internet connection
					feedMessage.setTitle("NESSUNA CONNESSIONE INTERNET DISPONIBILE");
				}
				groupService2machines();
				this.tabelloneView.updateViewOrder(services);
				this.tabelloneView.updateViewText(services);
				this.tabelloneView.updateViewOrder(services);
				if (timeLeftToUpdateFeed < 0) {
					this.tabelloneView.updateNewsPanel(feedMessage);
					timeLeftToUpdateFeed = 4;
				} else {
					timeLeftToUpdateFeed--;
				}
				// Update last number for service
				for (Service service : services) {
					service2lastNumberCalled.put(service.getId(), Integer.parseInt(service.getCurrentNumber()));
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {

			}
		}
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

	private void loadServices() {
		// find all services
		List<Service> services = this.xmlRepository.findAllServices();
		Map<Integer, Service> id2service = this.model.getId2service();
		for (int i = 0; i < services.size(); i++) {
			id2service.put(i, services.get(i));
		}
	}

	// find all the machines
	private void loadMachines() {
		List<Machine> machines = this.xmlRepository.findAllMachines();
		this.model.setMachines(machines);
	}

	// TODO: ordinare in base al numero in ordine crescente
	private void groupService2machines() {
		this.loadMachines();

		Map<Integer, List<Machine>> service2machines;
		service2machines = new HashMap<Integer, List<Machine>>();
		// List<Machine> machines = this.repository.findAllMachines();
		List<Machine> machines = this.model.getMachines();
		for (Machine machine : machines) {
			Integer serviceId = machine.getServiceId();
			List<Machine> machinesTmp = service2machines.get(serviceId);
			if (machinesTmp == null) {
				machinesTmp = new ArrayList<Machine>();
			}
			machinesTmp.add(machine);
			service2machines.put(serviceId, machinesTmp);
		}
		for (List<Machine> tmp : service2machines.values()) {
			Collections.sort(tmp, new MachineComparator());
		}

		this.model.setService2machines(service2machines);
	}

}
