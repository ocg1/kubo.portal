package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.FullName;
import mx.com.kubo.model.FullNamePK;
import mx.com.kubo.repositories.FullNameDao;

@Repository
public class FullNameDaoImp implements FullNameDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em){
		
		this.em = em;
		
	}
	
	public FullName getFullName( FullNamePK pk ){
		
		try{
			
			return em.find(FullName.class, pk);
		
		}catch( EntityNotFoundException f ){
			
			f.getLocalizedMessage();
			return null;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	@Transactional
	public boolean saveFullName( FullName full_name ){
		
		try{
			
			em.persist(full_name);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	@Transactional
	public boolean updateFullName( FullName full_name ){
		
		try{
			
			em.merge(full_name);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	
}
