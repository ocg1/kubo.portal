package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.repositories.AddressDao;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public abstract class AddressDaoDMO 
implements AddressDao
{
	protected Logger log = Logger.getLogger(getClass());
	
	protected EntityManager em = null;

	protected final int BENEFICIARIO = 6;
	protected final int NIVEL_1 = 1;
	protected final int NIVEL_2 = 2;
	protected final int OFICINA = 2;
	protected final int NEGOCIO = 3;
	protected final int EMPLEO  = 4;

    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }

	public Address loadSelectedAddress(AddressPK pk) 
	{
		log.info("FindAddress.AddressDaoImp loadSelectedAddress prospectus: "+pk.getProspectus_id());
		
		return em.find(Address.class, pk);
	}
	
	public List<Address> loadAddressList() 
	{	
		List<Address> lista_address = em.createQuery("from Address ", Address.class).getResultList();
		 
		 return lista_address;
	}
		
	@Transactional
	public boolean saveAddress(Address newAddress) 
	{				
		try
		{
			log.info("saveAddress.AddressDaoImp");
			
			em.merge(newAddress);
			
			return true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
	
	@Transactional
	public boolean updateAddress(Address newAddress) 
	{				
		try
		{
			log.info("updateAddress.AddressDaoImp");
			
			em.merge(newAddress);
			
			return true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
	
	@Transactional
	public Address update(Address address) 
	{				
		try
		{						
			address = em.merge(address);						
			
		} catch(Exception e) {
			
			address = null;
			
			e.printStackTrace();				
		}
		
		return address;
	}
		
	@Transactional
	public boolean removeAddress(AddressPK addressPk) 
	{			
		boolean remove_OK = false;
		
		 try
		 {
			 log.info("Inicia eliminar address");
			    
			 Address address = em.find(Address.class, addressPk);
			    
			if(address != null)
			{
				em.remove(address); 
				
				log.info("Termina eliminar address");
			    
				remove_OK = true;
			}
			
		 } catch (Exception e) {
			 
			 log.info("Error al eliminar address"+e.getMessage());
			 
			 remove_OK = false;
				
		 } finally {
			 
			 em.close();
		 }
		 
		 return remove_OK;
	}
}
