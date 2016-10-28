package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.MovementToVerify;
import mx.com.kubo.repositories.MovementToVerifyDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MovementToVerifyDaoImp implements MovementToVerifyDao  {

	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public List<MovementToVerify> getMovementToVerifyActiveList(){
    	
    	String query = "from MovementToVerify where movement_status = 1 order by movement_order";
    	
    	return em.createQuery( query , MovementToVerify.class ).getResultList();
    	
    }
    
    @Override
    @Transactional
    public boolean updateMovementToVerify( MovementToVerify movement ){
    	try{
    		
    		em.merge(movement);
    		return true;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
    }
	
}
