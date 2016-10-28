package mx.com.kubo.mesa.solicitud.perfil.domicilio;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public final class DomicilioIMP extends DomicilioAMO
implements DomicilioIMO 
{
	public void init() 
	{						
		if (address_ENABLED) 
		{
			init_coincidencias();	
			init_change_control_bean();
			
			token = new AddressTokenIMP();
			token.setAddress(address);		
			
			switch(address_type_id)
			{
				case EMPRESA:					
					token.setEmployment(employment);					
				break;
				
				case NEGOCIO:					
					token.setBusiness(business);					
				break;								
			}
			
			 address_TOKEN = token.getAddress_activity_TOKEN();			
			activity_TOKEN = token.getActivity_TOKEN();
			
			zipcode = address.getZip_code();
			
			lista_neighborhood = service_employment.getAsentamientosByCP(zipcode);
		}				
	}
	
	public final void listener_lista_coincidencias()
	{
		request = RequestContext.getCurrentInstance();				
		
		lista_address = service_address.getLista_coincidencias(NIVEL_1, address);	
		
		init_lista_coincidencias();
		
		request.addCallbackParam("address_type_id", address_type_id);
		request.addCallbackParam("numero_coincidencias", lista_coincidencias.size());
	}
	
	public final void listener_lista_coincidencias_NIVEL_2()
	{
		request = RequestContext.getCurrentInstance();							
		
		lista_address = service_address.getLista_coincidencias(NIVEL_2, address);		
		
		init_lista_coincidencias();
		
		request.addCallbackParam("address_type_id", address_type_id);
		request.addCallbackParam("numero_coincidencias", lista_coincidencias.size());
	}
	
	public final void init_zipcode(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		zipcode = input_text.getValue().toString();
		
		lista_neighborhood = service_employment.getAsentamientosByCP(zipcode);
		
		zipcode_ENABLED = false;
		
		if(lista_neighborhood != null && !lista_neighborhood.isEmpty())
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
		
		neighborhood = lista_neighborhood.get(DEFAULT);
		
		neighborhood_id = neighborhood.getNeighborhoodCatPK().getNeighborhood_id();
		
		town = neighborhood.getDelegMunicipio();	
		
		address.setTown_id  (town.getTownCatPK().getTown_id());
		address.setState_id (town.getEstados().getStateCatPK().getState_id());
		address.setNeighborhood(neighborhood);
		address.setTownCat(town);
				
		if(lista_neighborhood.size() == 1)
		{
			address.setNeighborhood_id(neighborhood_id);
			
		} else {						
			
			neighborhood_id = 0;
		}
							
		request.addCallbackParam("isValid", true);
		request.addCallbackParam("address_type_id", address_type_id);	
		request.addCallbackParam("town_name",  town.getName());
		request.addCallbackParam("state_name", town.getEstados().getName());	
	}
	
	public final void init_neighborhood_id(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_menu = (HtmlSelectOneMenu) event.getComponent();
		
		neighborhood_id = Integer.parseInt(select_menu.getValue().toString());			
		
		address.setNeighborhood_id(neighborhood_id);
		
		request.addCallbackParam("address_type_id", address_type_id);
		request.addCallbackParam("neighborhood_id", neighborhood_id);
	}
	
	public final void init_address_field(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		try
		{
			input_text = (HtmlInputText) evento.getComponent();
			
			input_text_value  = (String) input_text.getValue();		
			input_text_id     = (String) input_text.getId();
			
		} catch (ClassCastException e) {
			
			text_area = (HtmlInputTextarea) evento.getComponent();
			
			input_text_value  = (String) text_area.getValue();
			input_text_id     = (String) text_area.getId();
		}
		
		asignar_datos_address();	
		
		request.addCallbackParam("input_text_id",    input_text_id);
		request.addCallbackParam("input_text_value", input_text_value);
		
/*		
		init_addres_TOKEN();
		
		vivienda_TOKEN_NEW = token.getAddress_TOKEN();
		
		asignar_vivienda_CHANGED();
		
		if(auto_save_ENABLED)
		{
			guardar_address();
		}
*/		
	}
	
	public final void update_address() 
	{							
		request = RequestContext.getCurrentInstance();				
		
		token = new AddressTokenIMP();
		token.setAddress(address);
		
		address_TOKEN_NEW = token.getAddress_activity_TOKEN();	
		
		add_change_control(address_TOKEN, address_TOKEN_NEW);
								
		if(change_control_OK)
		{									
			//guardar_tipo_de_vivienda();			
			
			update_OK = service_address.update(address);	
			
			init_change_control_bean();			
		}						
		
		request.addCallbackParam("address_TOKEN", address_TOKEN_NEW);
		request.addCallbackParam("update_OK", update_OK);				
	}
}