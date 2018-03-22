package pojos;

public class Doctor extends Person {

	private Integer primarykey;

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
