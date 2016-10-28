package mx.com.kubo.registro.sobreti.actividad;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;

import mx.com.kubo.bean.ActividadEconomicaDMO;
import mx.com.kubo.bean.BusinessBean;
import mx.com.kubo.bean.EmploymentBean;
import mx.com.kubo.bean.ListNeighborhoodBean;
import mx.com.kubo.model.NeighborhoodCat;

public class DomicilioIMP extends DomicilioAMO 
implements DomicilioIMO
{	
	public final void init_zipcode(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		zipcode = input_text.getValue().toString();
		
		listAsents = service_employment.getAsentamientosByCP(zipcode);
		
		zipcode_ENABLED = false;
		
		if(listAsents != null && listAsents.size() > 0)
		{
			zipcode_ENABLED = true;
		}
		
		request.addCallbackParam("zipcode", zipcode);	
		request.addCallbackParam("zipcode_ENABLED", zipcode_ENABLED);
		request.addCallbackParam("address_type_id", address_type_id);
	}
	
	public final void init_lista_neighborhood() 
	{
		request = RequestContext.getCurrentInstance();
							
		if(zipcode_ENABLED)
		{
			catalogo = new ArrayList<ListNeighborhoodBean>();
			
			ListNeighborhoodBean neigt = null;
			
			for (NeighborhoodCat gn_neighborhood : listAsents) 
			{
				int neighborhood_id = gn_neighborhood.getNeighborhoodCatPK().getNeighborhood_id();
				
				neigt = new ListNeighborhoodBean(neighborhood_id, gn_neighborhood.getName());
				
				catalogo.add(neigt);	
			}			
						
			try 
			{
				json_array = "";
				
				json_array = new JSONArray(catalogo.toArray(),true).toString();				
				
			} catch (JSONException e) {
				
				e.printStackTrace();
			}			
			
			request.addCallbackParam("neighborhood", json_array);
			request.addCallbackParam("isValid", true);
			request.addCallbackParam("address_type_id", address_type_id);
		}		
	}

	public final void init_neighborhood(ActividadEconomicaDMO actividad_economica)
	{
		request = RequestContext.getCurrentInstance();
		
		address_bean = actividad_economica.getAddressbean();
		
		if(zipcode_ENABLED)
		{		
			neighborhood = listAsents.get(0);
			
			neighborhood_id = neighborhood.getNeighborhoodCatPK().getNeighborhood_id();
			
			town = neighborhood.getDelegMunicipio();						
			
			address_bean.setListNeighborhood(listAsents);
			address_bean.setZip_code(zipcode);
			
			address_bean.setTownName (town.getName());
			address_bean.setStateName(town.getEstados().getName());		
			address_bean.setTown_id  (town.getTownCatPK().getTown_id());
			address_bean.setState_id (town.getEstados().getStateCatPK().getState_id());
			
			if(listAsents.size() == 1)
			{
				address_bean.setNeighborhood_id(neighborhood_id);
				
			} else {
				
				address_bean.setNeighborhood_id(null);
				
				neighborhood_id = 0;
			}
			
			actividad_economica.setAddressbean(address_bean);			
		
		} else {						
			
			actividad_economica.getAddressbean().setZip_code(null);
		}		
		
		request.addCallbackParam("address_type_id", address_type_id);
		request.addCallbackParam("neighborhood_id", neighborhood_id);
		request.addCallbackParam("TownName",  town.getName());
		request.addCallbackParam("StateName", town.getEstados().getName());
	}
	
	public final void init_focus_date(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		focus_date = new Date();
		
		request.addCallbackParam("focus_date", date_format.format(focus_date));
	}
	
	public final void init_change_control_zipcode(ActividadEconomicaDMO employbean)
	{
		request = RequestContext.getCurrentInstance();
		
		address_bean = employbean.getAddressbean();
		
		address = address_bean.getAddress();
		
		
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
			
			employbean.setAddressbean(address_bean);
			
			flagUpdate = true;
		}
		
		request.addCallbackParam("update_OK", flagUpdate);
	}
	
	public final void init_neighborhood_id(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_menu = (HtmlSelectOneMenu) event.getComponent();
		
		neighborhood_id = Integer.parseInt(select_menu.getValue().toString());		
		
		request.addCallbackParam("address_type_id", address_type_id);
		request.addCallbackParam("neighborhood_id", neighborhood_id);
	}
	
	public final void init_change_control_neighborhood(ActividadEconomicaDMO actividad_economica)
	{
		request = RequestContext.getCurrentInstance();
		
		address_bean = actividad_economica.getAddressbean();
		
		address = address_bean.getAddress();
		
		if(neighborhood_id > 0)
		{
			address_bean.setNeighborhood_id(neighborhood_id);
			
		} else {
			
			address_bean.setNeighborhood_id(null);
		}
		
		change_control_OK = false;
		
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
			
			actividad_economica.setAddressbean(address_bean);
			
			flagUpdate = true;
		}
				
		request.addCallbackParam("update_OK", flagUpdate);
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
		
		input_text_ID = input_text.getId();
		
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
		
		if(input_text_ID.equals("address_intNumber"))
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
		
		request.addCallbackParam("address_type_id", address_type_id);
		request.addCallbackParam("field_index", field_index);
	}
	
	public final void init_change_control(ActividadEconomicaDMO actividad_economica)
	{
		request = RequestContext.getCurrentInstance();
		
		address_bean = actividad_economica.getAddressbean();
		
		address = address_bean.getAddress();
		
		change_control_OK = false;
		flagUpdate = false;
		flagSave  = false;
		
		switch(field_index)
		{
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
		
		if(change_control_OK)
		{
			address = service_address.update_address(address);						
			
			address_bean.setAddress(address);
			
			actividad_economica.setAddressbean(address_bean);
			
			flagUpdate = true;
		}
				
		request.addCallbackParam("update_OK", flagUpdate);
	}
	
/*	
	public void zipCodeFunctionAndSaveBusiness(BusinessBean business) 
	{
		request = RequestContext.getCurrentInstance();
		
		zipcode =  business.getAddressbean().getZip_code();
		
		listAsents = service_employment.getAsentamientosByCP(zipcode);
		
		if(listAsents.size() != 0)
		{			
			catalogo = new ArrayList<ListNeighborhoodBean>();
			
			ListNeighborhoodBean neigt = null;
			
			for (NeighborhoodCat iter : listAsents) 
			{
				int neighborhood_id = iter.getNeighborhoodCatPK().getNeighborhood_id();
				
				neigt = new ListNeighborhoodBean(neighborhood_id, iter.getName());
				
				catalogo.add(neigt);	
			}
			
			neighborhood = listAsents.get(0);
			
			neighborhood_id = neighborhood.getNeighborhoodCatPK().getNeighborhood_id();
			
			town = neighborhood.getDelegMunicipio();
			
			address_bean = business.getAddressbean();
			
			address_bean.setListNeighborhood(listAsents);
			address_bean.setTownName (town.getName());
			address_bean.setStateName(town.getEstados().getName());
			address_bean.setTown_id  (town.getTownCatPK().getTown_id());
			address_bean.setState_id (town.getEstados().getStateCatPK().getState_id());
			
			request.addCallbackParam("TownName",  town.getName());
			request.addCallbackParam("StateName", town.getEstados().getName());
			
			if(listAsents.size() == 1)
			{
				address_bean.setNeighborhood_id(neighborhood_id);
				
			} else {
				
				address_bean.setNeighborhood_id(null);
			}
			
			business.setAddressbean(address_bean);
			
			try 
			{
				String json_array = new JSONArray(catalogo.toArray(),true).toString();
				
				request.addCallbackParam("neighborhood", json_array);
				request.addCallbackParam("isValid", true);
				
				//updateAddressBlurBusiness(business);
				
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
						
		} else {
			
			request.addCallbackParam("isValid", false);
			
			business.getAddressbean().setZip_code(null);
		}				
	}
*/	
	
/*	
	public void updateAddressBlur(EmploymentBean employbean)
	{		
		address_bean = employbean.getAddressbean();
		
		address = address_bean.getAddress();
		
		if(address != null)
		{
			if (address_bean.getZip_code() != null && address_bean.getZip_code().length() > 0) 
			{
				address.setNeighborhood_id(address_bean.getNeighborhood_id());
				address.setState_id (address_bean.getState_id());
				address.setTown_id  (address_bean.getTown_id());				
				address.setZip_code (address_bean.getZip_code());
				address.setLatitude (address_bean.getLatitude());
				address.setLongitude(address_bean.getLongitude());
			}
		}
		
		if(address != null)
		{	
			flagUpdate = service_address.update(address);
			
		} else {
			
			employment = employbean.getEmployment();
			
			employment_id = employment.getEmploymentPK().getEmployment_id();
			
			address_id = service_address.getMaxAddressId(sesion.getProspectus_id(),sesion.getCompany_id());
			
			address_PK = new AddressPK();			
			address_PK.setCompany_id  (sesion.getCompany_id());
			address_PK.setProspectus_id(sesion.getProspectus_id());
			address_PK.setAddress_id(address_id);
			
			address = new Address();
			address.setAddressPK(address_PK);			
			address.setEmployment_id(employment_id);	
			address.setAddress_type_id(4);
			address.setCountry_id(700);
			address.setNeighborhood_id(address_bean.getNeighborhood_id());
			address.setTown_id (address_bean.getTown_id());			
			address.setState_id(address_bean.getState_id());
			address.setZip_code(address_bean.getZip_code());												
			
			flagSave = service_address.add(address);
			
			if(flagSave)
			{
				for(EmploymentBean iterar : employmentList)
				{
					int employment_id = iterar.getEmployment().getEmploymentPK().getEmployment_id();
					
					if(employment_id == employment.getEmploymentPK().getEmployment_id())
					{					
						iterar.setCheck_inH1 (employment.getCheck_in()  !=null ? employment.getCheck_in().split(":")[0]  : "09");
						iterar.setCheck_inM1 (employment.getCheck_in()  !=null ? employment.getCheck_in().split(":")[1]  : "00");
						iterar.setCheck_outH1(employment.getCheck_out( )!=null ? employment.getCheck_out().split(":")[0] : "18");
						iterar.setCheck_outM1(employment.getCheck_out() !=null ? employment.getCheck_out().split(":")[1] : "00");
						
						iterar.getAddressbean().setAddress(address);
						
						break;
					}
				}
			}
		}				
	}	
*/	
	
/*	
	public void updateAddressBlurBusiness(BusinessBean businessbean)
	{
		boolean flagUpdate = false;
		boolean flagSave   = false;
		
		address_bean = businessbean.getAddressbean();
		
		address = address_bean.getAddress();
		
		if(address != null)
		{
			if (address_bean.getZip_code() != null && address_bean.getZip_code().length() > 0) 
			{
				address.setNeighborhood_id(address_bean.getNeighborhood_id());
				address.setState_id (address_bean.getState_id());
				address.setTown_id  (address_bean.getTown_id());				
				address.setZip_code (address_bean.getZip_code());
				address.setLatitude (address_bean.getLatitude());
				address.setLongitude(address_bean.getLongitude());
			}
		}
		
		if(address != null)
		{	
			flagUpdate = service_address.update(address);
			
		} else {
			
			address_id = service_address.getMaxAddressId(sesion.getProspectus_id(), sesion.getCompany_id());
			
			business = businessbean.getBusiness();
			
			business_id = business.getBusinessPK().getBusiness_id();
			
			address_PK = new AddressPK();
			address_PK.setCompany_id  (sesion.getCompany_id());
			address_PK.setProspectus_id(sesion.getProspectus_id());
			address_PK.setAddress_id(address_id);
			
			address = new Address();
			address.setAddressPK(address_PK);
			address.setBusiness_id(business_id);
			address.setAddress_type_id(3);
			address.setCountry_id(700);			
			address.setNeighborhood_id(address_bean.getNeighborhood_id());
			address.setTown_id (address_bean.getTown_id());			
			address.setState_id(address_bean.getState_id());
			address.setZip_code(address_bean.getZip_code());																				
			
			flagSave = service_address.add(address);
			
			if(flagSave)
			{
				for(BusinessBean iterar:businessList)
				{
					int business_id = iterar.getBusiness().getBusinessPK().getBusiness_id();
					
					if(business_id == business.getBusinessPK().getBusiness_id())
					{
						iterar.getAddressbean().setAddress(address);
					}
				}
			}
		}			
	}
*/	
	
	
	public void setSplitLatLongEmployment(EmploymentBean employbean) 
	{
		try 
		{
			if(employbean.getAddressbean().getLatLong()!=null)
			{
				employbean.getAddressbean().setLatitude(employbean.getAddressbean().getLatLong().split(",")[0].trim());
				employbean.getAddressbean().setLongitude(employbean.getAddressbean().getLatLong().split(",")[1].trim());
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		//updateAddressBlur(employbean);
	}
	
	
	public void setSplitLatLongBusiness(BusinessBean business) 
	{
		try {
			if(business.getAddressbean().getLatLong()!=null){
			business.getAddressbean().setLatitude(business.getAddressbean().getLatLong().split(",")[0]);
			business.getAddressbean().setLongitude(business.getAddressbean().getLatLong().split(",")[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//updateAddressBlurBusiness(business);
	}
}
