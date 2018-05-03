package xml;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import pojos.*;

public class ExportDataBase {
	
	private DataBaseMarshaller marshaller;
	
	
	public void exportPatients(List<Patient>patients,File file) throws JAXBException {
		for (Patient patient : patients) {
			marshaller.marshallPatient(patient, file);
		}			
	}
	
	public void exportAddress(List<Address>addresses,File file) throws JAXBException {
		for (Address address : addresses) {
			marshaller.marshallAddress(address, file);;
		}			
	}
	
	public void exportAllergies(List<Allergies>allergies1,File file) throws JAXBException {
		for(Allergies allergies : allergies1) {
			marshaller.marshallAllergies(allergies, file);
		}
	}
	
	public void exportAppointment(List<Appointment>appointments,File file) throws JAXBException {
		for(Appointment appointment : appointments) {
			marshaller.marshallAppointment(appointment, file);
		}
	}
	
	public void exportClinicalHistory(List<ClinicalHistory> clinicalhistories,File file) throws JAXBException {
		for(ClinicalHistory ch : clinicalhistories) {
			marshaller.marshallClinicalHistory(ch, file);
		}
	}
	
	public void exportDoctor(List<Doctor>doctors,File file) throws JAXBException {
		for(Doctor doctor : doctors) {
			marshaller.marshallDoctor(doctor, file);
		}
	}
	
	
	

}
