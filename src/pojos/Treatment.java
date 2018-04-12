package pojos;
import java.io.*;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
@Entity
@Table(name = "treatments")

public class Treatment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "treatments")
	@TableGenerator(name = "treatments", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "treatments")
	

	private Integer ID;
	private Date startDate;
	private Date endDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	private Patient patient;
	public enum typeTreatment {
		MEDICATION, REHAB
	};
	private String name;

	private typeTreatment treatment;
	private String description;
	private String tresults;
	private Doctor doctor;
	@OneToOne(mappedBy="vaccine")
	private Vaccine vaccine;

	public Treatment() {
		this.name = null;
		this.ID = null;
		this.startDate = null;
		this.endDate = null;
		this.treatment = null;
		this.description = null;
		this.tresults = null;
	}

	public int getIDtreatment() {
		return ID;
	}

	public void setIDtreatment(int iDtreatment) {
		ID = iDtreatment;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getResults() {
		return this.tresults;
	}

	public void setResults(String results) {
		this.tresults = results;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public typeTreatment getTreatment() {
		return treatment;
	}

	public void setTreatment(typeTreatment treatment) {
		this.treatment = treatment;
	}

	public String getDescrpition() {
		return description;
	}

	public void setDescrpition(String descrpition) {
		this.description = descrpition;
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
		Treatment other = (Treatment) obj;
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
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Treatment [ID=" + ID + ", startDate=" + startDate + ", endDate=" + endDate + ", name=" + name
				+ ", description=" + description + ", tresults=" + tresults + "]";
	}
	

}
