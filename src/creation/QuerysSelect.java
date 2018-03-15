package creation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import graphicInterface.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pojos.Doctor;
import pojos.Doctor.SPECIALITY;
import pojos.Patient;
import pojos.Person;
import pojos.Person.GENDER;

public class QuerysSelect {
	private Conector conn = Main.conector;
	
	public String[] selectUser(String user, String psw) throws SQLException {
		String[] data = new String[2];
		String query = "SELECT USERNAME, PASSWORD from mappinglogin where username = '" + user + "' and password = '"
				+ psw + "'";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		while (set.next()) {
			data[0] = set.getString("username");
			data[1] = set.getString("password");
		}

		st.close();
		set.close();

		return data;
	}
	
	public Patient selectPatient(String[] data) throws SQLException {
		Patient patient = null;
		String query = "SELECT * from patient where username = '" + data[0] + "' and password = '" + data[1] + "'";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		ResultSet set = st.executeQuery();
		while(set.next()) {
			patient = new Patient();
			
			patient.setName(set.getString("Name"));
			patient.setSurname(set.getString("Surname"));
			patient.setEmail(set.getString("email"));
			patient.setNIF(set.getString("nif"));
			patient.setMobilePhone(set.getInt("mobilephone"));
			patient.setHousePhone(set.getInt("homephone"));
			patient.setDob(set.getDate("dob"));
			if(set.getString("gender").equals("Male")) {
				patient.setGender(GENDER.MALE);
			}else {
				patient.setGender(GENDER.FEMALE);
			}
			patient.setWeight(set.getFloat("weight"));
			patient.setHeight(set.getFloat("height"));
			patient.setUsername(set.getString("username"));
			patient.setPassword(set.getString("password"));
			patient.setPhoto(set.getBytes("photo"));
		}
		
		st.close();
		set.close();
		
		return patient;
	}
	public Doctor selectDoctor(String[] data) throws SQLException {
		Doctor doctor = null;
		String query = "SELECT * from doctor where username = '" + data[0] + "' and password = '" + data[1] + "'";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		ResultSet set = st.executeQuery();
		while(set.next()) {
			doctor = new Doctor();
			
			doctor.setName(set.getString("Name"));
			doctor.setSurname(set.getString("Surname"));
			doctor.setEmail(set.getString("email"));
			doctor.setNIF(set.getString("nif"));
			doctor.setMobilePhone(set.getInt("mobilephone"));
			doctor.setDob(set.getDate("dob"));
			switch(doctor.getSpeciality()){
			case ALLERGY_IMMUNOLLOGY:
				doctor.setSpeciality(SPECIALITY.ALLERGY_IMMUNOLLOGY);
				break;
			case CARDIOLOGY:
				doctor.setSpeciality(SPECIALITY.CARDIOLOGY);
				break;
			case CLINICAL_NEUROPHISIOLOGY:
				doctor.setSpeciality(SPECIALITY.CLINICAL_NEUROPHISIOLOGY);
				break;
			case ENDOCRINOLOGY:
				doctor.setSpeciality(SPECIALITY.ENDOCRINOLOGY);
				break;
			case GENERAL_PATHOLOGY:
				doctor.setSpeciality(SPECIALITY.GENERAL_PATHOLOGY);
				break;
			case GENERAL_PRACTICE:
				doctor.setSpeciality(SPECIALITY.GENERAL_PRACTICE);
				break;
			case GENERAL_SURGERY:
				doctor.setSpeciality(SPECIALITY.GENERAL_SURGERY);
				break;
			case INTERNAL_MEDICINE:
				doctor.setSpeciality(SPECIALITY.INTERNAL_MEDICINE);
				break;
			case NEONATOLOGY:
				doctor.setSpeciality(SPECIALITY.NEONATOLOGY);
				break;
			case NEPHROLOGY:
				doctor.setSpeciality(SPECIALITY.NEPHROLOGY);
				break;
			case NEUROLOGY:
				doctor.setSpeciality(SPECIALITY.NEUROLOGY);
				break;
			case OPHTHALMOLOGY:
				doctor.setSpeciality(SPECIALITY.OPHTHALMOLOGY);
				break;
			case ORTHOPAEDICS:
				doctor.setSpeciality(SPECIALITY.ORTHOPAEDICS);
				break;
			case PAEDIATRICS:
				doctor.setSpeciality(SPECIALITY.PAEDIATRICS);
				break;
			case PHYSICAL_MEDICINE_REHABILITATION:
				doctor.setSpeciality(SPECIALITY.PHYSICAL_MEDICINE_REHABILITATION);
				break;
			case PSYCHIATRY:
				doctor.setSpeciality(SPECIALITY.PSYCHIATRY);
				break;
			case PULMONOLOGY:
				doctor.setSpeciality(SPECIALITY.PULMONOLOGY);
				break;
			case RADIOLOGY:
				doctor.setSpeciality(SPECIALITY.RADIOLOGY);
				break;
			case UROLOGY:
				doctor.setSpeciality(SPECIALITY.UROLOGY);
				break;
			default:
				break;
			
			}
			
			if(set.getString("gender").equals("Male")) {
				doctor.setGender(GENDER.MALE);
			}else {
				doctor.setGender(GENDER.FEMALE);
			}
			
			doctor.setUsername(set.getString("username"));
			doctor.setPassword(set.getString("password"));
			doctor.setPhoto(set.getBytes("photo"));
		}
		
		st.close();
		set.close();
		
		return doctor;
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList checkEmailUser(String mail) throws SQLException {
		String query = "SELECT USERNAME, PASSWORD from mappinglogin where email = '" + mail + "'";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ArrayList<String> data = new ArrayList<>();
		
		ResultSet set = st.executeQuery();
		while(set.next()) {
			data.add(set.getString("username"));
			data.add(set.getString("password"));
		}
		
		st.close();
		set.close();
		
		return data;
	}
	
	public ArrayList appStats() throws SQLException {
		String query = "SELECT hour from appointment";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ArrayList<String> list = new ArrayList<>();
		ResultSet set = st.executeQuery();
		
		while(set.next()) {
			list.add(set.getString("hour"));
		}
		
		return list;
	}

	public int checkSecurityLevel(String user, String psw) throws SQLException {
		String query = "SELECT usertype from mappinglogin where username = '" + user + "' and password = '" + psw + "'";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		int sLevel = 0;
		while (set.next()) {
			sLevel = set.getInt("usertype");
		}
		st.close();
		set.close();
		return sLevel;
	}

	public ArrayList<Doctor> selectDoctorNSSpeciality(String speciality) throws SQLException {
		String query = "SELECT name, surname from doctor where speciality = '" + speciality + "'";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		ArrayList<Doctor> list = new ArrayList<>();
		while (set.next()) {
			Doctor doctor = new Doctor();
			doctor.setName(set.getString("name"));
			doctor.setSurname(set.getString("surname"));
			list.add(doctor);
		}
		st.close();
		set.close();
		return list;
	}
	
	public ArrayList<Person> selectDoctorsAccount() throws SQLException {
		String query = "SELECT * from doctor";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		ArrayList<Person> list = new ArrayList<>();
		
		while (set.next()) {
			Doctor doctor = new Doctor();
			doctor.setName(set.getString("name"));
			doctor.setSurname(set.getString("surname"));
			doctor.setDob(set.getDate("dob"));
			doctor.setEmail(set.getString("email"));
			doctor.setNIF(set.getString("nif"));
			doctor.setMobilePhone(set.getInt("mobilephone"));
			//doctor.setSpeciality(SPECIALITY.valueOf(set.getString("speciality")));
			doctor.setUsername(set.getString("username"));
			doctor.setPassword(set.getString("password"));
			doctor.setID(set.getInt("id"));
			//doctor.setGender(GENDER.valueOf(set.getString("gender")));
			list.add(doctor);
		}
		st.close();
		set.close();
		
		return list;
	}
	
	public ArrayList<Person> selectPatientsAccounts() throws SQLException {
		String query = "SELECT * from patient";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		ArrayList<Person> list = new ArrayList<>();
		
		while (set.next()) {
			Patient patient = new Patient();
			patient.setName(set.getString("name"));
			patient.setSurname(set.getString("surname"));
			patient.setUsername(set.getString("username"));
			patient.setPassword(set.getString("password"));
			patient.setID(set.getInt("id"));
			patient.setDob(set.getDate("dob"));
			patient.setEmail(set.getString("email"));
			patient.setNIF(set.getString("nif"));
			patient.setMobilePhone(set.getInt("mobilephone"));
			patient.setHousePhone(set.getInt("homephone"));
			list.add(patient);
		}
		
		st.close();
		set.close();
		
		return list;
	}
	
	public int selectLastId(String table) throws SQLException {
		String query = "SELECT MAX(id) from " + table;
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		int id = set.getInt(1);
		
		return id;
	}
}
