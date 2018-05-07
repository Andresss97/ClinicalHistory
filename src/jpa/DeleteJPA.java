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
}
