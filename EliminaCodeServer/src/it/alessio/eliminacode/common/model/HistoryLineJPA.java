package it.alessio.eliminacode.common.model;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@SequenceGenerator(name = "seq", initialValue = 2, allocationSize = 100)
public class HistoryLineJPA {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	private long id;
	private int serviceId;
	private int machineId;
	@Temporal(TemporalType.DATE)
	private Date timestamp;
	private int anno;
	private int month;
	private int day;

	public HistoryLineJPA() {
		super();
		this.serviceId = 0;
		this.machineId = 0;
		this.timestamp = new Date();
		 this.anno = 0;
		this.month = 0;
		this.day = 0;
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
		result = prime * result + day;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + machineId;
		result = prime * result + month;
		result = prime * result + serviceId;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + anno;
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
		HistoryLineJPA other = (HistoryLineJPA) obj;
		if (day != other.day)
			return false;
		if (id != other.id)
			return false;
		if (machineId != other.machineId)
			return false;
		if (month != other.month)
			return false;
		if (serviceId != other.serviceId)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (anno != other.anno)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HistoryLineJPA [id=" + id + ", serviceId=" + serviceId + ", machineId=" + machineId + ", timestamp="
				+ timestamp + ", year=" + anno + ", month=" + month + ", day=" + day + "]";
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	

}
