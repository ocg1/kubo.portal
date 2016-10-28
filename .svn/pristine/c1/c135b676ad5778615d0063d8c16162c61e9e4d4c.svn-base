package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.ContratoRepCapBenCollector;
import mx.com.kubo.model.ContratoRepCapCollector;
import mx.com.kubo.repositories.ContractCollectorDao;

@Repository
public class ContraCollectorDaoImp implements ContractCollectorDao, Serializable {
	 
	private EntityManager em = null;
		
	    @PersistenceContext
	    public void setEntityManager(EntityManager em) 
	    {
	        this.em = em;
	    }
	
	
	
	public List<ContratoRepCapCollector> getContractInvList(String safi_account_id){
		
		try {
			
		    return em.createNamedQuery("collectorContratoCaptacion",ContratoRepCapCollector.class)
			.setParameter("par_cuentaSafi",  safi_account_id)
			.setParameter("par_tipoContrato", 2)
		
			.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
public List<ContratoRepCapBenCollector> getContractBenList(String safi_account_id){
		
		try {
			
		    return em.createNamedQuery("collectorContratoCaptBeneficiaries",ContratoRepCapBenCollector.class)
			.setParameter("par_cuentaSafi",  safi_account_id)
			.setParameter("par_tipoContrato", 3)
		
			.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
	}
	
}
