package it.alessio.tastierino.controller.listeners;

import it.alessio.tastierino.controller.TastierinoController;
import it.alessio.tastierino.view.MachineButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The listener class for the number buttons
 * */
public class NumButtonListener implements ActionListener {

	private TastierinoController controller;

	public NumButtonListener(TastierinoController controller) {
		super();
		this.controller = controller;
	}

	/**
	 * It catches the input from the user and
	 * */
	@Override
	public void actionPerformed(ActionEvent action) {
		String value;
		MachineButton button = (MachineButton) action.getSource();
		if (button != null) {
			int id = button.getMachineId();
			controller.saveCurrentServiceNumberAction(id);
		}
	}

}
