package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.InvestmentProgress;
import mx.com.kubo.repositories.InvestmentProgressDao;

@Repository
public class InvestmentProgressDaoImp implements Serializable,InvestmentProgressDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public boolean saveInvestmentProgress( InvestmentProgress ip ){
		
		try{
			
			em.persist(ip);			
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	@Transactional
	public boolean updateInvestmentProgress( InvestmentProgress ip ){
		
		try{
			
			em.merge(ip);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	@Override
	public List<InvestmentProgress> getInvestmentProgressByDate( Date date ){
		
		try{
			
			String query = "from InvestmentProgress where investment_date = ?";
			return em.createQuery(query,InvestmentProgress.class).setParameter(1, date).getResultList();
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
