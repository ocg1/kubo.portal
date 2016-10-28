package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.AddressType;
import mx.com.kubo.model.AddressTypePK;
import mx.com.kubo.repositories.AddressTypeDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AddressTypeDaoImp implements AddressTypeDao {
	
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
	public AddressType loadSelectedAddressType(AddressTypePK pk) {
		 
		 return em.find(AddressType.class,pk);
	}

	@Transactional
	@Override
	public void saveAddressType(AddressType newAddressType) {
		log.info("saveAddressType.AddressTypeDaoImp");
		em.persist(newAddressType);
	}

	@Override
	public List<AddressType> loadAddressTypeList() {
		List<AddressType> addresstype = em.createQuery(
			    "from AddressType ", AddressType.class)
			    .getResultList();
		 return addresstype;
	}
    
    

}
