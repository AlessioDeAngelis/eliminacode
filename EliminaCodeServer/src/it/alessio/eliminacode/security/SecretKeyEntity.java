package it.alessio.eliminacode.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq", initialValue = 2, allocationSize = 100)
public class SecretKeyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	private int id;
	private String activationCode;// from the mac
	private String serialCode;

	public SecretKeyEntity() {
		super();
		this.id = 0;
		this.activationCode = "";
		this.serialCode = "";
	}

	public SecretKeyEntity(int id, String activationCode, String serialCode) {
		super();
		this.id = id;
		this.activationCode = activationCode;
		this.serialCode = serialCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activationCode == null) ? 0 : activationCode.hashCode());
		result = prime * result + id;
		result = prime * result + ((serialCode == null) ? 0 : serialCode.hashCode());
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
		SecretKeyEntity other = (SecretKeyEntity) obj;
		if (activationCode == null) {
			if (other.activationCode != null)
				return false;
		} else if (!activationCode.equals(other.activationCode))
			return false;
		if (id != other.id)
			return false;
		if (serialCode == null) {
			if (other.serialCode != null)
				return false;
		} else if (!serialCode.equals(other.serialCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SecretKeyEntity [id=" + id + ", activationCode=" + activationCode + ", serialCode=" + serialCode + "]";
	}

}
