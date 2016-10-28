package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ExpensesType;
import mx.com.kubo.model.ExpensesTypePK;
import mx.com.kubo.repositories.ExpensesTypeDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ExpensesTypeDaoImp implements ExpensesTypeDao{
	
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
	public ExpensesType loadSelectedExpensesType(ExpensesTypePK pk) {
		return em.find(ExpensesType.class, pk);
	}
    
    @Transactional
	@Override
	public void saveExpensesType(ExpensesType newExpensesType) {
		log.info("saveExpensesType.ExpensesTypeDaoImp");
		em.persist(newExpensesType);
	}
    
    @Override
	public List<ExpensesType> loadExpensesTypeList() {
		List<ExpensesType> expensesType=em.createQuery("from ExpensesType",ExpensesType.class).getResultList();
		return expensesType;
	}

}
