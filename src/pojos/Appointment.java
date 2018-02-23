package pojos;

import java.sql.Date;

public class Appointment {
	private int ID;
	private Date date;
	private int hour;
	private String reason;
	
	public Appointment () {
		this.ID = 0;
		this.date = null;
		this.hour = 0;
		this.reason = " ";
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
