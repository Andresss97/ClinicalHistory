package creation;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import graphicInterface.Main;
import pojos.Person.GENDER;
import pojos.Treatment.typeTreatment;
import pojos.*;
import pojos.ClinicalHistory.BLOODGROUP;
import pojos.Illness.typeDisease;

public class QuerysInsert {
	
	private Conector conn = Main.conector;
	
	public void insertDoctor(Doctor doctor, int iDAddress) throws SQLException {
		String query;
		query = "INSERT into doctor (username,password,email,gender,speciality,mobilephone,name,surname,nif,dob,photo,idaddress) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, doctor.getUsername());
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
		st.setInt(3, address.getPostalCode());
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
		
		st.setString(9, patient.getUsername());
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
			query = "INSERT into mappinglogin (username, password,usertype,email) values (?,?,?,?)";
			PreparedStatement st = conn.getConnect().prepareStatement(query);
			st.setString(1, patient.getUsername());
			st.setString(2, patient.getPassword());
			st.setInt(3, 1);
			st.setString(4,patient.getEmail());
			
			st.executeUpdate();
			st.close();
		}
		else if(!doctor.equals(null)) {
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
	
	public void insertIllness(Illness illness) throws SQLException {
		String query;

		query = "INSERT into illness (name,type,description,date,Tresults) values (?,?,?,?)";
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
		
		st.executeUpdate();
		st.close();
	
	}
	
	public void insertVaccine(Vaccine vaccine) throws SQLException{
		String query;
		
		query = "INSERT into vaccine (name,date,observations,) values(?,?,?)";
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
		
		st.executeUpdate();
		st.close();	
	}
	
	public void insertTreatment(Treatment treatment) throws SQLException {
		String query;
		
		query = "INSERT into treatment (start,end, type, description,) values(?,?,?,?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setDate(1, treatment.getStartDate());
		st.setDate(2, treatment.getEndDate());
		
		if(treatment.getTreatment().equals(typeTreatment.MEDICATION)) {
			st.setString(3, "Medication");
		}
		else {
			st.setString(3, "Rehab");
		}
		st.setString(4, treatment.getDescrpition());
		st.setString(5, treatment.getResults());
		
		st.executeUpdate();
		st.close();
		
	}
		
	public void insertClinicalHistory(ClinicalHistory clinicalHistory) throws SQLException {
		String query;

		query = "INSERT into clinicalHistory (addictions, observations, lastModification, bloodgroup, medicalInsurance) values (?,?,?,?,?)";
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
		st.setInt(5, clinicalHistory.getMedicalInsurance());
		
		st.executeUpdate();
		st.close();
	}
	
	public void insertAppointment(Appointment appointment) throws SQLException {
		String query;

		query = "INSERT into appointment (date, hour, reason) values (?,?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
	    st.setDate(1, appointment.getDate());
		st.setInt(2, appointment.getHour());
		st.setString(3, appointment.getReason());
		
		st.executeUpdate();
		st.close();
	}
	
	public void insertAllergies(Allergies allergies) throws SQLException {
		String query;

		query = "INSERT into allergies (group, observations) values (?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, allergies.getGroup());
		st.setString(2, allergies.getObservations());
		
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

}
