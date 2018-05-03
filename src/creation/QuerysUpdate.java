package creation;

import java.sql.PreparedStatement;
import pojos.*;

import java.sql.SQLException;

import graphicInterface.Main;
import pojos.Illness.typeDisease;

public class QuerysUpdate {

	private Conector conn = (Conector) Main.conector;
	
	public void updateIllness(Illness illness) throws SQLException {
		String query;

		query = "UPDATE illness"
				+ "SET  name =?,"
				+ "SET  description= ?,"
				+ "SET  type= ?,"
				+ "SET  date= ?,"
				+ "WHERE id =?";
			
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1,illness.getName());
		st.setString(2, illness.getDescription());
		
		if(illness.getTypeDisease().equals(typeDisease.HEREDITARY)) {
			st.setString(3,"Hereditary");
		}
		else {
			st.setString(3, "Personal");
		}
		st.setDate(4, illness.getDate());
		
		st.setString(3,illness.getDescription());
		st.setDate(4,illness.getDate());;
		st.setInt(5,illness.getID());
		
		st.executeUpdate();
		st.close();
	
	}	
	
	public void addressDoctorAssigment(Address address, Doctor doctor) throws SQLException {
		String query;

		query = "UPDATE doctor "
				+ "SET  addressID =?,"
				+ "WHERE id=?";
			
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setInt(1, address.getID());
		st.setInt(2, doctor.getID());
		
		st.executeUpdate();
		st.close();
	}

	public void updateAppointment (Appointment appointment) throws SQLException {
		String query;
		query = "UPDATE appointment " 
				+ "SET date = ?,"
				+ "hour = ?,"
				+ "reason = ?,"
				+ "iddoctor = ?,"
				+ "idpatient = ?"
				+ "WHERE id = ?";
		
		PreparedStatement st;
		st = conn.getConnect().prepareStatement(query);
		st.setDate(1, appointment.getDate());
		st.setString(2, appointment.getHour());
		st.setString(3, appointment.getReason());
		st.setInt(4, appointment.getDoctor().getID());
		st.setInt(5, Main.patient.getID());
		st.setInt(6, appointment.getID());
		
	    st.executeUpdate();
	    st.close();
	}
	
	/*private void updateClinicalHistory (ClinicalHistory clinicalHistory) throws SQLException {
		String query;
		query = "UPDATE clinicalHistory "
				+ " SET addictions = ? "
				+ " SET observations = ? "
				+ " SET lastModication = ? "
				+ " WHERE id = ?";
		PreparedStatement st;
		st = conn.getConnect().prepareStatement(query);
		st.setString(1, clinicalHistory.getAddictions());
		st.setString(2, clinicalHistory.getObservations());
		st.setDate(3, clinicalHistory.getLastModification());
		st.setInt(4, clinicalHistory.getID());
		
		st.executeUpdate();
		st.close();
	}*/
	
	private void updateAllergy (Allergies allergy) throws SQLException {
		String query;
		query = "UPDATE allergy "
				+ " SET group = ? "
				+ " SET observations = ? "
				+ " SET idtreatment = ? "
				+ " WHERE id = ?";
		PreparedStatement st;
		st = conn.getConnect().prepareStatement(query);
		st.setString(1, allergy.getType());
		st.setString(2, allergy.getObservations());
		st.setInt(3, allergy.getTreatment().getIDtreatment());
		st.setInt(4, allergy.getID());
		
		st.executeUpdate();
		st.close();
	}

private void updateTreatment (Treatment treatment) throws SQLException {
		String query;
		query = "UPDATE treatment"
				+ "SET  name = ?,"
				+ "SET  description = ?,"
				+ "SET  type = ?,"
				+ "SET  StartDate = ?,"
				+ "SET  EndDate = ?,"
				+ "SET  Results = ?,"	
				+ "WHERE id =?";
			
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		//st.setString(1,treatment.getName());
		st.setString(2, treatment.getDescrpition());
		
		/*if(treatment.getTreatment().equals("MEDICATION")) {
			st.setString(3,"MEDICATION");
		}
		else {
			st.setString(3, "REHAB");
		}*/
		/*st.setDate(4, treatment.getStartDate());
		st.setDate(5, treatment.getEndDate());*/
		st.setString(6, treatment.getResults());
		st.setInt(7,treatment.getIDtreatment());
		
		st.executeUpdate();
		st.close();
			
	}
	
	public void updateDoctor(Doctor doctor) throws SQLException {
		QuerysSelect qs= new QuerysSelect();
		String query = "UPDATE doctor "
				+ "SET name = ?,"
				+ "surname = ?,"
				+ "nif = ?,"
				+ "dob = ?,"
				+ "photo = ?,"
				+ "mobilephone = ?,"
				+ "username = ?,"
				+ "password = ?,"
				+ "email = ?,"
				+ "gender = ?,"
				+ "idspeciality = ? "
				+ "where id = ?";
		
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setString(1, doctor.getName());
		st.setString(2, doctor.getSurname());
		st.setString(3, doctor.getNIF());
		st.setDate(4, doctor.getDob());
		st.setBytes(5, doctor.getPhoto());
		st.setInt(6, doctor.getMobilePhone());
		st.setString(7, doctor.getUsername());
		st.setString(8, doctor.getPassword());
		st.setString(9, doctor.getEmail());
		if(doctor.getGender().equals("Male")) {
			st.setString(10, "Male");
		}
		else {
			st.setString(10, "Female");
		}
		
		int id = qs.selectIdSpeciality(doctor.getSpeciality());
		
		st.setInt(11, id);
		st.setInt(12, doctor.getID());
		
		st.executeUpdate();
		
		st.close();		
	}
	
	public void updatePatient(Patient patient) throws SQLException {
		String query = "UPDATE patient "
				+ "SET name = ?,"
				+ "surname = ?,"
				+ "nif = ?,"
				+ "dob = ?,"
				+ "photo = ?,"
				+ "mobilephone = ?,"
				+ "homephone = ?,"
				+ "username = ?,"
				+ "password = ?,"
				+ "email = ?,"
				+ "gender = ?,"
				+ "weight = ?,"
				+ "height = ?"
				+ "where id = ?";
		
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setString(1, patient.getName());
		st.setString(2, patient.getSurname());
		st.setString(3, patient.getNIF());
		st.setDate(4, patient.getDob());
		st.setBytes(5, patient.getPhoto());
		st.setInt(6, patient.getMobilePhone());
		st.setInt(7, patient.getHousePhone());
		st.setString(8, patient.getUsername());
		st.setString(9, patient.getPassword());
		st.setString(10, patient.getEmail());
		if(patient.getGender().equals("Male")) {
			st.setString(11, "Male");
		}
		else {
			st.setString(11, "Female");
		}
		st.setFloat(12, patient.getWeight());
		st.setFloat(13, patient.getHeight());
		st.setInt(14, patient.getID());
		
		st.executeUpdate();
		
		st.close();
	}
	
	public void updateAddress(Address address) throws SQLException {
		String query = "UPDATE address SET "
				+ "city = ?,"
				+ "street = ?,"
				+ "cp = ?,"
				+ "housenumber = ? where id = ?";
		
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, address.getCity());
		st.setString(2, address.getStreet());
		st.setInt(3, address.getPostalCode());
		st.setInt(4, address.getHouseNumber());
		st.setInt(5, address.getID());
		
		st.executeUpdate();
		
		st.close();
	}
}
