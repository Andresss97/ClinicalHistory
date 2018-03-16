package creation;

import java.sql.PreparedStatement;
import pojos.*;
import java.sql.SQLException;

import com.sun.jndi.cosnaming.IiopUrl.Address;

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
	
	public void addressDoctorAssigment(Address address, Doctor doctor) {
		String query;

		query = "UPDATE doctor"
				+ "SET  addressID =?,"
				+ "WHERE id=?";
			
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setInt(1, address.getID());
		st.setInt(2, doctor.getID());
		
		
		st.executeUpdate();
		st.close();
		
		
	}
	
	


}
