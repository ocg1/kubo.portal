package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Occupation;
import mx.com.kubo.model.OccupationPK;
import mx.com.kubo.repositories.OccupationDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OccupationDaoImp implements OccupationDao{
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
	 /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public Occupation loadSelectedOccupation(OccupationPK pk){
    	return em.find(Occupation.class, pk);
    }
    
    @Transactional
	@Override
	public void saveOccupation(Occupation newOccupation) {
		log.info("saveOccupation.OccupationDaoImp");
		em.persist(newOccupation);
	}
    
    @Override
	public List<Occupation> loadOccupationList() {
		List<Occupation> occupation=em.createQuery("from Occupation",Occupation.class).getResultList();
		return occupation;
	}
}
