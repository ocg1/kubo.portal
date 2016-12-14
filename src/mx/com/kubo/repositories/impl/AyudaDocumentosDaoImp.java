package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.AyudaDocumentos;
import mx.com.kubo.repositories.AyudaDocumentosDao;

@Repository
public class AyudaDocumentosDaoImp implements AyudaDocumentosDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	public List<AyudaDocumentos> getAyudaDocumentosList( Integer dias ){
		
		try{
			
			return em.createNamedQuery("ayudaDocumentos_SP", AyudaDocumentos.class).setParameter("dias", dias).getResultList();
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
