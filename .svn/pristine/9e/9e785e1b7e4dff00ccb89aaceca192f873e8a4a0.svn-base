package mx.com.kubo.registro.verificacion;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;

public abstract class PersonaBloqueadaAMO extends PersonaBloqueadaDMO
{
	protected void init_change_control(String original_value, String new_value)
	{
		change_control    = new Change_control();
		change_control_PK = new Change_controlPK();
		
		change_control_PK.setProspectus_id(prospectus_id);
		change_control_PK.setCompany_id(company_id);
		
		change_control.setChange_controlPK(change_control_PK);
		change_control.setAfected_table("ln_membership");
		change_control.setField("is_pld_blocked");
		change_control.setChange_prospectus_id(prospectus_id);
		change_control.setOriginal_value(original_value);
		change_control.setNew_value(new_value);		
		
		switch(tipo_operacion)
		{
			case OPERACION_PERSONA_BLOQUEADA:
				change_control.setComments("Operación de Persona Bloqueada");
			break;
				
			case REGISTRO_PERSONA_BLOQUEADA:				
				change_control.setComments("Registro de Persona Bloqueada");
			break;
			
			case BUSQUEDA_PERSONA_BLOQUEADA:
				change_control.setComments("Coincidencia de Persona Bloqueada");
			break;
		}
		
		change_control.setIp(IP_address_client);
		
		change_control_OK = service_change_control.addChangeControl(change_control, prospectus_id, company_id);
	}
	
	protected void init_access() 
	{
		access = new Access();
		access.setCompany_id(company_id);
		access.setProspectus_id(prospectus_id);
		access.setScreen_id(OPERACION_PERSONA_BLOQUEADA);
		access.setPercentage(0);
		access.setUser_agent(user_agent);
		access.setDevice_info(device_info);
		
		access.setProspectus_id_coach (prospectus_id_coach);
		access.setAccess_from		  (access_from);
		access.setVersion_description (version_description);
		
		
		service_access.add(access,false);
	}
	
	protected void notificar(String evento_id)
	{
		try 
		{													
			request_notificar_config = new NotificadorConfigRequest();												
			request_notificar_config.setEvento_id(evento_id);
			request_notificar_config.setCalled_FROM("PersonaBloqueadaIMP.notificar()");	
			request_notificar_config.setCompany_id(company_id + "");
			request_notificar_config.setProspectus_id(prospectus_id + "");		
			
			locator = new PublicProyectServiceLocator();
		
			kubo_services = locator.getPublicProyect();
			
			response = kubo_services.notificar(request_notificar_config);
			
			System.out.println("PersonaBloqueadaIMP.response:");		
			System.out.println("> status  = " + response.getStatus());
			System.out.println("> folio   = " + response.getFolio());
			System.out.println("> message = " + response.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
