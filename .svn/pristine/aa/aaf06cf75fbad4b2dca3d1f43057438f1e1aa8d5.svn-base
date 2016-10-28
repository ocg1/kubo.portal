package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.InfoScreen;
import mx.com.kubo.model.InfoScreenPK;
import mx.com.kubo.repositories.InfoScreenDao;

@Repository
public class InfoScreenDaoImp implements InfoScreenDao, Serializable {

private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) 
	{
        this.em = em;
    }
	
	public InfoScreen getInfoScreenById( InfoScreenPK pk ){
		
		try{
			
			return em.find(InfoScreen.class, pk);
			
		}catch(Exception e){
			
			return null;
			
		}
		
	}
	
}
