package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.NeighborhoodCatPK;
import mx.com.kubo.repositories.NeighborhoodDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NeighborhoodDaoImp implements NeighborhoodDao{
	
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
	public NeighborhoodCat loadSelectedNeighborhood(NeighborhoodCatPK pk) {
		return em.find(NeighborhoodCat.class, pk);
	}

	@Transactional
	@Override
	public void saveNeighborhood(NeighborhoodCat newNeighborhood) {
		log.info("saveNeighborhoodCat.NeighborhoodCatDaoImp");
		em.persist(newNeighborhood);		
	}

	@Override
	public List<NeighborhoodCat> loadNeighborhoodList() {
		List<NeighborhoodCat> neighborhood=em.createQuery("from NeighborhoodCat",NeighborhoodCat.class).getResultList();
		return neighborhood;
	}
    
    

}
