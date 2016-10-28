package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Role_Searching;
import mx.com.kubo.model.Role_Searching_PK;
import mx.com.kubo.repositories.RoleSearchingDao;

import org.springframework.stereotype.Repository;

@Repository
public class RoleSearchingDaoImp implements Serializable,RoleSearchingDao  {
	
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
   public Role_Searching getRolesearchingbyPK(Role_Searching_PK pk){
	   
	   try{
		   
		   return em.find(Role_Searching.class, pk); 
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   return null;
	   }
	   
   }
	
}
