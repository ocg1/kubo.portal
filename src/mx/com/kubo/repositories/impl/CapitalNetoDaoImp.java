package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.CapitalNeto;
import mx.com.kubo.model.PrecioUdi;
import mx.com.kubo.repositories.CapitalNetoDao;

import org.springframework.stereotype.Repository;

@Repository
public class CapitalNetoDaoImp implements CapitalNetoDao, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	  /**
   * Sets the entity manager.
   */
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	 }
	
	@Override
	public CapitalNeto getMaxCapitalNeto(){
		try{
			return em.createQuery("from CapitalNeto cn where cn.creation_date = (select max(creation_date) from CapitalNeto)",CapitalNeto.class).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<CapitalNeto> getListCapitalNeto(){
		
		try{
			return em.createQuery("from CapitalNeto)",CapitalNeto.class).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
				
		}
		
	}

	public PrecioUdi getMaxPrecioUdi(){
		
		try{
			return em.createQuery("from PrecioUdi cn where cn.creation_date = (select max(creation_date) from PrecioUdi)",PrecioUdi.class).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
