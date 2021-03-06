package pojos;

import java.sql.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xml.AdapterDate;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "VACCINE")
public class Vaccine {
	@XmlAttribute
	@Id
	@GeneratedValue(generator = "VACCINE")
	@TableGenerator(name = "VACCINE", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "VACCINE")
	private Integer ID;
	
	public enum typeVaccine{CHOLERA,DIPHTHERIA,INFLUENZA_A,INFLUENZA_B,HEPATITIS_A,HEPATITIS_B,PAPILLOMAVIRUS,
		HERPES,MEASLES,MENINGOCOCCAL,PNEUMOCOCCAL,RABIES,ROTAVIRUS,RUBELLA,SMALLPOX,TETANUS,TUBERCULOSIS,TYPHOID,
		VARICELLA,YELLOWFEVER};
	
	@XmlAttribute	
	@Column(name = "name")
	private typeVaccine nameVaccine;
	
	@XmlElement
	@XmlJavaTypeAdapter(AdapterDate.class)
	private Date date;
	
	@Column(name = "observations")
	@XmlElement
	private String description;
	
	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "IDPATIENT")
	private Patient patient;
	
	public Vaccine() {
		this.ID = 0;
		this.nameVaccine = null;
		this.date = null;
		this.patient = null;
		
	}
	
	public Vaccine(int ID, typeVaccine name, Date date) {
		this.ID = ID;
		this.nameVaccine = name;
		this.date = date;
	}
	
	public Patient getPatient() {
		return this.patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient=patient;
	}
	
	public int getIDvaccine() {
		return this.ID;
	}
	
	public void setIDvaccine(int ID) {
		this.ID = ID;
	}

	public typeVaccine getNameVaccine() {
		return this.nameVaccine;
	}
	
	public void setNameVaccine(typeVaccine name) {
		this.nameVaccine = name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String des) {
		this.description = des;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((nameVaccine == null) ? 0 : nameVaccine.hashCode());
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
		Vaccine other = (Vaccine) obj;
		if (ID != other.ID)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (nameVaccine != other.nameVaccine)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vaccine [IDvaccine=" + ID + ", nameVaccine=" + nameVaccine + ", date=" + date + ", description="
				+ description + "]";
	}
}
