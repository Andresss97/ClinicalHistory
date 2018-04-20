package pojos;
import java.io.*;
import java.util.LinkedList;
import javax.persistence.*;

@Entity
@Table(name = "DOCTOR")
public class Doctor extends Person  {

	@Id
	@GeneratedValue(generator = "DOCTOR")
	@TableGenerator(name = "DOCTOR", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "DOCTOR")
	private Integer id;
	
	private String speciality;
	
	@ManyToMany(mappedBy="DOCTOR")
	private LinkedList<Patient>patients;
	
	

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
