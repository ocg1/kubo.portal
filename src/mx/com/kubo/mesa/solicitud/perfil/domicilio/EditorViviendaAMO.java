package mx.com.kubo.mesa.solicitud.perfil.domicilio;

import java.util.ArrayList;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.ListNeighborhoodBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.gnNaturalPersonPK;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;

public abstract class EditorViviendaAMO extends EditorViviendaDMO
implements EditorViviendaIMO
{	
	protected void init_bitacora_cambios() 
	{
		if(bitacora_ENABLED)
		{
			change_control_bitacora = new ChangeBean();				
								
			lista_de_cambios = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id , afected_tables, afected_fields);
			
			if(lista_de_cambios != null && lista_de_cambios.size() > 0)
			{
				change_control_bitacora.setHasChange(true);
				change_control_bitacora.setLstChanges(lista_de_cambios);
				
			} else {
				change_control_bitacora.setHasChange(false);
			}
		}
	}
	
	protected void init_address() 
	{
		if(beneficiarie_id != null)
		{
			address = service_address.getMaxAddressByBeneficiario(prospectus_id, company_id, beneficiarie_id);
			
		} else {
			
			address = service_address.getMaxAddressByType(prospectus_id, company_id, address_type);
		}				
		
		if (address != null) 
		{
			address_ENABLED = true;
			
		} else {
			
			address = new Address();
			address_ENABLED = false;			
		}
		
/*		
		init_addres_TOKEN();
		
		vivienda_TOKEN = token.getAddress_TOKEN();
*/		
	}
	
	protected void init_address_INFO() 
	{		
		if(address_ENABLED)
		{		
			neighborhood_id       = address.getNeighborhood_id();
			neighborhood_text     = address.getNeighborhood_text();
			
			if(address.getNeighborhood() != null)
			{
				neighborhood_name = address.getNeighborhood().getName();
			}
			
			neighborhood_zip_code = address.getZip_code();
			neighborhood_latitude = address.getLatitude();
			neighborhood_longitud = address.getLongitude();
			
			if(neighborhood_id != null && neighborhood_id != 0)
			{
				colonia_id = neighborhood_id;				
			}
			
			init_colonia_ENABLED();
			
			if (neighborhood_zip_code != null && neighborhood_zip_code.length() > 0) 
			{		
				init_state_ENABLED();		
				
				zipcode = neighborhood_zip_code;
				
				colonias_por_codigo_postal =  service_vivienda.getAsentamientosByCP(neighborhood_zip_code);
				
				init_map_ubication();				
												
				if( address.getTownCat() != null)
				{
					delegacion_municipio_name = address.getTownCat().getName();
				}
				
				if( address.getStateCat() != null)
				{
					estado_name = address.getStateCat().getName();
				}	
				
				if( address.getState_id() != null)
				{
					stateId = address.getState_id();
				}
				
				if( address.getTown_id() != null )
				{
					townId = address.getTown_id();
				}				
			}						
		}
	}

	protected void init_addres_TOKEN() 
	{
		token = new AddressTokenIMP();
		token.setResidence(tipo_vivienda);
		token.setAntiguedad(length_residence);
		token.setAddress(address);
	}
	
	private void init_colonia_ENABLED() 
	{
		if(neighborhood_text != null)
		{
			colonia_Text = neighborhood_text;
			
			colonia_ENABLED      = "none";
			otra_colonia_ENABLED = "block";
			
		} else {
			colonia_ENABLED      = "block";
			otra_colonia_ENABLED = "none";
		}
		
		if( neighborhood_id != null)
		{
			colonia_id   = neighborhood_id;
			colonia_name = neighborhood_name;
			scriptColonia = "<script>"
						  + "$('#dvNeighborhoodText').css('display','none'); "
						  + "$('#dvNeighborhoodSel').css('display','block');"
						  + "</script>";
			
		} 
		
		if(neighborhood_id == null && neighborhood_text != null ) 
		{			
			colonia_id   = null;
			colonia_name = neighborhood_text;
			scriptColonia = "<script>"
						  + "$('#dvNeighborhoodSel').css('display','none'); "
						  + "$('#dvNeighborhoodText').css('display','block'); "
						  + "$('.neighborhood').val(0); "
						  + "$('#neighborhood_text_control').val('" + neighborhood_text + "');"
						  + "$('#neighborhood_text_control').blur();"
						  + "</script>";
		}
	}

	private void init_state_ENABLED() 
	{
		if(area == 'I')
		{
			state_ENABLED = true;
			
		} else {
			
			if( address.getStateCat() != null )
			{
				state_ENABLED =  address.getStateCat().getIs_enabled().equals("S");
				
			} else {
				
				state_ENABLED =  false;
			}
		}
	}
	
	private void init_map_ubication() 
	{
		if(neighborhood_latitude != null & neighborhood_longitud != null)
		{
			map_ubication = neighborhood_latitude + ", " + neighborhood_longitud + ", Mi domicilio";
		}
	}
			
	protected void init_coincidencias() 
	{
		coincidencias_NIVEL_1 = service_address.getCoincidencias_NUMBER(NIVEL_1, address);
		coincidencias_NIVEL_2 = service_address.getCoincidencias_NUMBER(NIVEL_2, address);
		
		if(coincidencias_NIVEL_1 != null)
		{
			if(coincidencias_NIVEL_1 > 1)
			{
				alarma_NIVEL_1 = coincidencias_NIVEL_1 + " personas más viven en la misma calle";
				
			} 
			
			if(coincidencias_NIVEL_1 == 1)
			{
				alarma_NIVEL_1 = coincidencias_NIVEL_1 + " persona más vive en la misma calle";
			}
		}
		
		if(coincidencias_NIVEL_2 != null)
		{
			if(coincidencias_NIVEL_2 > 1)
			{
				alarma_NIVEL_2 = coincidencias_NIVEL_2 + " personas más viven en la mismo domicilio";
				
			} 
			
			if(coincidencias_NIVEL_2 == 1)
			{
				alarma_NIVEL_2 = coincidencias_NIVEL_2 + " persona más vive en la mismo domicilio";
			}
		}
	}
	
	protected void init_lista_coincidencias() 
	{
		lista_coincidencias = new ArrayList<Coincidencia>(lista_address.size());
		
		for(Address domicilio: lista_address)
		{					
			coincidencia = new Coincidencia();
		
			prospectus_id = domicilio.getAddressPK().getProspectus_id();
			company_id    = domicilio.getAddressPK().getCompany_id();
			
			natural_person_PK = new gnNaturalPersonPK(prospectus_id, company_id);
			
			natural_person = service_natural_person.getNaturalPersonById(natural_person_PK);			
			proyect_loan   = service_proyect_loan.getMaxProyectLoanByProspect(prospectus_id, company_id);
			
			coincidencia.setAddress_type(domicilio.getAddress_type().getName());
			coincidencia.setNatural_person(natural_person);
			coincidencia.setProyect_loan(proyect_loan);
			
			token = new AddressTokenIMP();
			token.setResidence(tipo_vivienda);
			token.setAntiguedad(length_residence);
			token.setAddress(domicilio);
			
			String vivienda_TOKEN = token.getAddress_TOKEN();
			
			coincidencia.setAddress_TOKEN(vivienda_TOKEN);			
		
			lista_coincidencias.add(coincidencia);
		}
	}
	
	protected void asignar_datos_codigo_postal() 
	{		
		colonias_por_codigo_postal = service_vivienda.getAsentamientosByCP(zipcode);
				
		for (NeighborhoodCat colonia : colonias_por_codigo_postal) 
		{
			isEstados_ENABLED = colonia.getDelegMunicipio().getEstados().getIs_enabled();
			
			if(area == 'I' || isEstados_ENABLED.equals("S"))
			{
				state_ENABLED = true;
								
				delegacion_municipio_name  = colonia.getDelegMunicipio().getName();
				townId                     = colonia.getDelegMunicipio().getTownCatPK().getTown_id();
				estado_name                = colonia.getDelegMunicipio().getEstados().getName();
				stateId                    = colonia.getDelegMunicipio().getEstados().getStateCatPK().getState_id();
				
			} else {
				
				state_ENABLED   = false;
				delegacion_municipio_name  = null;
				estado_name = null;
				townId      = 0;					
				stateId     = 0;					
			}
			
			break;
		}

		if (colonias_por_codigo_postal.size() == 1)
		{
			colonia_id = colonias_por_codigo_postal.get(0).getNeighborhoodCatPK().getNeighborhood_id();
			
		} else {
			
			colonia_id = null;
		}
		
		if (zipcode != null && zipcode.length() > 0 && state_ENABLED) 
		{			
			address.setCountry_id      (MEXICO);
			address.setZip_code        (zipcode);	
			address.setNeighborhood_id (colonia_id);
			address.setState_id        (stateId);
			address.setTown_id         (townId);			
		}
		
		if(colonias_por_codigo_postal.size() != 0)
		{
			asignar_codigo_postal_JSON();
						
		} else {
			
			request.addCallbackParam("isValid", false);
		}
	}
	
	protected void asignar_datos_address() 
	{
		if(input_text_id.equals("edicion_vivienda_street"))
		{
			address.setStreet(input_text_value);
			
		} else if (input_text_id.equals("edicion_vivienda_address_number")) {
			
			address.setAddress_number(input_text_value);
			
		} else if (input_text_id.equals("apt_number")) {
			
			address.setApt_number(input_text_value);
			
		} else if (input_text_id.equals("mx_manzana")) {
			
			address.setMx_manzana(input_text_value);
			
		} else if (input_text_id.equals("mx_lote")) {
			
			address.setMx_lote(input_text_value);
			
		} else if (input_text_id.equals("first_street_reference")) {
			
			address.setFirst_street_reference(input_text_value);
			
		} else if (input_text_id.equals("second_street_reference")) {
			
			address.setSecond_street_reference(input_text_value);
			
		} else if (input_text_id.equals("punto_referencia")) {
			
			address.setDescription(input_text_value);
		}
	}
	
	private void asignar_codigo_postal_JSON()
	{
		lista_colonias_JSON = new ArrayList<ListNeighborhoodBean>();
		
		colonia_JSON = null;
		
		for (NeighborhoodCat iter : colonias_por_codigo_postal) 
		{
			int colonia_id = iter.getNeighborhoodCatPK().getNeighborhood_id();
			
			colonia_JSON = new ListNeighborhoodBean(colonia_id, iter.getName());
			
			lista_colonias_JSON.add(colonia_JSON);	
		}
		
		delegacion_municipio = colonias_por_codigo_postal.get(0).getDelegMunicipio();		
		estado               = colonias_por_codigo_postal.get(0).getDelegMunicipio().getEstados();
		
		if(colonias_por_codigo_postal.size() == 1)
		{
			colonia_id = colonias_por_codigo_postal.get(0).getNeighborhoodCatPK().getNeighborhood_id();
			colonia_Text = null ;
/*			
			scriptColonia = "<script>"
						  + "$('#dvNeighborhoodText').css('display','none'); "
						  + "$('#dvNeighborhoodSel').css('display','block');"
						  + "</script>";
*/						  
		}
		
		try 
		{
			request.addCallbackParam("colonias",             new JSONArray(lista_colonias_JSON.toArray(),true).toString());
			request.addCallbackParam("delegacion_municipio", delegacion_municipio.getName());
			request.addCallbackParam("is_estado_ENABLED",    estado.getIs_enabled().equals("S"));
			request.addCallbackParam("estado_name",          estado.getName());
			request.addCallbackParam("codigo_postal_valido", true);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	protected void asignar_vivienda_CHANGED() 
	{
		if(beneficiarie_id != null)
		{
			request.addCallbackParam("beneficiarie_id", beneficiarie_id);
		}
		
		if(vivienda_TOKEN_COMPARABLE != null && vivienda_TOKEN_COMPARABLE.length() > 0)
		{
			vivienda_CHANGED = !vivienda_TOKEN_NEW.equalsIgnoreCase(vivienda_TOKEN_COMPARABLE.trim().toLowerCase());
		}				
	}
	
	protected boolean guardar_change_control() 
	{
		change_control_bean.setOrigValue(vivienda_TOKEN);
		change_control_bean.setNewValue(vivienda_TOKEN_NEW);
		
		guardar_edicion_OK = service_change_control.registrar_change_control(change_control_bean, company_id, emisor_id, prospectus_id);
		
		return guardar_edicion_OK;
	}
	
	protected void guardar_tipo_de_vivienda() 
	{
		natural_person.setResidence_id(tipo_vivienda_id);
		
		service_natural_person.update(natural_person);
	}
	
	protected void guardar_address() 
	{
		if (address_ENABLED) 
		{			
			if(colonia_id != null && colonia_Text == null)
			{
				address.setNeighborhood_id(colonia_id);
				address.setNeighborhood_text(null);
				
			} else if ( colonia_id == null && colonia_Text != null ) {
				
				address.setNeighborhood_id(null);
				address.setNeighborhood_text(colonia_Text);
			}
			
			address_saved_OK = service_address.update(address);
			
		} else {
						
			address_id = service_address.getMaxAddressId(prospectus_id, company_id);
			
			address_PK = new AddressPK();
			address_PK.setCompany_id(company_id);
			address_PK.setProspectus_id(prospectus_id);
			address_PK.setAddress_id(address_id);
			
			address.setAddressPK(address_PK);
			address.setAddress_type_id(address_type);		
			address.setBeneficiarie_id(beneficiarie_id);
			
			address_saved_OK = service_address.add(address);
			
			address_ENABLED = true;
		}	
	}
}
