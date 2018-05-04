
package pojos;
import java.io.*;
import java.util.LinkedList;
import javax.persistence.*;
import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "PATIENT")
public class Patient extends Person{
	
	@XmlAttribute
	@Id
	@GeneratedValue(generator = "PATIENT")
	@TableGenerator(name = "PATIENT", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "PATIENT")
	private Integer ID;
	
	@XmlElement
	private float weight;
	
	@XmlElement
	private float height;
	
	@XmlElement
	@Column(name = "homephone")
	private int housePhone;
	
	@XmlTransient
	@ManyToMany
	@JoinTable(name="MAPPING",
			joinColumns={@JoinColumn(name="IDDOCTOR", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="IDPATIENT", referencedColumnName="id")})
	private LinkedList<Doctor> doctors;
	
	@XmlElement 
	@OneToMany(mappedBy="patient")
	private LinkedList<Appointment> appointments;
	
	@XmlElement
	@OneToMany(mappedBy="patient")
	private LinkedList<Illness> illnesses;
	
	
	@XmlElement
	@OneToMany(mappedBy="patient")
	private LinkedList<Allergies> allergies;
	
	@XmlElement
	@OneToMany(mappedBy="patient")
	private LinkedList<Surgeries> surgeries;
	
	@XmlElement
	@OneToMany(mappedBy="patient")
	private LinkedList<Treatment> treatment;
	
	@XmlElement
	@OneToMany(mappedBy="patient")
	private LinkedList<Vaccine> vaccines;
	
	@XmlElement
	@OneToOne(mappedBy="patient")
	private ClinicalHistory cHistory;
	
	public Patient() { 
		super();
		this.weight = 0.0F;
		this.height = 0.0F;
		this.housePhone = 0;
		this.doctors = new LinkedList<>();
		this.appointments = new LinkedList<>();
		this.illnesses = new LinkedList<>();
		this.allergies = new LinkedList<>();
		this.surgeries = new LinkedList<>();
		this.vaccines = new LinkedList<>();
		this.cHistory = new ClinicalHistory();
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getHousePhone() {
		return housePhone;
	}

	public void setHousePhone(int housePhone) {
		this.housePhone = housePhone;
	}

	public LinkedList<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(LinkedList<Doctor> doctors) {
		this.doctors = doctors;
	}

	public LinkedList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(LinkedList<Appointment> appointments) {
		this.appointments = appointments;
	}

	public LinkedList<Illness> getIllnesses() {
		return illnesses;
	}

	public void setIllnesses(LinkedList<Illness> illnesses) {
		this.illnesses = illnesses;
	}

	public LinkedList<Allergies> getAllergies() {
		return allergies;
	}

	public void setAllergies(LinkedList<Allergies> allergies) {
		this.allergies = allergies;
	}

	public LinkedList<Surgeries> getSurgeries() {
		return surgeries;
	}

	public void setSurgeries(LinkedList<Surgeries> surgeries) {
		this.surgeries = surgeries;
	}

	public LinkedList<Vaccine> getVaccines() {
		return vaccines;
	}

	public void setVaccines(LinkedList<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}
	
	public ClinicalHistory getClinicalHistory() {
		return this.cHistory;
	}
	
	public void setClinicalHistory(ClinicalHistory cHistory) {
		this.cHistory= cHistory;
	}
	
	public void addDoctor(Doctor doctor){
		this.doctors.add(doctor);
	} 
	
	public void removeDoctor(Doctor doctor){
		this.doctors.remove(doctor);
	}
	
	public void addIllness(Illness illness){
		this.illnesses.add(illness);
	} 
	
	public void removeIllness(Illness illness){
		this.illnesses.remove(illness);
	}
	
	public void addAppointment(Appointment appointment){
		this.appointments.add(appointment);
	} 
	
	public void removeAppointment(Appointment appointment){
		this.appointments.remove(appointment);
	}
	
	public void addAllergy(Allergies allergies){
		this.allergies.add(allergies);
	} 
	
	public void removeAllergy(Allergies allergies){
		this.allergies.remove(allergies);
	}
	
	public void addSurgery(Surgeries surgery){
		this.surgeries.add(surgery);
	} 
	
	public void removeSurgery(Surgeries surgery){
		this.surgeries.remove(surgery);
	}
	
	public void addVaccine(Vaccine vaccine){
		this.vaccines.add(vaccine);
	} 
	
	public void removeVaccine(Vaccine vaccine){
		this.vaccines.remove(vaccine);
	}
	
	
	
	@Override
	public String toString() {
		return "Patient: "+ name + " " + surname;
	}
}
