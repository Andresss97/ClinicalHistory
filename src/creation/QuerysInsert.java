package creation;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import graphicInterface.Main;
import pojos.Address;
import pojos.Doctor;
import pojos.Patient;
import pojos.Person.GENDER;


public class QuerysInsert {
	
	private Conector conn = Main.conector;
	
	public void insertDoctor(Doctor doctor, int iDAddress) throws SQLException {
		String query;
		query = "INSERT into doctor (username,password,email,gender,speciality,mobilephone,name,surname,nif,dob,photo,idaddress) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, doctor.getUser());
		st.setString(2, doctor.getPassword());
		st.setString(3, doctor.getEmail());
		if(doctor.getGender().equals(GENDER.MALE)) {
			st.setString(4,"Male");	
		}
		else {
			st.setString(4,"Female");
		}
		
		switch(doctor.getSpeciality()) {
		case ALLERGY_IMMUNOLLOGY:
			st.setString(5, "Allergy_Immunollogy");
			break;
		case CARDIOLOGY:
			st.setString(5, "Cardiology");
			break;
		case CLINICAL_NEUROPHISIOLOGY:
			st.setString(5, "Clinical neurophisiology");
			break;
		case ENDOCRINOLOGY:
			st.setString(5, "Endocrinology");
			break;
		case GENERAL_PATHOLOGY:
			st.setString(5, "General Pathology");
			break;
		case GENERAL_PRACTICE:
			st.setString(5, "General Practice");
			break;
		case GENERAL_SURGERY:
			st.setString(5, "General Surgery");
			break;
		case INTERNAL_MEDICINE:
			st.setString(5, "Internal Medicine");
			break;
		case NEONATOLOGY:
			st.setString(5, "Neonatology");
			break;
		case NEPHROLOGY:
			st.setString(5, "Nephrology");
			break;
		case NEUROLOGY:
			st.setString(5, "Neurology");
			break;
		case OPHTHALMOLOGY:
			st.setString(5, "Ophthalmology");
			break;
		case ORTHOPAEDICS:
			st.setString(5, "Orthopaedics");
			break;
		case PAEDIATRICS:
			st.setString(5, "Paediatrics");
			break;
		case PHYSICAL_MEDICINE_REHABILITATION:
			st.setString(5, "Physical Medicine Rehabilitation");
			break;
		case PSYCHIATRY:
			st.setString(5, "Psychiatry");
			break;
		case PULMONOLOGY:
			st.setString(5, "Pulmonogy");
			break;
		case RADIOLOGY:
			st.setString(5, "Radiology");
			break;
		case UROLOGY:
			st.setString(5, "Urology");
			break;
		default:
			break;
		
		}
		
		st.setInt(6,doctor.getMobilePhone());
		st.setString(7,doctor.getName());
		st.setString(8, doctor.getSurname());
		st.setString(9,doctor.getNIF());
		st.setDate(10, doctor.getDob());
		Blob blob = new SerialBlob(doctor.getPhoto());
		st.setBlob(11, blob);
		st.setInt(12, iDAddress);
		
		st.executeQuery();
		st.close();
	}
	
	public void insertAddress(Address address) throws SQLException {
		String query;

		query = "INSERT into address (city,street,cp,housenumber) values (?,?,?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, address.getCity());
		st.setString(2, address.getStreet());
		st.setInt(3, address.getHouseNumber());
		st.setInt(4, address.getHouseNumber());
		
		st.executeUpdate();
		st.close();
	}
	
	public void insertPatient(Patient patient, int iDAddress) throws Exception {
		String query;
		query = "INSERT into patient (name,surname,nif,email,mobilephone,homephone,dob,gender,username,password,weight,height,idaddress) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setString(1, patient.getName());
		st.setString(2, patient.getSurname());
		st.setString(3, patient.getNIF());
		st.setString(4, patient.getEmail());
		st.setInt(5, patient.getMobilePhone());
		st.setInt(6, patient.getHousePhone());
		st.setDate(7, patient.getDob());
		
		if(patient.getGender().equals(GENDER.MALE)) {
			st.setString(8,"Male");	
		}
		else {
			st.setString(8,"Female");
		}
		
		st.setString(9, patient.getUser());
		st.setString(10, patient.getPassword());
		st.setFloat(11, patient.getWeight());
		st.setFloat(12, patient.getHeight());
		st.setInt(13, iDAddress);
		
		st.executeUpdate();
		st.close();
	}
	
	public void insertUser(Doctor doctor, Patient patient, String user, String password) throws SQLException{
		
		if(!patient.equals(null)) {
			String query;
			query = "INSERT into mappinglogin (username, password,usertype) values (?,?,?)";
			PreparedStatement st = conn.getConnect().prepareStatement(query);
			st.setString(1, patient.getUser());
			st.setString(2, patient.getPassword());
			st.setInt(3, 1);
			
			st.executeUpdate();
			st.close();
		}
		else if(!doctor.equals(null)) {
			String query2;
			query2 = "INSERT into mappinglogin (username, password,usertype) values (?,?,?)";
			PreparedStatement st = conn.getConnect().prepareStatement(query2);
			st.setString(1, doctor.getUser());
			st.setString(2, doctor.getPassword());
			st.setInt(3, 2);
			
			st.executeUpdate();
			st.close();
		}
		else {
			String query3;
			query3 = "INSERT into mappinglogin (username, password,usertype) values (?,?,?)";
			PreparedStatement st = conn.getConnect().prepareStatement(query3);
			st.setString(1, user);
			st.setString(2, password);
			st.setInt(3, 3);
			
			st.executeUpdate();
			st.close();
		}
	}
}
