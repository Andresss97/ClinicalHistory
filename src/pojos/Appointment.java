package pojos;

import java.sql.Date;
import java.util.List;

public class Appointment {
	private int ID;
	private Date date;
	private int hour;
	private String reason;
	private List <Patient> patients;
	private List <Doctor> doctors;
	
	public Appointment () {
		this.ID = 0;
		this.date = null;
		this.hour = 0;
		this.reason = " ";
		this.patients = null;
		this.doctors = null; 
	}
	
	public int getID () {
		   return ID;
	   }
	   public void setID (int ID) {
		   this.ID = ID;
	   }
	   
	public Date getDate () {
		   return date;
	   }
	public void setDate (Date date) {
		   this.date= date;
	   }
	
	public int getHour () {
		   return hour;
	   }
	   public void setHour (int hour) {
		   this.hour = hour;
	   }
	   
	public String getReason () {
		   return reason;
	   }
	   public void setReason (String reason) {
		   this.reason = reason;
	   }
	   

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
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

	
	   
	  
	   
	   
}
