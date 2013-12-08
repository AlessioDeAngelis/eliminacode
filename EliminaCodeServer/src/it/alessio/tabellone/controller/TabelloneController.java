package it.alessio.tabellone.controller;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.model.TastierinoModel;
import it.alessio.eliminacode.common.persistance.Repository;
import it.alessio.eliminacode.common.sound.MusicPlayer;
import it.alessio.tabellone.view.TabelloneView;
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

import javax.persistence.PostLoad;

/**
 * The Controller coordinates interactions between the View and Model
 * Of the TABELLONE
 * */
public class TabelloneController {
	private TastierinoModel model;
	private TabelloneView tabelloneView;
	private Repository repository;
	private Map<Integer, TastierinoView> machineId2TastierinoView;
	private Properties properties;

	public TabelloneController() {
		loadProperties();
		this.model = new TastierinoModel();
		this.repository = new Repository();
		loadServices();
		groupService2machines();
		this.tabelloneView = new TabelloneView(properties.getProperty("nome_azienda", "Tabellone"), model);
		this.tabelloneView.updateViewText();
//		Singleton.setTabelloneController(this);

	}
	
	public void mainLoop(){
		while(true){
			try {
				Thread.sleep(4000);				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				groupService2machines();
				this.tabelloneView.updateViewOrder();
				this.tabelloneView.updateViewText();
				this.tabelloneView.updateViewOrder();

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
		//find all services
		List<Service> services = this.repository.findAllServices();
		Map<Integer, Service> id2service = this.model.getId2service();
		for(int i = 0; i<services.size(); i++){
			id2service.put(i, services.get(i));
		}	
	}	
	
	//find all the machines
	private void loadMachines(){
		List<Machine> machines = this.repository.findAllMachines();
		this.model.setMachines(machines);
	}

	private void groupService2machines() {
		this.loadMachines();

		Map<Integer, List<Machine>> service2machines;
		service2machines = new HashMap<Integer, List<Machine>>();
//		List<Machine> machines = this.repository.findAllMachines();
		List<Machine> machines = this.model.getMachines();
		for (Machine machine : machines) {
			Integer serviceId = machine.getCurrentService().getId();
			List<Machine> machinesTmp = service2machines.get(serviceId);
			if (machinesTmp == null) {
				machinesTmp = new ArrayList<Machine>();
			}
			machinesTmp.add(machine);
			service2machines.put(serviceId, machinesTmp);
		}
		this.model.setService2machines(service2machines);
	}
	
	public void onServiceEntityUpdatedAction(){
		this.groupService2machines();
		this.tabelloneView.updateViewOrder();
		this.tabelloneView.updateViewText();
	}
	
//	@PostLoad
//	public void onPostLoad(Service service){
//		System.out.println("Post load controller");
//	}

}
