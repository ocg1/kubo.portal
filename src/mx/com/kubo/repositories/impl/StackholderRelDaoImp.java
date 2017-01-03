package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Stackholder_relationship;
import mx.com.kubo.model.Stackholder_relationshipPK;
import mx.com.kubo.repositories.StackholderRelDao;

import org.springframework.stereotype.Repository;

@Repository
public class StackholderRelDaoImp implements StackholderRelDao {
	
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Override
	public List<Stackholder_relationship> getStackholderRelLst(){
		
		return em.createQuery(
			    "from Stackholder_relationship ", Stackholder_relationship.class)
			    .getResultList();
		
	}
	
	@Override
	public Stackholder_relationship getStackholderByPK( Stackholder_relationshipPK pk ){
		
		try{
		
			return em.find(Stackholder_relationship.class, pk);
			
		}catch( Exception e ){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
