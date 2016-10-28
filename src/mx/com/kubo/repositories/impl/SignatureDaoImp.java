package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.Signature;
import mx.com.kubo.repositories.SignatureDao;

@Repository
public class SignatureDaoImp implements Serializable,SignatureDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	public Signature getSignature(){
		return null;
	}
	
	public List<Signature> getSignatureByProspectus( Integer prospectus_id  , Integer proyect_loan_id){
		
		try{
			
			String query = "from Signature where prospectus_id = ? and proyect_loan_id = ? ";
			
			return em.createQuery(query, Signature.class).setParameter(1, prospectus_id).setParameter(2, proyect_loan_id).getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	@Transactional
	public boolean addSignature( Signature signature ){
		
		try{
			em.persist( signature );
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	public boolean updateSignature( Signature signature ){
		
		try{
			
			em.merge( signature );
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	public boolean deleteSignature( Signature signature ){
		
		try{
			
			em.remove( signature );
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
}
