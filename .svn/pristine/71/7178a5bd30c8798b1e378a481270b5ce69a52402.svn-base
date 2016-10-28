package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.PademobilePayment;
import mx.com.kubo.model.PademobilePaymentPK;
import mx.com.kubo.repositories.PademobilePaymentDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PademobilePaymentDaoImp implements Serializable,PademobilePaymentDao {
	
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
	@Override
	public boolean savePademobilePayment(PademobilePayment payment){
		try{
			
			String query="select MAX( pp.pk.payment_id ) from PademobilePayment pp";
			
			int id = 0;
			
			if(em.createQuery(query).getSingleResult() !=null){
				
				id=(Integer) em.createQuery(query).getSingleResult();
				id++;
				
			}
			else{
				
				id++;
				
			}
			
			payment.getPk().setPayment_id(id);
			
			em.persist(payment);
			
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
	}
	
	@Transactional
	@Override
	public boolean updatePademobilePayment(PademobilePayment payment){
		
		try{
			
			em.merge(payment);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	@Transactional
	@Override
	public boolean removePademobilePayment(PademobilePayment payment){
		boolean bandera= false;
		try{
			
			PademobilePayment temp = em.find(PademobilePayment.class, payment.getPk() );
			em.remove( temp );
			
		}catch (Exception e) {
			
			e.printStackTrace();
			bandera=false;
			
		}
		
		return bandera;
		
	}
	
	@Override
	public PademobilePayment getPademobilePaymentById(PademobilePaymentPK payment){
		try{
			
			return em.find( PademobilePayment.class,payment ) ;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
	}
	

}
