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
@Entity
public class Machine implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int chiave;
	private int id;
	/**
	 * this is the digit list that will form a number set by a user of the
	 * system
	 * */
	@Transient
	private DigitsList digitsList;
	/**
	 * The number of the currentService
	 * */
	@OneToOne
	(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	private Service currentService;

	/**
	 * the current machine is active or not
	 * */
	private boolean isActive;

	private int numberYouAreServing;

	public Machine() {
		super();
	}

	public Machine(int id) {
		this.digitsList = new DigitsList();
		this.currentService = new Service();
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

	public Service getCurrentService() {
		return currentService;
	}

	public void setCurrentService(Service currentService) {
		this.currentService = currentService;
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
		result = prime * result + ((currentService == null) ? 0 : currentService.hashCode());
		result = prime * result + ((digitsList == null) ? 0 : digitsList.hashCode());
		result = prime * result + id;
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + chiave;
		result = prime * result + numberYouAreServing;
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
		if (currentService == null) {
			if (other.currentService != null)
				return false;
		} else if (!currentService.equals(other.currentService))
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
		if (chiave != other.chiave)
			return false;
		if (numberYouAreServing != other.numberYouAreServing)
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
		return "Machine [chiave=" + chiave + ", id=" + id + ", digitsList=" + digitsList + ", currentService="
				+ currentService + ", isActive=" + isActive + ", numberYouAreServing=" + numberYouAreServing + "]";
	}

}
