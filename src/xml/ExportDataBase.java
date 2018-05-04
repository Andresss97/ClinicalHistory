package xml;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import pojos.*;

public class ExportDataBase {
	
	private DataBaseMarshaller marshaller;
	
	public void export (XmlLists lists, List <Address> addresses, List <Allergies> allergies, List <Appointment> appointments,
	  List <ClinicalHistory> clinicalHistories, List <Doctor> doctors, List <Illness> illnesses, List <Patient> patients, 
	  List <Surgeries> surgeries, List <Treatment> treatments, List <Vaccine> vaccines, File file) throws JAXBException {
		
		lists.setAddresses(addresses);
		lists.setAllergies(allergies);
		lists.setAppointments(appointments);
		lists.setClinicalHistories(clinicalHistories);
		lists.setDoctors(doctors);
		lists.setIllnesses(illnesses);
		lists.setPatients(patients);
		lists.setSurgeries(surgeries);
		lists.setTreatments(treatments);
		lists.setVaccines(vaccines);
	
		marshaller.marshallLists(lists, file);
	}

}
