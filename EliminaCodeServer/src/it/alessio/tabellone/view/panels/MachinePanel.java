package it.alessio.tabellone.view.panels;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.model.TastierinoModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class MachinePanel extends JPanel {
	private TastierinoModel model;
	private  Machine machine;
	
	/**
	 * The field that shows the number that is being served now
	 * */
	private JTextField currentNumberTextField;
	/**
	 * The text that shows the number of the current Machine
	 * */
	private JTextField machineNumberTextField;
	
	private Color numberColor;//according the corresponding service
	
	public MachinePanel(){
		super();
	}

	public MachinePanel(TastierinoModel model,Machine machine,Color textColor) {
		super(); 
		this.model = model;
		this.machine = machine;
		this.numberColor = textColor;
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new GridLayout(1,2));
//				this.setLayout(new MigLayout(
//						));
		this.currentNumberTextField = new JTextField();
//		this.currentNumberTextField.setPreferredSize(new Dimension(50, 30));
		this.currentNumberTextField.setFont(new Font("SansSerif", Font.BOLD, 70));
		this.currentNumberTextField.setHorizontalAlignment(JTextField.CENTER);
//		this.currentNumberTextField.setLineWrap(true);
//		this.currentNumberTextField.setBackground(Color.LIGHT_GRAY);
		this.currentNumberTextField.setForeground(this.numberColor);
		Service service = this.model.getId2service().get(machine.getServiceId());
		String lastCalledNumber = service.getCurrentNumber();
		this.currentNumberTextField.setText(lastCalledNumber);
		this.add(currentNumberTextField,BorderLayout.EAST);
		
		this.machineNumberTextField = new JTextField();
//		this.machineNumberTextField.setPreferredSize(new Dimension(50, 30));
		this.machineNumberTextField.setFont(new Font("SansSerif", Font.BOLD, 70));
		this.currentNumberTextField.setHorizontalAlignment(JTextField.CENTER);
//		this.machineNumberTextField.setLineWrap(true);
//		this.machineNumberTextField.setBackground(Color.LIGHT_GRAY);
		this.machineNumberTextField.setForeground(this.numberColor);
		this.machineNumberTextField.setText(""+(machine.getId()+1));
		this.add(machineNumberTextField,BorderLayout.CENTER);

	}
	
	public void updateCurrentNumberText(){
		this.currentNumberTextField.setText(""+machine.getNumberYouAreServing());
//		this.currentNumberTextField.setText(""+machine.getCurrentService().getCurrentNumber());
		this.machineNumberTextField.setText(""+(machine.getId()+1));
//		this.currentNumberTextField.setText(machine.getCurrentService().getCurrentNumber() +"      " + "OPERATORE " + (machine.getId()+1));
	}

	public void updateNumberColor(Color color) {
		this.currentNumberTextField.setForeground(color);
		this.machineNumberTextField.setForeground(color);
	}
	
	public void setMachine(Machine machine){
		this.machine = machine;
	}
	
	public void updateFontSize(int newSize){
		this.currentNumberTextField.setFont(new Font("SansSerif", Font.BOLD, newSize));
		this.machineNumberTextField.setFont(new Font("SansSerif", Font.BOLD, newSize));
	}
}
