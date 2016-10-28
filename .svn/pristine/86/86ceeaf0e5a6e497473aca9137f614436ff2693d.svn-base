package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ProfileCategoryInv;
import mx.com.kubo.repositories.ProfileCategoryDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileCategoryDaoImp implements Serializable, ProfileCategoryDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
	  /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	public List<ProfileCategoryInv> getListProfileCategory(){
		
		return em.createQuery( "from ProfileCategoryInv",ProfileCategoryInv.class ).getResultList();
		
	}
	
}
