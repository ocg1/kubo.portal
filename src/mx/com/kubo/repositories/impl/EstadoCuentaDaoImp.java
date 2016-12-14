package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import mx.com.kubo.model.TSafiCreditosMovs;
import mx.com.kubo.model.TSafiCuentasAhoMovDep;
import mx.com.kubo.model.TSafiPagosCuota;
import mx.com.kubo.model.TSafiPosicionInt;
import mx.com.kubo.repositories.EstadoCuentaDao;

@Repository
public class EstadoCuentaDaoImp implements EstadoCuentaDao, Serializable {
	
	
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
	

    public List<TSafiPosicionInt>	getTSafiPosicionInt( Integer prospectus_id){
    	
    	try{
    		
    		String query = " from TSafiPosicionInt where prospectoIdExt = ? order by pk.creditoId desc  ";
    		
    		return em.createQuery(query,TSafiPosicionInt.class ).setParameter(1, prospectus_id).getResultList();
    		
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return null;
    		
    	}
    	
    }
    
    public List<TSafiPagosCuota> getTSafiPagosCuota( Integer prospectus_id ){
    	
    	try{
    		
    		String query = " from TSafiPagosCuota where prospectoIdExt = ? order by pk.creditoId desc , pk.amortizacionId ";
    		
    		
    		
    		return em.createQuery(query,TSafiPagosCuota.class ).setParameter(1, prospectus_id).getResultList();
    		
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return null;
    		
    	}
    	
    }
    
    public List<TSafiCreditosMovs> getTSafiCreditosMovs( Integer crdt, Integer amrt, Integer prospectus_id){
    	
    	try{
    		
    		if( crdt == null && amrt == null ){
    			

	    		String query = " from TSafiCreditosMovs where  prospectoIdExt = ? order by creditoId desc , amorticreId, transaccion ";
	    		
	    		return em.createQuery(query,TSafiCreditosMovs.class ).setParameter(1, prospectus_id).getResultList();
		
    			
    		}else{
    		
		    		String query = " from TSafiCreditosMovs where creditoId = ? and amorticreId = ? and prospectoIdExt = ?  order by CREDITOID desc , AMORTICREID, TRANSACCION ";
		    		
		    		return em.createQuery(query,TSafiCreditosMovs.class ).setParameter(1, crdt).setParameter(2, amrt).setParameter(3, prospectus_id).getResultList();
    		
    		}
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return null;
    		
    	}
    	
    }
    
    public List<TSafiCuentasAhoMovDep> getTSafiCuentasAhoMovDep( Integer cuentaID ){
    	
    	try{
    		
    		String query = " from TSafiCuentasAhoMovDep where cuentaAhoId = ? ";
    		
    		return em.createQuery(query,TSafiCuentasAhoMovDep.class ).setParameter(1, cuentaID).getResultList();
    		
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return null;
    		
    	}
    	
    	
    }
    
    public Calendar getFechaCorte(){
    	EntityManager entityManager=em.getEntityManagerFactory().createEntityManager();
    	Session session = (Session) entityManager.unwrap(Session.class);
		try{
			
			
			
			Criteria crit =  session.createCriteria(TSafiCreditosMovs.class);
			
			crit.setProjection(Projections.max("fechaAplicacion"));
			
			@SuppressWarnings("unchecked")
			List<Date> lst = (List<Date>)crit.list();
			
			if( lst != null && lst.size() >0  ){
				
				Date t = (lst.get(0));
				
				Calendar c = Calendar.getInstance();
				c.setTime(t);
				
				return c;
				
			}else{
				
				return null;
			}
			
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			return null ;
		}
    }
    
}
