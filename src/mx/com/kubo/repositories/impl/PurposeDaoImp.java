package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Purpose;
import mx.com.kubo.model.PurposePK;
import mx.com.kubo.repositories.PurposeDao;

//import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PurposeDaoImp implements PurposeDao {

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
	public Purpose loadSelectedPurpose(PurposePK pk) {
//		log.info("PurposeDaoImp load purpose: ");		
		 return em.find( Purpose.class,pk);
	}
    
    /*
	@Override
	public Purpose loadSelectedPurpose(int id) {
		log.info("PurposeDaoImp load purpose: "+id);
		PurposePK pk = new PurposePK();
		 
		 pk.setCompany_id(1);
		 pk.setPurpose_id(id);
		 
		 return em.find( Purpose.class,pk);
	}*/

	@Transactional
	@Override
	public void savePurpose(Purpose newPurpose) {
//		log.info("savePurpose.PurposeDaoImp");
		em.persist(newPurpose);
	}

	@Override
	public List<Purpose> loadPurposeList() {
		List<Purpose> cats = em.createQuery(
			    "from Purpose ", Purpose.class)
			    .getResultList();
		 return cats;
	}
	
	@Override
	public List<Purpose> loadPurposeListByType(Integer type_id) {
		List<Purpose> purpose = em.createQuery(
			    "from Purpose where type_id = ? ", Purpose.class)
			    .setParameter(1, type_id)
			    .getResultList();
		 return purpose;
	}

}
