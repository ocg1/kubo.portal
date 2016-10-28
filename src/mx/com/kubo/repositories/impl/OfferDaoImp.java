package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Offer;
import mx.com.kubo.repositories.OfferDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OfferDaoImp implements Serializable, OfferDao{

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
    
	
	public Offer getOfferByBurSolNum( String bursolnum ){
		
		try{
			
			String query = "from  Offer where mx_solicitud_buro = ?"; 
			
			return em.createQuery(query, Offer.class).setParameter(1, bursolnum).getSingleResult();
		
		}catch(NoResultException nr){
			System.out.println( "Sin resultados" );
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Transactional
	public boolean updateOffer( Offer offer ){
		
		try{
			
			em.merge(offer);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	public boolean saveOffer( Offer offer ){
		
		try{
			
			em.persist(offer);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
}
