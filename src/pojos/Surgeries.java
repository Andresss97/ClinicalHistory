package pojos;

import java.sql.Date;

public class Surgeries {
	private int ID;
	private Date date;
	private String type;
	private Patient patient;
	
	public Surgeries () {
		this.ID = 0;
		this.date = null;
		this.type = " ";
		this.patient = null;
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
	
	public String getType () {
		   return type;
	   }
	   public void setType (String type) {
		   this.type = type;
	   }
	   
	  public Patient getPatient () {
		   return patient;
	   }
	   public void setPatient(Patient patient) {
		   this.patient = patient;
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
		Surgeries other = (Surgeries) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	  

}
