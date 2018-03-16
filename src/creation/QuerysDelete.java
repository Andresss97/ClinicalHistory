package creation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
<<<<<<< HEAD
=======

import graphicInterface.Main;
import pojos.*;

import graphicInterface.Main;
import pojos.*;
public class QuerysDelete {
	
	private Conector con;
	
	public QuerysDelete() {
		this.con = Main.conector;
	}	

	public void deleteAppointment (Appointment appointment) {
		String query;
		query = "DELETE FROM Appointment WHERE id = ?";
		PreparedStatement st;
		try {
			st = con.getConnect().prepareStatement(query);
			st.setInt(1, appointment.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteClinicalHistory (ClinicalHistory clinicalHistory) {
		String query;
		query = "DELETE FROM ClinicalHistory WHERE id = ?";
		
		PreparedStatement st;
		st.getConnection().prepareStatement(query);
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
