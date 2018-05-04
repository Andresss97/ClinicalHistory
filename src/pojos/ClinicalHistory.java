package pojos;
import java.io.*;
import java.sql.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "CLINICALHISTORY")
public class ClinicalHistory implements Serializable {
	
	@XmlAttribute
	@Id
	@GeneratedValue (generator = "CLINICALHISTORY")
	@TableGenerator (name = "CLINICALHISTORY", table = "sqlite_sequence", 
	pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "CLINICALHISTORY")
	private Integer ID;
	
	public enum ADDICTIONS {
		YES, NONE
	};
	
	@XmlElement
	@Column(name = "")
	private ADDICTIONS addictionsAlcohol;
	
	@XmlElement
	private ADDICTIONS addictionsDrugs;
	
	@XmlElement
	private ADDICTIONS addictionsOthers;
	
	@XmlTransient
	@OneToOne
	@JoinColumn(name = "IDPATIENT")
	private Patient patient;
	
	public enum BLOODGROUP {
		AP, BP, ABP, AN, BN, ABN, ZP, ZN
	};

	@XmlElement
	private BLOODGROUP bloodgroup;
	
	@Column(name = "insurancecompany")
	private String medicalInsurance;

	public ClinicalHistory() {
		this.ID = null;
		this.bloodgroup = null;
		this.addictionsAlcohol = null;
		this.addictionsDrugs = null;
		this.addictionsOthers = null;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public BLOODGROUP getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(BLOODGROUP bloodgroup) {
		this.bloodgroup = bloodgroup;
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

	public ADDICTIONS getAddictionAlcohol() {
		return addictionsAlcohol;
	}

	public void setAddictionAlcohol(ADDICTIONS addictionAlcohol) {
		this.addictionsAlcohol = addictionAlcohol;
	}

	public ADDICTIONS getAddictionsDrugs() {
		return addictionsDrugs;
	}

	public void setAddictionsDrugs(ADDICTIONS addictionsDrugs) {
		this.addictionsDrugs = addictionsDrugs;
	}

	public ADDICTIONS getAddictionsOthers() {
		return addictionsOthers;
	}

	public void setAddictionsOthers(ADDICTIONS addictionsOthers) {
		this.addictionsOthers = addictionsOthers;
	}

	public String getMedicalInsurance() {
		return medicalInsurance;
	}

	public void setMedicalInsurance(String medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	public void setID(Integer iD) {
		ID = iD;
	}
	
}
