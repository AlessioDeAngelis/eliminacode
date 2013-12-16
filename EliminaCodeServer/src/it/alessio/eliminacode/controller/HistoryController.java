package it.alessio.eliminacode.controller;

import it.alessio.eliminacode.common.model.HistoryLineJPA;
import it.alessio.eliminacode.common.model.ItalianMonthConverter;
import it.alessio.eliminacode.common.persistance.JPARepository;
import it.alessio.eliminacode.view.HistoryView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multiset;

public class HistoryController {
	private JPARepository repository;
	private HistoryView view;
	private List<HistoryLineJPA> lines;
	private List<Date> dates;
	private Date selectedDate;
	private String selectedValue;

	public HistoryController() {
		this.repository = new JPARepository();
		this.lines = new ArrayList<HistoryLineJPA>();
		this.view = new HistoryView("STATISTICHE");
		this.selectedValue = "";
		this.initialize();
		view.initialize(dates);
		this.view.addListeners(this);
		// view.initialize(lines,selectedDate);
	}

	public void initialize() {
		this.dates = this.repository.retrieveAllDatesFromHistoryLines();
	}

	public void calculateStatistics() {
		if (selectedValue != null && selectedValue != "") {
			// parse the value to print it as a date
			String[] dayMonthYear = selectedValue.split("-");
			String day = dayMonthYear[0];
			String month = dayMonthYear[1];

			String year = dayMonthYear[2];
			// retrieve all the history lines of the given date
			this.lines = this.repository.retrieveHistoryLinesByDate(Integer.parseInt(day),
					ItalianMonthConverter.fromMonthToNumber(month), Integer.parseInt(year));

			// a partire dalla data, voglio sapere il servizio quanto ha
			// lavorato, un impiegato quanto ha lavorato e in quali servizi
			
			StringBuilder text = new StringBuilder();
			
			Multiset<Integer> serviceIds = HashMultiset.create();
			ListMultimap<Integer,Integer> machine2service = ArrayListMultimap.create();
			//take all the ids(machine and service)
			for(HistoryLineJPA line : this.lines){
				int machineId = line.getMachineId();
				int serviceId = line.getServiceId();
				serviceIds.add(serviceId);
//				machine2line.put(machineId, line);
				machine2service.put(machineId,serviceId);
			}
			
			for(Integer serviceId : serviceIds.elementSet()){
				int count = serviceIds.count(serviceId);
				text.append("SERVIZIO " + serviceId + " ha servito " + count + " clienti \n");
			}
			
			text.append("\n");
			
			for(Integer machineId : machine2service.keySet()){
				text.append("OPERATORE " + machineId + ": \n");
				List<Integer> tmp =  machine2service.get(machineId);
				Multiset<Integer> allServiceIds = HashMultiset.create(tmp);
				for(Integer serId : allServiceIds.elementSet()){
					int count = allServiceIds.count(serId);
					text.append("\t ha servito " + count + " clienti nel SERVIZIO " + serId +"\n");
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
