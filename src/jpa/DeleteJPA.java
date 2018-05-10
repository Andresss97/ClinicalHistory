package jpa;

import graphicInterface.Main;
import pojos.*;

public class DeleteJPA {
	private JPAConnector con = null;
	
	public DeleteJPA() {
		this.con = (JPAConnector) Main.jpaConector;
	}
	
	public void deleteVaccine(Vaccine vaccine) {
		con.getEntityManager().getTransaction().begin();
		con.getEntityManager().remove(vaccine);
		con.getEntityManager().getTransaction().commit();
	}
	
	public void deleteIllness(Illness illness) {
		con.getEntityManager().getTransaction().begin();
		con.getEntityManager().remove(illness);
		con.getEntityManager().getTransaction().commit();
	}
	
	public void deleteSurgery(Surgeries surgery) {
		con.getEntityManager().getTransaction().begin();
		con.getEntityManager().remove(surgery);
		con.getEntityManager().getTransaction().commit();
	}
	
	public void deleteAllergy(Allergies allergy) {
		con.getEntityManager().getTransaction().begin();
		con.getEntityManager().remove(allergy);
		con.getEntityManager().getTransaction().commit();
	}
	
	public void deleteTreatment(Treatment treatment) {
		con.getEntityManager().getTransaction().begin();
		con.getEntityManager().remove(treatment);
		con.getEntityManager().getTransaction().commit();
	}
}
