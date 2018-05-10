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
	
	public List<Treatment> selectTreatment() {
		this.con.getEntityManager().getTransaction().begin();
		List<Treatment> treatments = new ArrayList<>();
		Query query = this.con.getEntityManager().createNativeQuery("SELECT * from treatment", Treatment.class);
		treatments.addAll(query.getResultList());
		this.con.getEntityManager().getTransaction().commit();
		return treatments;
	}
	
	public List<Allergies> selectAllergies() {
		this.con.getEntityManager().getTransaction().begin();
		List<Allergies> allergies = new ArrayList<>();
		Query query = this.con.getEntityManager().createNativeQuery("SELECT * from allergies", Allergies.class);
		allergies.addAll(query.getResultList());
		this.con.getEntityManager().getTransaction().commit();
		return allergies;
	}
	
	public List<Surgeries> selectSurgeries() {
		this.con.getEntityManager().getTransaction().begin();
		List<Surgeries> surgeries = new ArrayList<>();
		Query query = this.con.getEntityManager().createNativeQuery("SELECT * from surgeries", Surgeries.class);
		surgeries.addAll(query.getResultList());
		this.con.getEntityManager().getTransaction().commit();
		return surgeries;
	}
	
	public List<Vaccine> selectVaccine() {
		this.con.getEntityManager().getTransaction().begin();
		List<Vaccine> vaccines = new ArrayList<>();
		Query query = this.con.getEntityManager().createNativeQuery("SELECT * from vaccine", Vaccine.class);
		vaccines.addAll(query.getResultList());
		this.con.getEntityManager().getTransaction().commit();
		return vaccines;
	}
	
	public List<ClinicalHistory> selectClinicalHistory() {
		this.con.getEntityManager().getTransaction().begin();
		List<ClinicalHistory> clinicalRecord = new ArrayList<>();
		Query query = this.con.getEntityManager().createNativeQuery("SELECT * from clinicalhistory", ClinicalHistory.class);
		clinicalRecord.addAll(query.getResultList());
		this.con.getEntityManager().getTransaction().commit();
		return clinicalRecord;
	}
	
	public List<Illness> selectIllness() {
		this.con.getEntityManager().getTransaction().begin();
		List<Illness> illnesses = new ArrayList<>();
		Query query = this.con.getEntityManager().createNativeQuery("SELECT * from illnesses", Illness.class);
		illnesses.addAll(query.getResultList());
		this.con.getEntityManager().getTransaction().commit();
		return illnesses;
	}
}
