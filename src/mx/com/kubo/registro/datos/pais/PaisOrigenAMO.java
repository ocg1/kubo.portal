package mx.com.kubo.registro.datos.pais;

import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;

public abstract class PaisOrigenAMO extends PaisOrigenDMO
{
	protected void init_address()
	{		
		address = service_address.getMaxAddressByType(prospectus_id, company_id, PAIS_DE_ORIGEN);
		
		if(address != null)
		{
			codigo_postal = address.getZip_code();
			address_text  = address.getAddress_text();
			ciudad        = address.getCity_text();
			
			setCountry_id(address.getCountry_id());
						
			address_ENABLED = true;
			
		} else {
			
			address = new Address();
			
			codigo_postal = null;
			address_text  = null;
			ciudad        = null;
			pais          = null;
			
			country_id = 0;
			
			address_ENABLED = false;
		}
	}
	
	protected void save_address()
	{
		address.setZip_code(codigo_postal);
		address.setCity_text(ciudad);
		address.setAddress_text(address_text);
		
		if(address_ENABLED)
		{
			save_address_OK = service_address.update(address);
			
		} else {
			
			address_id = service_address.getMaxAddressId(prospectus_id, company_id);
			
			address_PK = new AddressPK();
			address_PK.setAddress_id(address_id);
			address_PK.setCompany_id(company_id);
			address_PK.setProspectus_id(prospectus_id);
			
			address.setAddressPK(address_PK);
			address.setAddress_type_id(PAIS_DE_ORIGEN);
			address.setCountry_id(country_id);
			
			save_address_OK = service_address.add(address);
			
			address_ENABLED = true;			
		}
	}
}
