package it.alessio.tabellone.view.panels;

import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.model.TastierinoModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServicePanel extends JPanel {
	private TastierinoModel model;
	private Service service;
	private List<MachinePanel> machinePanels;
	/**
	 * The field that shows the name of the service
	 * */
	private JTextField serviceNameTextField;
	private JTextField serviceLastNumberTextField;
	private JTextField descriptionTextField;

	private Color textColor;

	public ServicePanel() {
		super();
	}

	public ServicePanel(TastierinoModel model, Service service, Color textColor) {
		super();
		this.model = model;
		this.service = service;
		this.textColor = textColor;
		this.machinePanels = new ArrayList<MachinePanel>();
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new GridLayout(2, 1));
		JPanel topPanel = new JPanel();
//		this.add(topPanel);
		this.serviceNameTextField = new JTextField();
		this.serviceNameTextField.setEditable(false);
//		this.serviceNameTextField.setPreferredSize(new Dimension(50, 300));
		this.serviceNameTextField.setFont(new Font("SansSerif", Font.BOLD, 36));
		this.serviceNameTextField.setHorizontalAlignment(JTextField.CENTER);
		this.serviceNameTextField.setBackground(Color.BLACK);
//		this.serviceNameTextField.setBackground(Color.LIGHT_GRAY);
		this.serviceNameTextField.setForeground(this.textColor);
		this.serviceNameTextField.setText(service.getName().toUpperCase()+"     " + service.getCurrentNumber() );
//		topPanel.add(serviceNameTextField,BorderLayout.WEST);
		this.add(serviceNameTextField);
		
		this.serviceLastNumberTextField = new JTextField();
		this.serviceLastNumberTextField.setEditable(false);
//		this.serviceLastNumberTextField.setPreferredSize(new Dimension(50, 500));
		this.serviceLastNumberTextField.setFont(new Font("SansSerif", Font.BOLD, 36));
		this.serviceLastNumberTextField.setHorizontalAlignment(JTextField.CENTER);
		this.serviceLastNumberTextField.setBackground(Color.LIGHT_GRAY);
		this.serviceLastNumberTextField.setForeground(this.textColor);
		this.serviceLastNumberTextField.setText(service.getCurrentNumber());
		topPanel.add(serviceLastNumberTextField,BorderLayout.EAST);

		this.descriptionTextField = new JTextField();
		this.descriptionTextField.setEditable(false);
//		this.descriptionTextField.setPreferredSize(new Dimension(50, 50));
		this.descriptionTextField.setFont(new Font("SansSerif", Font.BOLD, 22));
		this.descriptionTextField.setHorizontalAlignment(JTextField.LEFT);
		this.descriptionTextField.setBackground(Color.LIGHT_GRAY);
		this.descriptionTextField.setForeground(Color.BLACK);
		this.descriptionTextField.setText("        NUMERO CHIAMATO                        " + "SPORTELLO");
		this.add(descriptionTextField);
	}
	
	public void updateLastNumberTextField(String number){
		this.serviceNameTextField.setText(service.getName().toUpperCase()+"     " + number );
	}
}
