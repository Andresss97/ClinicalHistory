package jpa;

import graphicInterface.Main;
import pojos.*;




public class UpdateJPA {
	
	private JPAConnector con = null;
	
	public void ReadJPA() {
		this.con = (JPAConnector) Main.jpaConector;
	}
	
	public void updatePatientVaccine(Patient patient, Vaccine vaccine) {
		con.getEntityManager().getTransaction().begin();
	
		patient.addVaccine(vaccine);
		vaccine.setPatient(patient);
	
		con.getEntityManager().getTransaction().commit();
	}
	

}
