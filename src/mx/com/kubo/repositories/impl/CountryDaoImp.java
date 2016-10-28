package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Country;
import mx.com.kubo.model.CountryPK;
import mx.com.kubo.repositories.CountryDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CountryDaoImp implements CountryDao{
	
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
	public Country loadSelectedCountry(CountryPK pk) {
		return em.find(Country.class, pk);
	}

	@Transactional
	@Override
	public void saveCountry(Country newCountry) {
		log.info("saveCountry.CountryDaoImp");
		em.persist(newCountry);		
	}

	@Override
	public List<Country> loadCountryList() {
		List<Country> country=em.createQuery("from Country order by name",Country.class).getResultList();
		return country;
	}
    
    

}
