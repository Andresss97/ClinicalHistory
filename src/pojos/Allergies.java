package pojos;

import java.util.Date;

public class Allergies {
	private int ID;
	private String group;
	private String observations;
	private Patient patient;
	private Treatment treatment;
	
	public Allergies () {
		this.ID = 0;
		this.group = " ";
		this.observations = " ";
		this.patient = null;
		this.treatment = null;
	}
	
	public int getID () {
		   return ID;
	   }
	   public void setID (int ID) {
		   this.ID = ID;
	   }
	   
	public String getGroup () {
		   return group;
	   }
	public void setGroup (String group) {
		   this.group = group;
	   }
	
	public String getObservations () {
		   return observations;
	   }
	   public void setObservations (String observations) {
		   this.observations = observations;
	   }
	   

public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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


	   
}
