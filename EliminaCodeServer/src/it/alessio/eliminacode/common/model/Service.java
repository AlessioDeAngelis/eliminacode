package it.alessio.eliminacode.common.model;

import it.alessio.tabellone.controller.TabelloneController;

import java.io.Serializable;

/**
 * This class represent a service, a queue *
 */

public class Service implements Serializable {

	private static final long serialVersionUID = 1L;
	private int chiave;
	private int id;
	private String name;
	private String color;

	/**
	 * the current number that must be served for this queue
	 * */
	private String currentNumber;

	public Service() {
		super();
		this.id = -1;
		this.name = "Servizio";
		this.currentNumber = "- - -";
	}

	public Service(int id, String name, String currentNumber, String color) {
		super();
		this.id = id;
		this.chiave = id + 1;// in the db the key must start by 1
		this.name = name;
		this.currentNumber = currentNumber;
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(String currentNumber) {
		this.currentNumber = currentNumber;
	}

	public int getChiave() {
		return chiave;
	}

	public void setChiave(int key) {
		this.chiave = key;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Service [chiave=" + chiave + ", id=" + id + ", name=" + name + ", color=" + color + ", currentNumber="
				+ currentNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chiave;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((currentNumber == null) ? 0 : currentNumber.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		if (chiave != other.chiave)
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (currentNumber == null) {
			if (other.currentNumber != null)
				return false;
		} else if (!currentNumber.equals(other.currentNumber))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
