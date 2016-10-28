package mx.com.kubo.managedbeans.portal.password;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyectServiceLocator;

public final class DesbloqueoPorCorreoIMP extends DesbloqueoPorCorreoDMO
implements DesbloqueoPorCorreoIMO
{
	public final void notificar()
	{
		try 
		{							
			lista_receptores = new String []{"TO::" + email};
			
			request_notificar_config = new NotificadorConfigRequest();												
			request_notificar_config.setEvento_id(DESBLOQUEO_DE_PASSWORD);
			request_notificar_config.setCalled_FROM("DesbloqueoPorCorreoIMP.notificar()");	
			request_notificar_config.setCompany_id(company_id + "");
			request_notificar_config.setProspectus_id(prospectus_id + "");													
			request_notificar_config.setLista_receptores (lista_receptores);
			
			locator = new PublicProyectServiceLocator();
		
			kubo_services = locator.getPublicProyect();
			
			response = kubo_services.notificar(request_notificar_config);
			
			System.out.println("DesbloqueoPorCorreoIMP.response:");		
			System.out.println("> status  = " + response.getStatus());
			System.out.println("> folio   = " + response.getFolio());
			System.out.println("> message = " + response.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
