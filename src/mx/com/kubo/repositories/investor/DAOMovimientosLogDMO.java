package mx.com.kubo.repositories.investor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mx.com.kubo.model.investor.MovimientosLog;

public abstract class DAOMovimientosLogDMO 
{
	protected EntityManager em = null;
	
	protected List<MovimientosLog> lista_movimientos;
	protected TypedQuery<MovimientosLog> typed_lista_movimientos;
	
	protected final String query_MAX_log_id;
	protected final String query_lista_movimientos;
	
	protected Integer MAX_log_id; 
	
	protected boolean registrar_OK;
	
	protected DAOMovimientosLogDMO()
	{
		query_MAX_log_id = "select MAX(m.pk.log_id) from MovimientosLog m";
		query_lista_movimientos = "from MovimientosLog m where m.pk.prospectus_id = ? and m.pk.company_id = ?" ;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) 
	{		 
		this.em = em;	      
	}
	
	public final Integer getLog_id() 
	{								
		MAX_log_id = (Integer) em.createQuery(query_MAX_log_id).getSingleResult();
		
		if(MAX_log_id != null)
		{					
			MAX_log_id++;
			
		} else {
			
			MAX_log_id = 1;
		}
		
		return MAX_log_id;
	}
	
	public List<MovimientosLog> getLista_movimientos(int prospectus_id, int company_id) 
	{
		try
		{
			lista_movimientos = null;
			
			typed_lista_movimientos = em.createQuery(query_lista_movimientos, MovimientosLog.class);
			typed_lista_movimientos.setParameter(1, prospectus_id);
			typed_lista_movimientos.setParameter(2, company_id);
			
			lista_movimientos = typed_lista_movimientos.getResultList();
			
		} catch(Exception e) {
			
			e.printStackTrace();		  
		}
		
		return lista_movimientos;
	}
}
