package it.alessio.eliminacode.common.model;

import java.util.Date;


public class HistoryLine {
	private Date timestamp;
	private long id;
	private int serviceId;
	private int machineId;

	public HistoryLine() {
		super();
		this.id = 0;
		this.serviceId = 0;
		this.machineId = 0;
		this.timestamp = new Date();
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + machineId;
		result = prime * result + serviceId;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		if (id != other.id)
			return false;
		if (machineId != other.machineId)
			return false;
		if (serviceId != other.serviceId)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HistoryLine [timestamp=" + timestamp + ", id=" + id + ", serviceId=" + serviceId + ", machineId="
				+ machineId + "]";
	}

}
