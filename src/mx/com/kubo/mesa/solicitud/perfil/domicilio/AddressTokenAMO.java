package mx.com.kubo.mesa.solicitud.perfil.domicilio;

import mx.com.kubo.tools.Utilities;

public abstract class AddressTokenAMO extends AddressTokenDMO 
{
	protected void init_activity_TOKEN() 
	{
		sb = new StringBuilder();
		
		sb.append(street);
		sb.append(address_number);
		sb.append(apt_number);
		sb.append(mx_manzana);
		sb.append(mx_lote);
		sb.append(colonia);
		sb.append(town);
		sb.append(estado);
		sb.append(zip_code);
		sb.append(first_street_reference);
		sb.append(second_street_reference);
		sb.append(description);
		
		address_TOKEN = sb.toString();
		
		if(address_TOKEN.length() > 0)
		{
			address_TOKEN = Utilities.capilizeString(address_TOKEN.trim());
		}					
	}	
	
	public void init_business_antiguedad() 
	{			
		if(business.getTenure_id() != null)
		{
			auxYears = service_employment.getTenure(business.getTenure_id()); 
			
		} else if(years_since_when != null && months_since_when != null) {
			
			if(years_since_when == 0 && months_since_when == 0)
			{
				auxYears = "0 Meses.";
				
			} else {
				
				if(years_since_when > 1)
				{
					auxYears = years_since_when + " años ";
					
				} else if(years_since_when == 0) {
					
					auxYears = " ";
					
				} else {
					
					auxYears = years_since_when + " año ";
				}
				
				if(months_since_when > 1)
				{
					auxYears = auxYears + months_since_when + " meses.";
					
				} else if(months_since_when == 0) {
					
					auxYears = auxYears + months_since_when + " meses.";
					
				} else {
					
					auxYears = auxYears + months_since_when + " mes.";
				}
			}
			
		} else if(years_since_when != null) {
			
			auxYears = years_since_when > 1 ? years_since_when + " años." : years_since_when + " año.";
			
		} else if(months_since_when != null) {
			
			auxYears = months_since_when > 1 ? months_since_when+" meses." : months_since_when + " mes.";
			
		} else {
			
			auxYears = "0 Meses.";
		}
	}
	
	public void init_employ_permanencia() 
	{
		if(employment.getTenure_id() != null)
		{
			auxTenure = service_employment.getTenure(employment.getTenure_id()); 
			
		} else if(employment.getTenure() != null && employment.getTenure_month() != null) {
			
			if(employment.getTenure() == 0 && employment.getTenure_month() == 0)
			{
				auxTenure = "0 Meses.";
				
			} else {
				
				if(employment.getTenure() > 1)
				{
					auxTenure = employment.getTenure() + " años ";
					
				} else if(employment.getTenure() == 0)
				{
					auxTenure = " ";
					
				} else {
					
					auxTenure = employment.getTenure() + " año ";
				}
				
				if(employment.getTenure_month() > 1)
				{
					auxTenure = auxTenure + employment.getTenure_month() + " meses.";
					
				} else if(employment.getTenure_month() == 0) {
					
					auxTenure = auxTenure + employment.getTenure_month() + " meses.";
					
				} else {
					
					auxTenure = auxTenure + employment.getTenure_month() + " mes.";
				}
			}
			
		} else if(employment.getTenure() != null) {
			
			auxTenure = employment.getTenure() > 1 ? employment.getTenure() + " años.":employment.getTenure()+" año.";
			
		} else if(employment.getTenure_month() != null) { 
			
			auxTenure = employment.getTenure_month() > 1 ? employment.getTenure_month() + " meses.":employment.getTenure_month() + " mes.";
			
		} else {					
			
			auxTenure = "0 Meses.";
		}
	}
	
	protected void init_pais_origen_TOKEN()
	{
		sb = new StringBuilder();		
		sb.append(country);
		sb.append(zip_code);
		sb.append(address_text);
		
		address_TOKEN = Utilities.capilizeString(sb.toString());
	}
	
	public void init_address() 
	{
		address_type_id = address.getAddress_type_id();		
		
		if(address.getCountry() != null)
		{
			country = address.getCountry().getName();
		}
		
		if(address.getStreet() != null && address.getStreet().length() > 0)
		{
			street = " " + address.getStreet();
		} 
		
		if(address.getAddress_number() != null && address.getAddress_number().length() > 0)
		{
			address_number = " número " + address.getAddress_number();
		}
		
		if(address.getApt_number() != null && address.getApt_number().length() > 0)
		{
			apt_number = " número interior " + address.getApt_number();
		}
		
		if(address.getMx_manzana() != null && address.getMx_manzana().length() > 0)
		{
			mx_manzana = " manzana " + address.getMx_manzana();
		}
		
		if(address.getMx_lote() != null && address.getMx_lote().length() > 0)
		{
			mx_lote = " lote " + address.getMx_lote();
		}
		
		if(address.getNeighborhood() != null)
		{
			colonia = ", colonia " + address.getNeighborhood().getName();			
		} 
		
		if(address.getNeighborhood_text() != null) 
		{			
			colonia = ", colonia " + address.getNeighborhood_text();			
		}
		
		if(address.getTownCat() != null)
		{
			if(address.getStateCat() != null && address.getStateCat().getStateCatPK().getState_id() == 9)
			{
				town = ", delegación " + address.getTownCat().getName();
				
			} else {
				
				town = ", municipio " + address.getTownCat().getName();
			}
		}
		
		if(address.getStateCat() != null)
		{
			estado = ", " + address.getStateCat().getName();
		}
		
		if(address.getZip_code() != null && address.getZip_code().length() > 0)
		{
			zip_code = ", Código Postal " + address.getZip_code();
		}
		
		if(address.getFirst_street_reference() != null && address.getSecond_street_reference() != null)
		{
			first_street_reference  = ", entre " + address.getFirst_street_reference();
			second_street_reference = " y " + address.getSecond_street_reference();
		}
		
		if(address.getDescription() != null)
		{					
			description = " , " + address.getDescription();					
		}
		
		if(address.getAddress_text()!= null && !address.getAddress_text().isEmpty())
		{
			address_text = " , " + address.getAddress_text();
		}
	}
}
