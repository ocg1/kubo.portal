package mx.com.kubo.registro.beneficiarios;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.AddressTokenIMP;

public abstract class DomicilioAMO extends DomicilioDMO 
{		
	protected void init_field_index() 
	{
		switch(field_index)
		{
			case NEIGHBORHOOD_TEXT:
				address_bean.setNeighborhood_text(neighborhood_text);
				
				original_value = address.getNeighborhood_text() != null ? address.getNeighborhood_text() : "";	
				
				init_change_control("gn_address", "neighborhood_text", original_value, neighborhood_text);
				
				neighborhood_id = 0;
				
				original_value = address.getNeighborhood_id() != null ? address.getNeighborhood_id() + "" : "";
				
				init_change_control("gn_address", "neighborhood_id", original_value, "0");
				
				if(change_control_OK)
				{
					address.setNeighborhood_id(neighborhood_id);
					address.setNeighborhood_text(neighborhood_text);	
				}
			break;
			
			case STREET:
				address_bean.setStreet(street);							
				
				original_value = address.getStreet() != null ? address.getStreet() + "" : "";
				
				new_value = street != null ? street : "";
				
				init_change_control("gn_address", "street", original_value, new_value);
								
				if(change_control_OK)
				{
					address.setStreet(street);		
				}																								
			break;
			
			case ADDRESS_NUMBER:
				address_bean.setAddress_number(address_number);								
				
				original_value = address.getAddress_number() != null ? address.getAddress_number() + "" : "";
				
				new_value = address_number != null ? address_number : "";
				
				init_change_control("gn_address", "address_number", original_value, new_value);
								
				if(change_control_OK)
				{
					address.setAddress_number(address_number);					
				}										
			break;
			
			case APT_NUMBER:
				address_bean.setApt_number(apt_number);								
				
				original_value = address.getApt_number() != null ? address.getApt_number() + "" : "";
				
				new_value = apt_number != null ? apt_number : "";
				
				init_change_control("gn_address", "apt_number", original_value, new_value);
								
				if(change_control_OK)
				{
					address.setApt_number(apt_number);					
				}	
			break;
			
			case MX_MANZANA:
				address_bean.setMx_manzana(mx_manzana);								
				
				original_value = address.getMx_manzana() != null ? address.getMx_manzana() + "" : "";
				
				new_value = mx_manzana != null ? mx_manzana : "";
				
				init_change_control("gn_address", "mx_manzana", original_value, new_value);
								
				if(change_control_OK)
				{
					address.setMx_manzana(mx_manzana);					
				}
			break;
				
			case MX_LOTE:
				address_bean.setMx_lote(mx_lote);								
				
				original_value = address.getMx_lote() != null ? address.getMx_lote() + "" : "";
				
				new_value = mx_lote != null ? mx_lote : "";
				
				init_change_control("gn_address", "mx_lote", original_value, new_value);
								
				if(change_control_OK)
				{
					address.setMx_lote(mx_lote);
				}
			break;
			
			case FIRST_STREET_REFERENCE:
				address_bean.setFirst_street_reference(first_street_reference);								
				
				original_value = address.getFirst_street_reference() != null ? address.getFirst_street_reference() + "" : "";
				
				new_value = first_street_reference != null ? first_street_reference : "";
				
				init_change_control("gn_address", "first_street_reference", original_value, new_value);
								
				if(change_control_OK)
				{
					address.setFirst_street_reference(first_street_reference);					
				}
			break;
			
			case SECOND_STREET_REFERENCE:
				address_bean.setSecond_street_reference(second_street_reference);								
				
				original_value = address.getSecond_street_reference() != null ? address.getSecond_street_reference() + "" : "";
				
				new_value = second_street_reference != null ? second_street_reference : "";
				
				init_change_control("gn_address", "second_street_reference", original_value, new_value);
								
				if(change_control_OK)
				{
					address.setSecond_street_reference(second_street_reference);					
				}
			break;
			
			case DESCRIPTION:
				address_bean.setDescription(description);								
				
				original_value = address.getDescription() != null ? address.getDescription() + "" : "";
				
				new_value = description != null ? description : "";
				
				init_change_control("gn_address", "description", original_value, new_value);
								
				if(change_control_OK)
				{
					address.setDescription(description);					
				}
			break;
		}
	}
	
	protected void init_change_control(String afected_table, String field, String original_value, String new_value) 
	{		
		change_control_ENABLED = ! new_value.equalsIgnoreCase(original_value);
		
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
	}
	
	protected void init_domicilio_CHANGED() 
	{
		token = new AddressTokenIMP();
		token.setAddress(address);
		
		address_TOKEN_BENEFICIARIO = token.getAddress_beneficiario_TOKEN();
		
		if(address_TOKEN_CASA_ENABLED)
		{
			domicilio_CHANGED = !address_TOKEN_BENEFICIARIO.equalsIgnoreCase(address_TOKEN_CASA);
		}	
	}
}
