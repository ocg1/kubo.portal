package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.InactiveAccount;
import mx.com.kubo.repositories.InactiveAccountDao;

@Repository
public class InactiveAccountDaoImp implements Serializable, InactiveAccountDao {

	private static final long serialVersionUID = 1L;
	//	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
	  /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Override
	public List<InactiveAccount> getInactiveAccountList(){
		
		String query = "from InactiveAccount";
		
		List<InactiveAccount> lst = null;
		
		try{
			
			lst = em.createQuery(query,InactiveAccount.class).getResultList();
		
		}catch(Exception e){
		
			e.printStackTrace();
			lst = null;
		}
		
		return lst;
		
	}
	
}
