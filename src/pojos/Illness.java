package pojos;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;


@Entity
@Table(name = "ILLNESSES")
public class Illness implements Serializable {

	@Id
	@GeneratedValue (generator = "ILLNESSES")
	@TableGenerator (name = "ILLNESSES", table = "sqlite_sequence", 
	pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "ILLNESSES")
	private Integer ID;
	
	private String description;
	private Date date_disease;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="IDPATIENT")
	private Patient patient;
	
	public enum typeDisease {
		HEREDITARY, PERSONAL
	};

	private typeDisease type;
	private String name;
	
	@OneToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="IDTREATMENT")
	private Treatment treatment;

	
	public Illness() {
		this.name = null;
		this.description = null;
		this.date_disease = null;
		this.type = null;
		this.ID = null;
	}

	public Illness(String name, String des, Date date, typeDisease type) {
		this.name = name;
		this.description = des;
		this.date_disease = date;
		this.type = type;
	}

	public int getID() {
		return this.ID;
	}

	public Treatment getTreatment() {
		return this.treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public void setID(int num) {
		this.ID = num;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return this.date_disease;
	}

	public void setDate(Date date) {
		this.date_disease = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public typeDisease getTypeDisease() {
		return this.type;
	}

	public void setTypeDisease(typeDisease type) {
		this.type = type;
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
		Illness other = (Illness) obj;
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
		return "Illness [description=" + description + ", date_disease=" + date_disease + ", type=" + type + ", name="
				+ name + "]";
	}

}
