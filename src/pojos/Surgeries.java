package pojos;

import java.util.Date;

public class Surgeries {
	private int ID;
	private Date date;
	private String type;
	
	public Surgeries () {
		this.ID = 0;
		this.date = null;
		this.type = " ";
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

	   @Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
