package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.EconomicActivityPK;
import mx.com.kubo.model.Economic_Activity;
import mx.com.kubo.repositories.EconomicActivityDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EconomicActivityDaoImp implements EconomicActivityDao{
	
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
	public Economic_Activity loadSelectedEconomicActivity(EconomicActivityPK pk) {
		return em.find(Economic_Activity.class, pk);
	}

	@Transactional
	@Override
	public void saveEconomicActivity(Economic_Activity newEconomicActivity) {
		log.info("saveEconomicActivity.EconomicActivityDaoImp");
		em.persist(newEconomicActivity);		
	}

	@Override
	public List<Economic_Activity> loadEconomicActivityList() {
		List<Economic_Activity> economicactivity=em.createQuery("from Economic_Activity",Economic_Activity.class).getResultList();
		return economicactivity;
	}
    
	@Override
	public List<Economic_Activity> loadEconomicActivityListEnabled() {
		List<Economic_Activity> economicactivity=em.createQuery("from Economic_Activity where is_enabled = 1 order by sequency_field ",Economic_Activity.class).getResultList();
		return economicactivity;
	}

}
