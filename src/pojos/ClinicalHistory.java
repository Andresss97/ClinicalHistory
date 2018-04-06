package pojos;
import java.io.*;
import java.sql.Date;

public class ClinicalHistory implements Serializable  {
	private Integer ID;
	private String addictions;
	private String observations;
	private Date lastModification;
	private Patient patient;
	
	public enum BLOODGROUP {
		AP, BP, ABP, AN, BN, ABN, ZP, ZN
	};

	private BLOODGROUP bloodgroup;
	private int medicalInsurance;

	public ClinicalHistory() {
		this.ID = null;
		this.addictions = null;
		this.observations = null;
		this.lastModification = null;
		this.bloodgroup = null;
		this.medicalInsurance = 0;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getAddictions() {
		return addictions;
	}

	public void setAddictions(String addictions) {
		this.addictions = addictions;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Date getLastModification() {
		return lastModification;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}

	public BLOODGROUP getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(BLOODGROUP bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public int getMedicalInsurance() {
		return medicalInsurance;
	}

	public void setMedicalInsurance(int medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
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
		ClinicalHistory other = (ClinicalHistory) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
