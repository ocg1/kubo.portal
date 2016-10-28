package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.CreCertSegrepCollector;
import mx.com.kubo.repositories.CreCertSegrepCollectorDao;


@Repository
public class CreCertSegrepCollectorDaoImp implements Serializable, CreCertSegrepCollectorDao  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected EntityManager em = null;
	
	private final Integer par_NumCon = 1;
	private final String  par_NomInst = "";
	private final Integer Par_EmpresaID = 1;
	private final Integer Aud_Usuario	 = 1;
	private final String  Aud_DireccionIP = "192.0.0.0";
	private final String  Aud_ProgramaID = "kubofinanciero.generaContratos";
	private final Integer Aud_Sucursal	 = 1;
	private final Integer Aud_NumTransaccion  = 0;

    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
    public CreCertSegrepCollector getCreCertSegrep( String safi_credit_id ){
    	
    	 return em.createNamedQuery("creCertSegrepCollectorData",CreCertSegrepCollector.class)
		    		.setParameter("par_CreditoID",BigInteger.valueOf(Long.parseLong(safi_credit_id)))
					.setParameter("par_NomInstitucion",par_NomInst)
					.setParameter("par_NumCon",par_NumCon)
					.setParameter("par_EmpresaID",Par_EmpresaID)
					.setParameter("aud_Usuario",Aud_Usuario)
					.setParameter("aud_FechaActual",new Date())
					.setParameter("aud_DireccionIP",Aud_DireccionIP)
					.setParameter("aud_ProgramaID",Aud_ProgramaID)
					.setParameter("aud_Sucursal",Aud_Sucursal)
					.setParameter("aud_NumTransaccion",Aud_NumTransaccion).
					getSingleResult();
    	
    }
	
}
