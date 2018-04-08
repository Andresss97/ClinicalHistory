package pojos;
import java.io.*;

import java.util.LinkedList;

public class Patient extends Person{
	private float weight;
	private float height;
	private int housePhone;
	private LinkedList<Doctor> doctors;
	private LinkedList<Appointment> appointments;
	private LinkedList<Illness> illnesses;
	private LinkedList<Allergies> allergies;
	private LinkedList<Surgeries> surgeries;
	private LinkedList<Vaccine> vaccines;
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
