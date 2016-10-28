package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.ProyectLoanInfo;
import mx.com.kubo.model.ProyectLoanInfoPK;
import mx.com.kubo.repositories.ProyectLoanInfoDao;

@Repository
public class ProyectLoanInfoDaoImp implements Serializable, ProyectLoanInfoDao  {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;

	@PersistenceContext
    public void setEntityManager(EntityManager em) 
	{
        this.em = em;
    }
	
	@Override
	@Transactional
	public boolean saveProyectLoanInfo( ProyectLoanInfo proyectloaninfo ){
		
		try{
			
			em.persist( proyectloaninfo );
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	@Override
	@Transactional
	public boolean updateProyectLoanInfo( ProyectLoanInfo proyectloaninfo ){
		
		try{
			
			em.merge( proyectloaninfo );
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	@Override
	public ProyectLoanInfo getProyectLoanInfo( ProyectLoanInfoPK pk ){
		
		try{
			
			return em.find( ProyectLoanInfo.class, pk );
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
		}
		
		
	}
	
}
