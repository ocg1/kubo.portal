package mx.com.kubo.managedbeans.perfil;


import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.SecQuestPoolPK;
import mx.com.kubo.model.SecurityQuestionPool;

public abstract class PerfilControllerAMO extends PerfilControllerDMO
{
	public final void init_preguntas_seguridad() 
	{				
		if(pool_preguntas_seguridad != null && pool_preguntas_seguridad.size() <= 3)
		{		
			question_pool = pool_preguntas_seguridad.get(0);
			
			answer_1      = question_pool.getAnswer();
			question_id_1 = question_pool.getPk().getSecurity_question_id();
			
			question_pool = pool_preguntas_seguridad.get(1);
			
			answer_2      = question_pool.getAnswer();
			question_id_2 = question_pool.getPk().getSecurity_question_id();
			
			question_pool = pool_preguntas_seguridad.get(2);
			
			answer_3      = question_pool.getAnswer();
			question_id_3 = question_pool.getPk().getSecurity_question_id();
		}
	}

	protected void init_notificar_evento() 
	{
		try 
		{							
			lista_receptores = new String []{"TO::" + membership.getEmail()};
			
			request_notificar_config = new NotificadorConfigRequest();												
			request_notificar_config.setEvento_id(CONFIRMACION_CAMBIO_PASSWORD);
			request_notificar_config.setCalled_FROM("PerfilControllerIMP.saveNewPass()");	
			request_notificar_config.setCompany_id(company_id + "");
			request_notificar_config.setProspectus_id(prospectus_id + "");													
			request_notificar_config.setLista_receptores (lista_receptores);
			
			locator = new PublicProyectServiceLocator();
		
			kubo_services = locator.getPublicProyect();
			
			response = kubo_services.notificar(request_notificar_config);
			
			System.out.println("PerfilControllerIMP.print_response():");		
			System.out.println("> status  = " + response.getStatus());
			System.out.println("> folio   = " + response.getFolio());
			System.out.println("> message = " + response.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	protected void init_change_control() 
	{
		change_control   = new Change_control();
		change_controlPK = new Change_controlPK();
		
		change_controlPK.setProspectus_id(prospectus_id);
		change_controlPK.setCompany_id(company_id);
		
		change_control.setChange_controlPK(change_controlPK);
		change_control.setChange_prospectus_id(prospectus_id);
		change_control.setAfected_table("ln_membership");
		change_control.setField("notification_preference_id");		
		change_control.setOriginal_value(preference_id_ORIGINAL + "");
		change_control.setNew_value(preference_id_NEW + "");		
		change_control.setComments(comments);
		change_control.setIp(ip_address);
		
		change_control_OK = service_change_control.addChangeControl(change_control, prospectus_id, company_id);		
	}
	
	protected void actulizar_question_pool() 
	{
		question_pool_PK = new SecQuestPoolPK();
		
		question_pool_PK.setCompany_id(company_id);
		question_pool_PK.setProspectus_id(prospectus_id);
		question_pool_PK.setSecurity_question_id(question_id);
		
		question_pool = new SecurityQuestionPool();
		
		question_pool.setPk(question_pool_PK);
		question_pool.setAnswer(respuesta);
		question_pool.setDate_used(null);
		
		is_saved_OK = service_pool_preguntas.updateSecQuest(question_pool);
		
		System.out.printf("\nPerfilControllerAMO.actulizar_question_pool(): " + is_saved_OK);
		
		question_pool = service_pool_preguntas.getSecQuestPoolByPK(question_pool_PK);
		
		pool_preguntas_seguridad.remove(pregunta_selected - 1);
		pool_preguntas_seguridad.add(pregunta_selected - 1, question_pool);
	}
	
	protected void guardar_question_pool(int question_selected) 
	{
		question_pool_PK = new SecQuestPoolPK();
		
		question_pool_PK.setCompany_id(company_id);
		question_pool_PK.setProspectus_id(prospectus_id);
		question_pool_PK.setSecurity_question_id(question_selected);
		
		is_saved_OK = service_pool_preguntas.removeSecQuest(question_pool_PK);
		
		question_pool_PK.setSecurity_question_id(question_id);
		
		question_pool = new SecurityQuestionPool();
		
		question_pool.setPk(question_pool_PK);
		question_pool.setAnswer(respuesta);
		question_pool.setDate_used(null);
		
		is_saved_OK = service_pool_preguntas.saveSecQuest(question_pool);	
				
		System.out.printf("\nPerfilControllerAMO.guardar_question_pool(): " + is_saved_OK);
		
		question_pool = service_pool_preguntas.getSecQuestPoolByPK(question_pool_PK);
		
		pool_preguntas_seguridad.remove(pregunta_selected - 1);
		pool_preguntas_seguridad.add(pregunta_selected - 1, question_pool);
	}
	
	protected String getCompleteNameStr() 
	{
		if (person != null)
			if (person.getFirst_name() != null
					&& person.getFather_last_name() != null
					&& !person.getFirst_name().isEmpty()
					&& !person.getFather_last_name().isEmpty()) 
		{

				if (person.getMiddle_name()!=null&&!person.getMiddle_name().isEmpty())
				{
					completeName = person.getFirst_name().trim() + " " + person.getMiddle_name().trim();
				} else {
					completeName = person.getFirst_name().trim();
				}

				completeName += " " + person.getFather_last_name().trim();
				
				if (person.getMother_last_name()!=null&&!person.getMother_last_name().isEmpty())
				{
					completeName += " "+ person.getMother_last_name().trim();
				}
		}
		
		return completeName;
	}
	
	protected boolean saveChangeData(String table,String field,String origValue,String newValue,String comment)
	{
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
		String ipAddress  = httpServletRequest.getHeader("X-FORWARDED-FOR");
		
	    if(ipAddress == null)  
	    {  
	    	ipAddress = httpServletRequest.getRemoteAddr();  
	    }
	    
	    prospectus_id = person.getNatPerPK().getProspectus_id();
	    company_id    = person.getNatPerPK().getCompany_id();
	    
	    change_controlPK = new Change_controlPK();
	    change_controlPK.setProspectus_id(prospectus_id);
	    change_controlPK.setCompany_id(company_id);
		
		change_control = new Change_control();
		change_control.setChange_controlPK(change_controlPK);
		change_control.setChange_prospectus_id(sesion.getProspectus_id());
		change_control.setAfected_table(table);
		change_control.setIp(ipAddress);			
		change_control.setOriginal_value(origValue);
		change_control.setNew_value(newValue);
		change_control.setField(field);
		change_control.setComments(comment);
		
		if(service_change_control.addChangeControl(change_control,prospectus_id,company_id))
		{
			return true;
			
		} else {
			
			return false;		
		}
	}
	
	protected void registar_bitacora_access(int screen_id) 
	{
		access = new Access();
		
		access.setScreen_id(screen_id);
		access.setPercentage(0);
		
		access.setCompany_id          (sesion.getCompany_id());
		access.setProspectus_id       (sesion.getProspectus_id());
		access.setProspectus_id_viewed(sesion.getProspectus_id());		
		
		access.setWeb_browser         (sesion.getNamebrawser());
		access.setWeb_browser_version (sesion.getVersionbrawser());
		access.setOp_system           (sesion.getOsbrawser());
		access.setHorizontal_size     (sesion.getBrowser_width());
		access.setVertical_size       (sesion.getBrowser_height());
		access.setIpaddress           (sesion.getIP_address_client());
		access.setUser_agent          (sesion.getUser_agent());
		access.setDevice_info         (sesion.getDevice_info());
		
		access.setUrl_access		  (sesion.getUrl_access());
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		service_access.add(access, true);
		
		System.out.printf("\nPerfilControllerAMO.registar_bitacora_access(): OK");
	}
}
