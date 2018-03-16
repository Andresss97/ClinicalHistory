package creation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import graphicInterface.Main;
import pojos.*;
public class QuerysDelete {

	private Conector conn = Main.conector;
	
	
	private void deleteAppointment (Appointment appointment) {
		
		String query;
		query = "DELETE FROM Appointment WHERE id = ?";
		PreparedStatement st;
		try {
			st = conn.getConnect().prepareStatement(query);
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
}
