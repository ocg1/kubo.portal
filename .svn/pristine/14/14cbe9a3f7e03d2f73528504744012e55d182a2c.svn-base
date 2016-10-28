package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.repositories.StateDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StateDaoImp implements StateDao{
	
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
	public StateCat loadSelectedState(StateCatPK pk) {
		return em.find(StateCat.class, pk);
	}

	@Transactional
	@Override
	public void saveState(StateCat newState) {
		log.info("saveStateCat.StateCatDaoImp");
		em.persist(newState);		
	}

	@Override
	public List<StateCat> loadStateList() {
		List<StateCat> neighborhood=em.createQuery("from StateCat",StateCat.class).getResultList();
		return neighborhood;
	}
    
    

}
