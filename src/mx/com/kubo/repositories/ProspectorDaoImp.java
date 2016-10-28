package mx.com.kubo.repositories;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.Prospector;

@Repository
public class ProspectorDaoImp implements Serializable, ProspectorDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	@Override
	public boolean saveProspector( Prospector prospector ){
		
		try{
			
			em.persist(prospector);
			return true;
			
		}catch( Exception e ){
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	@Override
	public boolean updateProspector( Prospector prospector ){
		
		try{
			
			em.merge(prospector);
			return true;
			
		}catch( Exception e ){
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	@Override
	public Prospector getMaxProspector( int prospectus_id, int company_id ){
		
		try{
			
			EntityManager entityManager=em.getEntityManagerFactory().createEntityManager();
			Session session = (Session) entityManager.unwrap(Session.class);
			
			Prospector oldest = 
				    (Prospector) session.createCriteria(Prospector.class)
				    .addOrder(Order.desc("prospector_id"))
				    .add(Restrictions.eq("company_id", company_id))
				    .add(Restrictions.eq("prospectus_id", prospectus_id))
				    .setMaxResults(1)
				    .uniqueResult();
			
			return oldest;
			
		}catch( Exception e ){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
