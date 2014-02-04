package it.alessio.eliminacode.controller;

import it.alessio.eliminacode.common.model.HistoryLine;
import it.alessio.eliminacode.common.model.ItalianEnglishMonthConverter;
import it.alessio.eliminacode.common.persistance.XMLRepository;
import it.alessio.eliminacode.view.HistoryView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multiset;

public class HistoryController {
	private XMLRepository repo;
	private HistoryView view;
	private List<HistoryLine> lines;
	private Set<String> dates;
	private Date selectedDate;
	private String selectedValue;

	public HistoryController() {
		this.repo = new XMLRepository();
		this.lines = new ArrayList<HistoryLine>();
		this.view = new HistoryView("STATISTICHE");
		this.selectedValue = "";
		this.initialize();
		view.initialize(dates);
		this.view.addListeners(this);
		// view.initialize(lines,selectedDate);
	}

	public void initialize() {
		Set<String> tmp = this.repo.retrieveAllDatesFromHistoryLines();
		this.dates = new HashSet<String>();
		int limit = 0;
		if(tmp.size()>500){
			limit = 500;
		}else{
			limit = tmp.size();
		}
		
		Iterator<String> iterator = tmp.iterator();
		int i = 0;
		while(iterator.hasNext() && i<limit){
			this.dates.add(iterator.next());
			i++;
		}
	
	}

	public void calculateStatistics() {
		if (selectedValue != null && selectedValue != "") {
			// parse the value to print it as a date
			String[] dayMonthYear = selectedValue.split("-");
			String day = dayMonthYear[1];
			String month = dayMonthYear[2];

			String year = dayMonthYear[3];
			// retrieve all the history lines of the given date
			this.lines = this.repo.retrieveHistoryLinesByDate(day,month,year);

			// a partire dalla data, voglio sapere il servizio quanto ha
			// lavorato, un impiegato quanto ha lavorato e in quali servizi

			StringBuilder text = new StringBuilder();
			text.append("STATISTICHE DEL GIORNO: " + selectedValue + "\n\n\n");
			Multiset<Integer> serviceIds = HashMultiset.create();
			ListMultimap<Integer, Integer> machine2service = ArrayListMultimap.create();
			// take all the ids(machine and service)
			for (HistoryLine line : this.lines) {
				int machineId = line.getMachineId();
				int serviceId = line.getServiceId();
				serviceIds.add(serviceId);
				// machine2line.put(machineId, line);
				machine2service.put(machineId, serviceId);
			}

			for (Integer serviceId : serviceIds.elementSet()) {
				int count = serviceIds.count(serviceId);
				text.append("SERVIZIO " + (serviceId + 1) + " ha servito un totale di " + count + " clienti \n");
			}

			text.append("\n");

			for (Integer machineId : machine2service.keySet()) {
				text.append("OPERATORE " + (machineId + 1) + ": \n");
				List<Integer> tmp = machine2service.get(machineId);
				Multiset<Integer> allServiceIds = HashMultiset.create(tmp);
				for (Integer serId : allServiceIds.elementSet()) {
					int count = allServiceIds.count(serId);
					text.append("\t ha servito " + count + " clienti nel SERVIZIO " + (serId + 1) + "\n");
				}
			}

			// notify the view
			this.view.updateText(text.toString());
		}
	}

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

}
