package mx.com.kubo.managedbeans.portal.password;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.portal.AccessIMP;
import mx.com.kubo.portal.reader.ParameterReaderIMP;

public abstract class DesbloqueoPasswordAMO extends DesbloqueoPasswordDMO 
{	
	protected void init_sesion() 
	{
		reader = new ParameterReaderIMP();
		reader.setFaces(faces);
		reader.setSesion_ENABLED(false);
		reader.init_sesion();

		access_from = reader.getAccess_from();						  
		
		if(access_from != null)
		{
			sesion = reader.getSesion();
			
			company_id    = sesion.getCompany_id();
			prospectus_id = sesion.getProspectus_id();
			
			auditor = new AccessIMP();		
			auditor.setSesion(sesion);
			auditor.setService_access(service_access);
			auditor.setScreen_id(SCREEN_ID_DESBLOQUEO_PASSWORD);
			auditor.setAccess_from(access_from);
		}
	}
	
	protected void init_desbloqueo() 
	{
		display_desbloqueo_MSG = NONE;
		
		if(sesion != null)
		{						
			membership_PK = new MembershipPK();
			membership_PK.setCompany_id(company_id);
			membership_PK.setProspectus_id(prospectus_id);
			
			membership = service_membership.getMembershipById(membership_PK);
			
			if(membership != null)
			{
				email = membership.getEmail();
				
				membership.setFailed_token_attempts(0);
				membership.setFailed_login_attempts(0);
				membership.setIs_blocked("N");												
				
				service_membership.update(membership);
								
				init_notificar_evento();				
				
				display_desbloqueo_MSG = BLOCK;										
			}				
		}
	}
	
	protected void init_notificar_evento()
	{
		try 
		{							
			lista_receptores = new String []{"TO::" + membership.getEmail()};
			
			request_notificar_config = new NotificadorConfigRequest();												
			request_notificar_config.setEvento_id(CONFIRMACION_DESBLOQUEO_PASSWORD);
			request_notificar_config.setCalled_FROM("DesbloqueoPasswordAMO.init_notificar_evento()");	
			request_notificar_config.setCompany_id(company_id + "");
			request_notificar_config.setProspectus_id(prospectus_id + "");													
			request_notificar_config.setLista_receptores (lista_receptores);
			
			locator = new PublicProyectServiceLocator();
		
			kubo_services = locator.getPublicProyect();
			
			response = kubo_services.notificar(request_notificar_config);
			
			System.out.println("DesbloqueoPasswordAMO.print_response():");		
			System.out.println("> status  = " + response.getStatus());
			System.out.println("> folio   = " + response.getFolio());
			System.out.println("> message = " + response.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
