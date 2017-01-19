package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.ProspectusExtra;
import mx.com.kubo.repositories.ProspectusExtraDao;

@Repository
public class ProspectusExtraDaoImp implements Serializable, ProspectusExtraDao {

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
	public boolean saveProspectusExtra( ProspectusExtra extra ){
		
		try{
			
			em.persist(extra);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	public boolean updateProspectusExtra( ProspectusExtra extra ){
		
		try{
			
			em.merge(extra);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
}
