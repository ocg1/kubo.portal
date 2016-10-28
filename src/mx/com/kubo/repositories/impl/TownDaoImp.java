package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.TownCat;
import mx.com.kubo.model.TownCatPK;
import mx.com.kubo.repositories.TownDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TownDaoImp 
implements TownDao 
{	
	Logger log = Logger.getLogger(getClass());
	
	private EntityManager em = null;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

	public TownCat loadSelectedTown(TownCatPK pk) {
		return em.find(TownCat.class, pk);
	}

	@Transactional
	public void saveTown(TownCat newTown) 
	{
		log.info("saveTownCat.TownCatDaoImp");
		
		em.persist(newTown);		
	}

	public List<TownCat> loadTownList() 
	{
		List<TownCat> town=em.createQuery("from TownCat",TownCat.class).getResultList();
		
		return town;
	}
	
	public List<TownCat> loadTownList(int state_id) 
	{
		List<TownCat> town = em.createQuery("from TownCat where state_id = ?", TownCat.class).setParameter(1, state_id).getResultList();
		
		return town;
	}
}
