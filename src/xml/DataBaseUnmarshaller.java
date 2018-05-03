package xml;

import java.io.File;
import javax.xml.bind.*;
import pojos.*;
public class DataBaseUnmarshaller {
	
	private JAXBContext jaxbContext;
	private Unmarshaller unmarshaller;
	
	private Appointment unmarshallAppointment (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Appointment.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Appointment appointment = (Appointment) unmarshaller.unmarshal(file);
		return appointment;
	}
	
	private Allergies unmarshallAllergy (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Appointment.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Allergies allergy = (Allergies) unmarshaller.unmarshal(file);
		return allergy;
	}
	
	private Address unmarshallAddress (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Appointment.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Address address = (Address) unmarshaller.unmarshal(file);
		return address;
	}
	
	private ClinicalHistory unmarshallClinicalHistory (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Appointment.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		ClinicalHistory clinicalHistory = (ClinicalHistory) unmarshaller.unmarshal(file);
		return clinicalHistory;
	}
	
	private Doctor unmarshallDoctor (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Appointment.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Doctor doctor = (Doctor) unmarshaller.unmarshal(file);
		return doctor;
	}
	
	private Illness unmarshallIllness (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Appointment.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Illness illness = (Illness) unmarshaller.unmarshal(file);
		return illness;
	}
	
	private Patient unmarshallPatient (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Appointment.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Patient patient = (Patient) unmarshaller.unmarshal(file);
		return patient;
	}
	
	private Surgeries unmarshallSurgery (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Appointment.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Surgeries surgery = (Surgeries) unmarshaller.unmarshal(file);
		return surgery;
	}
	
	private Treatment unmarshallTreatment (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Appointment.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Treatment treatment = (Treatment) unmarshaller.unmarshal(file);
		return treatment;
	}
	
	private Vaccine unmarshallVaccine (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Appointment.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Vaccine vaccine = (Vaccine) unmarshaller.unmarshal(file);
		return vaccine;
	}
}

