package it.alessio.eliminacode.common.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * Represents a physical keypad
 * */
public class Machine implements Serializable{

	private int chiave;
	private int id;
	/**
	 * this is the digit list that will form a number set by a user of the
	 * system
	 * */
	private DigitsList digitsList;
	/**
	 * The number of the currentService
	 * */
	private int serviceId;

	/**
	 * the current machine is active or not
	 * */
	private boolean isActive;

	private int numberYouAreServing;

	public Machine() {
		super();
		this.digitsList = new DigitsList();
	}

	public Machine(int id) {
		this.digitsList = new DigitsList();
		this.serviceId = id;
		this.isActive = true;
		this.id = id;
		this.chiave = id + 1;// in the db the key must start by 1
		this.numberYouAreServing = 0;
	}

	public DigitsList getDigitsList() {
		return digitsList;
	}

	public void setDigitsList(DigitsList digitsList) {
		this.digitsList = digitsList;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberYouAreServing() {
		return numberYouAreServing;
	}

	public void setNumberYouAreServing(int numberYouAreServing) {
		this.numberYouAreServing = numberYouAreServing;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chiave;
		result = prime * result + ((digitsList == null) ? 0 : digitsList.hashCode());
		result = prime * result + id;
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + numberYouAreServing;
		result = prime * result + serviceId;
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
		Machine other = (Machine) obj;
		if (chiave != other.chiave)
			return false;
		if (digitsList == null) {
			if (other.digitsList != null)
				return false;
		} else if (!digitsList.equals(other.digitsList))
			return false;
		if (id != other.id)
			return false;
		if (isActive != other.isActive)
			return false;
		if (numberYouAreServing != other.numberYouAreServing)
			return false;
		if (serviceId != other.serviceId)
			return false;
		return true;
	}

	public int getChiave() {
		return chiave;
	}

	public void setChiave(int key) {
		this.chiave = key;
	}

	@Override
	public String toString() {
		return "Machine [chiave=" + chiave + ", id=" + id + ", digitsList=" + digitsList + ", serviceId=" + serviceId
				+ ", isActive=" + isActive + ", numberYouAreServing=" + numberYouAreServing + "]";
	}

}
