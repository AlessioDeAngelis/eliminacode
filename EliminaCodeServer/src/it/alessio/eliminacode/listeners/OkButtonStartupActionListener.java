package it.alessio.eliminacode.listeners;

import it.alessio.eliminacode.controller.HistoryController;
import it.alessio.eliminacode.controller.StartUpController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OkButtonStartupActionListener implements ActionListener {
	private StartUpController controller;

	public OkButtonStartupActionListener(StartUpController controller) {
		super();
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.controller.generateActivationCode();
	}

}
