package mx.com.kubo.managedbeans.portal.password;

import java.util.Date;

import javax.faces.context.FacesContext;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.managedbeans.HeaderBean;
import mx.com.kubo.model.SecQuestPoolPK;

public abstract class PreguntaSeguridadAMO extends PreguntaSeguridadDMO 
{	
	protected void init_security_question_pool() 
	{
		security_question_pool_PK = new SecQuestPoolPK();
		
		security_question_pool_PK.setCompany_id(company_id);
		security_question_pool_PK.setProspectus_id(prospectus_id);
		security_question_pool_PK.setSecurity_question_id(security_question_id);
		
		security_question_pool = service_security_question_pool.getSecQuestPoolByPK(security_question_pool_PK);
		
		security_question_pool.setDate_used(new Date());
		
		service_security_question_pool.updateSecQuest(security_question_pool);
		
		security_question_pool = service_security_question_pool.getNextRandomSecQuestPool(prospectus_id, company_id);
	}
	
	protected void init_membership() 
	{
		if(security_question_pool != null)
		{
			security_question_id = security_question_pool.getPk().getSecurity_question_id();
			security_answer      = security_question_pool.getAnswer();
			
			membership.setSecurity_question_id(security_question_id);
			membership.setAnswer(security_answer);
		}
		
		membership.setFailed_token_attempts(0);
		membership.setFailed_question_attempts(0);
		membership.setFailed_login_attempts(0);
		membership.setIs_blocked("N");
			
		service_membership.update(membership);
	}
	
	protected void init_notificar_evento()
	{
		try 
		{							
			lista_receptores = new String []{"TO::" + membership.getEmail()};
			
			request_notificar_config = new NotificadorConfigRequest();												
			request_notificar_config.setEvento_id(CONFIRMACION_DESBLOQUEO_PASSWORD);
			request_notificar_config.setCalled_FROM("PreguntaSeguridadAMO.init_notificar_evento()");	
			request_notificar_config.setCompany_id(company_id + "");
			request_notificar_config.setProspectus_id(prospectus_id + "");													
			request_notificar_config.setLista_receptores (lista_receptores);
			
			locator = new PublicProyectServiceLocator();
		
			kubo_services = locator.getPublicProyect();
			
			response = kubo_services.notificar(request_notificar_config);
			
			System.out.println("PreguntaSeguridadAMO.print_response():");		
			System.out.println("> status  = " + response.getStatus());
			System.out.println("> folio   = " + response.getFolio());
			System.out.println("> message = " + response.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	protected void init_login() 
	{				
		faces = FacesContext.getCurrentInstance();	
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
						
		header = (HeaderBean) resolver.getValue(elContext, null, "headerBean");
		
		header.setPassword(password);
		header.setUser(email);		
		header.iniciaSesion();
								
		login_OK = true;
	}
}
