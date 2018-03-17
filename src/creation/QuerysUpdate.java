package creation;

import java.sql.PreparedStatement;
import pojos.*;
import java.sql.SQLException;

import graphicInterface.Main;
import pojos.Illness.typeDisease;

public class QuerysUpdate {

	private Conector conn = Main.conector;
	
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
		st.setInt(5,illness.getIDdisease());
		
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
	
	private void updateAppointment (Appointment appointment) throws SQLException {
		String query;
		query = "UPDATE appointment " 
				+ "SET date = ? "
				+ "SET hour = ? "
				+ "SET reason = ? "
				+ "SET iddoctor = ? "
				+ "WHERE id = ?";
		
		PreparedStatement st;
		st = conn.getConnect().prepareStatement(query);
		st.setDate(1, appointment.getDate());
		st.setString(2, appointment.getHour());
		st.setString(3, appointment.getReason());
		st.setInt(4, appointment.getDoctor().getID());
		st.setInt(5, appointment.getID());
		
	    st.executeUpdate();
	    st.close();
		
	}
	
	private void updateClinicalHistory (ClinicalHistory clinicalHistory) throws SQLException {
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
	}
	
	private void updateAllergy (Allergies allergy) throws SQLException {
		String query;
		query = "UPDATE allergy "
				+ " SET group = ? "
				+ " SET observations = ? "
				+ " SET idtreatment = ? "
				+ " WHERE id = ?";
		PreparedStatement st;
		st = conn.getConnect().prepareStatement(query);
		st.setString(1, allergy.getGroup());
		st.setString(2, allergy.getObservations());
		st.setInt(3, allergy.getTreatment().getIDtreatment());
		st.setInt(4, allergy.getID());
		
		st.executeUpdate();
		st.close();
	}
	
private void updateTreatment (Treatment treatment) throws SQLException {
		String query;
	    query = "UPDATE treatment"
				+ "SET  name =?,"
				+ "SET  description= ?,"
				+ "SET  type= ?,"
				+ "SET  StartDate= ?,"
				+ "SET  EndDate= ?,"
				+ "SET  Results= ?,"
				+ "SET  Doctor= ?,"	
				+ "WHERE id =?";
			
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1,treatment.getName());
		st.setString(2, treatment.getDescrpition());
		
		if(treatment.getTreatment().equals("MEDICATION")) {
			st.setString(3,"MEDICATION");
		}
		else {
			st.setString(3, "REHAB");
		}
		st.setDate(4, treatment.getStartDate());
		st.setDate(5, treatment.getEndDate());
		st.setString(6, treatment.getResults());
		st.setInt(7, treatment.getDoctor().getID());
		
		st.setInt(8,treatment.getIDtreatment());
		
		st.executeUpdate();
		st.close();
			
	}

}
