package xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import pojos.*;

public class DataBaseMarshaller {

	private JAXBContext jaxbContext;
	private Marshaller marshaller;
	
	public void marshallAppointment(Appointment appointment) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(appointment.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		File file = new File("./xmls/Sample-Appointment.xml");//*path to decide
		marshaller.marshal(appointment, file);
		marshaller.marshal(appointment, System.out);
	}
	
	public void marshallAddress(Address address) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(address.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		File file = new File("./xmls/Sample-Appointment.xml");//*path to decide
		marshaller.marshal(address, file);
		marshaller.marshal(address, System.out);
	}
	
	public void marshallAllergies(Allergies allergies) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(allergies.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		File file = new File("./xmls/Sample-Appointment.xml");//*path to decide
		marshaller.marshal(allergies, file);
		marshaller.marshal(allergies, System.out);
	}
	
	public void marshallClinicalHistory(ClinicalHistory ct) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(ct.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		File file = new File("./xmls/Sample-Appointment.xml");//*path to decide
		marshaller.marshal(ct, file);
		marshaller.marshal(ct, System.out);
	}
	
	public void marshallDoctor(Doctor doctor) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(doctor.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		File file = new File("./xmls/Sample-Appointment.xml");//*path to decide
		marshaller.marshal(doctor, file);
		marshaller.marshal(doctor, System.out);
	}
	
	public void marshallPatient(Patient patient) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(patient.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		File file = new File("./xmls/Sample-Appointment.xml");//*path to decide
		marshaller.marshal(patient, file);
		marshaller.marshal(patient, System.out);
	}
	
	public void marshallIllness(Illness illness) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(illness.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		File file = new File("./xmls/Sample-Appointment.xml");//*path to decide
		marshaller.marshal(illness, file);
		marshaller.marshal(illness, System.out);
	}
	
	public void marshallSurgeries(Surgeries surgery) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(surgery.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		File file = new File("./xmls/Sample-Appointment.xml");//*path to decide
		marshaller.marshal(surgery, file);
		marshaller.marshal(surgery, System.out);
	}
	
	public void marshallTreatment(Treatment treatment) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(treatment.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		File file = new File("./xmls/Sample-Appointment.xml");//*path to decide
		marshaller.marshal(treatment, file);
		marshaller.marshal(treatment, System.out);
	}
	
	public void marshallVaccine(Vaccine vaccine) throws JAXBException {
		jaxbContext	=	JAXBContext.newInstance(vaccine.getClass());
		marshaller	=	jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		File file = new File("./xmls/Sample-Appointment.xml");//*path to decide
		marshaller.marshal(vaccine, file);
		marshaller.marshal(vaccine, System.out);
	}

}
