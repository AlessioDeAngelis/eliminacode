package it.alessio.eliminacode.common.model;

import java.util.Date;
import java.util.GregorianCalendar;


public class HistoryLine {
	private long id;
	private int serviceId;
	private int machineId;
	private String timestamp;
	private String year;
	private String month;
	private String day;

	public HistoryLine() {
		super();
		this.serviceId = 0;
		this.machineId = 0;
		this.timestamp = "";
		this.year = "";
		this.month = "";
		this.day = "";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getMachineId() {
		return machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + machineId;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + serviceId;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		HistoryLine other = (HistoryLine) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (id != other.id)
			return false;
		if (machineId != other.machineId)
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (serviceId != other.serviceId)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HistoryLine [id=" + id + ", serviceId=" + serviceId + ", machineId=" + machineId + ", timestamp="
				+ timestamp + ", year=" + year + ", month=" + month + ", day=" + day + "]";
	}
	
	

}
