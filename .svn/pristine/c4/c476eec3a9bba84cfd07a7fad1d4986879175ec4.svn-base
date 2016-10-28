package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Company;
import mx.com.kubo.repositories.CompanyDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDaoImp implements CompanyDao {
	
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
	public Company loadSelectedCompany(int id) {
		return em.find(Company.class,id);
	}

	@Override
	public void saveCompany(Company newCompany) {
		log.info("saveCompany.CompanyDaoImp");
		String query="select MAX(c.company_id) from Company c";
		Integer id=0;
		id=(Integer) em.createQuery(query).getSingleResult();	
		id++;
		newCompany.setCompany_id(id);	
		em.persist(newCompany);
	}

}
