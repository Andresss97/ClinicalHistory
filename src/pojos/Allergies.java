package pojos;
import java.io.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table (name = "ALLERGIES")
public class Allergies implements Serializable {
	
	@XmlAttribute
	@Id
	@GeneratedValue (generator = "ALLERGIES")
	@TableGenerator (name = "ALLERGIES", table = "sqlite_sequence", 
	pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "ALLERGIES")
	private Integer ID;
	
	@XmlElement
	@Column(name = "type")
	private String type;
	
	@XmlElement
	private String observations;
	
	@XmlElement
	@OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn (name = "IDTREATMENT")
	private Treatment treatment;
	
	@XmlTransient
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "IDPATIENT")
	private Patient patient;

	public Allergies() {
		this.ID = null;
		this.type = null;
		this.observations = null;
		this.treatment = null;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
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
		Allergies other = (Allergies) obj;
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

	@Override
	public String toString() {
		return "Allergies [ID=" + ID + ", group=" + type + ", observations=" + observations + "]";
	}
	
}
