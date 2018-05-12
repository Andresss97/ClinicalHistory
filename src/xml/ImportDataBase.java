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
	
	
	public void importDataBase (File file) throws Exception {
		
		XmlLists database = (XmlLists)databaseunmarshaller.unmarshallXmL(file);
		QuerysInsert insert = new QuerysInsert();
		CreateJPA create = new CreateJPA();
		
		for(Address address: database.getAddresses()) {
			if(database.getAddresses().equals(null)) {
				break;
			}
			insert.insertAddress(address);	
		}
		
		for(Allergies allergy:database.getAllergies()) {
			if(database.getAllergies() == null) {
				break;
			}
			create.createAllergy(allergy);
			create.createTreatment(allergy.getTreatment());
		}
		
		for(Appointment appointment: database.getAppointments()) {
			if(database.getAppointments().equals(null)) {
				break;
			}
			insert.insertAppointment(appointment);
		}
		
		for(ClinicalHistory cl:database.getClinicalHistories()) {
			if(database.getClinicalHistories().equals(null)) {
				break;
			}
			create.createClinicalRecord(cl);
		}
		
		//*revisar doctor appointment falta un get no hay una lista de appointments en doctor
		for(Doctor doctor:database.getDoctors()) {
			if(database.getDoctors().equals(null)) {
				break;
			}
			insert.insertDoctor(doctor);
			insert.insertAddress(doctor.getAddress());
		}
		
		for(Illness illness:database.getIllnesses()) {
			if(database.getPatients().equals(null)) {
				break;
			}
			create.createIllnes(illness);
			create.createTreatment(illness.getTreatment());
		}
		
		for(Patient patient:database.getPatients()) {
			if(database.getPatients().equals(null)) {
				break;
			}
			insert.insertPatient(patient);
			insert.insertAddress(patient.getAddress());
			create.createClinicalRecord(patient.getcHistory());
			
			for(Allergies allergy:patient.getAllergies()) {
				if(patient.getAllergies().equals(null)) {
					break;
				}
				insert.insertAllergies(allergy);
				create.createTreatment(allergy.getTreatment());
			}
			for(Appointment appointment: patient.getAppointments()) {
				if(patient.getAppointments().equals(null)) {
					break;
				}
				insert.insertAppointment(appointment);
			}
			for(Illness illness:patient.getIllnesses()) {
				if(patient.getIllnesses().equals(null)) {
					break;
				}
				create.createIllnes(illness);
				create.createTreatment(illness.getTreatment());
			}
			for(Surgeries surgeries:patient.getSurgeries()) {
				if(patient.getSurgeries().equals(null)) {
					break;
				}
				insert.insertSurgeries(surgeries);
				create.createTreatment(surgeries.getTreatment());
			}
			
		}
		
		for(Surgeries surgeries :database.getSurgeries()) {
			if(database.getSurgeries().equals(null)) {
				break;
			}
			insert.insertSurgeries(surgeries);
			create.createTreatment(surgeries.getTreatment());
		}
		
		for(Treatment treatment: database.getTreatments()) {
			if(database.getTreatments().equals(null)) {
				break;
			}
			create.createTreatment(treatment);
		}
		
		for(Vaccine vaccine:database.getVaccines()) {
			if(database.getVaccines().equals(null)) {
				break;
			}
			create.createVaccine(vaccine);
		}
	}

}
