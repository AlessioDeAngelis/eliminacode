package it.alessio.tastierino.controller.listeners;

import it.alessio.eliminacode.common.model.TastierinoModel;
import it.alessio.tabellone.view.TabelloneView;
import it.alessio.tastierino.controller.TastierinoController;
import it.alessio.tastierino.view.MachineButton;
import it.alessio.tastierino.view.TastierinoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listens for the next button to be clicked
 * */
public class NextButtonListener implements ActionListener {

	private TastierinoController controller;

	public NextButtonListener(TastierinoController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		/**
		 * Va fatto così, altrimenti in caso di più frame vengono generate
		 * eccezioni. Il listener ha il solo compito di notificare il controller
		 * che poi chiamerà l'azione corrispondente
		 * */
		MachineButton button = (MachineButton) event.getSource();
		if (button != null) {
			this.controller.nextNumberAction(button.getMachineId());
		}
	}
	/**
	 * Altra cosa che avrei potuto fare è un unico listener, che prende il nome
	 * del buttone che ha generato l'evento e poi lo passa al controller che,
	 * tramite una factory, chiamerà la giusta azione
	 * */

}
