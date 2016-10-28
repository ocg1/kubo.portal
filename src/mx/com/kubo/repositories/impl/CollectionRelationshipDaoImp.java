package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.CollectionRelationship;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.repositories.CollectionRelationshipDao;

import org.springframework.stereotype.Repository;

@Repository
public class CollectionRelationshipDaoImp implements CollectionRelationshipDao, Serializable {

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
    public CollectionRelationship getCollectionRelationshipByPLPK( ProyectLoanPK pylnpk ){
    	
    	String query = "from CollectionRelationship "
    			     + "where pk.company_id     = ? " 
    			     + " and pk.prospectus_id   = ? " 
    			     + " and pk.proyect_id      = ? " 
    			     + " and pk.proyect_loan_id = ? "
    			     + " and is_active          = ? ";
    	
    	try{
    	
    	return em.createQuery(query,CollectionRelationship.class)
    						.setParameter(1, pylnpk.getCompany_id())
    						.setParameter(2, pylnpk.getProspectus_id())
    						.setParameter(3, pylnpk.getProyect_id())
    						.setParameter(4, pylnpk.getProyect_loan_id())
    						.setParameter(5, "S")
    						.getSingleResult();
    	}catch(Exception e){
    		//e.printStackTrace();
    		return null;
    	}
    	
    }
	
}
