package pojos;

import java.sql.Date;

public class Treatment {

	private int IDtreatment;
	private Date startDate;
	private Date endDate;
	public enum typeTreatment{MEDICATION,REHAB};
	private typeTreatment treatment;
	private String descrpition;
	
	public Treatment() {
		this.IDtreatment=0;
		this.startDate=null;
		this.endDate=null;
		this.treatment=null;
		this.descrpition=null;
	}
	
	public int getIDtreatment() {
		return IDtreatment;
	}
	
	public void setIDtreatment(int iDtreatment) {
		IDtreatment = iDtreatment;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public typeTreatment getTreatment() {
		return treatment;
	}
	
	public void setTreatment(typeTreatment treatment) {
		this.treatment = treatment;
	}
	
	public String getDescrpition() {
		return descrpition;
	}
	
	public void setDescrpition(String descrpition) {
		this.descrpition = descrpition;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IDtreatment;
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
		Treatment other = (Treatment) obj;
		if (IDtreatment != other.IDtreatment)
			return false;
		return true;
	}
	
	
	
	
}
