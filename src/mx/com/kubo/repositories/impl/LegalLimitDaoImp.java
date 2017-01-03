package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.LegalLimit;
import mx.com.kubo.model.LegalLimitPK;
import mx.com.kubo.repositories.LegalLimitDao;

@Repository
public class LegalLimitDaoImp implements Serializable , LegalLimitDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	public LegalLimit getLegalLimitByPK( LegalLimitPK lpk ){
	
		try{
			
			String query = "from LegalLimit where pk.company_id = ? and pk.legal_limit_id = ? ";
			
			return em.createQuery(query,LegalLimit.class)
						.setParameter(1, lpk.getCompany_id())
						.setParameter(2, lpk.getLegal_limit_id())
						.getSingleResult();
			
		}catch( Exception e ){
			
			e.printStackTrace();
			return null;
			
			
		}
		
	}
	
}
