package it.alessio.eliminacode.listeners;

import it.alessio.eliminacode.controller.HistoryController;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyListSelectionListener implements ListSelectionListener {
	private HistoryController controller;

	public MyListSelectionListener(HistoryController controller) {
		super();
		this.controller = controller;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			JList list = (JList) e.getSource();
			String value = (String) list.getSelectedValue();
			controller.setSelectedValue(value);
		}
	}

}