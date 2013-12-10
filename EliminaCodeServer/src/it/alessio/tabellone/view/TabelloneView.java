package it.alessio.tabellone.view;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.model.TastierinoModel;
import it.alessio.eliminacode.common.persistance.JDBCRepository;
import it.alessio.eliminacode.common.util.ColorFactory;
import it.alessio.tabellone.news.FeedMessage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/* This is the View
 Its only job is to display what the user sees
 It performs no calculations, but instead passes
 information entered by the user to whomever needs
 it.
 SO it controls user input and output

 */
public class TabelloneView extends JFrame {

	/**
	 * The model. The view will render the model values to the screen
	 * */
	private TastierinoModel model;

	private Map<Integer, ServicePanel> serviceId2ServicePanel;
	private Map<Integer, MachinePanel> machineId2MachinePanel;
	private JPanel leftPanel;
	private ImagePanel imagePanel;
	private NewsPanel newsPanel;
	private JPanel upperPanel;
	private Map<Integer, List<MachinePanel>> serviceId2MachinePanel;

	public TabelloneView(String title, TastierinoModel model) {
		super(title);
		this.model = model;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.getContentPane().setBackground(Color.black);
		this.setVisible(true);
		initComponents();
	}

	private void initComponents() {
		
		this.setLayout(new GridLayout(1, 2));

		this.upperPanel = new JPanel();
		upperPanel.setLayout(new GridLayout(2, 1));
//		this.add(upperPanel,BorderLayout.NORTH);

		this.serviceId2ServicePanel = new HashMap<Integer, ServicePanel>();
		/* The services */
		for (Service service : this.model.getId2service().values()) {
			ServicePanel servicePanel = new ServicePanel(model, service, ColorFactory.getColor(service.getColor()));
			this.serviceId2ServicePanel.put(service.getId(), servicePanel);
			
		}
		/* The machine panels */
		this.machineId2MachinePanel = new HashMap<Integer, MachinePanel>();
		for (Machine machine : this.model.getMachines()) {
			Service service = this.model.getId2service().get(machine.getServiceId());
			Color serviceColor = ColorFactory.getColor(service.getColor());
			MachinePanel machinePanel = new MachinePanel(model, machine, serviceColor);
			machineId2MachinePanel.put(machine.getId(), machinePanel);
		}

		List<Service> services = new ArrayList<Service>();
		for(Service service : this.model.getId2service().values()){
			services.add(service);
		}
		updateViewOrder(services);

		/*
		 * The imagePanel
		 * */
		this.imagePanel = new ImagePanel(new ImageIcon("data/images/tabellone.JPG").getImage());
//		this.upperPanel.add(imagePanel, BorderLayout.EAST);
		this.add(imagePanel,BorderLayout.EAST);
		
		/**
		 * The news panel
		 * */
		this.newsPanel = new NewsPanel(new FeedMessage());
//		this.add(newsPanel, BorderLayout.SOUTH);
	}

	/**
	 * Update the order of the various machine panels according to the service
	 * */
	public void updateViewOrder(List<Service> allServices) {
		if (leftPanel != null) {
			this.remove(leftPanel);
		}
		this.leftPanel = new JPanel();
		this.leftPanel.setBackground(Color.black);
		this.leftPanel.setLayout(new GridLayout(10, 1));
		Map<Integer, List<Machine>> service2machines = this.model.getService2machines();
		for (Service service : allServices) {
			int serviceId = service.getId();
			// insert the service panel to the frame
			ServicePanel servicePanel = this.serviceId2ServicePanel.get(serviceId);
			this.leftPanel.add(servicePanel);
			List<Machine> machines = service2machines.get(serviceId);
			if (machines != null && machines.size() > 0) {
				for (Machine machine : machines) {
					// insert the various machine panels corresponding to the
					// service after the service panel
					int machineId = machine.getId();
					MachinePanel machinePanel = this.machineId2MachinePanel.get(machineId);
					machinePanel.setMachine(machine);
					String colorString = this.model.getId2service().get(serviceId).getColor();
					Color color = ColorFactory.getColor(colorString);
					machinePanel.updateNumberColor(color);
					this.leftPanel.add(machinePanel);
				}
			}
		}
//		this.upperPanel.add(leftPanel, BorderLayout.WEST);// add the left panel to the frame
		this.add(leftPanel,BorderLayout.WEST);
		this.leftPanel.validate();//for rendering the new layout
	}

	public void updateViewText(List<Service> services) {
		//update each servicePanel
		Set<Integer> serviceIds = this.model.getId2service().keySet();
		JDBCRepository r = new JDBCRepository();
		for(Service service : services){
			ServicePanel servicePanel = this.serviceId2ServicePanel.get(service.getId());
			servicePanel.updateLastNumberTextField(service.getCurrentNumber());
		}

		// update each machine panel
		for (MachinePanel machinePanel : this.machineId2MachinePanel.values()) {
			machinePanel.updateCurrentNumberText();
		}
	}

	public void updateNewsPanel(FeedMessage feedMessage) {
		this.newsPanel.updateText(feedMessage);
	}

}
