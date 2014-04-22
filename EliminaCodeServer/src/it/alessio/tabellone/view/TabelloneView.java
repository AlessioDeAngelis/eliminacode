package it.alessio.tabellone.view;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.model.TastierinoModel;
import it.alessio.eliminacode.common.util.ColorFactory;
import it.alessio.tabellone.news.FeedMessage;
import it.alessio.tabellone.view.panels.ImagePanel;
import it.alessio.tabellone.view.panels.MachinePanel;
import it.alessio.tabellone.view.panels.NewsPanel;
import it.alessio.tabellone.view.panels.ServicePanel;
import it.alessio.tabellone.view.panels.VideoPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/* This is the View
 Its only job is to display what the user sees
 It performs no calculations, but instead passes
 information entered by the user to whomever needs
 it.
 SO it controls user input and output

 */
//TODO: audio viene fatto partire dal tabellone solo dopo che il tabellone abbia correttamente visualizzato i cambi
public class TabelloneView extends JFrame {

	/**
	 * The model. The view will render the model values to the screen
	 * */
	private TastierinoModel model;

	private Map<Integer, ServicePanel> serviceId2ServicePanel;
	private Map<Integer, MachinePanel> machineId2MachinePanel;
	private JPanel leftPanel;
	private ImagePanel imagePanel;
	private VideoPanel videoPanel;
	private NewsPanel newsPanel;
	private JPanel upperPanel;
	private Map<Integer, List<MachinePanel>> serviceId2MachinePanel;
	private Properties properties;

	public TabelloneView(TastierinoModel model, Properties properties) {
		super(properties.getProperty("nome_azienda", "Tabellone"));
		this.properties = properties;
		this.model = model;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.getContentPane().setBackground(Color.black);
		this.setVisible(true);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		initComponents();
		this.validate();// TODO: maybe delete it
	}

	private void initComponents() {
		// this.setLayout(new GridBagLayout());

		this.upperPanel = new JPanel();
		upperPanel.setLayout(new GridLayout(2, 1));
		// this.add(upperPanel,BorderLayout.NORTH);
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
		for (Service service : this.model.getId2service().values()) {
			services.add(service);
		}

		/**
		 * The left panel
		 * */
		this.leftPanel = new JPanel();
		this.leftPanel.setBackground(Color.DARK_GRAY);
		this.leftPanel.setLayout(new GridLayout(10, 1));
		updateViewOrder(services);

		/*
		 * The imagePanel
		 */
		this.imagePanel = new ImagePanel(new ImageIcon("data/images/tabellone.JPG").getImage());
		// this.upperPanel.add(imagePanel, BorderLayout.EAST);
		// this.add(imagePanel, BorderLayout.EAST);

		/**
		 * The news panel
		 * */
		this.newsPanel = new NewsPanel(new FeedMessage());
		// this.add(newsPanel, BorderLayout.SOUTH);

		/**
		 * The video Panel, according to the architecture *
		 * */
		String vlcPath = "";
		String osArch = System.getProperty("os.arch");
		if (osArch.equals("amd64")) {// 64 bit
			vlcPath = this.properties.getProperty("vlc_path_win64");
		} else {// 32 bit
			vlcPath = this.properties.getProperty("vlc_path_win32");
		}
		this.videoPanel = new VideoPanel(vlcPath);

	}

	/**
	 * Update the order of the various machine panels according to the service
	 * */
	public void updateViewOrder(List<Service> allServices) {
		// if (leftPanel != null) {
		// this.remove(leftPanel);
		// }

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
					if (machine.isActive()) { //show the machine only if it's active
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
		}
		// this.upperPanel.add(leftPanel, BorderLayout.WEST);// add the left
		// panel to the frame
		// this.add(leftPanel, BorderLayout.WEST);
		this.leftPanel.validate();// for rendering the new layout
		this.validate();
	}

	public void updateViewText(List<Service> services) {
		// update each servicePanel
		Set<Integer> serviceIds = this.model.getId2service().keySet();
		for (Service service : services) {
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

	public void orderPanels4() {
		this.setLayout(new GridBagLayout());
		Container pane = this.getContentPane();
		GridBagConstraints c = new GridBagConstraints();
		// left panel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.ipady = 400; // make this component tall
		c.ipadx = 1250; // make this component tall

		c.gridy = 0;
		// c.insets = new Insets(50, 50, 50, 50); //top padding
		// c.weightx = 0.5;
		JButton button = new JButton("1");
		// pane.add(button,c);
		pane.add(this.leftPanel, c);
		// right panel (image or video)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;// third column
		c.gridy = 0;
		c.ipady = 0;
		c.ipadx = 0; // make this component tall

		// c.weightx = 0.5;
		// button = new JButton("2");
		// pane.add(button,c);

		pane.add(this.imagePanel, c);
		// news panel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;// third row
		c.ipady = 500;
		c.ipadx = 500;
		c.anchor = GridBagConstraints.SOUTH; // bottom of space
		// c.weightx = 0.5;
		// pane.add(button, c);

		pane.add(this.newsPanel, c);

	}

	public void orderPanels() {
		this.setLayout(new BorderLayout());
		Container pane = this.getContentPane();

		pane.add(this.leftPanel, BorderLayout.LINE_START);
		this.leftPanel.setPreferredSize(new Dimension(800, 800));
		pane.add(this.videoPanel, BorderLayout.LINE_END);
		this.videoPanel.setPreferredSize(new Dimension(500, 500));
		this.videoPanel.validate();

		pane.add(this.newsPanel, BorderLayout.PAGE_END);
	}

	public void playVideo(String videoPath) {

		this.videoPanel.play(videoPath);
	}

}
