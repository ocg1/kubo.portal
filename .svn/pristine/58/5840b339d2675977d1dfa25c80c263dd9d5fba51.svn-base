package mx.com.kubo.registro.datos.domicilio;

import java.util.Calendar;
import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.model.Address;

import org.primefaces.context.RequestContext;

public class DomicilioIMP extends DomicilioAMO
implements DomicilioIMO
{	
	public final void init_copy(DomicilioIMO to_copy)
	{				
		address = to_copy.getAddress();
		
		address.setAddress_type_id(DOMICILIO_FISCAL);
		address.setAddressPK(address_PK);			
						
		save_address_OK = service_address.update(address);
	}
	
	public final void init_reset()
	{				
		address = new Address();
		
		address.setAddress_type_id(DOMICILIO_FISCAL);
		address.setCountry_id(700);
		address.setAddressPK(address_PK);			 
								
		save_address_OK = service_address.update(address);
	}
	
	public final void init() 
	{
		residencelist = service_residence.getResidenceList();
		
		address = service_address.getMaxAddressByType(prospectus_id, company_id, address_type_id);
		
		if(address == null)
		{			
			init_address_NEW();		
		}
		
		if(address != null)
		{
			init_address();	
		} 
	}
	
	public final void init_focus_date(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		focus_date = new Date();
		
		request.addCallbackParam("focus_date", date_format.format(focus_date));
	}
	
	public final void init_zipcode(AjaxBehaviorEvent event)
	{
		Date d1 = new Date();
		
		Calendar cd_1 = Calendar.getInstance();
		cd_1.setTime(d1);
		
		System.out.println( "Inicia Carga de colonias - prospectus_id =  " + person.getNatPerPK().getProspectus_id() + " fecha: " + d1 );
		
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		zipcode = input_text.getValue().toString();
				
		init_coverage_zone();
		
		Calendar cd_2 = Calendar.getInstance();
		cd_2.setTime(new Date());
		
		long dif_1 = cd_2.getTimeInMillis() - cd_1.getTimeInMillis();
		
		System.out.println( "Fin COVERAGE_ZONE - prospectus_id =  " + person.getNatPerPK().getProspectus_id() + " timepo: " + dif_1 );
		
		init_lista_colonias();			
		
		Calendar cd_3 = Calendar.getInstance();
		cd_3.setTime(new Date());
		
		long dif_2 = cd_3.getTimeInMillis() - cd_2.getTimeInMillis();
		
		System.out.println( "Fin init_lista_colonias - prospectus_id =  " + person.getNatPerPK().getProspectus_id() + " timepo: " + dif_2 );
		
		change_control_OK = false;
		
		if(isValid)
		{
			original_value = address.getZip_code() != null ? address.getZip_code() : "";
			
			init_change_control("gn_address", "zip_code", original_value, zipcode);
			
			original_value = address.getTown_id() != null ? address.getTown_id() + "" : "";
			
			new_value = townId + "";
			
			//init_change_control("gn_address", "town_id", original_value, new_value);
			
			original_value = address.getState_id() != null ? address.getState_id() + "" : "";
			
			new_value = stateId + "";
					
			//init_change_control("gn_address", "state_id", original_value, new_value);
		}
		
		Calendar cd_4 = Calendar.getInstance();
		cd_4.setTime(new Date());
		
		long dif_3 = cd_4.getTimeInMillis() - cd_3.getTimeInMillis();
		
		System.out.println( "Fin valida_change_control - prospectus_id =  " + person.getNatPerPK().getProspectus_id() + " tiempo: " + dif_3 );
		
		save_address_OK = false;
		
		if(change_control_OK)
		{
			persist_address();
		}
		
		if(isValid)
		{
			request.addCallbackParam("zipcode", zipcode);
			request.addCallbackParam("TownName", delegMun);
			request.addCallbackParam("StateName", estado);
			request.addCallbackParam("neighborhood", colonias_JSON);
			request.addCallbackParam("isValid", isValid);
			request.addCallbackParam("flagState", coverage_zone_ENABLED);
			request.addCallbackParam("save_address_OK", save_address_OK);	
			
		}else{
			
			request.addCallbackParam("zipcode", zipcode);
			request.addCallbackParam("TownName", "");
			request.addCallbackParam("StateName", "");
			request.addCallbackParam("neighborhood", colonias_JSON);
			request.addCallbackParam("isValid", isValid);
			request.addCallbackParam("flagState", coverage_zone_ENABLED);
			request.addCallbackParam("save_address_OK", save_address_OK);	
			
		}
		Calendar cd_5 = Calendar.getInstance();
		cd_5.setTime(new Date());
		
		long dif_4 = cd_5.getTimeInMillis() - cd_4.getTimeInMillis();
		
		System.out.println( "Fin saveAddress - prospectus_id =  " + person.getNatPerPK().getProspectus_id() + " tiempo: " + dif_4 );
		
		
		Calendar cd_6 = Calendar.getInstance();
		cd_6.setTime(new Date());
		
		long dif_5 = cd_6.getTimeInMillis() - cd_1.getTimeInMillis();
		
		System.out.println( "FINALIZA INIT_ZIP_CODE - prospectus_id =  " + person.getNatPerPK().getProspectus_id() + " tiempo: " + dif_5 );
		
	}
	
	public final void init_neighborhood_id(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_menu = (HtmlSelectOneMenu) event.getComponent();
		
		neighborhood_id = Integer.parseInt(select_menu.getValue().toString());
		
		change_control_OK = false;
		
		original_value = address.getNeighborhood_id() != null ? address.getNeighborhood_id() + "" : "";
		
		new_value = neighborhood_id + "";
		
		init_change_control("gn_address", "neighborhood_id", original_value, new_value);
		
		neighborhood_text = null;
		
		original_value = address.getNeighborhood_text() != null ? address.getNeighborhood_text() : "";				
				
		init_change_control("gn_address", "neighborhood_text", original_value, "");
		
		save_address_OK = false;
		
		if(change_control_OK)
		{
			persist_address();
		}
		
		request.addCallbackParam("neighborhood_id", neighborhood_id);
		request.addCallbackParam("save_address_OK", save_address_OK);		
	}
	
/*	
	public final void init_neighborhood_text_ENABLED(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
						
		neighborhood_text_ENABLED = new Boolean(input_text.getValue().toString());
		
		request.addCallbackParam("neighborhood_text_ENABLED", neighborhood_text_ENABLED);
	}
*/	
	
	public final void init_neighborhood_text(AjaxBehaviorEvent event)
	{		
		request = RequestContext.getCurrentInstance();
	
		input_text = (HtmlInputText) event.getComponent();
	
		neighborhood_text = input_text.getValue().toString();
		
		change_control_OK = false;
		
		original_value = address.getNeighborhood_text() != null ? address.getNeighborhood_text() : "";	
		
		init_change_control("gn_address", "neighborhood_text", original_value, neighborhood_text);
		
		neighborhood_id = 0;
		
		original_value = address.getNeighborhood_id() != null ? address.getNeighborhood_id() + "" : "";
		
		init_change_control("gn_address", "neighborhood_id", original_value, "0");
		
		save_address_OK = false;
	
		if(change_control_OK)
		{
			persist_address();
		}
		
		request.addCallbackParam("neighborhood_text", neighborhood_text);
		request.addCallbackParam("save_address_OK", save_address_OK);
	}
	
	public final void init_street(AjaxBehaviorEvent event)
	{		
		request = RequestContext.getCurrentInstance();
	
		input_text = (HtmlInputText) event.getComponent();
	
		street = input_text.getValue().toString();
		
		change_control_OK = false;
		
		original_value = address.getStreet() != null && street.trim().length() > 0 ? address.getStreet() : "";
		
		init_change_control("gn_address", "street", original_value, street);
		
		save_address_OK = false;
	
		if(change_control_OK)
		{
			address.setStreet(street);		
			
			save_address_OK = service_address.update(address);
		}
		
		request.addCallbackParam("street", street);
		request.addCallbackParam("save_address_OK", save_address_OK);
	}
	
	public final void init_address_number(AjaxBehaviorEvent event)
	{		
		request = RequestContext.getCurrentInstance();
	
		input_text = (HtmlInputText) event.getComponent();
	
		address_number = input_text.getValue().toString();			
		
		original_value = address.getAddress_number() != null ? address.getAddress_number() : "";
		
		init_change_control("gn_address", "address_number", original_value, address_number);	
		
		save_address_OK = false;
	
		if(change_control_OK)
		{
			address.setAddress_number(address_number);
			
			save_address_OK = service_address.update(address);
		}
		
		request.addCallbackParam("address_number", address_number);
		request.addCallbackParam("save_address_OK", save_address_OK);
	}
	
	public final void init_apt_number(AjaxBehaviorEvent event)
	{		
		request = RequestContext.getCurrentInstance();
	
		input_text = (HtmlInputText) event.getComponent();
	
		apt_number = input_text.getValue().toString();
		
		original_value = address.getApt_number() != null ? address.getApt_number() : "";
		
		init_change_control("gn_address", "apt_number", original_value, apt_number);	
		
		save_address_OK = false;
	
		if(change_control_OK)
		{
			address.setApt_number(apt_number);
			
			save_address_OK = service_address.update(address);
		}
		
		request.addCallbackParam("apt_number", apt_number);
		request.addCallbackParam("save_address_OK", save_address_OK);
	}
	
	public final void init_mx_manzana(AjaxBehaviorEvent event)
	{		
		request = RequestContext.getCurrentInstance();
	
		input_text = (HtmlInputText) event.getComponent();
	
		mx_manzana = input_text.getValue().toString();
		
		original_value = address.getMx_manzana() != null ? address.getMx_manzana() : "";
		
		init_change_control("gn_address", "mx_manzana", original_value, mx_manzana);
		
		save_address_OK = false;
	
		if(change_control_OK)
		{
			address.setMx_manzana(mx_manzana);
			
			save_address_OK = service_address.update(address);
		}
		
		request.addCallbackParam("mx_manzana", mx_manzana);
		request.addCallbackParam("save_address_OK", save_address_OK);
	}
	
	public final void init_mx_lote(AjaxBehaviorEvent event)
	{		
		request = RequestContext.getCurrentInstance();
	
		input_text = (HtmlInputText) event.getComponent();
	
		mx_lote = input_text.getValue().toString();
		
		original_value = address.getMx_lote() != null ? address.getMx_lote() : "";
		
		init_change_control("gn_address", "mx_lote", original_value, mx_lote);	
		
		save_address_OK = false;
	
		if(change_control_OK)
		{
			address.setMx_lote(mx_lote);
			
			save_address_OK = service_address.update(address);
		}
		
		request.addCallbackParam("mx_lote", mx_lote);
		request.addCallbackParam("save_address_OK", save_address_OK);
	}
	
	public final void init_residence_id(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_menu = (HtmlSelectOneMenu) event.getComponent();
		
		residence_id = Integer.parseInt(select_menu.getValue().toString());
				
		person.setResidence_id(residence_id);
		
		person = service_natural_person.update(person);
		
		request.addCallbackParam("residence_id", residence_id);	
	}
	
	public final void init_first_reference(AjaxBehaviorEvent event)
	{		
		request = RequestContext.getCurrentInstance();
	
		input_text = (HtmlInputText) event.getComponent();
	
		first_reference = input_text.getValue().toString();
		
		original_value = address.getFirst_street_reference() != null ? address.getFirst_street_reference() : "";
		
		init_change_control("gn_address", "first_street_reference", original_value, first_reference);		
		
		save_address_OK = false;
	
		if(change_control_OK)
		{
			address.setFirst_street_reference(first_reference);
			
			save_address_OK = service_address.update(address);
		}
		
		request.addCallbackParam("first_reference", first_reference);
		request.addCallbackParam("save_address_OK", save_address_OK);
	}
	
	public final void init_second_reference(AjaxBehaviorEvent event)
	{		
		request = RequestContext.getCurrentInstance();
	
		input_text = (HtmlInputText) event.getComponent();
	
		second_reference = input_text.getValue().toString();
		
		original_value = address.getSecond_street_reference() != null ? address.getSecond_street_reference() : "";
		
		init_change_control("gn_address", "second_street_reference", original_value, second_reference);		
		
		save_address_OK = false;
	
		if(change_control_OK)
		{
			address.setSecond_street_reference(second_reference);
			
			save_address_OK = service_address.update(address);
		}
		
		request.addCallbackParam("second_reference", second_reference);
		request.addCallbackParam("save_address_OK", save_address_OK);
	}
	
	public final void init_description(AjaxBehaviorEvent event)
	{		
		request = RequestContext.getCurrentInstance();
	
		input_textarea = (HtmlInputTextarea) event.getComponent();
	
		description = input_textarea.getValue().toString();
		
		original_value = address.getDescription() != null ? address.getDescription() : "";
		
		init_change_control("gn_address", "description", original_value, description);	
		
		save_address_OK = false;
	
		if(change_control_OK)
		{
			address.setDescription(description);
			
			save_address_OK = service_address.update(address);
		}
		
		request.addCallbackParam("description", description);
		request.addCallbackParam("save_address_OK", save_address_OK);
	}
	
	public final void setSplitLatLong() 
	{
		String res = latLong;
		
		try 
		{
			if(res.split(",").length > 1)
			{
				address.setLatitude(res.split(",")[0]);
				address.setLongitude(res.split(",")[1]);
				
				save_address_OK = service_address.update(address);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}							
	}
}
