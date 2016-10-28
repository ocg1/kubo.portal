package mx.com.kubo.listeners.registro;

import mx.com.kubo.bean.AddressBean;
import mx.com.kubo.bean.PhoneBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.Phone;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.AddressTokenIMP;

public abstract class ListenerMiPrestamoAMO extends ListenerMiPrestamoDMO
{
	protected void init_employment_address() 
	{			
		address = employment.getAddressEmploy();
		
		address_bean = new AddressBean();
		
		if(address == null)
		{
			address    = new Address();	
			address_PK = new AddressPK();
			
			address_id = service_address.getMaxAddressId(prospectus_id, company_id);
			
			address_PK.setAddress_id(address_id);		
			address_PK.setProspectus_id(prospectus_id);
			address_PK.setCompany_id(company_id);
			
			address.setAddressPK(address_PK);
			address.setEmployment_id(employment_id);
			address.setCountry_id(MEXICO);
			address.setAddress_type_id(EMPLOYMENT);
			
			saved_OK = service_address.add(address);
			
		} else {				  
			
			zipcode    = address.getZip_code();
			latitude   = address.getLatitude();
			longitud   = address.getLongitude();
			colonia_id = address.getNeighborhood_id();
			estado     = address.getStateCat();
			delegacion = address.getTownCat();
			
			address_bean.setStreet(address.getStreet());
			address_bean.setAddress_number(address.getAddress_number());
			address_bean.setApt_number(address.getApt_number());
			address_bean.setMx_manzana(address.getMx_manzana());
			address_bean.setMx_lote   (address.getMx_lote());
			address_bean.setFirst_street_reference(address.getFirst_street_reference());
			address_bean.setSecond_street_reference(address.getSecond_street_reference());
			address_bean.setDescription(address.getDescription());
			   
		   if(zipcode != null)
		   {
			   colonias_por_zipcode = service_employment.getAsentamientosByCP(zipcode);
			   
			   address_bean.setListNeighborhood(colonias_por_zipcode);
			   address_bean.setZip_code(zipcode);
			   
			   if(latitude != null && longitud != null)
			   {
				   address_bean.setLatLong(latitude + ", " + longitud + ", Mi empleo");
			   }
		   }
			   
		   if(colonia_id != null)
		   {
			   address_bean.setNeighborhood_id(colonia_id);
		   }
				   
		   if(delegacion != null && estado != null)
		   {
			   address_bean.setTown_id  (delegacion.getTownCatPK().getTown_id());
			   address_bean.setTownName (delegacion.getName());			   
			   address_bean.setStateName(estado.getName());				   
			   address_bean.setState_id (estado.getStateCatPK().getState_id());
		   }		   		  		 				 
	   } 
		
		address_bean.setAddress(address);
		
		token = new AddressTokenIMP();
		token.setResidence(tipo_vivienda);
		token.setAntiguedad(antiguedad_vivienda);
		token.setAddress(address);
		
		address_TOKEN = token.getAddress_TOKEN();
   
		if(address_CASA_TOKEN.equalsIgnoreCase(address_TOKEN))
		{
			employment_bean.setSame_address(true);
	   
		} else {
	   
			employment_bean.setSame_address(false);
		}	
						
		employment_bean.setAddressbean(address_bean);
	}
	
	protected void init_employment_phone() 
	{				
		lista_employment_phone = service_phone.getPhoneByEmploymentListByArea(prospectus_id, company_id, employment_id, area);
		   
		employment_phone_FIJO = new PhoneBean();
		employment_phone_CEL  = new PhoneBean();
				
		if(lista_employment_phone.size() > 0)
		{												   
		   for (Phone phone : lista_employment_phone) 
		   {
			   phone_type_id   = phone.getPhone_type_id();
			   phone_number    = phone.getPhone_number();
			   phone_extension = phone.getExtension();
			   
			   init_employment_phone_type();			   	
		   	}
		   
	   } else {
		   
		   employment_bean.setPhonebeanFixed(employment_phone_FIJO);
		   employment_bean.setPhonebeanCel  (employment_phone_CEL);			   
	   }
	}
	
	private void init_employment_phone_type() 
	{
	   	if(phone_type_id == EMPLOYMENT_FIJO)
	   	{					   						   		
	   		phonestr = phone_number.split("\\)");
	   		
	   		if(phonestr.length > 1)
	   		{				   					   			
	   			employment_phone_FIJO.setNumPhone(phone_number.split("\\)")[0].replace("(", "").trim() + phone_number.split("\\)")[1]);
	   			
	   		} else {
	   			
	   			employment_phone_FIJO.setNumPhone(phone_number);			   			
	   		}
	   		
	   		employment_phone_FIJO.setExtension(phone_extension);	
	   		employment_phone_FIJO.setPhone(phone);		
	   		
	   		employment_bean.setPhonebeanFixed(employment_phone_FIJO);
	   	} 
		   	
	   	if(phone_type_id == EMPLOYMENT_CELLULAR) 
	   	{	   		
	   		if(phone_number!= "")
	   		{				   			
	   			phonestr = phone_number.split("\\)");
	   			
	   			if(phonestr.length > 1)
	   			{	   				
	   				employment_phone_CEL.setNumCelPhone(phone_number.split("\\)")[0].replace("(", "").trim() + phone_number.split("\\)")[1]);
	   				
	   			} else {
	   				
	   				employment_phone_CEL.setNumCelPhone(phone_number);
	   			}
	   			
	   			employment_phone_CEL.setPhone(phone);
	   			
	   			employment_bean.setPhonebeanCel(employment_phone_CEL);
	   		}
	   	}	
	}

	protected void init_check_IN_OUT() 
	{
	   if(employment.getCheck_in() != null && employment.getCheck_out() != null)
	   {
		   employment_bean.setCheck_inH1(employment.getCheck_in().split(":")[0]);
		   employment_bean.setCheck_inM1(employment.getCheck_in().split(":")[1]);
		   
		   employment_bean.setCheck_outH1(employment.getCheck_out().split(":")[0]);
		   employment_bean.setCheck_outM1(employment.getCheck_out().split(":")[1]);
		   
	   } else {
		   
		   employment_bean.setCheck_inH1("09");
		   employment_bean.setCheck_inM1("00");
		   employment_bean.setCheck_outH1("18");
		   employment_bean.setCheck_outM1("00"); 
		   
		   employment.setCheck_in("09:00");
		   employment.setCheck_out("18:00");
	   }
	}
	
	protected void init_employment_antiguedad() 
	{
	   if(employment.getTenure() != null && employment.getTenure() > 1)
	   {
		   employment_bean.setYearOrYears("Años");
		   
	   } else {
		   
		   employment_bean.setYearOrYears("Año");
	   }
	   
	   if(employment.getTenure_month()!= null && employment.getTenure_month() > 1)
	   {
		   employment_bean.setMonthOrMonths("Meses");
		   
	   } else {
		   
		   employment_bean.setMonthOrMonths("Mes");
	   }
	}
		
	protected void init_business_address() 
	{
		address = business.getAddressBusiness();
		
		address_bean = new AddressBean();	
		
		if(address == null)
		{	
			address    = new Address();
			address_PK = new AddressPK();
			
			address_id = service_address.getMaxAddressId(prospectus_id, company_id);
			
			address_PK.setAddress_id(address_id);			
			address_PK.setProspectus_id(prospectus_id);		
			address_PK.setCompany_id(company_id);
			
			address.setAddressPK(address_PK);
			address.setBusiness_id(business_id);
			address.setCountry_id(MEXICO);
			address.setAddress_type_id(BUSINESS);
			
			saved_OK = service_address.add(address);						
			
		} else {
			
			zipcode    = address.getZip_code();
			latitude   = address.getLatitude();
			longitud   = address.getLongitude();
			colonia_id = address.getNeighborhood_id();
			estado     = address.getStateCat();
			delegacion = address.getTownCat();
			
			address_bean.setStreet(address.getStreet());
			address_bean.setAddress_number(address.getAddress_number());
			address_bean.setApt_number(address.getApt_number());
			address_bean.setMx_manzana(address.getMx_manzana());
			address_bean.setMx_lote   (address.getMx_lote());
			address_bean.setFirst_street_reference(address.getFirst_street_reference());
			address_bean.setSecond_street_reference(address.getSecond_street_reference());
			address_bean.setDescription(address.getDescription());
											
			if(zipcode != null)
			{
				colonias_por_zipcode = service_employment.getAsentamientosByCP(zipcode);
				
				address_bean.setListNeighborhood(colonias_por_zipcode);
				address_bean.setZip_code(zipcode);	
				 
				 if(latitude != null && longitud != null)
				 {
					 address_bean.setLatLong(latitude + ", " + longitud + ", Mi negocio");
				 }
			}
			
		   if(colonia_id != null)
		   {
			   address_bean.setNeighborhood_id(colonia_id);
		   }
			   		   
		   if(delegacion != null && estado != null)
		   {
			   address_bean.setTown_id  (delegacion.getTownCatPK().getTown_id());
			   address_bean.setTownName (delegacion.getName());			   
			   address_bean.setStateName(estado.getName());				   
			   address_bean.setState_id (estado.getStateCatPK().getState_id());
		   }		   			   		   		   		   		   	 
		}
		
		address_bean.setAddress(address);
		
		token = new AddressTokenIMP();
		token.setResidence(tipo_vivienda);
		token.setAntiguedad(antiguedad_vivienda);
		token.setAddress(address);
		
		address_TOKEN = token.getAddress_TOKEN();
		   
		if(address_CASA_TOKEN.equalsIgnoreCase(address_TOKEN))
		{
			business_bean.setSame_address(true);
			   
		} else {
			   
			business_bean.setSame_address(false);
		}
						
		business_bean.setAddressbean(address_bean);
	}
	
	protected void init_business_phone() 
	{
		lista_business_phone = service_phone.getPhoneByBusinessListByArea(prospectus_id, company_id, business_id, area);
		
		business_phone_CEL  = new PhoneBean();
		business_phone_FIJO = new PhoneBean();	
		
		if(lista_business_phone.size() > 0)
		{															
		   for (Phone phone : lista_business_phone) 
		   {
			   this.phone = phone;
			   
			   phone_type_id = phone.getPhone_type_id();
			   phone_number  = phone.getPhone_number();
			   
			   init_business_phone_type();
		   }
		   
	   } else {
		   
		   business_bean.setPhonebeanFixed(business_phone_FIJO);
		   business_bean.setPhonebeanCel  (business_phone_CEL);				   
	   }
	}
	
	private void init_business_phone_type() 
	{
	   	if(phone_type_id == BUSINESS_FIJO)
	   	{	
	   		String[] phonestr = phone_number.split("\\)");
	   		
	   		if(phonestr.length > 1)
	   		{				   			
	   			business_phone_FIJO.setNumPhone(phone_number.split("\\)")[0].replace("(", "").trim() + phone_number.split("\\)")[1]);
	   			
	   		} else {
	   			
	   			business_phone_FIJO.setNumPhone(phone_number);
	   		}
	   		
	   		business_phone_FIJO.setPhone(phone);
	   		
	   		business_bean.setPhonebeanFixed(business_phone_FIJO);	   		
	   	}
	   	
	   	if(phone_type_id == BUSINESS_CELLULAR) 
	   	{	   	
	   		if(phone_number != "")
	   		{			   			
	   			String[] phonestr = phone_number.split("\\)");
		   		
		   		if( phonestr.length > 1 )
		   		{			   						
		   			business_phone_CEL.setNumCelPhone(phone_number.split("\\)")[0].replace("(", "").trim() + phone_number.split("\\)")[1]);
			   		
		   		} else {
		   			
		   			business_phone_CEL.setNumCelPhone(phone_number);
		   		}
			   		
		   		business_phone_CEL.setPhone(phone);
		   		
		   		business_bean.setPhonebeanCel(business_phone_CEL);
	   		}
	   	}
	}
	
	protected void init_business_antiguedad() 
	{
		if(business.getYears_since_when() != null && business.getYears_since_when() > 1)
		{
			business_bean.setYearOrYears("Años");
			
		} else {
			
			business_bean.setYearOrYears("Año");
		}
		
		if(business.getMonths_since_when()!=null && business.getMonths_since_when() > 1)
		{
			business_bean.setMonthOrMonths("Meses");
			
		} else {
			
			business_bean.setMonthOrMonths("Mes");
		}
	}
	
	protected void asignar_mismo_domicilio(int address_type_id)
	{										
		address_PK = address.getAddressPK();
		
		if(address_PK != null)
		{		
			address_CASA.setAddressPK(address_PK);
			address_CASA.setAddress_type_id(address_type_id);
			address_CASA.setEmployment_id(employment_id);
			address_CASA.setBusiness_id(business_id);
			
			saved_OK = service_address.update(address_CASA);							
		}
	}
	
	protected void clear_address() 
	{				
		address.setAddress_number(null);
		address.setAddress_text(null);
		address.setApt_number(null);
		address.setCity_text(null);		
		address.setDescription(null);
		address.setFirst_street_reference(null);
		address.setSecond_street_reference(null);
		address.setFloor(null);
		address.setLatitude(null);
		address.setLongitude(null);
		address.setMx_lote(null);
		address.setMx_manzana(null);
		address.setNeighborhood(null);
		address.setNeighborhood_id(null);
		address.setNeighborhood_text(null);
		address.setState_id(null);
		address.setStateCat(null);
		address.setStreet(null);
		address.setTown_id(null);
		address.setTownCat(null);
		address.setZip_code(null);
		
		saved_OK = service_address.update(address);
	}
}
