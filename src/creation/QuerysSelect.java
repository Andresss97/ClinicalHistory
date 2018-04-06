package creation;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import graphicInterface.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pojos.Address;
import pojos.ClinicalHistory;
import pojos.ClinicalHistory.BLOODGROUP;
import pojos.Doctor;
import pojos.Patient;
import pojos.Person;
import pojos.Person.GENDER;

public class QuerysSelect {
	private Conector conn = (Conector) Main.conector;
	
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
			doctor.setSpeciality(this.selectIdSpeciality(set.getInt("idspeciality")));
			
			if(set.getString("gender").equals("Male")) {
				doctor.setGender(GENDER.MALE);
			}else {
				doctor.setGender(GENDER.FEMALE);
			}
			
			doctor.setUsername(set.getString("username"));
			doctor.setPassword(set.getString("password"));
			doctor.setPhoto(set.getBytes("photo"));
			doctor.setID(set.getInt("id"));
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
		Address address;
		int idAddress;
		
		while (set.next()) {
			Doctor doctor = new Doctor();
			doctor.setName(set.getString("name"));
			doctor.setSurname(set.getString("surname"));
			doctor.setDob(set.getDate("dob"));
			doctor.setEmail(set.getString("email"));
			doctor.setNIF(set.getString("nif"));
			doctor.setMobilePhone(set.getInt("mobilephone"));
			String sp = this.selectIdSpeciality(set.getInt("idspeciality"));
			doctor.setSpeciality(sp);
			if(set.getString("gender").equals("Male")) {
				doctor.setGender(GENDER.MALE);
			}
			else {
				doctor.setGender(GENDER.FEMALE);
			}
			doctor.setUsername(set.getString("username"));
			doctor.setPassword(set.getString("password"));
			doctor.setID(set.getInt("id"));
			idAddress = set.getInt("idaddress");
			address = this.selectAddress(idAddress);
			doctor.setAddress(address);
			
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
		int idAddress;
		Address address;
		
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
			patient.setWeight(set.getFloat("weight"));
			patient.setHeight(set.getFloat("height"));
			patient.setMobilePhone(set.getInt("mobilephone"));
			patient.setHousePhone(set.getInt("homephone"));
			patient.setPhoto(set.getBytes("photo"));
			if(set.getString("gender").equals("Male")) {
				patient.setGender(GENDER.MALE);
			}
			else {
				patient.setGender(GENDER.FEMALE);
			}
			idAddress = set.getInt("idaddress");
			address = this.selectAddress(idAddress);
			patient.setAddress(address);
			patient.getAddress().setID(idAddress);
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
		
		st.close();
		set.close();
		return id;
	}
	
	public Address selectAddress(int id) throws SQLException {
		String query = "SELECT city, street, housenumber, cp from address where id = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setInt(1, id);
		ResultSet set = st.executeQuery();
		Address address = new Address();
		
		address.setCity(set.getString("city"));
		address.setStreet(set.getString("street"));
		address.setHouseNumber(set.getInt("housenumber"));
		address.setPostalCode(set.getInt("cp"));
		
		st.close();
		set.close();
		
		return address;
	}
	
	
	public ArrayList<String> selectSpecialities() throws SQLException {
		String query;
		
		query = "SELECT * from speciality";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		ArrayList<String> specialities = new ArrayList<>();
		
		while(set.next()) {
			specialities.add(set.getString("type"));
		}
		
		st.close();
		set.close();
		
		return specialities;
	}
	
	public int selectIdSpeciality(String spec) throws SQLException {
		String query;
		
		query = "SELECT id from speciality where type = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setString(1, spec);
		ResultSet set = st.executeQuery();
		int id = 0;
		
		id = set.getInt("id");
		
		st.close();
		set.close();
		
		return id;
	}
	
	public String selectIdSpeciality(int id) throws SQLException {
		String query;
		
		query = "SELECT type from speciality where id = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setInt(1, id);
		ResultSet set = st.executeQuery();
		String type = set.getString("type");

		st.close();
		set.close();

		return type;
	}
	public List<ClinicalHistory> selectClinicalHistory () throws SQLException { 
		String query;
		
		query = "SELECT * FROM ClinicalHistory";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		st.close();
		
		List<ClinicalHistory> clinicalH =new LinkedList();
		while(set.next()){
			ClinicalHistory clinicalHistory = new ClinicalHistory();
			clinicalHistory.setAddictions(set.getString("additions"));
			clinicalHistory.setID(set.getInt("ID"));
			clinicalHistory.setLastModification(set.getDate("date"));
			clinicalHistory.setMedicalInsurance(set.getInt("medicalInsurance"));
			clinicalHistory.setObservations(set.getString("observations"));
			
			if(set.getString("bloodGroup").equals("AP")) {
				clinicalHistory.setBloodgroup(BLOODGROUP.AP);
			}if(set.getString("bloodGroup").equals("AN")) {
				clinicalHistory.setBloodgroup(BLOODGROUP.AN);
			}if(set.getString("bloodGroup").equals("ABP")) {
				clinicalHistory.setBloodgroup(BLOODGROUP.ABP);
			}if(set.getString("bloodGroup").equals("ABN")) {
				clinicalHistory.setBloodgroup(BLOODGROUP.ABN);
			}if(set.getString("bloodGroup").equals("BP")) {
				clinicalHistory.setBloodgroup(BLOODGROUP.BP);
			}if(set.getString("bloodGroup").equals("BN")) {
				clinicalHistory.setBloodgroup(BLOODGROUP.BN);
			}if(set.getString("bloodGroup").equals("ZP")) {
				clinicalHistory.setBloodgroup(BLOODGROUP.ZP);
			}if(set.getString("bloodGroup").equals("ZN")) {
				clinicalHistory.setBloodgroup(BLOODGROUP.ZN);
			}
			
			clinicalHistory.setLastModification(set.getDate("lastModification"));
			clinicalH.add(clinicalHistory);
		}
		set.close();
		return clinicalH ;
				
	}
	
}
