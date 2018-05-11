package xml;

import java.io.File;

import javax.xml.bind.JAXBException;

import creation.QuerysInsert;
import jpa.CreateJPA;
import pojos.*;

public class ImportDataBase {
	
	private DataBaseUnmarshaller databaseunmarshaller;
	
	public ImportDataBase() {
		databaseunmarshaller = new DataBaseUnmarshaller();
	}
	
	//*Mirar cual te conviene añadir con jpa o jdbc
	public void importDataBase (File file) throws Exception {
		
		XmlLists database = (XmlLists)databaseunmarshaller.unmarshallXmL(file);
		QuerysInsert insert = new QuerysInsert();
		CreateJPA create = new CreateJPA();
		
		for(Address address: database.getAddresses()) {
			insert.insertAddress(address);	
		}
		
		for(Allergies allergy:database.getAllergies()) {
			create.createAllergy(allergy);
			create.createTreatment(allergy.getTreatment());
		}
		
		for(Appointment appointment: database.getAppointments()) {
			insert.insertAppointment(appointment);
		}
		
		for(ClinicalHistory cl:database.getClinicalHistories()) {
			create.createClinicalRecord(cl);
		}
		
		//*revisar doctor appointment falta un get no hay una lista de appointments en doctor
		for(Doctor doctor:database.getDoctors()) {
			insert.insertDoctor(doctor);
			insert.insertAddress(doctor.getAddress());
			for(Appointment appointment: doctor.getAppointments()) {
				insert.insertAppointment(appointment);
			}
		}
		
		for(Illness illness:database.getIllnesses()) {
			create.createIllnes(illness);
			create.createTreatment(illness.getTreatment());
		}
		
		for(Patient patient:database.getPatients()) {
			insert.insertPatient(patient);
			insert.insertAddress(patient.getAddress());
			create.createClinicalRecord(patient.getcHistory());
			
			for(Allergies allergy:patient.getAllergies()) {
				insert.insertAllergies(allergy);
				create.createTreatment(allergy.getTreatment());
			}
			for(Appointment appointment: patient.getAppointments()) {
				insert.insertAppointment(appointment);
			}
			for(Illness illness:patient.getIllnesses()) {
				create.createIllnes(illness);
				create.createTreatment(illness.getTreatment());
			}
			for(Surgeries surgeries:patient.getSurgeries()) {
				insert.insertSurgeries(surgeries);
				create.createTreatment(surgeries.getTreatment());
			}
			
		}
		
		for(Surgeries surgeries :database.getSurgeries()) {
			insert.insertSurgeries(surgeries);
			create.createTreatment(surgeries.getTreatment());
		}
		
		for(Treatment treatment: database.getTreatments()) {
			create.createTreatment(treatment);
		}
		
		for(Vaccine vaccine:database.getVaccines()) {
			create.createVaccine(vaccine);
		}
	}

}
