package xml;

import java.io.File;

import javax.xml.bind.JAXBException;

import creation.QuerysInsert;
import pojos.*;

public class ImportDataBase {
	
	private DataBaseUnmarshaller databaseunmarshaller;
	
	public XmlLists importDataBase (File file) throws Exception {
		
		XmlLists database = (XmlLists)databaseunmarshaller.unmarshallXmL(file);
		QuerysInsert insert = null;
		
		for(Patient patient:database.getPatients()) {
			insert.insertPatient(patient);
			for(Allergies allergy:patient.getAllergies()) {
				insert.insertAllergies(allergy);
			}
			
		}
		
		
		
		return database;
	}

}
