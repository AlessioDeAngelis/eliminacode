package it.alessio.tabellone.controller;

public class Singleton {
	/**
	 * Costruttore privato, in quanto la creazione dell'istanza deve essere
	 * controllata.
	 */
	private Singleton() {
	}

	/**
	 * La classe Contenitore viene caricata/inizializzata alla prima esecuzione
	 * di getInstance() ovvero al primo accesso a Contenitore.ISTANZA, ed in
	 * modo thread-safe. Anche l'inizializzazione dell'attributo statico,
	 * pertanto, viene serializzata.
	 */
	private static class Contenitore {
		private final static Singleton ISTANZA = new Singleton();
	}

	/**
	 * Punto di accesso al Singleton. Ne assicura la creazione thread-safe solo
	 * all'atto della prima chiamata.
	 * 
	 * @return il Singleton corrispondente
	 */
	public static Singleton getInstance() {
		return Contenitore.ISTANZA;
	}

	private static TabelloneController view;

	public static TabelloneController getTabelloneController() {
		return view;
	}

	public static void setTabelloneController(TabelloneController tabController) {
		view = tabController;
	}

}
