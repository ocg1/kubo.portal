package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.InvestmentParamCat;
import mx.com.kubo.repositories.InvestmentParamCatDao;

@Repository
public class InvestmentParamCatDaoImp implements Serializable, InvestmentParamCatDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em)
	{
		this.em = em;
	}
	
	public List<InvestmentParamCat> getInvestmentParamCat(){
		
		try{
			
			return em.createQuery(" from InvestmentParamCat", InvestmentParamCat.class).getResultList();
			
		}catch( Exception e ){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
