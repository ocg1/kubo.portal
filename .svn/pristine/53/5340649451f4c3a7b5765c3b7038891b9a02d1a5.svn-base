package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.repositories.EstadoCuentaDatosDao;

@Repository
public class EstadoCuentaDatosDaoImp implements EstadoCuentaDatosDao, Serializable {

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
	
	public Double getMontoExigible( String safi_credit_id ){
		
		String hql = "select microfin.KFUNCIONEXIGIBLE( '" + safi_credit_id + "' )";
		
		BigDecimal list =(BigDecimal) em.createNativeQuery(hql).getSingleResult();
		
		
		Double exiglible = (list == null) ? 0D : list.doubleValue() ;
		
		return exiglible ;
	}
	
	public Double getMontoParaLiquidar( String safi_credit_id ){
		
		String hql = "select microfin.FUNCIONTOTDEUDACRE( '" + safi_credit_id + "' )";
		
		BigDecimal list =(BigDecimal) em.createNativeQuery(hql).getSingleResult();
		
		
		Double exiglible = (list == null) ? 0D : list.doubleValue() ;
		
		return exiglible ;
		
	}
	
}
