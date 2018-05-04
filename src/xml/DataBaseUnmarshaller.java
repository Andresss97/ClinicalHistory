package xml;

import java.io.File;
import javax.xml.bind.*;
import pojos.*;
public class DataBaseUnmarshaller {
	
	private JAXBContext jaxbContext;
	private Unmarshaller unmarshaller;
	
	public Appointment unmarshallAppointment (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Appointment.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Appointment appointment = (Appointment) unmarshaller.unmarshal(file);
		return appointment;
	}
	
	public Allergies unmarshallAllergy (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Allergies.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Allergies allergy = (Allergies) unmarshaller.unmarshal(file);
		return allergy;
	}
	
	public Address unmarshallAddress (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Address.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Address address = (Address) unmarshaller.unmarshal(file);
		return address;
	}
	
	public ClinicalHistory unmarshallClinicalHistory (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(ClinicalHistory.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		ClinicalHistory clinicalHistory = (ClinicalHistory) unmarshaller.unmarshal(file);
		return clinicalHistory;
	}
	
	public Doctor unmarshallDoctor (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Doctor.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Doctor doctor = (Doctor) unmarshaller.unmarshal(file);
		return doctor;
	}
	
	public Illness unmarshallIllness (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Illness.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Illness illness = (Illness) unmarshaller.unmarshal(file);
		return illness;
	}
	
	public Patient unmarshallPatient (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Patient.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Patient patient = (Patient) unmarshaller.unmarshal(file);
		return patient;
	}
	
	public Surgeries unmarshallSurgery (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Surgeries.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Surgeries surgery = (Surgeries) unmarshaller.unmarshal(file);
		return surgery;
	}
	
	public Treatment unmarshallTreatment (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Treatment.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Treatment treatment = (Treatment) unmarshaller.unmarshal(file);
		return treatment;
	}
	
	public Vaccine unmarshallVaccine (File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Vaccine.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		Vaccine vaccine = (Vaccine) unmarshaller.unmarshal(file);
		return vaccine;
	}
	
	public XmlLists unmarshallXmL(File file) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(XmlLists.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		
		XmlLists xmllists =(XmlLists)unmarshaller.unmarshal(file);
		return xmllists;
	}
	
}

