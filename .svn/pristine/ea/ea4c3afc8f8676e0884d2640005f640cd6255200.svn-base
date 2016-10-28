package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.RetornoAnual;
import mx.com.kubo.repositories.RetornoAnualDao;

@Repository
public class RetornoAnualDaoImp implements Serializable,RetornoAnualDao  {

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
	
	public RetornoAnual getRetornoAnual( int cuentaAhoId ){
		
		try{
			
			return em.createNamedQuery("collectorRetornoAnual",  RetornoAnual.class )
					.setParameter("CuentaAhoID", cuentaAhoId)
					.getSingleResult();
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
