package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.IncomeType;
import mx.com.kubo.model.IncomeTypePK;
import mx.com.kubo.repositories.IncomeTypeDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class IncomeTypeDaoImp implements IncomeTypeDao{
	
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
	public IncomeType loadSelectedIncomeType(IncomeTypePK pk) {
		return em.find(IncomeType.class, pk);
	}
    
    @Transactional
	@Override
	public void saveIncomeType(IncomeType newIncomeType) {
		log.info("saveIncomeType.IncomeTypeDaoImp");
		em.persist(newIncomeType);
	}
    
    @Override
	public List<IncomeType> loadIncomeTypeList() {
		List<IncomeType> incomeType=em.createQuery("from IncomeType",IncomeType.class).getResultList();
		return incomeType;
	}

	@Override
	public List<IncomeType> getIncomeTypeListOrderByConsec() {
			List<IncomeType> incomeType=em.createQuery("from IncomeType order by consec",IncomeType.class).getResultList();
			return incomeType;
		
	}
    
    
    
}
