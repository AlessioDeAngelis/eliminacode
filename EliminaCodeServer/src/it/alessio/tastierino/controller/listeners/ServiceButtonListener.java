package it.alessio.tastierino.controller.listeners;

import it.alessio.tastierino.controller.TastierinoController;
import it.alessio.tastierino.view.MachineButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Listens for the next button to be clicked
 * */
public class ServiceButtonListener implements ActionListener {
	private TastierinoController controller;

	public ServiceButtonListener(TastierinoController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		MachineButton button = (MachineButton) event.getSource();
		if (button != null) {
			String buttonName = button.getText();
			this.controller.changeCurrentServiceAction(buttonName, button.getMachineId());
		}

	}
	/**
	 * Altra cosa che avrei potuto fare è un unico listener, che prende il nome
	 * del buttone che ha generato l'evento e poi lo passa al controller che,
	 * tramite una factory, chiamerà la giusta azione
	 * */

}
