package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Marital_Status;
import mx.com.kubo.model.Marital_StatusPK;
import mx.com.kubo.repositories.MaritalStatusDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MaritalStatusDaoImp implements MaritalStatusDao{
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
	  /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

	@Override
	public Marital_Status loadSelectedMaritalStatus(Marital_StatusPK pk) {
		return em.find(Marital_Status.class, pk);
	}

	@Transactional
	@Override
	public void saveMaritalStatus(Marital_Status newMaritalStatus) {
		log.info("saveMaritalStatus.MaritalStatusDaoImp");
		em.persist(newMaritalStatus);		
	}

	@Override
	public List<Marital_Status> loadMaritalStatusList() {
		List<Marital_Status> maritalstatus=em.createQuery("from Marital_Status",Marital_Status.class).getResultList();
		return maritalstatus;
	}
    
    

}
