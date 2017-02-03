package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.InvestorGroup;
import mx.com.kubo.repositories.InvestorGroupDao;

@Repository
public class InvestorGroupDaoImp implements Serializable , InvestorGroupDao {

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
	public boolean saveInvestorGroup( InvestorGroup group ){
		
		try{
			
			em.persist(group);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	public boolean updateInvestorGroup( InvestorGroup group ){
		
		try{
		
			em.merge(group);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
}
