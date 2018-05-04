package xml;

import java.io.File;
import java.util.List;

import javax.xml.bind.*;


import pojos.*;

public class DataBaseMarshaller {

	private JAXBContext jaxbContext;
	private Marshaller marshaller;
	
	public void marshallAppointment(Appointment appointment,File file) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(appointment.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		marshaller.marshal(appointment, file);
		marshaller.marshal(appointment, System.out);
	}
	
	public void marshallAddress(Address address,File file) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(address.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		marshaller.marshal(address, file);
		marshaller.marshal(address, System.out);
	}
	
	public void marshallAllergies(Allergies allergies,File file) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(allergies.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		marshaller.marshal(allergies, file);
		marshaller.marshal(allergies, System.out);
	}
	
	public void marshallClinicalHistory(ClinicalHistory ct,File file) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(ct.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		marshaller.marshal(ct, file);
		marshaller.marshal(ct, System.out);
	}
	
	public void marshallDoctor(Doctor doctor,File file) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(doctor.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		marshaller.marshal(doctor, file);
		marshaller.marshal(doctor, System.out);
	}
	
	public void marshallPatient(Patient patient,File file) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(patient.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		marshaller.marshal(patient, file);
		marshaller.marshal(patient, System.out);
	}
	
	public void marshallIllness(Illness illness,File file) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(illness.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		marshaller.marshal(illness, file);
		marshaller.marshal(illness, System.out);
	}
	
	public void marshallSurgeries(Surgeries surgery,File file) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(surgery.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		marshaller.marshal(surgery, file);
		marshaller.marshal(surgery, System.out);
	}
	
	public void marshallTreatment(Treatment treatment,File file) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(treatment.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		marshaller.marshal(treatment, file);
		marshaller.marshal(treatment, System.out);
	}
	
	public void marshallVaccine(Vaccine vaccine,File file) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(vaccine.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		marshaller.marshal(vaccine, file);
		marshaller.marshal(vaccine, System.out);
	}
	
	public void marshallLists(XmlLists lists,File file) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(lists.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		marshaller.marshal(lists, file);
		marshaller.marshal(lists, System.out);
	}
}
