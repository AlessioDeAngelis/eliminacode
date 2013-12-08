package it.alessio.eliminacode.common.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TastierinoModel {

	/**
	 * for each service it maps the currentNumber
	 * */
	private Map<Integer, Service> id2service;
	private List<Machine> machines;
	private Map<Integer, List<Machine>> service2machines;
	private Machine currentMachine;

	public TastierinoModel() {
		this.id2service = new HashMap<Integer, Service>();
		this.machines = new ArrayList<Machine>();
		this.service2machines = new HashMap<Integer, List<Machine>>();
		this.currentMachine = new Machine();
	}

	public TastierinoModel(String value) {

	}

	public Map<Integer, Service> getId2service() {
		return id2service;
	}

	public void setId2service(Map<Integer, Service> id2service) {
		this.id2service = id2service;
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}

	public void addMachine(Machine machine) {
		this.machines.add(machine);
	}

	public Map<Integer, List<Machine>> getService2machines() {
		return service2machines;
	}

	public void setService2machines(Map<Integer, List<Machine>> service2machines) {
		this.service2machines = service2machines;
	}

	public Machine getCurrentMachine() {
		return currentMachine;
	}

	public void setCurrentMachine(Machine currentMachine) {
		this.currentMachine = currentMachine;
	}

}
