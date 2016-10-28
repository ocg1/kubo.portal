package mx.com.kubo.registro.beneficiarios;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;

import mx.com.kubo.bean.Benefi_ciaries;
import mx.com.kubo.bean.ListNeighborhoodBean;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.AddressTokenIMP;

public class DomicilioIMP extends DomicilioAMO 
implements DomicilioIMO
{	
	public final void init_focus_date(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		focus_date = new Date();
		
		request.addCallbackParam("focus_date", date_format.format(focus_date));
	}
	
	public final void init()
	{
		address = service_address.getMaxAddressByType(prospectus_id, company_id, CASA);
		
		token = new AddressTokenIMP();
		token.setAddress(address);
		
		address_TOKEN_CASA = token.getAddress_beneficiario_TOKEN();
		
		address_TOKEN_CASA_ENABLED = address_TOKEN_CASA != null && address_TOKEN_CASA.length() > 0;
	}
	
	public final void init_zipcode(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		beneficiarie_id = (Integer) event.getComponent().getAttributes().get("beneficiarie_id");
		
		zipcode = input_text.getValue().toString();
		
		lista_neighborhood = service_employment.getAsentamientosByCP(zipcode);				
		
		zipcode_ENABLED = lista_neighborhood != null && lista_neighborhood.size() > 0 ? true : false;
		
		request.addCallbackParam("zipcode", zipcode);	
		request.addCallbackParam("zipcode_ENABLED", zipcode_ENABLED);
		request.addCallbackParam("address_type_id", address_type_id);
		request.addCallbackParam("beneficiarie_id", beneficiarie_id);
	}
	
	public final void init_lista_neighborhood() 
	{
		request = RequestContext.getCurrentInstance();
							
		if(zipcode_ENABLED)
		{
			json_neighborhoodbean = new ArrayList<ListNeighborhoodBean>();
			
			ListNeighborhoodBean neigt = null;
			
			for (NeighborhoodCat gn_neighborhood : lista_neighborhood) 
			{
				int neighborhood_id = gn_neighborhood.getNeighborhoodCatPK().getNeighborhood_id();
				
				neigt = new ListNeighborhoodBean(neighborhood_id, gn_neighborhood.getName());
				
				json_neighborhoodbean.add(neigt);	
			}			
						
			try 
			{
				json_array = "";
				
				json_array = new JSONArray(json_neighborhoodbean.toArray(),true).toString();				
				
			} catch (JSONException e) {
				
				e.printStackTrace();
			}			
			
			request.addCallbackParam("neighborhood", json_array);
			request.addCallbackParam("isValid", true);
			request.addCallbackParam("beneficiarie_id", beneficiarie_id);			
		}		
	}

	public final void init_neighborhood(Benefi_ciaries beneficiario_bean)
	{
		request = RequestContext.getCurrentInstance();
		
		address_bean = beneficiario_bean.getAddressbean();
		
		if(zipcode_ENABLED)
		{		
			neighborhood = lista_neighborhood.get(0);
			
			neighborhood_id = neighborhood.getNeighborhoodCatPK().getNeighborhood_id();
			
			town = neighborhood.getDelegMunicipio();						
			
			address_bean.setListNeighborhood(lista_neighborhood);
			address_bean.setZip_code(zipcode);
			
			address_bean.setTownName (town.getName());
			address_bean.setStateName(town.getEstados().getName());		
			address_bean.setTown_id  (town.getTownCatPK().getTown_id());
			address_bean.setState_id (town.getEstados().getStateCatPK().getState_id());
			
			if(lista_neighborhood.size() == 1)
			{
				address_bean.setNeighborhood_id(neighborhood_id);
				
			} else {
				
				address_bean.setNeighborhood_id(null);
				
				neighborhood_id = 0;
			}
			
			beneficiario_bean.setAddressbean(address_bean);			
		
		} else {						
			
			beneficiario_bean.getAddressbean().setZip_code(null);
		}		
		
		request.addCallbackParam("beneficiarie_id", beneficiarie_id);
		request.addCallbackParam("neighborhood_id", neighborhood_id);
		request.addCallbackParam("TownName",  town.getName());
		request.addCallbackParam("StateName", town.getEstados().getName());
	}
	
	public final void init_change_control_zipcode(Benefi_ciaries beneficiario_bean)
	{
		request = RequestContext.getCurrentInstance();
		
		beneficiarie_id = beneficiario_bean.getBeneficiarie_id();
		address_bean    = beneficiario_bean.getAddressbean();
		
		address = address_bean.getAddress();
		
		comments = "beneficiario: (" + beneficiario_bean.getBeneficiarie_id() + ") " + beneficiario_bean.getNombre_completo();
		
		change_control_OK = false;
		
		original_value = address.getZip_code() != null ? address.getZip_code() : "";
		
		init_change_control("gn_address", "zip_code", original_value, zipcode);
		
		original_value = address.getTown_id() != null ? address.getTown_id() + "" : "";
		
		new_value = address_bean.getTown_id()  + "";
		
		init_change_control("gn_address", "town_id", original_value, new_value);
		
		original_value = address.getState_id() != null ? address.getState_id() + "" : "";
		
		new_value = address_bean.getState_id() + "";
				
		init_change_control("gn_address", "state_id", original_value, new_value);
		
		flagUpdate = false;
		flagSave   = false;	
		
		if(change_control_OK)
		{					
			address.setState_id (address_bean.getState_id());
			address.setTown_id  (address_bean.getTown_id());				
			address.setZip_code (address_bean.getZip_code());
			
			address = service_address.update_address(address);						
			
			address_bean.setAddress(address);
			
			beneficiario_bean.setAddressbean(address_bean);
			
			flagUpdate = true;						
		}
		
		init_domicilio_CHANGED();
		
		request.addCallbackParam("update_OK", flagUpdate);
		request.addCallbackParam("beneficiarie_id", beneficiarie_id);		
		request.addCallbackParam("domicilio_CHANGED", domicilio_CHANGED);	
	}
	
	public final void init_neighborhood_id(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_menu = (HtmlSelectOneMenu) event.getComponent();
		
		beneficiarie_id = (Integer) event.getComponent().getAttributes().get("beneficiarie_id");
		
		neighborhood_id = Integer.parseInt(select_menu.getValue().toString());		
		
		request.addCallbackParam("beneficiarie_id", beneficiarie_id);
		request.addCallbackParam("neighborhood_id", neighborhood_id);
	}
	
	public final void init_change_control_neighborhood(Benefi_ciaries beneficiario_bean)
	{
		request = RequestContext.getCurrentInstance();
		
		beneficiarie_id = beneficiario_bean.getBeneficiarie_id();
		address_bean    = beneficiario_bean.getAddressbean();
		
		address = address_bean.getAddress();
		
		if(neighborhood_id > 0)
		{
			address_bean.setNeighborhood_id(neighborhood_id);
			
		} else {
			
			address_bean.setNeighborhood_id(null);
		}
		
		change_control_OK = false;
		
		comments = "beneficiario: (" + beneficiario_bean.getBeneficiarie_id() + ") " + beneficiario_bean.getNombre_completo();
		
		original_value = address.getNeighborhood_id() != null ? address.getNeighborhood_id() + "" : "";
		
		new_value = neighborhood_id > 0 ? neighborhood_id + "" : "";
		
		init_change_control("gn_address", "neighborhood_id", original_value, new_value);
		
		neighborhood_text = null;
		
		original_value = address.getNeighborhood_text() != null ? address.getNeighborhood_text() : "";				
				
		init_change_control("gn_address", "neighborhood_text", original_value, "");
		
		flagUpdate = false;
		flagSave  = false;	
		
		if(change_control_OK)
		{
			address.setNeighborhood_id(address_bean.getNeighborhood_id());
			address.setNeighborhood_text(null);
			
			address = service_address.update_address(address);						
			
			address_bean.setAddress(address);
			
			beneficiario_bean.setAddressbean(address_bean);
			
			flagUpdate = true;						
		}
		
		init_domicilio_CHANGED();
				
		request.addCallbackParam("update_OK", flagUpdate);
		request.addCallbackParam("beneficiarie_id", beneficiarie_id);		
		request.addCallbackParam("domicilio_CHANGED", domicilio_CHANGED);	
	}
		
	public final void init_input_textarea(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_textarea = (HtmlInputTextarea) event.getComponent();
		
		input_text_ID = input_textarea.getId();
		
		if(input_text_ID.equals("point_ref"))
		{
			field_index = DESCRIPTION;
			
			description = input_textarea.getValue().toString();
			
			request.addCallbackParam("description", description);
		}
		
		request.addCallbackParam("address_type_id", address_type_id);
		request.addCallbackParam("field_index", field_index);
	}
	
	public final void init_input_text(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		beneficiarie_id = (Integer) input_text.getAttributes().get("beneficiarie_id");		
		input_text_ID   = (String)  input_text.getAttributes().get("input_text_ID");
		
		if(input_text_ID.equals("neighborhood_text"))
		{
			field_index = NEIGHBORHOOD_TEXT;
			
			neighborhood_text = input_text.getValue().toString();
			
			request.addCallbackParam("neighborhood_text", neighborhood_text);
		}	
		
		if(input_text_ID.equals("street"))
		{
			field_index = STREET;
			
			street = input_text.getValue().toString();
			
			request.addCallbackParam("street", street);
		}	
		
		if(input_text_ID.equals("address_number"))
		{
			field_index = ADDRESS_NUMBER;
			
			address_number = input_text.getValue().toString();
			
			request.addCallbackParam("address_number", address_number);
		}		
		
		if(input_text_ID.equals("apt_number"))
		{
			field_index = APT_NUMBER;
			
			apt_number = input_text.getValue().toString();
			
			request.addCallbackParam("apt_number", apt_number);
		}
		
		if(input_text_ID.equals("mx_manzana"))
		{
			field_index = MX_MANZANA;
			
			mx_manzana = input_text.getValue().toString();
			
			request.addCallbackParam("mx_manzana", mx_manzana);
		}
		
		if(input_text_ID.equals("mx_lote"))
		{
			field_index = MX_LOTE;
			
			mx_lote = input_text.getValue().toString();
			
			request.addCallbackParam("mx_lote", mx_lote);
		}
		
		if(input_text_ID.equals("betweenStreet1"))
		{
			field_index = FIRST_STREET_REFERENCE;
			
			first_street_reference = input_text.getValue().toString();
			
			request.addCallbackParam("first_street_reference", first_street_reference);
		}
		
		if(input_text_ID.equals("betweenStreet2"))
		{
			field_index = SECOND_STREET_REFERENCE;
			
			second_street_reference = input_text.getValue().toString();
			
			request.addCallbackParam("second_street_reference", second_street_reference);
		}
		
		request.addCallbackParam("beneficiarie_id", beneficiarie_id);
		request.addCallbackParam("field_index", field_index);
	}
	
	public final void init_change_control(Benefi_ciaries beneficiario_bean)
	{
		request = RequestContext.getCurrentInstance();
		
		beneficiarie_id = beneficiario_bean.getBeneficiarie_id();
		address_bean    = beneficiario_bean.getAddressbean();
		
		address = address_bean.getAddress();
		
		comments = "beneficiario: (" + beneficiario_bean.getBeneficiarie_id() + ") " + beneficiario_bean.getNombre_completo();
		
		change_control_OK = false;
		flagUpdate = false;
		flagSave  = false;
		
		init_field_index();
				
		if(change_control_OK)
		{
			address = service_address.update_address(address);						
			
			address_bean.setAddress(address);
			
			beneficiario_bean.setAddressbean(address_bean);
			
			flagUpdate = true;									
		}
		
		init_domicilio_CHANGED();
				
		request.addCallbackParam("update_OK", flagUpdate);
		request.addCallbackParam("beneficiarie_id", beneficiarie_id);		
		request.addCallbackParam("domicilio_CHANGED", domicilio_CHANGED);		
	}
}	
	