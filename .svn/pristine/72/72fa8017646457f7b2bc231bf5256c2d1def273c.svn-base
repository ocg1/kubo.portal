package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.ClabeAccountPK;
import mx.com.kubo.repositories.ClabeAccountDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ClabeAccountDaoImp implements ClabeAccountDao {
	
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
	public ClabeAccount loadSelectedClabeAccount(ClabeAccountPK pk) {
		return em.find(ClabeAccount.class, pk);
	}

	@Transactional
	@Override
	public void saveClabeAccount(ClabeAccount newClabeAccount) {
		log.info("saveClabeAccount.ClabeAccountDaoImp");
		
		String query="select MAX(ca.clabepk.clabe_account_id) from ClabeAccount ca where ca.clabepk.prospectus_id = ? and ca.clabepk.company_id = ?";
		
		
		Integer clabeId  =(Integer) em.createQuery(query).setParameter(1, newClabeAccount.getClabepk().getProspectus_id()).setParameter(2,newClabeAccount.getClabepk().getCompany_id()).getSingleResult();
		 
		if(clabeId==null||clabeId==0)
			clabeId=1;
		else
			clabeId++;
		
		newClabeAccount.getClabepk().setClabe_account_id(clabeId);
		
		em.persist(newClabeAccount);		
	}
	
	@Transactional
	@Override
	public void updateClabeAccount(ClabeAccount newClabeAccount) {
		log.info("mergeClabeAccount.ClabeAccountDaoImp");
		em.merge(newClabeAccount);		
	}
	
	@Transactional
	@Override
	public void removeClabeAccount(ClabeAccount newClabeAccount) {
		log.info("removeClabeAccount.ClabeAccountDaoImp");
		ClabeAccount cb = em.find(ClabeAccount.class,newClabeAccount.getClabepk());
		if(cb!=null)
			em.remove(cb);		
	}

	@Override
	public List<ClabeAccount> loadClabeAccountListByProspectus(int prospectus_id,int company_id) {
		List<ClabeAccount> clabeaccount=em.createQuery("from ClabeAccount where clabepk.prospectus_id = ? and clabepk.company_id = ? ",ClabeAccount.class)
														.setParameter(1, prospectus_id).setParameter(2, company_id)
														.getResultList();
		return clabeaccount;
	}
	
	
}
