package pojos;

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
}
