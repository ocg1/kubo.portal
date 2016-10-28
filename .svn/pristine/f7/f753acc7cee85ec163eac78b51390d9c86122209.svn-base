package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.ContratoCreditoCollector;
import mx.com.kubo.repositories.ContratoCreditoCollectorDao;

@Repository
public class ContratoCreditoCollectorDaoImp implements Serializable, ContratoCreditoCollectorDao {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private final Integer TipoContrat = 1;
	private final Integer Par_EmpresaID = 1;
	private final Integer Aud_Usuario	 = 1;
	private final String Aud_DireccionIP = "192.0.0.0";
	private final String Aud_ProgramaID = "kubofinanciero.generaContratos";
	private final Integer Aud_Sucursal	 = 1;
	private final Integer Aud_NumTransaccion  = 0;
	
	
	protected EntityManager em = null;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
    public ContratoCreditoCollector getContratoCreditoCollectorByCreditId( String safi_credit_id ){
    	
    	
    	String query2 = "SELECT ValorCAT from microfin.CREDITOS where CreditoID = ?";
		
    	Double cat = null;
    	
		try
		{
			
			BigDecimal cat2  = (BigDecimal)em.createNativeQuery(query2).setParameter(1, BigInteger.valueOf(Long.parseLong(safi_credit_id))).getSingleResult();
			
			cat = cat2.doubleValue();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			System.out.println( "Error en la carga del cat" );
			return null;
			
		}
    	
		try {
			
		    return em.createNamedQuery("collectorContratoCredito",ContratoCreditoCollector.class)
		    		.setParameter("par_CreditoID",BigInteger.valueOf(Long.parseLong(safi_credit_id)))
		    		.setParameter("par_CAT",cat)
		    		.setParameter("par_TipoContrat",TipoContrat)
		    		.setParameter("par_EmpresaID",Par_EmpresaID)
		    		.setParameter("aud_Usuario",Aud_Usuario)
		    		.setParameter("aud_FechaActual",new Date())
		    		.setParameter("aud_DireccionIP",Aud_DireccionIP)
		    		.setParameter("aud_ProgramaID",Aud_ProgramaID)
		    		.setParameter("aud_Sucursal",Aud_Sucursal)
		    		.setParameter("aud_NumTransaccion",Aud_NumTransaccion)
				.getSingleResult();
			
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println( "Error en la carga de los datos del contrato" );
			return null;
			
		}
    	
    }

}

