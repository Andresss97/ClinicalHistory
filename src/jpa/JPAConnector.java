package jpa;

import javax.persistence.*;

import creation.ConnInterface;

public class JPAConnector implements ConnInterface {
	
	private EntityManager em = null;
	private String url;
	
	@Override
	public void connect() {
		this.em = Persistence.createEntityManagerFactory("hospitalManager").createEntityManager();	
		this.em.getTransaction().begin();
		this.em.createNativeQuery("PRAGMA foreign_keys = ON").executeUpdate();
		this.em.getTransaction().commit();
	}
	
	@Override
	public void killConnection() {
		this.em.close();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
}
