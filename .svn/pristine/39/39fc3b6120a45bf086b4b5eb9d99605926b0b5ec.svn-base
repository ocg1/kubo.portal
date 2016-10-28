package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.OfferValues;
import mx.com.kubo.repositories.OfferValuesDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OfferValuesDaoImp implements Serializable, OfferValuesDao {

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
	
	@Transactional
	public boolean deleteOfferValueByOfferId( Integer offer_id ){
		try{
			
			String query = "delete from ln_offer_values where offer_id = " + offer_id + ";";
			
			em.createNativeQuery(query).executeUpdate();
			
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean deleteOfferValue( OfferValues value ){
		try{
			em.remove(value);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveOfferValues( List<OfferValues> values ){
		try{
			
			for( OfferValues of : values  ){
				em.persist(of);
			}
			
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return true;
		}
	}
	
	@Transactional
	public boolean saveOfferValue( OfferValues value ){
		
		try{
				em.persist(value);
		
				return true;
				
			}catch(Exception e){
				
				e.printStackTrace();
				return true;
			
			}
		
	}
	
	public List<OfferValues> getOfferValuesByOfferId( Integer offer_id ){
		try{
			
			String query = " from OfferValues where pk.offer_id = ? order by pk.position   ";
			
			return em.createQuery(query, OfferValues.class).setParameter(1, offer_id).getResultList();
			
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
