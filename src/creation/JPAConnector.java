package creation;

import javax.persistence.*;

public class JPAConnector implements ConnInterface {
	
	private EntityManager em = null;
	private String url;
	
	@Override
	public void connect() {
		this.em = Persistence.createEntityManagerFactory("hospitalManager").createEntityManager();	
		em.getTransaction().begin();
		em.createNamedQuery("PRAGMA foreign_keys = ON").executeUpdate();
		em.getTransaction().commit();
	}
	
	@Override
	public void killConnection() {
		em.close();
	}
	
	public EntityManager getEntityManager () {
		return this.em;
	}
	

}
