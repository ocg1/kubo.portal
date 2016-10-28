package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;

public interface AddressService 
{
	Address getAddressById(AddressPK pk);
	
	Address getMaxAddressByType        (int prospectusID, int companyID, int addressType);	
	Address getAdressByTypeByEmployment(int prospectusID, int companyID, int addressType, int employmentID);
	Address getAdressByTypeByBusiness  (int prospectusID, int companyID, int addressType, int businessID);
	Address getMaxAddressByBeneficiario(int prospectusID, int companyID, int beneficiarie_id);
	
	Address update_address(Address address);	
	
	
	List<Address> getAddressList();
	List<Address> getLista_coincidencias(int nivel_filtro, Address address);
		
	Long getCoincidencias_NUMBER(int nivel_filtro, Address address);
	
	int getMaxAddressId(int prospectusID, int companyID);
	
	boolean add   (Address address);
	boolean update(Address address); 
	boolean removeAddress(AddressPK addressPk);		
}
