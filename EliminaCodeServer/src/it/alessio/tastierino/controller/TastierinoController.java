package it.alessio.tastierino.controller;

import it.alessio.eliminacode.common.model.HistoryLine;
import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.model.TastierinoModel;
import it.alessio.eliminacode.common.persistance.XMLRepository;
import it.alessio.tastierino.controller.listeners.NextButtonListener;
import it.alessio.tastierino.controller.listeners.NumButtonListener;
import it.alessio.tastierino.controller.listeners.NumberButtonListener;
import it.alessio.tastierino.controller.listeners.OnOffButtonListener;
import it.alessio.tastierino.controller.listeners.ServiceButtonListener;
import it.alessio.tastierino.view.TastierinoView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * The Controller coordinates interactions between the View and Model
 * */
public class TastierinoController {
	private TastierinoModel model;
	// private TabelloneView tabelloneView;
//	private JDBCRepository repository;
	private XMLRepository repo;
//	private JPARepository jpaRepository;
	private TastierinoView tastierinoView;
	private Map<Integer, TastierinoView> machineId2TastierinoView;
	private Properties properties;

	public TastierinoController(int machineNumber) {
		initProperties();
		this.model = new TastierinoModel();
//		this.repository = new JDBCRepository();
		this.repo = new XMLRepository();
//		this.jpaRepository = new JPARepository();
		this.machineId2TastierinoView = new HashMap<Integer, TastierinoView>();
//		initializeServices();
		loadServices();
		// TODO: initialize the machine
//		Machine machine = new Machine(machineNumber);
//		machine.setServiceId(model.getId2service().get(machineNumber).getId());// default
		// initial
		// service
		// TODO check this if
//		if (machine.getServiceId() != null && machine.getServiceId().getCurrentNumber() != null) {
//			machine.setNumberYouAreServing(Integer.parseInt(machine.getServiceId().getCurrentNumber()));
//		}
		// this.model.addMachine(machine);//deprecated
//		this.model.setCurrentMachine(machine);
//		this.repository.findOrCreateMachine(machine);
		Machine machine = null;
		machine = this.repo.findMachineById(machineNumber); 
		if(machine == null){
			machine = new Machine(machineNumber);
			this.repo.persistMachine(machine); 
		}
		//TODO: caricare i dati della macchina all'inizio potrebbe essere un'idea carina
		this.model.setCurrentMachine(machine);

		// Tell the View that when ever the calculate button
		// is clicked to execute the actionPerformed method
		// in the CalculateListener inner class
		TastierinoView view = new TastierinoView("OPERATORE " + (machine.getId() + 1), model, machine.getId());
		view.addNumberButtonsListener(new NumberButtonListener(this));
		view.addNextButtonListener(new NextButtonListener(this));
		view.addOnOffButtonListener(new OnOffButtonListener(this));
		view.addServiceButtonListener(new ServiceButtonListener(this));
		view.addNumButtonActionListener(new NumButtonListener(this));
		view.updateDisplayText();
		machineId2TastierinoView.put(machine.getId(), view);// add it to the
															// hash map
		this.tastierinoView = view;

		// groupService2machines();// this must be done before rendering
		// this.tabelloneView = new
		// TabelloneView(properties.getProperty("nome_azienda", "Tabellone"),
		// model);
		// this.tastierinoView.updateDisplayText();
		// this.tabelloneView.updateViewText();
	}

	private void initProperties() {
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
		List<Service> services = this.repo.findAllServices();
		Map<Integer, Service> id2service = this.model.getId2service();
		for(int i = 0; i<services.size(); i++){
			id2service.put(i, services.get(i));
		}	
	}	

//	private void initializeServices() {
//		Map<Integer, Service> id2service = this.model.getId2service();
//		String numServiceString = properties.getProperty("numero_servizi");
//		int numServices = 4;
//		if (numServiceString != null && !numServiceString.equals("")) {
//			numServices = Integer.parseInt(numServiceString);
//		}
//		for (int i = 0; i < numServices; i++) {
//			Service service = new Service(i, properties.getProperty("nome_servizio" + (i + 1)), "0",
//					properties.getProperty("colore_servizio" + (i + 1)));
//			service = this.repository.findOrCreateService(service);
//			id2service.put(i, service);
//		}
//	}

	/*
	 * When the next button is pressed the current number is incremented by 1.
	 * The current machine must be active (ON), otherwise it will not increment
	 * the value. The last number called of the service will be incremented by 1 as well
	 */
	public void nextNumberAction(int machineThatTriggered) {
		Machine currentMachine = this.model.getCurrentMachine();
		boolean isCurrentMachineActive = currentMachine.isActive();
		if (isCurrentMachineActive) {
			int serviceId = currentMachine.getServiceId();
			String lastCalledNumberForService;
//			Properties propService = new Properties();
//			propService.load(new FileInputStream("data/prop/service"));
			Service currentServiceOfTheMachine = this.repo.findServiceById(serviceId);
			lastCalledNumberForService = currentServiceOfTheMachine.getCurrentNumber();
			int nextNumberForService = 0;
			if (lastCalledNumberForService != null && !lastCalledNumberForService.equals("")) {
				nextNumberForService = (Integer.valueOf(lastCalledNumberForService) + 1) % 99;
			}
			currentMachine.setNumberYouAreServing(nextNumberForService);
			currentMachine.setServiceId(serviceId);
			this.repo.updateMachine(currentMachine);
			
			currentServiceOfTheMachine.setCurrentNumber(""+nextNumberForService);
			Service serviceUpdated = currentServiceOfTheMachine;//TODO: to remove because now is useless
					this.repo.updateService(currentServiceOfTheMachine);
		
			
			//new history line
			HistoryLine line = new HistoryLine();
			line.setMachineId(currentMachine.getId());
			line.setServiceId(serviceUpdated.getId());
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(System.currentTimeMillis());
			String timestamp = gc.getTime().toString();
			line.setTimestamp(timestamp);
			String[] dmy = timestamp.split(" ");
			String day = dmy[0];
			String month = dmy[1];
			String year = dmy[2];
			line.setDay(day);
			line.setMonth(month);
			line.setYear(year);
			//save the history line
			this.repo.persistHistoryLine(line);
			
			//update the model according to the last changes
			this.model.getId2service().put(serviceUpdated.getId(), serviceUpdated);
			this.model.setCurrentMachine(currentMachine);
			/**
			 * ==================
			 * */
//			int nextNumber = Integer.parseInt(currentMachine.getServiceId().getCurrentNumber()) + 1;
//			currentMachine.getServiceId().setCurrentNumber("" + nextNumber);
//			currentMachine.setNumberYouAreServing(nextNumber);
//			// update machine in the db
//			int serviceId = currentMachine.getServiceId().getId();
//			Service currentService = this.repository.findServiceById(serviceId);
//			currentService.setCurrentNumber(""+nextNumber);
//			this.repository.updateMachine(currentMachine, currentService);
//			this.repository.updateServiceCurrentNumber(currentService);
//			this.model.setCurrentMachine(currentMachine);
			
			/**
			 * ==================
			 * */
			// notify the view about the change
			// this.tabelloneView.updateViewText();
			

			/* Only the current machine view wants to be notified */
			TastierinoView view = machineId2TastierinoView.get(currentMachine.getId());
			view.changeDisplayText("" + currentMachine.getNumberYouAreServing());
			
			// only the view of the machines that have this service as current
			// want to be notified about the change
			// while the others don't want to be disturbed
			// for (Machine machine : this.model.getMachines()) {
			// if (machine.getCurrentService().getId() ==
			// currentMachine.getCurrentService().getId()) {
			// TastierinoView view =
			// machineId2TastierinoView.get(machine.getId());
			// view.changeDisplayText(currentMachine.getCurrentService().getCurrentNumber());
			// }
			// }

		}

	}

	/**
	 * When the number of the keyboard are pressed it will create a digit list
	 * and eventually modify the current number of the service
	 * */
	public void createDigitsListAction(String value, int machineThatTriggered) {
		Machine currentMachine = this.model.getCurrentMachine();
		boolean isCurrentMachineActive = currentMachine.isActive();
		int valueAsNumber = -1;
		if (isCurrentMachineActive && value != null) {
			if (value.equals("OK")) {
				// sets the list of numbers as the current value to be displayed
				String newNumberToBeServed = currentMachine.getDigitsList().toString();
				int serviceId = currentMachine.getServiceId();
				//TODO: you can do the following only with the id without finding the service
				Service currentService = this.repo.findServiceById(serviceId);
				currentService.setCurrentNumber(newNumberToBeServed);
				this.repo.updateService(currentService);
				currentMachine.getDigitsList().clearList();
				currentMachine.setNumberYouAreServing(Integer.parseInt(newNumberToBeServed));
				//TODO: update the machine in the db
				// this.tabelloneView.updateViewText();

				// only the current machine view wants to be notified
				TastierinoView view = machineId2TastierinoView.get(currentMachine.getId());
				view.changeDisplayText(newNumberToBeServed);
			} else if (value.equals("Del")) {
				// delete the last number entered
				currentMachine.getDigitsList().removeLastDigit();
			} else {
				// is a number, add a new digit
				valueAsNumber = Integer.parseInt(value);
				currentMachine.getDigitsList().addDigit(valueAsNumber);
			}
		}
	}

	public void turnOnOffAction(int machineThatTriggered) {
		/*
		 * sets active or not active the current machine
		 */
		Machine currentMachine = this.model.getCurrentMachine();
		boolean isActive = currentMachine.isActive();
		currentMachine.setActive(!isActive);
		// notify the view of the current machine
		TastierinoView view = machineId2TastierinoView.get(currentMachine.getId());
//		view.changeDisplayText(currentMachine.getServiceId().getCurrentNumber()); //TODO: maybe it's correct
		view.changeDisplayText(""+currentMachine.getNumberYouAreServing());
	}

	/**
	 * Change the current service. The service that corresponds to the button
	 * that triggered the event becomes the current service
	 * */
	public void changeCurrentServiceAction(String serviceName, int machineThatTriggered) {
		Machine currentMachine = this.model.getCurrentMachine();

		Collection<Service> services = this.model.getId2service().values();
		Service currentService = null;
		for (Service service : services) {
			if (serviceName != null && service.getName().equals(serviceName)) {
				currentService = service;
				break;
			}
		}
		if (currentService == null) {
			// default service will be selected
			currentService = this.model.getId2service().get(0);
		}
//		currentMachine.setServiceId(currentService.getId());
//		currentMachine.setNumberYouAreServing(Integer.parseInt(currentService.getCurrentNumber()));
		int lastNumberCalled = Integer.parseInt(currentService.getCurrentNumber());
		currentMachine.setNumberYouAreServing(lastNumberCalled);
		currentMachine.setServiceId(currentService.getId());
		this.repo.updateMachine(currentMachine);
		this.model.setCurrentMachine(currentMachine);
		// notify the view of the currentMachine
		TastierinoView view = machineId2TastierinoView.get(currentMachine.getId());
		view.changeDisplayColor(currentService.getColor());
		view.changeDisplayText(currentService.getCurrentNumber());
//		view.changeDisplayText(""+currentMachine.getNumberYouAreServing());

		// update grouping of the machine according to the services and notify
		// the tabellone view
		// this.groupService2machines();
		// this.tabelloneView.updateViewOrder();
		// this.tabelloneView.updateViewText();

	}

	/**
	 * Save the current service number for doing a jump action
	 * */
	public void saveCurrentServiceNumberAction(int machineThatTriggered) {
		TastierinoView view = this.machineId2TastierinoView.get(machineThatTriggered);
		// change the display text and color
		view.updateJumpText();
	}
}
