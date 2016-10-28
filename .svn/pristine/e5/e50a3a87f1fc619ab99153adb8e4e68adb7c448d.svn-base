package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.services.AddressService;

import org.springframework.stereotype.Service;

@Service
public final class AddressServiceImp extends AddressServiceDMO
implements AddressService
{		
	public boolean add(Address newAddress) 
	{
		log.info("add.AddressServiceImp");
		
		return dao.saveAddress(newAddress);		
	}
	
	public boolean update(Address newAddress) 
	{
		log.info("update.AddressServiceImp");
		
		return dao.updateAddress(newAddress);		
	}
	
	public Address getAddressById(AddressPK pk) 
	{
		return dao.loadSelectedAddress(pk);
	}
	
	public boolean removeAddress(AddressPK addressPk) 
	{		
		return dao.removeAddress(addressPk);
	}
	
	public List<Address> getAddressList() 
	{
		return dao.loadAddressList();
	}
	
	public final Long getCoincidencias_NUMBER(int nivel_filtro, Address address)
	{
		return dao.getCoincidencias_NUMBER(nivel_filtro, address);	
	}
			
	public int getMaxAddressId(int prospectusID, int companyID)
	{
		return dao.getMaxAddressId(prospectusID, companyID);
	}
	
	public final Address getMaxAddressByBeneficiario(int prospectusID, int companyID, int beneficiarie_id)
	{
		return dao.getMaxAddressByBeneficiario(prospectusID, companyID, beneficiarie_id);
	}
	
	public Address getMaxAddressByType(int prospectusID, int companyID, int addressType)
	{
		return dao.getMaxAddressByType(prospectusID, companyID, addressType);
	}

	public Address getAdressByTypeByEmployment(int prospectusID, int companyID, int addressType, int employmentID) 
	{
		return dao.getAdressByTypeByEmployment(prospectusID, companyID, addressType, employmentID);
	}
	
	public Address getAdressByTypeByBusiness(int prospectusID, int companyID,int addressType, int businessID) 
	{
		return dao.getAdressByTypeByBusiness(prospectusID, companyID, addressType, businessID);
	}
	
	public final List<Address> getLista_coincidencias(int nivel_filtro, Address address)
	{
		List<Address> lista_address = null;
		
		lista_address = dao.getLista_coincidencias(nivel_filtro, address);			
		
		return lista_address;
	}
	
	public Address update_address(Address address) 
	{		
		return dao.update(address);
	}
}
