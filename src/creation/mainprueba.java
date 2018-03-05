package creation;

import java.sql.Date;

import pojos.*;
import pojos.Person.GENDER;

public class mainprueba {
	public static void mainprueba(String arg[]) { 
		/*Conector conector= null;
		conector.connect();*/
		
		Patient patient1;
		patient1 = new Patient();
		patient1.setName("Juan");
		patient1.setSurname("Marchan");
	    patient1.setNIF("51527449C");
		patient1.setEmail("asdfghjk");
		patient1.setDob(new Date (1,1,1));
		patient1.setGender(GENDER.MALE);
		patient1.setUsername("username");
		patient1.setPassword("password");
		patient1.setAddress(new Address());
		System.out.println(patient1);
		
		
		
		/*conector.killConnection();*/
	}
	
}
