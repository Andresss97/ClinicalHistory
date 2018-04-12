package pojos;

import java.sql.Date;

import javax.persistence.*;




@Entity
@Table(name = "vaccines")

public class Vaccine {
	@Id
	@GeneratedValue(generator = "vaccines")
	@TableGenerator(name = "vaccines", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "vaccines")
	
	private Integer IDvaccine;
	public enum typeVaccine{CHOLERA,DIPHTHERIA,INFLUENZA_A,INFLUENZA_B,HEPATITIS_A,HEPATITIS_B,PAPILLOMAVIRUS,
		HERPES,MEASLES,MENINGOCOCCAL,PNEUMOCOCCAL,RABIES,ROTAVIRUS,RUBELLA,SMALLPOX,TETANUS,TUBERCULOSIS,TYPHOID,
		VARICELLA,YELLOWFEVER};
		@OneToMany(mappedBy="vaccine")
	private typeVaccine nameVaccine;
	private Date date;
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	private Patient patient;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "treatment_id")
	private Treatment treatment;
	

	public Vaccine() {
		this.IDvaccine = 0;
		this.nameVaccine = null;
		this.date = null;
		this.patient = null;
		this.treatment = null;
	}
	
	public Vaccine(int ID, typeVaccine name, Date date) {
		this.IDvaccine = ID;
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
		return this.IDvaccine;
	}
	
	public void setIDvaccine(int ID) {
		this.IDvaccine = ID;
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
		result = prime * result + IDvaccine;
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
		if (IDvaccine != other.IDvaccine)
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

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	@Override
	public String toString() {
		return "Vaccine [IDvaccine=" + IDvaccine + ", nameVaccine=" + nameVaccine + ", date=" + date + ", description="
				+ description + "]";
	}
	
	
	
}
