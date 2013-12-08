package it.alessio.tastierino.controller.listeners;

import it.alessio.eliminacode.common.model.TastierinoModel;
import it.alessio.tastierino.controller.TastierinoController;
import it.alessio.tastierino.view.MachineButton;
import it.alessio.tastierino.view.TastierinoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listens for the next button to be clicked
 * */
public class OnOffButtonListener implements ActionListener {

	public TastierinoController controller;

	public OnOffButtonListener(TastierinoController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		MachineButton button = (MachineButton) event.getSource();
		if (button != null) {
			this.controller.turnOnOffAction(button.getMachineId());
		}
	}
}
