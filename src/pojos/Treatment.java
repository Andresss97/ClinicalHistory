package pojos;
import java.io.*;
import java.sql.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

//*CLASE NO ACABADA endate y startdate salen en las tables

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "TREATMENT")
public class Treatment implements Serializable{
	
	@XmlAttribute
	@Id
	@GeneratedValue(generator = "TREATMENT")
	@TableGenerator(name = "TREATMENT", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "TREATMENT")
	private Integer ID;
	
	/*private Date startDate;
	private Date endDate;*/
	
	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPATIENT")
	private Patient patient;
	
	
	//*Falta anotar en xml las que estan comentadas
	
	/*public enum typeTreatment {
		MEDICATION, REHAB
	};*/
	
	//private String name;
	//private typeTreatment treatment;
	
	@XmlTransient
	@OneToOne(fetch = FetchType.LAZY, mappedBy="treatment")
	private Illness illness; 
	
	@XmlElement
	private String description;
	
	@XmlElement
	private String tresults;
	
	@XmlTransient
	@OneToOne (fetch = FetchType.LAZY, mappedBy = "treatment")
	private Allergies allergy;
	
	@XmlTransient
	@OneToOne (fetch = FetchType.LAZY, mappedBy = "treatment")
	private Surgeries surgery;

	public Treatment() {
		//this.name = null;
		this.ID = null;
		/*this.startDate = null;
		this.endDate = null;*/
		//this.treatment = null;
		this.description = null;
		this.tresults = null;
	}

	public int getIDtreatment() {
		return ID;
	}

	public void setIDtreatment(int iDtreatment) {
		ID = iDtreatment;
	}

	public String getResults() {
		return this.tresults;
	}

	public void setResults(String results) {
		this.tresults = results;
	}

	/*public Date getStartDate() {
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
	}*/

	/*public typeTreatment getTreatment() {
		return treatment;
	}

	public void setTreatment(typeTreatment treatment) {
		this.treatment = treatment;
	}*/

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
	
	/*public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}*/

	public Surgeries getSurgery() {
		return surgery;
	}

	public void setSurgery(Surgeries surgery) {
		this.surgery = surgery;
	}

	@Override
	public String toString() {
		return "Treatment [ID=" + ID + ", startDate=" + /*startDate + ", endDate=" + endDate + */", name=" + /*name
				+ */", description=" + description + ", tresults=" + tresults + "]";
	}
}
