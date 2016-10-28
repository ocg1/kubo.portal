package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.ClientesEnMora;
import mx.com.kubo.repositories.ClientesEnMoraDao;

@Repository
public class ClientesEnMoraDaoImp implements Serializable,ClientesEnMoraDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Override
	public List<ClientesEnMora> getClientesEnMora( Integer event_id ){
		

		try{
			
			return em.createNamedQuery("cobranzaClientesMoraQuery", ClientesEnMora.class).setParameter("event_id", event_id).getResultList();
			
		}catch( Exception e ){
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public List<ClientesEnMora> getClientesEnMoraByProspect( int prospectus_id ){
		

		try{
			
			return em.createNamedQuery("cobranzaClientesMoraProspectoQuery", ClientesEnMora.class).setParameter("prospectus_id", prospectus_id).getResultList();
			
		}catch( Exception e ){
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
}
