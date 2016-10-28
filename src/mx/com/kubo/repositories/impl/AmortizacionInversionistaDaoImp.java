package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.AmortizacionInversionista;
import mx.com.kubo.repositories.AmortizacionInversionistaDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class AmortizacionInversionistaDaoImp implements Serializable, AmortizacionInversionistaDao{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
	        this.em = em;
	}
	    
	@Override
	public List<AmortizacionInversionista> getAmortizacionInversionistaListByIdInver(Integer id){
		String query = "from AmortizacionInversionista where pk.fondeoKuboID = ?";
		try{
			List<AmortizacionInversionista> lst = em.createQuery(query,AmortizacionInversionista.class).setParameter(1, id).getResultList();
			return lst;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
