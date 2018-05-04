package jpa;

import graphicInterface.Main;
import pojos.Vaccine;

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
}
