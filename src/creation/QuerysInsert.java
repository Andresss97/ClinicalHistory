package creation;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.serial.SerialBlob;

import graphicInterface.Main;
//import pojos.Treatment.typeTreatment;
import pojos.*;
import pojos.ClinicalHistory.BLOODGROUP;
import pojos.Illness.typeDisease;

public class QuerysInsert {
	
	private Conector conn = (Conector) Main.conector;
	//change the Integer you are changing
	
	public void insertDoctor(Doctor doctor, Integer iDAddress) throws SQLException {
		String query;
		query = "INSERT into doctor (username,password,email,gender,idspeciality,mobilephone,name,surname,nif,dob,idaddress) values (?,?,?,?,?,?,?,?,?,?,?)";
		QuerysSelect qs = new QuerysSelect();
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		int idspeciality = 0;
		
		st.setString(1, doctor.getUsername());
		st.setString(2, doctor.getPassword());
		st.setString(3, doctor.getEmail());
		if(doctor.getGender().equals("Male")) {
			st.setString(4,"Male");	
		}
		else {
			st.setString(4,"Female");
		}
		idspeciality = qs.selectIdSpeciality(doctor.getSpeciality());
		st.setInt(5, idspeciality);
		st.setInt(6,doctor.getMobilePhone());
		st.setString(7,doctor.getName());
		st.setString(8, doctor.getSurname());
		st.setString(9,doctor.getNIF());
		st.setDate(10, doctor.getDob());
		//Blob blob = new SerialBlob(doctor.getPhoto());
		//st.setBlob(11, blob);
		st.setInt(11, iDAddress);
		
		st.executeUpdate();
		st.close();
	}
	
	public void insertAddress(Address address) throws SQLException {
		String query;
		ResultSet set = null;
		query = "INSERT into address (city,street,cp,housenumber) values (?,?,?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, address.getCity());
		st.setString(2, address.getStreet());
		st.setInt(3, address.getPostalCode());
		st.setInt(4, address.getHouseNumber());
		
		st.executeUpdate();

		st.close();
	}
	
	public void insertPatient(Patient patient, Integer iDAddress) throws Exception {
		String query;
		query = "INSERT into patient (name,surname,nif,email,mobilephone,homephone,dob,gender,username,password,weight,height,idaddress, photo) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setString(1, patient.getName());
		st.setString(2, patient.getSurname());
		st.setString(3, patient.getNIF());
		st.setString(4, patient.getEmail());
		st.setInt(5, patient.getMobilePhone());
		st.setInt(6, patient.getHousePhone());
		st.setDate(7, patient.getDob());
		
		if(patient.getGender().equals("Male")) {
			st.setString(8,"Male");	
		}
		else {
			st.setString(8,"Female");
		}
		
		st.setString(9, patient.getUsername());
		st.setString(10, patient.getPassword());
		st.setFloat(11, patient.getWeight());
		st.setFloat(12, patient.getHeight());
		st.setInt(13, iDAddress);
		st.setBytes(14, patient.getPhoto());
		st.executeUpdate();
		st.close();
	}
	
	public void insertUser1(Doctor doctor, Patient patient) throws SQLException{
		if(!(patient == null)) {
			String query;
			query = "INSERT into mappinglogin (username, password,usertype,email) values (?,?,?,?)";
			PreparedStatement st = conn.getConnect().prepareStatement(query);
			st.setString(1, patient.getUsername());
			st.setString(2, patient.getPassword());
			st.setInt(3, 1);
			st.setString(4,patient.getEmail());
			
			st.executeUpdate();
			st.close();
		}
		else if(!(doctor == null)) {
			String query2;
			query2 = "INSERT into mappinglogin (username, password,usertype,email) values (?,?,?,?)";
			PreparedStatement st = conn.getConnect().prepareStatement(query2);
			st.setString(1, doctor.getUsername());
			st.setString(2, doctor.getPassword());
			st.setInt(3, 2);
			st.setString(4,doctor.getEmail());
			
			st.executeUpdate();
			st.close();
		}
	}
	
	public void insertUser2(String user, String password) throws SQLException {
		String query;
		query = "INSERT into mappinglogin (username, password,usertype) values (?,?,?)";
		PreparedStatement st;
		st = conn.getConnect().prepareStatement(query);

		st.setString(1, user);
		st.setString(2, password);
		st.setInt(3, 3);
		st.executeUpdate();
		st.close();
	}
	
	public void insertIllness(Illness illness,Integer idTreatment ,Integer idPatient) throws SQLException {
		String query;

		query = "INSERT into illness (name,type,description,date,Tresults,idtreatment,idpatient,) values (?,?,?,?,?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1,illness.getName());
		
		if(illness.getTypeDisease().equals(typeDisease.HEREDITARY)) {
			st.setString(2,"Hereditary");
		}
		else {
			st.setString(2, "Personal");
		}
		
		st.setString(3,illness.getDescription());
		st.setDate(4,illness.getDate());;
		st.setInt(5, idTreatment);
		st.setInt(6, idPatient);
		
		st.executeUpdate();
		st.close();
	
	}
	
	public void insertVaccine(Vaccine vaccine,Integer idPatient) throws SQLException{
		String query;
		
		query = "INSERT into vaccine (name,date,observations,idpatient,) values(?,?,?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		switch(vaccine.getNameVaccine()){
		case CHOLERA:
			st.setString(1, "Cholera");
			break;
		case DIPHTHERIA:
			st.setString(1, "Diphtheria");
			break;
		case HEPATITIS_A:
			st.setString(1, "HepatitisA");
			break;
		case HEPATITIS_B:
			st.setString(1, "HepatitisB");
			break;
		case HERPES:
			st.setString(1, "Herpes");
			break;
		case INFLUENZA_A:
			st.setString(1, "InfluenzaA");
			break;
		case INFLUENZA_B:
			st.setString(1, "InfluenzaB");
			break;
		case MEASLES:
			st.setString(1, "Measles");
			break;
		case MENINGOCOCCAL:
			st.setString(1, "Meningococcal");
			break;
		case PAPILLOMAVIRUS:
			st.setString(1, "Papillomavirus");
			break;
		case PNEUMOCOCCAL:
			st.setString(1, "Pneumocaccal");
			break;
		case RABIES:
			st.setString(1, "Rabies");
			break;
		case ROTAVIRUS:
			st.setString(1, "Rotavirus");
			break;
		case RUBELLA:
			st.setString(1, "Rubella");
			break;
		case SMALLPOX:
			st.setString(1, "SmallPox");
			break;
		case TETANUS:
			st.setString(1, "Tetanus");
			break;
		case TUBERCULOSIS:
			st.setString(1, "Tuberculosis");
			break;
		case TYPHOID:
			st.setString(1, "Typhoid");
			break;
		case VARICELLA:
			st.setString(1, "Varicella");
			break;
		case YELLOWFEVER:
			st.setString(1, "YellowFever");
			break;
		default:
			break;	
		}
		
		st.setDate(2, vaccine.getDate());
		st.setString(3, vaccine.getDescription());
		st.setInt(4, idPatient);
		
		st.executeUpdate();
		st.close();	
	}
	
	
	//*Ojo con treatment 
	public void insertTreatment(Treatment treatment,Integer idPatient) throws SQLException {
		String query;
		
		query = "INSERT into treatment (start,end, type, description,tresults,idpatient,) values(?,?,?,?,?,?,)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		/*st.setDate(1, treatment.getStartDate());
		st.setDate(2, treatment.getEndDate());*/
		
		/*if(treatment.getTreatment().equals(typeTreatment.MEDICATION)) {
			st.setString(3, "Medication");
		}
		else {
			st.setString(3, "Rehab");
		}*/
		st.setString(4, treatment.getDescrpition());
		st.setString(5, treatment.getResults());
		st.setInt(6, idPatient);
		
		st.executeUpdate();
		st.close();
		
	}
		//*set num 5 esta como comentario
	public void insertClinicalHistory(ClinicalHistory clinicalHistory,Integer idPatient) throws SQLException {
		String query;

		query = "INSERT into clinicalHistory (addictions, observations, lastModification, bloodgroup, medicalInsurance,idpatient,) values (?,?,?,?,?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, clinicalHistory.getAddictions());
		st.setString(2, clinicalHistory.getObservations());
		st.setDate(3, clinicalHistory.getLastModification());
		if(clinicalHistory.getBloodgroup().equals(BLOODGROUP.ABN)) {
			st.setString(4,"AB negative");	
		}
		if(clinicalHistory.getBloodgroup().equals(BLOODGROUP.ABP)) {
			st.setString(4,"A positive");	
		}
		if(clinicalHistory.getBloodgroup().equals(BLOODGROUP.AN)) {
			st.setString(4,"A negative");	
		}
		if(clinicalHistory.getBloodgroup().equals(BLOODGROUP.AP)) {
			st.setString(4,"AB positive");	
		}
		if(clinicalHistory.getBloodgroup().equals(BLOODGROUP.BN)) {
			st.setString(4,"B negative");	
		}
		if(clinicalHistory.getBloodgroup().equals(BLOODGROUP.BP)) {
			st.setString(4,"B positive");	
		}
		if(clinicalHistory.getBloodgroup().equals(BLOODGROUP.ZN)) {
			st.setString(4,"Zero negative");	
		}
		if(clinicalHistory.getBloodgroup().equals(BLOODGROUP.ZP)) {
			st.setString(4,"Zero positive");	
		}
		//st.setInt(5, clinicalHistory.getMedicalInsurance());
		
		st.setInt(6, idPatient);
		
		st.executeUpdate();
		st.close();
	}
	
	//*No entiendo q pasa con main patient.getID
	public void insertAppointment(Appointment appointment) throws SQLException {
		String query;

		query = "INSERT into appointment (date, hour, reason, iddoctor, idpatient) values (?,?,?,?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
	    st.setDate(1, appointment.getDate());
		st.setString(2, appointment.getHour());
		st.setString(3, appointment.getReason());
		st.setInt(4, appointment.getDoctor().getID());
		st.setInt(5, Main.patient.getID());
		
		st.executeUpdate();
		st.close();
	}
	
	public void insertAllergies(Allergies allergies, Integer idpatient, Integer idtreatment) throws SQLException {
		String query;

		query = "INSERT into allergies (group, observations, idpatient, idtreatment,) values (?, ?, ?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, allergies.getType());
		st.setString(2, allergies.getObservations());
		st.setInt(3, idpatient);
		st.setInt(4, idtreatment);
		
		st.executeUpdate();
		st.close();
	}
	
	public void insertSurgeries (Surgeries surgeries) throws SQLException {
		String query;

		query = "INSERT into surgeries (date, type) values (?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setDate(1, surgeries.getDate());
		st.setString(2, surgeries.getType());
		
		st.executeUpdate();
		st.close();
	}
	
	public void insertSpecialities(String speciality) throws SQLException {
		String query;
		
		query = "INSERT into speciality (type) values (?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, speciality);
		
		st.executeUpdate();
		st.close();
	}
}
