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
import pojos.Person.GENDER;

public class QuerysSelect {
	private Conector conn = Main.conector;
	
	public String[] selectUser(String user, String psw) throws SQLException {
		String[] data = new String[2];
		String query = "SELECT USERNAME, PASSWORD from mappinglogin where username = '" + user + "' and password = '"
				+ psw + "'";
		PreparedStatement st;
		st = conn.getConnect().prepareStatement(query);
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
			patient.setGender(Patient.GENDER.valueOf(set.getString("gender")));
			patient.setWeight(set.getFloat("weight"));
			patient.setHeight(set.getFloat("height"));
			patient.setUsername(set.getString("username"));
			patient.setPassword(set.getString("password"));
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
			doctor.setSpeciality(Doctor.SPECIALITY.valueOf(set.getString("speciality")));
			
			doctor.setGender(Doctor.GENDER.valueOf(set.getString("gender")));
			
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
		
		st.close();
		set.close();
		
		return list;
	}
	
	public int checkSecurityLevel(String user, String psw) throws SQLException {
		String query = "SELECT usertype from mappinglogin where username = '" + user + "' and password = '" + psw + "'";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		int sLevel = 0;
		
		while(set.next()) {
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
		
		while(set.next()) {
			Doctor doctor = new Doctor();
			doctor.setName(set.getString("name"));
			doctor.setSurname(set.getString("surname"));
			list.add(doctor);
		}
		
		st.close();
		set.close();
		
		return list;
	}
}
