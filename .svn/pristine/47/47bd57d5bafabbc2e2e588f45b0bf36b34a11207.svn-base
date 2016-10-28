package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Reca;
import mx.com.kubo.repositories.RecaDao;

import org.springframework.stereotype.Repository;

@Repository
public class RecaDaoImp implements Serializable,RecaDao {
	
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
	
	@Override
	public List<Reca> getRecaList(){
		
		String query = "from Reca order by  valid_from desc ";
		
		try{
			
			return em.createQuery( query, Reca.class ).getResultList();
		
		}catch(Exception e){
		
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
