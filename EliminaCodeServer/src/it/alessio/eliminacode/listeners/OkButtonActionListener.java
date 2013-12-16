package it.alessio.eliminacode.listeners;

import it.alessio.eliminacode.controller.HistoryController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OkButtonActionListener implements ActionListener {
	private HistoryController controller;

	public OkButtonActionListener(HistoryController controller) {
		super();
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.controller.calculateStatistics();
	}

}
