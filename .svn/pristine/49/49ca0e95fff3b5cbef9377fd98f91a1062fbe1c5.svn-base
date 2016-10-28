package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Residence;
import mx.com.kubo.model.ResidencePK;
import mx.com.kubo.repositories.ResidenceDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ResidenceDaoImp implements ResidenceDao{
	
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
	public Residence loadSelectedResidence(ResidencePK pk) {
		return em.find(Residence.class, pk);
	}

	@Transactional
	@Override
	public void saveResidence(Residence newResidence) {
		log.info("saveResidence.ResidenceDaoImp");
		em.persist(newResidence);		
	}

	@Override
	public List<Residence> loadResidenceList() {
		List<Residence> residence=em.createQuery("from Residence",Residence.class).getResultList();
		return residence;
	}
    
    

}
