package creation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import graphicInterface.Main;
import pojos.Patient;

public class QuerysSelect {
	private Conector conn = Main.conector;
	
	public String[] selectUser(String user, String psw) throws SQLException {
		String[] data = new String[2];
		String query = "SELECT USERNAME, PASSWORD from mappinglogin where username = '" + user + "' and password = '" + psw + "'";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		ResultSet set = st.executeQuery();
		
		while(set.next()) {
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
			patient.setUsername(set.getString("username"));
			patient.setPassword(set.getString("password"));
		}
		
		st.close();
		set.close();
		
		return patient;
	}
	
}
