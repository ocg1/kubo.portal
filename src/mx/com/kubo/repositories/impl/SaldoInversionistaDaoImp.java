package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.SaldoInversionista;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.repositories.SaldoInversionistaDao;

import org.springframework.stereotype.Repository;

@Repository
public class SaldoInversionistaDaoImp implements Serializable, SaldoInversionistaDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
	
	
	public SaldoInversionista getSaldoByAccount( String account ){
		
		try{
			
			String query = "getSaldoInversionista";
			
			SaldoInversionista saldos = em.createNamedQuery(query,SaldoInversionista.class).setParameter("safi_account_id", Integer.parseInt( account ) ).getSingleResult();
			
			return saldos;
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			return null;
			
		}
		
	}
	
	
}
