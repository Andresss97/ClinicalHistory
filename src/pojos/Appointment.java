package pojos;

import java.sql.Date;
import java.util.List;

public class Appointment {
	private Integer ID;
	private Date date;
	private String hour;
	private String reason;
	private Patient patient;
	private Doctor doctor;
	
	public Appointment() {
		this.ID = 0;
		this.date = null;
		this.hour = null;
		this.reason = null;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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
		Appointment other = (Appointment) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Appointment reason: " + this.reason + " date: " + this.date.toString() + " at " + this.hour ; 
	}
}
