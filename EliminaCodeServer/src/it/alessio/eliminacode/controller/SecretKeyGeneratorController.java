package it.alessio.eliminacode.controller;

import it.alessio.eliminacode.security.EncryptorManager;
import it.alessio.eliminacode.view.SecretKeyView;

public class SecretKeyGeneratorController {
	private SecretKeyView view;
	private String inputText;

	public SecretKeyGeneratorController() {
		this.view = new SecretKeyView(this);
		this.inputText = "";
	}

	public void generateSerialCode() {
		if (!inputText.equals("")) {
			EncryptorManager encMan = new EncryptorManager();
			String serialCode = encMan.generateSerialKey(inputText);
			this.view.changeOutputText(serialCode);
		}
	}

	public void modifyInputText(String text) {
		this.inputText = text;
	}
}
