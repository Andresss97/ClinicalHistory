package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import graphicInterface.Main;
import pojos.*;

public class ReadJPA {
	private JPAConnector con = null;
	
	public ReadJPA() {
		this.con = (JPAConnector) Main.jpaConector;
	}
	
	public List<Patient> selectPatientsForDoctor(Doctor doctor) {
		this.con.getEntityManager().getTransaction().begin();
		Query query = this.con.getEntityManager().createNativeQuery("SELECT * from patient as p JOIN "
				+ "appointment as a on p.id = a.idpatient where "
				+ "a.iddoctor = " + doctor.getID(), Patient.class);
		
		List<Patient> patients = new ArrayList<>();
		patients.addAll(query.getResultList());
		
		this.con.getEntityManager().getTransaction().commit();
		
		return patients;
	}
}
