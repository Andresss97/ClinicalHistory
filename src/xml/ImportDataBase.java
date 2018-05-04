package xml;

import java.io.File;

import javax.xml.bind.JAXBException;

import pojos.*;

public class ImportDataBase {
	
	private DataBaseUnmarshaller databaseunmarshaller;
	
	public XmlLists importDataBase (File file) throws JAXBException {
		
		XmlLists database = (XmlLists)databaseunmarshaller.unmarshallXmL(file);
				
		
		
		
		
		return database;
	}

}
