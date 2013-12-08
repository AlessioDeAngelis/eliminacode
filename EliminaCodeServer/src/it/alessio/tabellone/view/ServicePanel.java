package it.alessio.tabellone.view;

import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.model.TastierinoModel;

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

		this.serviceNameTextField = new JTextField();
		this.serviceNameTextField.setEditable(false);
		this.serviceNameTextField.setPreferredSize(new Dimension(50, 30));
		this.serviceNameTextField.setFont(new Font("SansSerif", Font.BOLD, 36));
		this.serviceNameTextField.setHorizontalAlignment(JTextField.CENTER);
		this.serviceNameTextField.setBackground(Color.LIGHT_GRAY);
		this.serviceNameTextField.setForeground(this.textColor);
		this.serviceNameTextField.setText(service.getName());
		this.add(serviceNameTextField);

		this.descriptionTextField = new JTextField();
		this.descriptionTextField.setEditable(false);
		this.descriptionTextField.setPreferredSize(new Dimension(50, 30));
		this.descriptionTextField.setFont(new Font("SansSerif", Font.BOLD, 22));
		this.descriptionTextField.setHorizontalAlignment(JTextField.CENTER);
		this.descriptionTextField.setBackground(Color.LIGHT_GRAY);
		this.descriptionTextField.setForeground(Color.white);
		this.descriptionTextField.setText("NUMERO              " + "OPERATORE");
		this.add(descriptionTextField);
	}
}
