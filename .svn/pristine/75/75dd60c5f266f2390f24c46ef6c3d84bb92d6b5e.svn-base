package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.InvestmentProgressDet;
import mx.com.kubo.repositories.InvestmentProgressDetDao;

@Repository
public class InvestmentProgressDetDaoImp implements InvestmentProgressDetDao, Serializable {

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
	@Override
	public boolean saveInvestmentProgressDet(InvestmentProgressDet ipd){
		
		try{
			
			em.persist(ipd);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
	}
	
	@Transactional
	@Override
	public boolean updateInvestmentProgressDet(InvestmentProgressDet ipd){
		
		try{
			
			em.persist(ipd);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
}
