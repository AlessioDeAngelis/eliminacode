package it.alessio.eliminacode.main;

import it.alessio.tabellone.controller.TabelloneController;

public class TabelloneClient {
	public static void main(String[] args) {
		TabelloneController tabellone = new TabelloneController();
		tabellone.mainLoop();
	}
}
