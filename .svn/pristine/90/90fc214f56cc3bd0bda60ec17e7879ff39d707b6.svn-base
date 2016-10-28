package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Motive;
import mx.com.kubo.model.MotivePK;
import mx.com.kubo.repositories.MotiveDao;

import org.springframework.stereotype.Repository;

@Repository
public class MotiveDaoImp implements MotiveDao {

	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    
    @Override
    public List<Motive> getMotiveStatusListByStatus(Integer status_id){
    	
    	String query = "from  Motive where status_id = ? order by description";
    	
    	List<Motive> lst = em.createQuery(query, Motive.class ).setParameter(1, status_id).getResultList();
    	
    	return lst;
    	
    }
    
    public List<Motive> getMotiveStatusListByNoteType(Integer note_type_id){
    	
    	String query = "from  Motive where note_type_id = ? order by description";
    	
    	List<Motive> lst = em.createQuery(query, Motive.class ).setParameter(1, note_type_id).getResultList();
    	
    	return lst;
    	
    }
    
    @Override
    public Motive getMotiveByPK( MotivePK pk ){
    	
    	try{
    		
    		return em.find( Motive.class,pk);
    		
    	}catch(Exception e){
    	
    		return null;
    		
    	}
    }
    
	
}
