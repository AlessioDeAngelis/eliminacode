package it.alessio.eliminacode.listeners;

import it.alessio.eliminacode.controller.StartUpController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ModifyInputSerialCodeActionListener implements ActionListener {
	private StartUpController controller;

	public ModifyInputSerialCodeActionListener(StartUpController controller) {
		super();
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JTextField field = (JTextField)event.getSource();
		String text = field.getText();
		if(text!=null && !text.equals("") ){
		this.controller.modifyInputSerialCode(text);
		}
	}

}
