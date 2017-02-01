package mx.com.kubo.managedbeans.mesa.administracion;

import java.text.SimpleDateFormat;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.model.MailLog;

public abstract class AdministrationProfileAMO extends  AdministrationProfileDMO
{
	protected void init_email_date() 
	{
		SimpleDateFormat formatter 	=  new SimpleDateFormat("dd 'de' MMMMM 'del' yyyy hh:mm:ss");
		
		MailLog log = service_mail_log.getMaxMailLog(Integer.parseInt(RESUMEN_TABLERO_NORMATIVO));
		
		email_date_ENABLED = false;
		
		if(log != null)
		{		
			email_date = formatter.format(log.getEmail_date());
			
			email_date_ENABLED = true;
		}
	}
	
	protected void init_notificar_evento()
	{
		try 
		{													
			request_notificar_config = new NotificadorConfigRequest();										
			request_notificar_config.setCompany_id("1");
			request_notificar_config.setProspectus_id("0");	
			request_notificar_config.setEvento_id(RESUMEN_TABLERO_NORMATIVO);
			request_notificar_config.setCalled_FROM("AdministrationProfileAMO.init_notificar_evento()");						
			
			locator = new PublicProyectServiceLocator();
		
			kubo_services = locator.getPublicProyect();
			
			response = kubo_services.notificar(request_notificar_config);
			
			sb_exito = new StringBuilder();
			sb_exito.append("AdministrationProfileAMO.response:").append("\n");
			sb_exito.append("> status  = " + response.getStatus()).append("\n");
			sb_exito.append("> folio   = " + response.getFolio()).append("\n");
			sb_exito.append("> message = " + response.getMessage());						
			
			notificar_OK = true;
			email_date_ENABLED = true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			sb_exito = new StringBuilder();
			sb_exito.append(e.getMessage());
			
			notificar_OK = false;
			email_date_ENABLED = false;
		}
	}
}
