package creation;


import java.sql.PreparedStatement;
import java.sql.SQLException;
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/Andresss97/ClinicalHistory.git
import graphicInterface.Main;
import pojos.*;

public class QuerysDelete {
	
	private Conector con;
	
	public QuerysDelete() {
		this.con = Main.conector;
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
