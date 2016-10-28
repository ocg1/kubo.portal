package mx.com.kubo.repositories.investor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mx.com.kubo.model.investor.MotivosCancelacion;

import org.springframework.stereotype.Repository;

@Repository("dao_movimientos")
public class DAOMovimientosIMP 
implements DAOMovimientosIMO
{
	private EntityManager em = null;
	
	private TypedQuery<MotivosCancelacion> typed_lista_motivos;
	
	private List<MotivosCancelacion> lista_motivos;	
	
	private String query_lista_motivos = "from MotivosCancelacion m where m.pk.product_type_id = ?" ;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) 
	{		 
		this.em = em;	      
	}
	
	public final List<MotivosCancelacion> getLista_motivos_cancelacion(int product_type_id)
	{
		try
		{			 
			lista_motivos = null;
			
			typed_lista_motivos = em.createQuery(query_lista_motivos, MotivosCancelacion.class);
			
			typed_lista_motivos.setParameter(1, product_type_id);
	  
			lista_motivos = typed_lista_motivos.getResultList();		  
	  
		} catch(Exception e) {
			
			e.printStackTrace();		  
		}
						
		return lista_motivos;
	}
}
