package mx.com.kubo.managedbeans.mesa.autenticacion;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.mesa.autenticacion.Autenticacion;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;

public abstract class TableroAutenticacionAMO extends TableroAutenticacionDMO
{
	protected void init_lista_preguntas() 
	{
		sb = new StringBuilder();
		
		for(int index = 0; index < MAX_NUMBER_QUESTIONS; index++)
		{
			autenticacion = lista_preguntas.get(index);
			
			authentication = autenticacion.getAuthentication();
			
			authentication_id = authentication.getPk().getAuthentication_id();
			
			sb.append(authentication_id).append("::");
			
			autenticacion_id = Autenticacion.getInstance(authentication_id);
			
			switch(autenticacion_id)
			{	
				case BIRTH_DATE:
					birthdate = autenticacion;
				break;
				
				case BIRTH_PLACE:
					birthplace = autenticacion;
				break;
								
				case DELEGACION_MUNICIPIO:			
					domicilio = autenticacion;
				break;
				
				case CODIGO_POSTAL:
					zipcode = autenticacion;
				break;
				
				case TELEFONO_CELULAR:
					telefono = autenticacion;
				break;
				
				case CORREO_ELECTRONICO:
					cuenta = autenticacion;
				break;

				default: break;
			}
		}
		
		autenticacion_id_TOKEN = sb.toString();
	}
	
	protected void init_autenticacion() 
	{
		autenticacion = lista_preguntas.get(respuesta_id);
		
		autenticacion_OK = autenticacion.isValue_ENABLED();
		original_value   = autenticacion.getValue_ORIGINAL();
		new_value        = autenticacion.getValue_NEW();
		authentication   = autenticacion.getAuthentication();
		
		sb = new StringBuilder();
		
		if(autenticacion_OK)
		{
			sb.append("CORRECTO");						
			
		} else {
			
			sb.append("INCORRECTO");
		}					
		
		sb.append(" :: ").append(authentication.getPk().getAuthentication_id());
		sb.append(" :: ").append(authentication.getDescription());	
		
		comments = sb.toString();
		
		respuestas_CORRECTAS[respuesta_id] = autenticacion_OK;
		
		contador_CORRECTAS = 0;
		
		for(int i = 0; i < MAX_NUMBER_QUESTIONS; i++)
		{
			if(respuestas_CORRECTAS[i]) 
			{
				contador_CORRECTAS++;
			}
		}
		
		desbloquear_password_ENABLED = contador_CORRECTAS == MAX_NUMBER_QUESTIONS ? true : false;
	}
	
	protected void init_change_control() 
	{
		change_control   = new Change_control();
		change_controlPK = new Change_controlPK();
		
		change_controlPK.setProspectus_id(prospectus_id);
		change_controlPK.setCompany_id(company_id);
		
		change_control.setChange_controlPK(change_controlPK);
		change_control.setAfected_table("gn_authentication_pool");
		change_control.setField("authentication_id");
		change_control.setChange_prospectus_id(change_prospectus_id);
		change_control.setOriginal_value(original_value);
		change_control.setNew_value(new_value);		
		change_control.setComments(comments);
		change_control.setIp(IP_address_client);
		
		change_control_OK = service_change_control.addChangeControl(change_control, prospectus_id, company_id);		
	}
	
	protected void init_notificar_evento()
	{
		try 
		{							
			lista_receptores = new String []{"TO::" + membership.getEmail()};
			
			request_notificar_config = new NotificadorConfigRequest();												
			request_notificar_config.setEvento_id(CONFIRMACION_DESBLOQUEO_PASSWORD);
			request_notificar_config.setCalled_FROM("TableroAutenticacionAMO.init_notificar_evento()");	
			request_notificar_config.setCompany_id(company_id + "");
			request_notificar_config.setProspectus_id(prospectus_id + "");													
			request_notificar_config.setLista_receptores (lista_receptores);
			
			locator = new PublicProyectServiceLocator();
		
			kubo_services = locator.getPublicProyect();
			
			response = kubo_services.notificar(request_notificar_config);
			
			System.out.println("TableroAutenticacionAMO.print_response():");		
			System.out.println("> status  = " + response.getStatus());
			System.out.println("> folio   = " + response.getFolio());
			System.out.println("> message = " + response.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
