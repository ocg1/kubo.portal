package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.TableroNormativoDetallado;
import mx.com.kubo.model.TableroNormativoResumen;
import mx.com.kubo.repositories.TableroNormativoDao;

@Repository
public class TableroNormativoDaoImp implements Serializable,TableroNormativoDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	public TableroNormativoResumen getResumenTableroNormativo(){
		
		try{
			
			List<TableroNormativoResumen> lst = em.createNamedQuery("ResumenTableroNormativo", TableroNormativoResumen.class).getResultList();
			
			if( lst != null && lst.size() > 0 ){
				
				return lst.get(0);
				
			}else{
				
				return null;
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public TableroNormativoDetallado getDetalleTableroNormativo( Integer proyect_loan_id ){
		
		try{
			
			List<TableroNormativoDetallado> lst = em.createNamedQuery("DetalleTableroNormativo", TableroNormativoDetallado.class).setParameter("param_proyect_loan_id", proyect_loan_id).getResultList();
			
			if( lst != null && lst.size() > 0 ){
				
				return lst.get(0);
				
			}else{
				
				return null;
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
