package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import mx.com.kubo.model.DetSaldosKuboGlobal;
import mx.com.kubo.repositories.DetSaldosKuboGlobalDAO;

public class DetSaldosKuboGlobalDAOImp implements DetSaldosKuboGlobalDAO, Serializable{
	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;

	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Override
	public DetSaldosKuboGlobal consultaSaldoKuboGlobal(Integer clienteID, Integer cuentaAhoID, 
													   char tipoConsulta, String fechaInicio, String fechaFin, int mes, int anio){
		try{
			log.info("saveAccess.DetSaldosKuboGlobal");
			
			@SuppressWarnings("unchecked")
			DetSaldosKuboGlobal detSaldosKuboGlobalBean = /*(DetSaldosKuboGlobal)*/ em.createNamedQuery("getDetSaldoskuboGlo",DetSaldosKuboGlobal.class)
						.setParameter("clienteID", clienteID)
						.setParameter("cuentaAhoID", cuentaAhoID)
						.setParameter("tipoConsulta", tipoConsulta)
						.setParameter("fechaInicio", fechaInicio)
						.setParameter("fechaFin", fechaFin)
						.setParameter("mes",mes)
						.setParameter("anio", anio).getSingleResult();
			
			return detSaldosKuboGlobalBean;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
	