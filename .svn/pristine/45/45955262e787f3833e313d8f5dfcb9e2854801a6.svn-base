package mx.com.kubo.registro.datos.domicilio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;

import mx.com.kubo.bean.ListNeighborhoodBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.NeighborhoodCat;

public abstract class DomicilioAMO extends DomicilioDMO 
{	
	protected void init_address_NEW() 
	{
		address_id = service_address.getMaxAddressId(prospectus_id, company_id);
		
		address    = new Address();									
		address_PK = new AddressPK();
		
		address_PK.setCompany_id(company_id);
		address_PK.setProspectus_id(prospectus_id);
		address_PK.setAddress_id(address_id);
		
		address.setAddressPK(address_PK);			
		address.setAddress_type_id(address_type_id);
		address.setCountry_id(700);
		
		save_address_OK = service_address.add(address);
		
		address = service_address.getMaxAddressByType(prospectus_id, company_id, address_type_id);
	}
	
	protected void init_address() 
	{			
		address_PK        = address.getAddressPK();				
		zipcode           = address.getZip_code();
		neighborhood_id   = address.getNeighborhood_id();
		neighborhood_text = address.getNeighborhood_text();
		street            = address.getStreet();
		address_number    = address.getAddress_number();
		apt_number        = address.getApt_number();
		mx_manzana        = address.getMx_manzana();
		mx_lote           = address.getMx_lote();
		first_reference   = address.getFirst_street_reference();
		second_reference  = address.getSecond_street_reference();
		description       = address.getDescription();		
		
		neighborhood_text_ENABLED = neighborhood_text != null ? true : false;	
		
		delegMun = null;
		townId   = null;
		estado   = null;
		stateId  = null;
		
		coverage_zone_ENABLED = true;
		
		if (zipcode != null && zipcode.length() > 0) 
		{	
			ciudad = address.getCity_text();
			
			colonias_por_codigo_postal = service_employment.getAsentamientosByCP(zipcode);
			
			if(inversionista_ENABLED)
			{
				coverage_zone_ENABLED = true;
				
			} else {
				
				if( address.getStateCat() != null )
				{
					coverage_zone_ENABLED =  address.getStateCat().getIs_enabled().equals("S");
					
				} else {
					
					coverage_zone_ENABLED =  false;
				}
			}									
			
			if(address.getLatitude()!=null & address.getLongitude()!=null)
			{
				latLong = (address.getLatitude()+","+address.getLongitude()+",Mi domicilio");
			}								
			
			if( address.getNeighborhood_id() != null )
			{
				neighborhood_id = address.getNeighborhood_id(); 
			}
						
			if( address.getTownCat() != null )
			{
				delegMun = address.getTownCat().getName();
			}
			
			if( address.getStateCat() != null )
			{
				estado = address.getStateCat().getName();
			}
			
			if( address.getState_id() != null )
			{
				stateId = address.getState_id();
			}
			
			if( address.getTown_id() != null )
			{
				townId = address.getTown_id();
			}				
		}				
	}
		
	protected void init_coverage_zone() 
	{				
		colonias_por_codigo_postal = service_employment.getAsentamientosByCP(zipcode);				
		
		for (NeighborhoodCat colonia : colonias_por_codigo_postal) 
		{
			//state_ENABLED = colonia.getDelegMunicipio().getEstados().getIs_enabled().equals("S");
			
			state_ENABLED = true;
					
//			if(state_ENABLED || inversionista_ENABLED  )
//			{
				coverage_zone_ENABLED = true;
				
				delegMun = colonia.getDelegMunicipio().getName();
				townId   = colonia.getDelegMunicipio().getTownCatPK().getTown_id();
				estado   = colonia.getDelegMunicipio().getEstados().getName();
				stateId  = colonia.getDelegMunicipio().getEstados().getStateCatPK().getState_id();
				
//			} else {
//				
//				coverage_zone_ENABLED = false;
//				
//				delegMun = null;
//				estado   = null;
//				
//				townId   = 0;					
//				stateId  = 0;					
//			}
			
			break;			
		}

		if (colonias_por_codigo_postal.size() == 1)
		{
			neighborhood_id = colonias_por_codigo_postal.get(0).getNeighborhoodCatPK().getNeighborhood_id();
			
		} else {
			
			neighborhood_id = null;
		}
	}
	
	protected void init_same_address_ENABLED() 
	{
		int counter = 0;
		
		if(zipcode != null && zipcode.equals(to_compare.getAddress().getZip_code()))
		{
			counter++;
		}
		
		if(neighborhood_text_ENABLED)
		{
			if(neighborhood_text != null && neighborhood_text.equals(to_compare.getAddress().getNeighborhood_text()))
			{
				counter++;
			}
			
		} else {
			
			if(neighborhood_id != null && neighborhood_id.equals(to_compare.getAddress().getNeighborhood_id()))
			{
				counter++;
			}
		}
		
		if(stateId != null && stateId.equals(to_compare.getAddress().getState_id()))
		{
			counter++;
		}
		
		if(townId != null && townId.equals(to_compare.getAddress().getTown_id()))
		{
			counter++;
		}
		
		
		if(counter > 3)
		{
			if(street != null && street.equals(to_compare.getAddress().getStreet()))
			{
				counter++;
			}
			
			if(address_number != null && address_number.equals(to_compare.getAddress().getAddress_number()))
			{
				counter++;
			}
			
			if(apt_number != null && apt_number.equals(to_compare.getAddress().getApt_number()))
			{
				counter++;
			}
			
			if(mx_manzana != null && mx_manzana.equals(to_compare.getAddress().getMx_manzana()))
			{
				counter++;
			}
			
			if(mx_lote != null && mx_lote.equals(to_compare.getAddress().getMx_lote()))
			{
				counter++;
			}
		}
		
		same_address_ENABLED = counter > 4 ? true : false;
	}
	
	protected void init_lista_colonias() 
	{				
		isValid = false;
		
		lista_de_colonias = new ArrayList<ListNeighborhoodBean>();
		
		if(colonias_por_codigo_postal.size() != 0)
		{
			
			colonia = null;
			
			for (NeighborhoodCat neighborhood : colonias_por_codigo_postal) 
			{
				int neighborhood_id = neighborhood.getNeighborhoodCatPK().getNeighborhood_id();
				
				colonia = new ListNeighborhoodBean(neighborhood_id, neighborhood.getName());
				
				lista_de_colonias.add(colonia);	
			}		
			
			try 
			{
				colonias_JSON = new JSONArray(lista_de_colonias.toArray(), true).toString();		
				
				isValid = true;
				
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			
		} else{
			
			try 
			{
				
				colonias_JSON = new JSONArray(lista_de_colonias.toArray(), true).toString();		
				
				isValid = false;
				
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			
		}
	}
		
	protected void persist_address() 
	{
		if (zipcode != null && zipcode.length() > 0 && coverage_zone_ENABLED) 
		{			
			address.setZip_code(zipcode);
			address.setNeighborhood_id(neighborhood_id);
			address.setState_id(stateId);			
			address.setTown_id(townId);									
		}
		
		if(neighborhood_id != null && neighborhood_id > 0)
		{			
			address.setNeighborhood_id(neighborhood_id);
			address.setNeighborhood_text(null);
		}
		
		if(neighborhood_text != null && neighborhood_text.trim().length() > 0)
		{				
			address.setNeighborhood_text(neighborhood_text);
			address.setNeighborhood_id(null);			
		} 
								
		save_address_OK = service_address.update(address);			 
	}
	
	protected void init_change_control(String afected_table, String field, String original_value, String new_value) 
	{
		change_control_ENABLED = ! new_value.equalsIgnoreCase(original_value);
		
		Calendar cd_2 = Calendar.getInstance();
		cd_2.setTime(new Date());
		
		if(change_control_ENABLED)
		{
			change_control_PK = new Change_controlPK();
			change_control    = new Change_control();
			
			change_control_PK.setProspectus_id(prospectus_id);
			change_control_PK.setCompany_id(company_id);
			
			change_control.setChange_controlPK(change_control_PK);		
			change_control.setChange_prospectus_id(change_prospectus_id);
			
			change_control.setAfected_table_type("gn_address_type");
			change_control.setAfected_table(afected_table);	
			change_control.setField(field);
			change_control.setField_type_id(address_type_id);
			
			change_control.setOriginal_value(original_value);
			change_control.setNew_value(new_value);
			
			change_control.setComments(comments);
			change_control.setIp(IP_address_client);
			change_control.setFocus_date(focus_date);
			
			change_control_OK = service_change_control.addChangeControl(change_control, prospectus_id, company_id);
		}		
		
		Calendar cd_3 = Calendar.getInstance();
		cd_3.setTime(new Date());
		
		long dif_2 = cd_3.getTimeInMillis() - cd_2.getTimeInMillis();
		
		System.out.println( "Ejecuta ChangeControl table = " + afected_table + " campo = " + field + " - prospectus_id =  " + person.getNatPerPK().getProspectus_id() + " tiempo: " + dif_2 );
		
	}
}
