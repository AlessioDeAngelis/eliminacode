package it.alessio.tastierino.view;

import javax.swing.JButton;

/**
 * A JButton with the id of the corresponding machine
 * */
public class MachineButton extends JButton {
	private int machineId;

	public MachineButton(){
		super();
		this.machineId = 0;
	}
	public MachineButton(String text, int machineId) {
		super(text);
		this.machineId = machineId;
	}
	public int getMachineId() {
		return machineId;
	}
	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}
	
	
}
