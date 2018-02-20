package creation;

import java.sql.SQLException;
import java.sql.Statement;

import pojos.*;

public class Querys {

	public void insertDoctor(Doctor doctor, int iDAddress) throws SQLException {
		Conector con = new Conector();
		con.conectar();
		
		Statement st = con.getConnect().createStatement();
		String query;
		
		query = "INSERT into doctor (username,password,email,gender,speciality,mobilephone,name,surname,nif,dob,photo,idaddress) values ('" + doctor.getUser() + "','" + 
		doctor.getPassword() + "','" + doctor.getEmail() + "','" + doctor.getGender() + "','" + doctor.getSpeciality() + "','" + doctor.getMobilePhone() + "','" + doctor.getName() +
		"','" + doctor.getSurname() + "','" + doctor.getNIF() + "','" + doctor.getPhoto() + "','" + iDAddress + "')";
		
		st.executeUpdate(query);
		st.close();
		con.killConnection();
	}
	
	public void insertAddress(Address address) throws SQLException {
		Conector con = new Conector();
		con.conectar();
		
		Statement st = con.getConnect().createStatement();
		String query;
		
		query = "INSERT into address (city,street,cp,housenumber) values ('" + address.getCity() + "','" + address.getStreet() + "','" + 
		address.getPostalCode() + "','" + address.getHouseNumber() + "')";
		
		st.executeUpdate(query);
		
		st.close();
		con.killConnection();
	}
	
	public void insertPatient(Patient patient, int iDAddress) throws SQLException {
		Conector con = new Conector();
		con.conectar();
		
		Statement st = con.getConnect().createStatement();
		String query;
		
		query = "INSERT into patient (name,surname,nif,email,mobilephone,homephone,dob,gender,username,password,weight,height,photo,idaddress) values ('" + patient.getName() +
				"','" + patient.getSurname() + "','" + patient.getNIF() + "','" + patient.getEmail() + "'," + patient.getMobilePhone() + "," + 
				patient.getHousePhone() + ",'" + patient.getDob() + "','" + patient.getGender() + "','" + patient.getUser() + "','" + patient.getPassword() + "'," +
				patient.getWeight() + "," + patient.getHeight() + "," + patient.getPhoto() + "," + iDAddress + ")";
		
		st.executeUpdate(query);
		st.close();
		con.killConnection();
	}
}
