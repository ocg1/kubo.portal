package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Function;
import mx.com.kubo.repositories.FunctionDao;

import org.springframework.stereotype.Repository;

@Repository
public class FunctionDaoImp  implements FunctionDao {
		
		private EntityManager em = null;

	    /**
	     * Sets the entity manager.
	     */
		
	    @PersistenceContext
	    public void setEntityManager(EntityManager em) {
	        this.em = em;
	    }
		
		@Override
		public List<Function> getLstFunction(){
			
			return em.createQuery("from Function",Function.class).getResultList();
			
		}
		
		@Override
		public List<Function> getLstFunctionByRole( int role, int company_id ){
			
			return em.createQuery("from Function where pk.role_id = ? and company_id = ?  ",Function.class).setParameter(1, role).setParameter(2, company_id).getResultList();
			
		}
	
}
