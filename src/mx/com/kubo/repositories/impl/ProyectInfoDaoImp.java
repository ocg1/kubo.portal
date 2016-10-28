package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.ProyectInfo;
import mx.com.kubo.repositories.ProyectInfoDao;

@Repository
public class ProyectInfoDaoImp implements ProyectInfoDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	protected EntityManager em = null;
	
	protected static final int CIERRE = 6;

    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
	
    
    public ProyectInfo getProyectInfoByProyectLoan( Integer proyect_loan_id ){
    	
    	try{
    	
    		String str =  "from ProyectInfo where proyect_loan_id = ?";
    	
    		return em.createQuery(str, ProyectInfo.class).setParameter(1, proyect_loan_id).getSingleResult();
    		
    	}catch( NoResultException nr ){
    		
    		return null;
    		
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    	
    }
    
}
