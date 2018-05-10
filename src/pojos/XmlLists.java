package pojos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType (XmlAccessType.FIELD)
@XmlRootElement (name = "XmlLists")

public class XmlLists {
	private ArrayList<Address> addresses;
	private ArrayList<Allergies> allergies;
	private ArrayList<Appointment> appointments;
	private ArrayList<ClinicalHistory> clinicalHistories;
	private ArrayList<Doctor> doctors;
	private ArrayList<Illness> illnesses;
	private ArrayList<Patient> patients;
	private ArrayList<Surgeries> surgeries;
	private ArrayList<Treatment> treatments;
	private ArrayList<Vaccine> vaccines;

	
	
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

	public ArrayList<Address> getAddresses() {
		return addresses;
	}



	public void setAddresses(ArrayList<Address> addresses) {
		this.addresses = addresses;
	}



	public ArrayList<Allergies> getAllergies() {
		return allergies;
	}



	public void setAllergies(ArrayList<Allergies> allergies) {
		this.allergies = allergies;
	}



	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}



	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}



	public ArrayList<ClinicalHistory> getClinicalHistories() {
		return clinicalHistories;
	}



	public void setClinicalHistories(ArrayList<ClinicalHistory> clinicalHistories) {
		this.clinicalHistories = clinicalHistories;
	}



	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}



	public void setDoctors(ArrayList<Doctor> doctors) {
		this.doctors = doctors;
	}



	public ArrayList<Illness> getIllnesses() {
		return illnesses;
	}



	public void setIllnesses(ArrayList<Illness> illnesses) {
		this.illnesses = illnesses;
	}



	public ArrayList<Patient> getPatients() {
		return patients;
	}



	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}



	public ArrayList<Surgeries> getSurgeries() {
		return surgeries;
	}



	public void setSurgeries(ArrayList<Surgeries> surgeries) {
		this.surgeries = surgeries;
	}



	public ArrayList<Treatment> getTreatments() {
		return treatments;
	}



	public void setTreatments(ArrayList<Treatment> treatments) {
		this.treatments = treatments;
	}



	public ArrayList<Vaccine> getVaccines() {
		return vaccines;
	}



	public void setVaccines(ArrayList<Vaccine> vaccines) {
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
