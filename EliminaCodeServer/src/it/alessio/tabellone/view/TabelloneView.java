package it.alessio.tabellone.view;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.model.TastierinoModel;
import it.alessio.eliminacode.common.util.ColorFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		this.serviceId2ServicePanel = new HashMap<Integer, ServicePanel>();
		/* The services */
		for (Service service : this.model.getId2service().values()) {
			ServicePanel servicePanel = new ServicePanel(model, service, ColorFactory.getColor(service.getColor()));
			this.serviceId2ServicePanel.put(service.getId(), servicePanel);
		}
		/* The machine panels */
		this.machineId2MachinePanel = new HashMap<Integer, MachinePanel>();
		for (Machine machine : this.model.getMachines()) {
			Color serviceColor = ColorFactory.getColor(machine.getServiceId().getColor());
			MachinePanel machinePanel = new MachinePanel(model, machine, serviceColor);
			machineId2MachinePanel.put(machine.getId(), machinePanel);
		}

		updateViewOrder();

		this.imagePanel = new ImagePanel(new ImageIcon("data/images/tabellone.JPG").getImage());
		this.add(imagePanel, BorderLayout.EAST);
	}

	/**
	 * Update the order of the various machine panels according to the service
	 * */
	public void updateViewOrder() {
		if (leftPanel != null) {
			this.remove(leftPanel);
		}
		this.leftPanel = new JPanel();
		this.leftPanel.setBackground(Color.black);
		this.leftPanel.setLayout(new GridLayout(10, 1));
		Map<Integer, List<Machine>> service2machines = this.model.getService2machines();
		for (Integer serviceId : service2machines.keySet()) {
			// insert the service panel to the frame
			ServicePanel servicePanel = this.serviceId2ServicePanel.get(serviceId);
			this.leftPanel.add(servicePanel);
			List<Machine> machines = service2machines.get(serviceId);
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
		this.add(leftPanel, BorderLayout.WEST);// add the left panel to the frame

		this.validate();//for rendering the new layout
	}

	public void updateViewText() {
		// update each machine panel
		for (MachinePanel machinePanel : this.machineId2MachinePanel.values()) {
			//TODO: aggiornare anche la macchina
			machinePanel.updateCurrentNumberText();
		}
	}

}
