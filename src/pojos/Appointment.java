package pojos;

import java.util.Date;

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
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	   
	   
}
