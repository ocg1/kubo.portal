package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Role;
import mx.com.kubo.repositories.RoleDao;

import org.springframework.stereotype.Repository;


@Repository
public class RoleDaoImp implements RoleDao {
	
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Override
	public List<Role> getLstRoles(){
		return em.createQuery("from Role",Role.class).getResultList();
	}
	
}
