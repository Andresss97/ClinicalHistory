package jpa;

import graphicInterface.Main;
import pojos.*;




public class UpdateJPA {
	
	private JPAConnector con = null;
	
	public UpdateJPA() {
		this.con = (JPAConnector) Main.jpaConector;
	}
	
	public void updatePatientVaccine(Vaccine vaccine, Patient patient) {
		con.getEntityManager().getTransaction().begin();
		
		con.getEntityManager().getTransaction().commit();
	}
	

}
