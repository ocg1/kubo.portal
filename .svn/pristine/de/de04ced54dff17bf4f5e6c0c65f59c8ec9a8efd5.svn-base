package mx.com.kubo.mesa.solicitud.perfil.domicilio;

import java.util.ArrayList;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.gnNaturalPersonPK;

public abstract class DomicilioAMO extends DomicilioDMO 
{
	protected void init_coincidencias() 
	{
		coincidencias_NIVEL_1 = service_address.getCoincidencias_NUMBER(NIVEL_1, address);
		coincidencias_NIVEL_2 = service_address.getCoincidencias_NUMBER(NIVEL_2, address);		
		
		if(coincidencias_NIVEL_1 != null)
		{
			if(coincidencias_NIVEL_1 > 1)
			{		
				alarma_NIVEL_1 = coincidencias_NIVEL_1 + " personas más viven en la misma calle";
				
				if(address_type_id > 1 && address_type_id < 5)
				{
					alarma_NIVEL_1 = coincidencias_NIVEL_1 + " personas más trabajan en la misma calle";
				}				
			} 
			
			if(coincidencias_NIVEL_1 == 1)
			{
				alarma_NIVEL_1 = coincidencias_NIVEL_1 + " persona más vive en la misma calle";
				
				if(address_type_id > 1 && address_type_id < 5)
				{
					alarma_NIVEL_1 = coincidencias_NIVEL_1 + " persona más trabaja en la misma calle";
				}	
			}
		}
		
		if(coincidencias_NIVEL_2 != null)
		{
			if(coincidencias_NIVEL_2 > 1)
			{
				alarma_NIVEL_2 = coincidencias_NIVEL_2 + " personas más viven en el mismo domicilio";
				
				if(address_type_id > 1 && address_type_id < 5)
				{
					alarma_NIVEL_2 = coincidencias_NIVEL_2 + " personas más trabajan en el mismo domicilio";
				}	
				
			} 
			
			if(coincidencias_NIVEL_2 == 1)
			{
				alarma_NIVEL_2 = coincidencias_NIVEL_2 + " persona más vive en el mismo domicilio";
				
				if(address_type_id > 1 && address_type_id < 5)
				{
					alarma_NIVEL_2 = coincidencias_NIVEL_2 + " persona más trabaja en el mismo domicilio";
				}	
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
			token.setAddress(domicilio);
			
			String vivienda_TOKEN = token.getAddress_activity_TOKEN();
			
			coincidencia.setAddress_TOKEN(vivienda_TOKEN);			
		
			lista_coincidencias.add(coincidencia);
		}
	}
	
	protected void init_change_control_bean() 
	{
		change_control_bean = new ChangeBean();				
								
		lista_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id , afected_tables, afected_fields);
			
		if(lista_change_control != null && lista_change_control.size() > 0)
		{
			change_control_bean.setHasChange(true);
			change_control_bean.setLstChanges(lista_change_control);
			
		} else {
			
			change_control_bean.setHasChange(false);
		}
		
	}
	
	protected void asignar_datos_address() 
	{
		if(input_text_id.equals("actividad-street"))
		{
			address.setStreet(input_text_value);
			
		} else if (input_text_id.equals("actividad-address-number")) {
			
			address.setAddress_number(input_text_value);
			
		} else if (input_text_id.equals("actividad-apt-number")) {
			
			address.setApt_number(input_text_value);
			
		} else if (input_text_id.equals("actividad-mx-manzana")) {
			
			address.setMx_manzana(input_text_value);
			
		} else if (input_text_id.equals("actividad-mx-lote")) {
			
			address.setMx_lote(input_text_value);
			
		} else if (input_text_id.equals("first_street_reference")) {
			
			address.setFirst_street_reference(input_text_value);
			
		} else if (input_text_id.equals("second_street_reference")) {
			
			address.setSecond_street_reference(input_text_value);
			
		} else if (input_text_id.equals("punto_referencia")) {
			
			address.setDescription(input_text_value);
		}
	}
	
	protected void add_change_control(String original_value, String new_value) 
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
			change_control.setAfected_table("gn_address");	
			change_control.setField("address_TOKEN");
			change_control.setField_type_id(address_type_id);
			
			change_control.setOriginal_value(original_value);
			change_control.setNew_value(new_value);
			
			change_control.setComments(null);
			change_control.setIp(IP_address_client);
			
			change_control_OK = service_change_control.persist(change_control);
		}
	}
}
