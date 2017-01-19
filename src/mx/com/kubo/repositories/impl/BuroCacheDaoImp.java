package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.BuroCache;
import mx.com.kubo.repositories.BuroCacheDao;

@Repository
public class BuroCacheDaoImp implements BuroCacheDao 
{
	private EntityManager em = null;
		
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
	
	@Transactional
	public boolean delete(String burSolNum) 
	{
		BuroCache cache = getBuroCache(burSolNum);
		
		boolean delete_cache_OK = false;
		
		try
		{
			if(cache != null)
			{
				em.remove(cache);
			
				delete_cache_OK = true;
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return delete_cache_OK;
	}
}
