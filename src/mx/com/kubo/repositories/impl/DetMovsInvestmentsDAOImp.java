package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.DetalleMovsInvestments;
import mx.com.kubo.repositories.DetMovsInvestmentsDAO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class DetMovsInvestmentsDAOImp implements DetMovsInvestmentsDAO,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String tipoDeConsulta = "M";
	private static final String fechaDeInicio  = "1900-01-01";
	private static final String fechaDeFinal   = "1900-01-01";
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Override
	public List<DetalleMovsInvestments> getMovimientosInvestments(Integer clienteID, Integer cuentaAhoID, char tipoConsulta, 
																  String fechaInicio,  String fechaFinal, Integer mes,	Integer Anio) {
    	
    	try{
    		log.info("saveAccess.DetalleMovsImp");
    		
    		@SuppressWarnings("unchecked")
    		List<DetalleMovsInvestments> menu =   em.createNamedQuery("getDetalleMovimientos",DetalleMovsInvestments.class)
					.setParameter("clienteID", clienteID)
					.setParameter("cuentaAhoID",cuentaAhoID )
					.setParameter("tipoConsulta",tipoDeConsulta )
					.setParameter("fechaInicio",fechaDeInicio )
					.setParameter("fechaFin",fechaDeFinal )
					.setParameter("mes",mes )
					.setParameter("anio",Anio )
					.getResultList();
    		return menu;
    	}catch(Exception e){
    		e.printStackTrace();
    	}

		/*if(id == null)
			return null;
		else
			return em.find(Access.class,id);
		*/
    	
    	return null;
	}
    
}