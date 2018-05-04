package xml;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import pojos.*;

public class ExportDataBase {
	
	private DataBaseMarshaller marshaller;
	
	public void export (XmlLists lists, List<Doctor>doctors, List<Patient>patients, List<ClinicalHistory>clinicalHistories, List<Appointment>appointments, File file) throws JAXBException {
		
		lists.setDoctors(doctors);
		lists.setPatients(patients);
		lists.setClinicalHistories(clinicalHistories);
		lists.setAppointments(appointments);
	    
		marshaller.marshallLists(lists, file);
	}

}
