package mx.com.kubo.mesa.solicitud.perfil.domicilio;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.NeighborhoodCatPK;
import mx.com.kubo.model.ResidencePK;

import org.primefaces.context.RequestContext;

public final class EditorViviendaIMP extends EditorViviendaAMO
implements EditorViviendaIMO
{	
	public final void init()
	{						
		init_bitacora_cambios();
		init_address();		
		init_address_INFO();		
		init_coincidencias();
		init_addres_TOKEN();
			
		if(area.equals('I'))
		{

			vivienda_TOKEN = token.getInvestor_TOKEN();
			
		} else {
			
			switch(person_type)
			{
				case 'F':
				case 'A':
					vivienda_TOKEN = token.getAddress_TOKEN();										
				break;
				
				case 'M':
					vivienda_TOKEN = token.getAddress_activity_TOKEN();
					
					if(address_ENABLED && address_type == FISCAL)
					{
						fiscal_ENABLED = true;
						
					} else {
						
						fiscal_ENABLED = false;
					}
				break;
			}

		}
	}

	public final void listener_codigo_postal(AjaxBehaviorEvent evento) 
	{			
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) evento.getComponent();
		
		zipcode = input_text.getValue().toString();				
				
		asignar_datos_codigo_postal();		
		
		init_addres_TOKEN();
		
		vivienda_TOKEN_NEW = token.getAddress_TOKEN();
		
		asignar_vivienda_CHANGED();
		
		if(auto_save_ENABLED)
		{
			guardar_address();
		}
		
		request.addCallbackParam("zipcode", zipcode);		
		request.addCallbackParam("vivienda_CHANGED", vivienda_CHANGED);
		request.addCallbackParam("address_saved_OK", address_saved_OK);
	}
	
	public final void listener_mapa_ubicacion(AjaxBehaviorEvent evento) 
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) evento.getComponent();
		
		map_ubication = input_text.getValue().toString();

		try 
		{
			String [] map_ubication_TOKEN = map_ubication.split(",");
			
			if(map_ubication_TOKEN.length > 1)
			{
				address.setLatitude (map_ubication_TOKEN[0]);
				address.setLongitude(map_ubication_TOKEN[1]);
				
				map_ubication_CHANGED = true;
				
				System.out.printf("\nEditorViviendaIMP.listener_mapa_ubicacion(): " + address.getLatitude() + ", " + address.getLongitude());
			}			
			
			request.addCallbackParam("map_ubication", map_ubication);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}				
	}
	
	public final void listener_colonia_SELECTED(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one = (HtmlSelectOneMenu) evento.getComponent();
		colonia_id = Integer.parseInt(select_one.getValue().toString());
		
		colonia_PK = new NeighborhoodCatPK(colonia_id, company_id);
		
		colonia = service_colonia.getNeighborhoodById(colonia_PK);
		
		colonia_name =  colonia.getName();
		
		System.out.println("EditorViviendaIMP.listener_colonia_SELECTED(): " + colonia_id + " - " + colonia_name);
		
		colonia_Text = null;
		
		address.setNeighborhood_id(colonia_id);
		address.setNeighborhood(colonia);
		address.setNeighborhood_text(colonia_name);
		
		init_addres_TOKEN();
		
		vivienda_TOKEN_NEW = token.getAddress_TOKEN();
		
		asignar_vivienda_CHANGED();
		
		if(auto_save_ENABLED)
		{
			guardar_address();
		}
		
		request.addCallbackParam("vivienda_CHANGED", vivienda_CHANGED);
		request.addCallbackParam("address_saved_OK", address_saved_OK);
	}
	
	public final void listener_colonia_TEXT(AjaxBehaviorEvent evento)
	{	
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) evento.getComponent();
				
		colonia_Text  = input_text.getValue().toString();
		input_text_id = input_text.getId().toString();
		
		colonia_PK = null;		
		colonia    = null;		
		colonia_id = null;
		
		colonia_name = colonia_Text;						
		
		address.setNeighborhood_id(colonia_id);
		address.setNeighborhood(colonia);
		address.setNeighborhood_text(colonia_name);
		
		init_addres_TOKEN();
		
		vivienda_TOKEN_NEW = token.getAddress_TOKEN();
				
		asignar_vivienda_CHANGED();
		
		if(auto_save_ENABLED)
		{
			guardar_address();
		}
		
		request.addCallbackParam("vivienda_CHANGED", vivienda_CHANGED);
		request.addCallbackParam("address_saved_OK", address_saved_OK);		
		request.addCallbackParam("neighborhood_text", colonia_name);
		request.addCallbackParam("vivienda_TOKEN_NEW", vivienda_TOKEN_NEW);
	}
	
	public final void listener_generar_vivienda_TOKEN(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		try
		{
			input_text = (HtmlInputText) evento.getComponent();
			
			input_text_value  = input_text.getValue().toString();		
			input_text_id     = input_text.getId().toString();
			
		} catch (ClassCastException e) {
			
			text_area = (HtmlInputTextarea) evento.getComponent();
			
			input_text_value  = text_area.getValue().toString();
			input_text_id     = text_area.getId().toString();
		}
		
		asignar_datos_address();		
		
		init_addres_TOKEN();
		
		vivienda_TOKEN_NEW = token.getAddress_TOKEN();
		
		asignar_vivienda_CHANGED();
		
		if(auto_save_ENABLED)
		{
			guardar_address();
		}
		
		request.addCallbackParam("address_saved_OK", address_saved_OK);
		request.addCallbackParam("input_text_id",    input_text_id);
		request.addCallbackParam("input_text_value", input_text_value);
		request.addCallbackParam("vivienda_TOKEN_NEW", vivienda_TOKEN_NEW);
	}
	
	public final void listener_tipo_vivienda_SELECTED(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one = (HtmlSelectOneMenu) evento.getComponent();
		
		tipo_vivienda_id = Integer.parseInt(select_one.getValue().toString());				
		
		tipo_vivienda_PK = new ResidencePK(tipo_vivienda_id, company_id);
		
		tipo_vivienda = service_residence.getResidenceById(tipo_vivienda_PK);
		
		init_addres_TOKEN();
		
		vivienda_TOKEN_NEW = token.getAddress_TOKEN();
		
		request.addCallbackParam("tipo_vivienda_id", tipo_vivienda_id);
		request.addCallbackParam("vivienda_TOKEN_NEW", vivienda_TOKEN_NEW);
	}
	
	public final void listener_motivo_del_cambio(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		text_area = (HtmlInputTextarea) evento.getComponent();
		
		motivo_del_cambio  = text_area.getValue().toString();
		
		change_control_bean.setWhyChangeData(motivo_del_cambio);	
		
		request.addCallbackParam("motivo_del_cambio", motivo_del_cambio);
	}
		
	public final void listener_guardar_edicion() 
	{							
		request = RequestContext.getCurrentInstance();				
				
		guardar_edicion_OK = guardar_change_control();
						
		if(guardar_edicion_OK || map_ubication_CHANGED)
		{									
			guardar_tipo_de_vivienda();			
			guardar_address();		
			
			init_bitacora_cambios();
			
			vivienda_TOKEN        = vivienda_TOKEN_NEW;			
			motivo_del_cambio     = "";
			map_ubication_CHANGED = false;
			
			if(colonia_id != null && colonia_Text == null)
			{				
				scriptColonia = "<script>"
						       + "$('#dvNeighborhoodText').css('display','none'); "
						       + "$('#dvNeighborhoodSel').css('display','block');"
						       + "</script>";
		
			}else if(colonia_Text != null && colonia_id == null){
			
				scriptColonia = "<script>"
							  + "$('#dvNeighborhoodSel').css('display','none'); "
							  + "$('#dvNeighborhoodText').css('display','block'); "
							  + "$('.neighborhood').val(0); "
							  + "$('#neighborhood_text').val('"+address.getNeighborhood_text()+"');"
							  + "$('#neighborhood_text').blur();"
							  + "</script>";
				
			}
		}
					
		request.addCallbackParam("address_saved_OK", address_saved_OK);
		request.addCallbackParam("guardar_edicion_OK", guardar_edicion_OK);
		request.addCallbackParam("vivienda_TOKEN", vivienda_TOKEN);
	}
	
	public final void listener_lista_coincidencias()
	{
		request = RequestContext.getCurrentInstance();				
		
		lista_address = service_address.getLista_coincidencias(NIVEL_1, address);	
		
		init_lista_coincidencias();
		
		request.addCallbackParam("address_type_id", address.getAddress_type_id());
		request.addCallbackParam("numero_coincidencias", lista_coincidencias.size());
	}
	
	public final void listener_lista_coincidencias_NIVEL_2()
	{
		request = RequestContext.getCurrentInstance();						
		
		lista_address = service_address.getLista_coincidencias(NIVEL_2, address);		
		
		init_lista_coincidencias();
				
		request.addCallbackParam("numero_coincidencias", lista_coincidencias.size());
	}
	
	public final boolean eliminar()
	{
		is_remove_OK = false;
		
		address = service_address.getMaxAddressByBeneficiario(prospectus_id, company_id, beneficiarie_id);
		
		if(address != null)
		{
			is_remove_OK = service_address.removeAddress(address.getAddressPK());
		}
		
		return is_remove_OK;
	}
	
	public final void asignar_mismo_domicilio()
	{
		System.out.println("EditorViviendaIMP.asignar_mismo_domicilio(): ");
		
		address_ORIGINAL = service_address.getMaxAddressByType(prospectus_id, company_id, CASA);
		
		address_PK = address.getAddressPK();
		
		if(address_PK == null)
		{		
			address_id = service_address.getMaxAddressId(prospectus_id, company_id);
			
			address_PK = new AddressPK();
			address_PK.setCompany_id(company_id);
			address_PK.setProspectus_id(prospectus_id);
			address_PK.setAddress_id(address_id);
			
			address.setAddressPK(address_PK);
			
			address_ORIGINAL.setAddressPK(address_PK);
			address_ORIGINAL.setBeneficiarie_id(beneficiarie_id);
			address_ORIGINAL.setAddress_type_id(address_type);
			
			address_saved_OK = service_address.add(address_ORIGINAL);
			
		} else {
		
			address_ORIGINAL.setAddressPK(address_PK);
			address_ORIGINAL.setBeneficiarie_id(beneficiarie_id);
			address_ORIGINAL.setAddress_type_id(address_type);
			
			address_saved_OK = service_address.update(address_ORIGINAL);		
		}
		
		init();
	}
}
