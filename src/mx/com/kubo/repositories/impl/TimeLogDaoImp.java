package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.TimeLog;
import mx.com.kubo.repositories.TimeLogDao;

@Repository
public class TimeLogDaoImp implements Serializable, TimeLogDao {

	
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
	
	@Transactional
	public boolean saveTimeLog( TimeLog timelog ){
		
		try{
			
			em.persist(timelog);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
}
