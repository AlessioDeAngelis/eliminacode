package it.alessio.tastierino.view;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.model.TastierinoModel;
import it.alessio.eliminacode.common.util.ColorFactory;
import it.alessio.tastierino.controller.listeners.NumButtonListener;
import it.alessio.tastierino.controller.listeners.OnOffButtonListener;
import it.alessio.tastierino.controller.listeners.ServiceButtonListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/* This is the View
 Its only job is to display what the user sees
 It performs no calculations, but instead passes
 information entered by the user to whomever needs
 it.
 SO it controls user input and output

 */
public class TastierinoView extends JFrame {

	/**
	 * Button that represents the next button
	 * **/
	private MachineButton nextButton;

	/**
	 * Button that represents the on off functionality
	 * */
	private MachineButton onOffButton;

	/**
	 * Buttons that represent the services
	 * */
	private MachineButton[] serviceButtons = new MachineButton[4];

	/**
	 * Buttons that represent the numbers
	 * */
	private MachineButton[] numberButtons;

	/**
	 * Button NUM for storing a number of the queue to be remembered
	 * */
	private MachineButton numButton;
	/**
	 * The number will be remembered in this area after num button is pressed
	 * */
	private JTextArea jumpNumText;

	/***
	 * Panel that groups the numbers
	 */
	private JPanel numbersPanel;

	/**
	 * The top panel
	 * */
	private JPanel topPanel;

	/**
	 * The panel on the right
	 * */
	private JPanel rightPanel;

	/**
	 * This text field shows the current number of the queue. When is not active
	 * it shows "- - -"
	 * */
	private JTextField currentNumberDisplay;

	/**
	 * The model. The view will render the model values to the screen
	 * */
	private TastierinoModel model;

	private int id;

	public TastierinoView(String title, TastierinoModel model, int id) {
		super(title);
		this.model = model;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);
		this.id = id;
		initComponents();
	}

	private void initComponents() {
		/**
		 * The number buttons
		 * */
		this.numberButtons = new MachineButton[12];
		this.numberButtons[0] = new MachineButton("1", id);
		this.numberButtons[1] = new MachineButton("2", id);
		this.numberButtons[2] = new MachineButton("3", id);
		this.numberButtons[3] = new MachineButton("4", id);
		this.numberButtons[4] = new MachineButton("5", id);
		this.numberButtons[5] = new MachineButton("6", id);
		this.numberButtons[6] = new MachineButton("7", id);
		this.numberButtons[7] = new MachineButton("8", id);
		this.numberButtons[8] = new MachineButton("9", id);
		this.numberButtons[9] = new MachineButton("Del", id);
		this.numberButtons[10] = new MachineButton("0", id);
		this.numberButtons[11] = new MachineButton("OK", id);
		for (int i = 0; i < numberButtons.length; i++) {
			this.numberButtons[i].setFont(new Font("SansSerif", Font.BOLD, 12));
		}

		/**
		 * The num button and jump area
		 * */
		this.numButton = new MachineButton("NUM", id);
		this.jumpNumText = new JTextArea();
		this.jumpNumText.setFont(new Font("SansSerif", Font.BOLD, 16));
		this.jumpNumText.setBackground(Color.WHITE);
		/**
		 * The panel for numbers
		 * */
		this.numbersPanel = new JPanel();
		this.numbersPanel.setLayout(new GridLayout(3, 3));
		// this.numbersPanel.setLayout(new FlowLayout());
		this.add(this.numbersPanel, BorderLayout.CENTER);
		// add the numbers to the panel
		for (int i = 0; i < numberButtons.length; i++) {
			this.numbersPanel.add(numberButtons[i]);
		}
		/**
		 * Init the current numbers field
		 * */
		this.currentNumberDisplay = new JTextField();

		// this.add(currentNumberDisplay,BorderLayout.NORTH); //uncomment if you
		// don't want to add it to the top panel
		this.currentNumberDisplay.setEditable(false);
		this.currentNumberDisplay.setPreferredSize(new Dimension(50, 30));
		this.currentNumberDisplay.setFont(new Font("SansSerif", Font.BOLD, 30));
		this.currentNumberDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		this.currentNumberDisplay.setBackground(Color.LIGHT_GRAY);
		int serviceId = this.model.getCurrentMachine().getServiceId();//TODO:
		Service serviceTmp = this.model.getId2service().get(serviceId);
		this.changeDisplayColor(serviceTmp.getColor());
		this.updateDisplayText();

		/**
		 * Init the next button
		 * */
		this.nextButton = new MachineButton("NEXT", id);
		// this.add(this.nextButton,BorderLayout.EAST);

		/**
		 * Init the services button
		 * */
		for (int i = 0; i < serviceButtons.length; i++) {
			this.serviceButtons[i] = new MachineButton();
			Service service =  this.model.getId2service().get(i);
			String serviceName =service.getName();
			this.serviceButtons[i].setText(serviceName);
			this.serviceButtons[i].setForeground(ColorFactory.getColor(service.getColor()));
			this.serviceButtons[i].setMachineId(id);
			this.serviceButtons[i].setFont(new Font("SansSerif", Font.BOLD, 30));
		}

		/**
		 * Init the on/off button
		 * */
		this.onOffButton = new MachineButton("ON/OFF", id);

		/**
		 * Init the top panel. It contains the display, next button, and the
		 * on/off button
		 * */
		this.topPanel = new JPanel(new FlowLayout());
		this.topPanel.add(currentNumberDisplay);// add the display
		this.topPanel.add(this.nextButton);// add the next button
		this.topPanel.add(this.onOffButton);// add the on off button
		this.topPanel.add(numButton);// add the num button
		this.topPanel.add(jumpNumText);// add the text field area
		this.add(this.topPanel, BorderLayout.NORTH);// place the panel in north

		/**
		 * Init the panel on the right that contains the services
		 * */
		this.rightPanel = new JPanel(new GridLayout(4, 1));
		for (int i = 0; i < serviceButtons.length; i++) {
			this.rightPanel.add(serviceButtons[i]);
		}
		this.rightPanel.setVisible(true);
		this.add(rightPanel, BorderLayout.EAST);// add the panel to the frame
	}

	/**
	 * Add a listener to each number button
	 * */
	public void addNumberButtonsListener(ActionListener listenForCalcButton) {
		for (int i = 0; i < numberButtons.length; i++) {
			numberButtons[i].addActionListener(listenForCalcButton);
		}
	}

	/**
	 * Add a listener for the next button
	 * */
	public void addNextButtonListener(ActionListener listenForNextButton) {
		this.nextButton.addActionListener(listenForNextButton);
	}

	public void changeDisplayText(String newValue) {
		boolean isCurrentMachineActive = this.model.getCurrentMachine().isActive();
		if (isCurrentMachineActive) {
			this.currentNumberDisplay.setText(newValue);
		} else {
			this.currentNumberDisplay.setText("- - -");
		}
	}

	/**
	 * Takes the value from the current service current number
	 * */
	public void updateDisplayText() {
		boolean isCurrentMachineActive = this.model.getCurrentMachine().isActive();
		if (isCurrentMachineActive) {
//			this.currentNumberDisplay.setText(this.model.getCurrentMachine().getServiceId().getCurrentNumber());

			this.currentNumberDisplay.setText(""+this.model.getCurrentMachine().getNumberYouAreServing());
		} else {
			this.currentNumberDisplay.setText("- - -");
		}
	}

	public void addOnOffButtonListener(OnOffButtonListener onOffButtonListener) {
		this.onOffButton.addActionListener(onOffButtonListener);
	}

	/**
	 * Change the display color according to the current service
	 * */
	public void changeDisplayColor(String color) {
//		TastierinoModel model = this.model;
//		Machine machine = this.model.getCurrentMachine();
//		Service service = machine.getServiceId();
//			String color = machine.getServiceId().getColor();
			this.currentNumberDisplay.setForeground(ColorFactory.getColor(color));
	
	}

	public void updateJumpText() {
//		String colorName = this.model.getMachines().get(id).getServiceId().getColor();
		String colorName = "RED"; //TODO: modified it
		Color color = ColorFactory.getColor(colorName);
		this.jumpNumText.setForeground(color);
		String text = this.currentNumberDisplay.getText();
		this.jumpNumText.setText(text);
	}

	public void addServiceButtonListener(ServiceButtonListener serviceButtonListener) {
		for (int i = 0; i < this.serviceButtons.length; i++) {
			this.serviceButtons[i].addActionListener(serviceButtonListener);
		}
	}

	public void addNumButtonActionListener(NumButtonListener numButtonListener) {
		this.numButton.addActionListener(numButtonListener);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
