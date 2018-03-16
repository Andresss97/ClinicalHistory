package creation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import graphicInterface.Main;
import pojos.*;

import graphicInterface.Main;
import pojos.*;
public class QuerysDelete {
	
	private Conector con;
	
	public QuerysDelete() {
		this.con = Main.conector;
	}	

	public void deleteAppointment (Appointment appointment) throws SQLException {
		String query;
		query = "DELETE FROM appointment WHERE id = ?";
		PreparedStatement st;
			st = con.getConnect().prepareStatement(query);
			st.setInt(1, appointment.getID());
			st.executeUpdate();
			st.close();
	}
	
	private void deleteClinicalHistory (ClinicalHistory clinicalHistory) throws SQLException {
		String query;
		query = "DELETE FROM clinicalHistory WHERE id = ?";
	    PreparedStatement st;
		st= con.getConnect().prepareStatement(query);
		st.setInt(1, clinicalHistory.getID());
		st.executeUpdate();
		st.close();
	}
	
	private void deleteSurgery (Surgeries surgery) throws SQLException {
		String query;
		query = "DELETE FROM surgeries WHERE id = ?";
		PreparedStatement st;
			st = con.getConnect().prepareStatement(query);
			st.setInt(1, surgery.getID());
			st.executeUpdate();
			st.close();
	}
	
	private void deleteAllergy (Allergies allergy) throws SQLException {
		String query;
		query = "DELETE FROM allergies WHERE id = ?";
		PreparedStatement st;
			st = con.getConnect().prepareStatement(query);
			st.setInt(1, allergy.getID());
			st.executeUpdate();
			st.close();		
	}
	
	public void deleteDoctorAccount(Doctor doctor) throws SQLException {
		String query = "DELETE from doctor where id = " + doctor.getID();
		PreparedStatement st = con.getConnect().prepareStatement(query);
		
		st.executeUpdate();
		
		st.close();
	}
	
	public void deletePatientAccount(Patient patient) throws SQLException {
		String query = "DELETE from patient where id = " + patient.getID();
		PreparedStatement st = con.getConnect().prepareStatement(query);
		
		st.executeUpdate();
		
		st.close();
	}
	
	public void deleteUser(int id) throws SQLException {
		String query = "DELETE from mappinglogin where id = " + id;
		PreparedStatement st = con.getConnect().prepareStatement(query);
		
		st.executeUpdate();
		
		st.close();
	}
}
