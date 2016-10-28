package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Frequency;
import mx.com.kubo.model.FrequencyPK;
import mx.com.kubo.repositories.FrequencyDao;

//import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FrequencyDaoImp implements FrequencyDao,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
	  /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

	@Override
	public Frequency loadSelectedFrequency(int id) {
	
		 FrequencyPK pk = new FrequencyPK();
		 
		 pk.setCompany_id(1);
		 pk.setFrequency_id(id);		 
		 return em.find(Frequency.class,pk);
	}

	@Transactional
	@Override
	public void saveFrequency(Frequency newFrequency) {
//		log.info("saveFrequency.FrequencyDaoImp");
		em.persist(newFrequency);
	}

	@Override
	public List<Frequency> loadFrequencyList() {
		List<Frequency> frequency = em.createQuery(
			    "from Frequency ", Frequency.class)
			    .getResultList();
		 return frequency;
	}
    
    

}
