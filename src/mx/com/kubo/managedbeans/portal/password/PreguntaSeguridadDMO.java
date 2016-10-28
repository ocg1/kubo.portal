package mx.com.kubo.managedbeans.portal.password;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.managedbeans.HeaderBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.SecQuestPoolPK;
import mx.com.kubo.model.SecurityQuestionPool;
import mx.com.kubo.model.SystemNotificationLog;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.SecurityQuestionPoolService;
import mx.com.kubo.services.impl.SystemParamServiceImp;
import mx.com.kubo.tools.Utilities;

public abstract class PreguntaSeguridadDMO 
implements PreguntaSeguridadIMO
{
	protected SystemParamServiceImp       service_system_param;
	protected SecurityQuestionPoolService service_security_question_pool;
	protected MembershipService           service_membership;
	
	protected FacesContext    faces;
	protected RequestContext  request;	
	protected ELResolver      resolver;
	protected ELContext       elContext;	
	
	protected HtmlInputSecret input_secret;
	
	protected Membership membership;
	protected HeaderBean header;
	
	protected SystemNotificationLog notification_log;
	protected NotificadorConfigRequest request_notificar_config;
	protected PublicProyectServiceLocator locator;
	protected PublicProyect kubo_services;
	protected WsResponse    response;
	
	protected SystemParam   system_param;
	protected SystemParamPK system_param_PK;
	protected SecurityQuestionPool security_question_pool;
	protected SecQuestPoolPK       security_question_pool_PK;
	
	protected String security_answer;
	protected String security_answer_INPUT;
	protected String security_answer_UPP;
	protected String security_answer_LOW;
	protected String email;
	protected String password;
	
	private String security_question;
	
	protected final String CONFIRMACION_DESBLOQUEO_PASSWORD = "37";
	
	protected String [] lista_receptores;
	
	protected Integer security_question_id;
		
	protected int company_id;
	protected int prospectus_id;		
	protected int failed_question_attempts;	
	protected int max_question_attempts;
	
	protected final static int SECURITY_QUESTION_ATTEMPTS = 64;
	
	protected boolean security_answer_OK;
	protected boolean security_question_ENABLED;
	protected boolean max_question_attempts_ENABLED;
	protected boolean login_OK;
	
	protected PreguntaSeguridadDMO()
	{
		service_system_param           = Utilities.findBean("systemParamServiceImp");
		service_security_question_pool = Utilities.findBean("securityQuestionPoolServiceImp");
		service_membership             = Utilities.findBean("membershipServiceImp");
	}
	
	public final void setMembership(Membership membership) 
	{
		this.membership = membership;
		
		prospectus_id = membership.getMembershipPK().getProspectus_id();
		company_id    = membership.getMembershipPK().getCompany_id();	
		email         = membership.getEmail();
		password      = membership.getPassword();
				
		failed_question_attempts = membership.getFailed_question_attempts();
		security_question_id     = membership.getSecurity_question_id();
		
		if(security_question_id != null && membership.getSecurityQuestion() != null)
		{					
			security_question = membership.getSecurityQuestion().getQuestion();
			security_answer   = membership.getAnswer();
			
			security_question_ENABLED = true;		
			
			system_param_PK = new SystemParamPK(SECURITY_QUESTION_ATTEMPTS, company_id);
			
			system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
			
			max_question_attempts = Integer.parseInt(system_param.getValue());
		}
	}

	public final boolean isSecurity_question_ENABLED() 
	{
		return security_question_ENABLED;
	}

	public final String getSecurity_question() 
	{
		return security_question;
	}
}
