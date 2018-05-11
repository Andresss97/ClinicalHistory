package xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import pojos.*;

public class ExportDataBase {
	
	private DataBaseMarshaller marshaller;
	
	public ExportDataBase() {
		marshaller = new DataBaseMarshaller();
	}
	
	public void export (XmlLists lists, File file) throws JAXBException {
		marshaller.marshallLists(lists, file);
	}
}
