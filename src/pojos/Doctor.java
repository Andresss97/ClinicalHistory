package pojos;
import java.io.*;

public class Doctor extends Person  {

	private String speciality;

	public Doctor() {
		super();
		this.speciality = null;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	public String toString() {
		return "Dr:" + name + " " + surname;
	}
}
