package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mx.com.kubo.model.InstitutionalInvestor;
import mx.com.kubo.model.InstitutionalInvestorPK;
import mx.com.kubo.model.InvestorCategory;
import mx.com.kubo.repositories.InstitutionalInvestorDao;

import org.springframework.stereotype.Repository;

@Repository
public class InstitutionalInvestorDaoImp 
implements InstitutionalInvestorDao
{
	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) 
	{
        this.em = em;
    }
	
	public List<InstitutionalInvestor> getListInstInvestor() 
	{		
		List<InstitutionalInvestor> listInstInvestor = em.createQuery("from InstitutionalInvestor", InstitutionalInvestor.class).getResultList();
		
		return listInstInvestor;
	}
	
	public InstitutionalInvestor getInstInvestorByID(InstitutionalInvestorPK pK) 
	{
		return em.find(InstitutionalInvestor.class, pK);
	}

	public List<InvestorCategory> getLista_investor_category(int institutional_investor_id) 
	{
		List<InvestorCategory> lista_investor_category = null;
		
		TypedQuery<InvestorCategory> typed;
		
		typed = em.createQuery("from InvestorCategory where institutional_investor_id = ? and is_enabled = 'S'", InvestorCategory.class);
		typed.setParameter(1, institutional_investor_id);
		
		lista_investor_category = typed.getResultList();
		
		return lista_investor_category;
	}

}
