package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.RoleAccess;
import mx.com.kubo.repositories.RoleAccessDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoleAccessDaoImp implements Serializable, RoleAccessDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
    @Override
	public List<RoleAccess> getAccessListByRole(int role_id, int company_id){
		try{
		
			return em.createQuery("from RoleAccess where pk.role_id = ? and pk.company_id = ?",RoleAccess.class)
						.setParameter(1, role_id)
						.setParameter(2, company_id)
						.getResultList();
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
    
    @Override
    @Transactional
    public boolean deleteAndSaveRoleAccess( List<RoleAccess> roleaccess, int company, int role){
    	
    	List<RoleAccess> lst = loadRoleAccessList();
		  for(RoleAccess rf : lst){
			  if(rf.getPk().getRole_id() == role){
				  em.remove( rf);
			  }
		  }
		  
		  for(RoleAccess thisrole : roleaccess){
			  if(thisrole != null){
				  
				  em.persist(thisrole);
				  
			  }
		  }
		  
		
		return true;
    	
    }
    
	  public List<RoleAccess> loadRoleAccessList(){
		  
		  List<RoleAccess> roleaccesslist=em.createQuery("from RoleAccess",RoleAccess.class).getResultList();
		  return roleaccesslist;
			
	  }

}
