package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Alert;
import mx.com.kubo.repositories.AlertDao;

import org.springframework.stereotype.Repository;


@Repository
public class AlertDaoImp implements Serializable, AlertDao {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    public List<Alert> getAlertListSelectable(){
    	
    	try{
	    	
    		String query = "from Alert where is_selectable = ? ";
	    	List<Alert> list = em.createQuery(query,Alert.class).setParameter(1, "S").getResultList();
	    	return list;
	    	
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return null;
    		
    	}
    	
    }
	
}
