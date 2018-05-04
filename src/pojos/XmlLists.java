package pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType (XmlAccessType.FIELD)
@XmlRootElement (name = "XmlLists")

public class XmlLists {
	private List <Address> addresses;
	private List <Allergies> allergies;
	private List <Appointment> appointments;
	private List <ClinicalHistory> clinicalHistories;
	private List <Doctor> doctors;
	private List <Illness> illnesses;
	private List <Patient> patients;
	private List <Surgeries> surgeries;
	private List <Treatment> treatments;
	private List <Vaccine> vaccines;

	
	
	public void XmlLists () {
		addresses = null;
		allergies = null;
		appointments = null;
		clinicalHistories = null;
		doctors = null;
		illnesses = null;
		patients = null;
		surgeries = null;
		treatments = null;
		vaccines = null;
		
		
	}



	public List<Address> getAddresses() {
		return addresses;
	}



	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}



	public List<Allergies> getAllergies() {
		return allergies;
	}



	public void setAllergies(List<Allergies> allergies) {
		this.allergies = allergies;
	}



	public List<Appointment> getAppointments() {
		return appointments;
	}



	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}



	public List<ClinicalHistory> getClinicalHistories() {
		return clinicalHistories;
	}



	public void setClinicalHistories(List<ClinicalHistory> clinicalHistories) {
		this.clinicalHistories = clinicalHistories;
	}



	public List<Doctor> getDoctors() {
		return doctors;
	}



	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}



	public List<Illness> getIllnesses() {
		return illnesses;
	}



	public void setIllnesses(List<Illness> illnesses) {
		this.illnesses = illnesses;
	}



	public List<Patient> getPatients() {
		return patients;
	}



	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}



	public List<Surgeries> getSurgeries() {
		return surgeries;
	}



	public void setSurgeries(List<Surgeries> surgeries) {
		this.surgeries = surgeries;
	}



	public List<Treatment> getTreatments() {
		return treatments;
	}



	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}



	public List<Vaccine> getVaccines() {
		return vaccines;
	}



	public void setVaccines(List<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addresses == null) ? 0 : addresses.hashCode());
		result = prime * result + ((allergies == null) ? 0 : allergies.hashCode());
		result = prime * result + ((appointments == null) ? 0 : appointments.hashCode());
		result = prime * result + ((clinicalHistories == null) ? 0 : clinicalHistories.hashCode());
		result = prime * result + ((doctors == null) ? 0 : doctors.hashCode());
		result = prime * result + ((illnesses == null) ? 0 : illnesses.hashCode());
		result = prime * result + ((patients == null) ? 0 : patients.hashCode());
		result = prime * result + ((surgeries == null) ? 0 : surgeries.hashCode());
		result = prime * result + ((treatments == null) ? 0 : treatments.hashCode());
		result = prime * result + ((vaccines == null) ? 0 : vaccines.hashCode());
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
		if (addresses == null) {
			if (other.addresses != null)
				return false;
		} else if (!addresses.equals(other.addresses))
			return false;
		if (allergies == null) {
			if (other.allergies != null)
				return false;
		} else if (!allergies.equals(other.allergies))
			return false;
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
		if (illnesses == null) {
			if (other.illnesses != null)
				return false;
		} else if (!illnesses.equals(other.illnesses))
			return false;
		if (patients == null) {
			if (other.patients != null)
				return false;
		} else if (!patients.equals(other.patients))
			return false;
		if (surgeries == null) {
			if (other.surgeries != null)
				return false;
		} else if (!surgeries.equals(other.surgeries))
			return false;
		if (treatments == null) {
			if (other.treatments != null)
				return false;
		} else if (!treatments.equals(other.treatments))
			return false;
		if (vaccines == null) {
			if (other.vaccines != null)
				return false;
		} else if (!vaccines.equals(other.vaccines))
			return false;
		return true;
	}
		
}
