package mx.com.kubo.repositories.catalogos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.catalogos.DependantsNumber;

import org.springframework.stereotype.Repository;

@Repository("dao_dependants_number")
public class DAODependantsNumberIMP
implements DAODependantsNumber
{
	protected EntityManager em = null;
	
	List<DependantsNumber> catalogo;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
    public List<DependantsNumber> getLista_dependants_number()
    {
    	catalogo = em.createQuery("from DependantsNumber ", DependantsNumber.class).getResultList();
    	
    	return catalogo;
    }
}
