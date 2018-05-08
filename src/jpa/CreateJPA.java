package jpa;

import graphicInterface.Main;
import pojos.*;

public class CreateJPA {
	
private JPAConnector con = null;
	
	public CreateJPA() {
		this.con = (JPAConnector) Main.jpaConector;
	}

	public void createVaccine(Vaccine vaccine) {
		this.con.getEntityManager().getTransaction().begin();
		this.con.getEntityManager().persist(vaccine);
		this.con.getEntityManager().getTransaction().commit();
	}
	
	public void createClinicalRecord(ClinicalHistory cl) {
		this.con.getEntityManager().getTransaction().begin();
		this.con.getEntityManager().persist(cl);
		this.con.getEntityManager().getTransaction().commit();
	}
	
	public void createIllnes(Illness illness) {
		this.con.getEntityManager().getTransaction().begin();
		this.con.getEntityManager().persist(illness);
		this.con.getEntityManager().getTransaction().commit();
	}
	
	public void createTreatment(Treatment treatment) {
		this.con.getEntityManager().getTransaction().begin();
		this.con.getEntityManager().persist(treatment);
		this.con.getEntityManager().getTransaction().commit();
	}
}
