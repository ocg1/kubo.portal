package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ProfileInvestForm;
import mx.com.kubo.repositories.ProfileInvestFormDao;

import org.springframework.stereotype.Repository;

@Repository
public class ProfileInvestFormDaoImp implements Serializable, ProfileInvestFormDao {

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
	
	public List<ProfileInvestForm> getProfileInvestFormList(){
		
		try{
			
			return em.createQuery("from ProfileInvestForm",ProfileInvestForm.class).getResultList();
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
	}

}
