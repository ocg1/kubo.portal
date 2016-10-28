package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;

public interface AddressDao 
{
	Address getMaxAddressByType        (int prospectusID, int companyID, int addressType);
	Address getAdressByTypeByEmployment(int prospectusID, int companyID, int addressType, int employmentID);
	Address getAdressByTypeByBusiness  (int prospectusID, int companyID, int addressType, int businessID);
	Address getMaxAddressByBeneficiario(int prospectusID, int companyID, int beneficiarie_id);
	
	Address loadSelectedAddress(AddressPK pk);
	Address update(Address address); 
	
	List<Address> loadAddressList();
	List<Address> getLista_coincidencias(int nivel_filtro, Address address);
	
	Long getCoincidencias_NUMBER(int nivel_filtro, Address address);
	
	int getMaxAddressId(int prospectusID, int companyID);
	
	boolean removeAddress(AddressPK addressPk);
	boolean saveAddress  (Address newAddress);
	boolean updateAddress(Address newAddress);
}
