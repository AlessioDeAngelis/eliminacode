package it.alessio.tabellone.controller;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.model.TastierinoModel;
import it.alessio.eliminacode.common.persistance.JDBCRepository;
import it.alessio.tabellone.news.FeedController;
import it.alessio.tabellone.news.FeedMessage;
import it.alessio.tabellone.view.TabelloneView;
import it.alessio.tastierino.view.TastierinoView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
	private JDBCRepository repository;
	private Map<Integer, TastierinoView> machineId2TastierinoView;
	private Properties properties;
	private FeedController feedController;

	public TabelloneController() {
		loadProperties();
		this.model = new TastierinoModel();
		this.repository = new JDBCRepository();
		String rssUrl = properties.getProperty("rss_url");
		this.feedController = new FeedController(rssUrl);
		loadServices();
		groupService2machines();
		this.tabelloneView = new TabelloneView(model, properties);
		// this.tabelloneView.updateViewText();
		// Singleton.setTabelloneController(this);

	}

	public void mainLoop() {

		while (true) {
			try {
				List<Service> services = repository.findAllServices();
				FeedMessage feedMessage = this.feedController.giveNextMessage();
				groupService2machines();
				this.tabelloneView.orderPanels();
				this.tabelloneView.playVideo(properties.getProperty("video_path"));
				this.tabelloneView.updateViewOrder(services);
				this.tabelloneView.updateViewText(services);
				this.tabelloneView.updateViewOrder(services);
				this.tabelloneView.updateNewsPanel(feedMessage);

				Thread.sleep(4000);
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
		List<Service> services = this.repository.findAllServices();
		Map<Integer, Service> id2service = this.model.getId2service();
		for (int i = 0; i < services.size(); i++) {
			id2service.put(i, services.get(i));
		}
	}

	// find all the machines
	private void loadMachines() {
		List<Machine> machines = this.repository.findAllMachines();
		this.model.setMachines(machines);
	}

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
		this.model.setService2machines(service2machines);
	}

}
