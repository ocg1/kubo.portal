package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.PagareCollector;
import mx.com.kubo.repositories.PagareCollectorDao;

@Repository
public class PagareCollectorDaoImp implements Serializable,PagareCollectorDao {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Integer par_NumCon = 4;
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
	
	public List<PagareCollector> getPagareCollectorList( String safi_credit_id ){
	 
		try {
				
			    return em.createNamedQuery("pagareCollectorData",PagareCollector.class)
			    		.setParameter("par_CreditoID",BigInteger.valueOf(Long.parseLong(safi_credit_id)))
			    		.setParameter("par_NumCon",par_NumCon)
	    				.setParameter("par_EmpresaID",Par_EmpresaID)
	    				.setParameter("aud_Usuario",Aud_Usuario)
	    				.setParameter("aud_FechaActual",new Date())
	    				.setParameter("aud_DireccionIP",Aud_DireccionIP)
	    				.setParameter("aud_ProgramaID",Aud_ProgramaID)
	    				.setParameter("aud_Sucursal",Aud_Sucursal)
	    				.setParameter("aud_NumTransaccion",Aud_NumTransaccion)
	    				.getResultList();
			    
		}catch( PersistenceException p ){
			
			p.getMessage();
			return null;
			
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
	
	}
}
