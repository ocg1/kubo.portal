package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.BuroCache;
import mx.com.kubo.repositories.BuroCacheDao;

@Repository
public class BuroCacheDaoImp implements Serializable, BuroCacheDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	  /**
	   * Sets the entity manager.
	   */
		
	  @PersistenceContext
	  public void setEntityManager(EntityManager em) {
	      this.em = em;
	  }
	
	public BuroCache getBuroCache( String burSolNum ){
		
		String query = "from BuroCache where mx_solicitud_buro = ?";
		
		try{
			
			return em.createQuery(query, BuroCache.class).setParameter(1,burSolNum ).getSingleResult();
			
		}catch(Exception e){
			System.out.println( e.getMessage() );
			return null;
		}
	}
	
	@Transactional
	public boolean saveBuroCache( BuroCache cache ){
		try{
			
			em.persist(cache);
			return true;
			
		}catch(Exception e){
			System.out.println( e.getMessage() );
			return false;
		}
	}
	
}
