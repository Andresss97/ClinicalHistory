package pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType (XmlAccessType.FIELD)
@XmlRootElement (name = "XmlLists")

public class XmlLists {
	private List <Doctor> doctors;
	private List <Patient> patients;
	private List <ClinicalHistory> clinicalHistories;
	private List <Appointment> appointments;
	
	public void XmlLists () {
		doctors = null;
		patients = null;
		clinicalHistories = null;
		appointments = null;
	}
	
	
	public List<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	public List<ClinicalHistory> getClinicalHistories() {
		return clinicalHistories;
	}
	public void setClinicalHistories(List<ClinicalHistory> clinicalHistories) {
		this.clinicalHistories = clinicalHistories;
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointments == null) ? 0 : appointments.hashCode());
		result = prime * result + ((clinicalHistories == null) ? 0 : clinicalHistories.hashCode());
		result = prime * result + ((doctors == null) ? 0 : doctors.hashCode());
		result = prime * result + ((patients == null) ? 0 : patients.hashCode());
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
		XmlLists other = (XmlLists) obj;
		if (appointments == null) {
			if (other.appointments != null)
				return false;
		} else if (!appointments.equals(other.appointments))
			return false;
		if (clinicalHistories == null) {
			if (other.clinicalHistories != null)
				return false;
		} else if (!clinicalHistories.equals(other.clinicalHistories))
			return false;
		if (doctors == null) {
			if (other.doctors != null)
				return false;
		} else if (!doctors.equals(other.doctors))
			return false;
		if (patients == null) {
			if (other.patients != null)
				return false;
		} else if (!patients.equals(other.patients))
			return false;
		return true;
	}
	
	
}
