package pojos;

import java.util.Date;

public class Allergies {
	private int ID;
	private String group;
	private String observations;
	
	public Allergies () {
		this.ID = 0;
		this.group = " ";
		this.observations = " ";
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

@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}	   
	   
}
