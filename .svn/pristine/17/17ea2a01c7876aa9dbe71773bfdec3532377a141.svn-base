package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.TypeCat;
import mx.com.kubo.repositories.TypeDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class TypeDaoImp implements TypeDao {
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
	  /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
	@Override
	public List<TypeCat> loadTypeList() {
		List<TypeCat> type=em.createQuery("from TypeCat",TypeCat.class).getResultList();
		return type;
	}
	
	@Override
	public List<TypeCat> loadTypeListBySelectable() {
		List<TypeCat> type=em.createQuery("from TypeCat where selectable = 'S'",TypeCat.class).getResultList();
		return type;
	}

}
