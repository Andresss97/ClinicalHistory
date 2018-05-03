package jpa;

import graphicInterface.Main;
import pojos.Vaccine;

public class CreateJPA {
	
private JPAConnector con = null;
	
	public void ReadJPA() {
		this.con = (JPAConnector) Main.jpaConector;
	}
	
	
	public void createVaccine(Vaccine vaccine) {
		con.getEntityManager().getTransaction().begin();
		con.getEntityManager().persist(vaccine);
		con.getEntityManager().getTransaction().commit();
		
	}
	
	

}
