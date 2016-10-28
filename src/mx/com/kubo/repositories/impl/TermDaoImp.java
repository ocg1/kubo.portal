package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Term;
import mx.com.kubo.repositories.TermDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TermDaoImp implements TermDao{
	
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
	public Term loadSelectedTerm(int id) {
		return em.find(Term.class,id);
	}

	@Transactional
	@Override
	public void saveTerm(Term newTerm) {
		log.info("saveTerm.TermDaoImp");
		em.persist(newTerm);
	}

	@Override
	public List<Term> loadTermList() {
		List<Term> term = em.createQuery(
			    "from Term ", Term.class)
			    .getResultList();
		 return term;
	}
    
    

}
