package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.CobranzaPreventiva;
import mx.com.kubo.repositories.CobranzaPreventivaDao;

@Repository
public class CobranzaPreventivaDaoImp implements Serializable,CobranzaPreventivaDao {

	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	public List<CobranzaPreventiva> getCobranzaPreventivaLst( String fecha ){
		
		try{
			
			return em.createNamedQuery("cobranzaPreventivaQuery", CobranzaPreventiva.class).setParameter("fecha", fecha).getResultList();
			
		}catch( Exception e ){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public CobranzaPreventiva getCobranzaPreventivaProspecto( String fecha, Integer prospectus_id ){
		
		try{
			
			List<CobranzaPreventiva> cp = em.createNamedQuery("cobranzaPreventivaQuery", CobranzaPreventiva.class).setParameter("fecha", fecha).setParameter("prospectus_id", prospectus_id).getResultList();
			
			if( cp != null && cp.size() > 0 ){
				
				return cp.get(0);
				
			}else{
				
				return null;
				
			}
			
		}catch( Exception e ){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
