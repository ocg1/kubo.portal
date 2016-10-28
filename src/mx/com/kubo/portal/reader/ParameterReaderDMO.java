package mx.com.kubo.portal.reader;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.tools.Utilities;

public abstract class ParameterReaderDMO
implements ParameterReaderIMO
{
	protected NaturalPersonService service_natural_person;
	protected MembershipService service_membership;
	
	protected    FacesContext faces;	
	protected ExternalContext external;
	protected       ELContext context;
	protected ELResolver     resolver;
	
	protected HttpSession http_sesion;
	protected HttpServletRequest servlet;				
	
	protected SessionBean sesion;
	
    protected   NaturalPerson   person;
	protected gnNaturalPersonPK person_PK;
	protected Prospectus prospectus;
	protected MembershipPK membership_pk;
	
	protected SimpleDateFormat date_format;
	
	protected String company;
	protected String prospecto;	
	protected String prospecto_viewed;
	protected String proyect_loan;
	protected String proyect;
	protected String access_CONFIG;			
	protected String access_client_IP;
	protected String page_title;
	protected String last_login;
	
	protected String credit_id;
	protected String access_from;
	
	protected String phone_id;
	
	protected Integer company_id;
	protected Integer prospectus_id;
	protected Integer prospectus_id_viewed;
	protected Integer proyect_loan_id;
	protected Integer proyect_id;
	
	protected boolean access_OK;
	protected boolean sesion_ENABLED;
	
	protected final int SCREEN_REFERENCIAS_PERSONALES = 70;
	 	
	protected Map<String, String> parameter;
	
	protected ParameterReaderDMO()
	{
		service_natural_person = Utilities.findBean("naturalPersonServiceImp");
		    service_membership = Utilities.findBean("membershipServiceImp");
		    
		    sesion_ENABLED = true;
	}
	
	public final void setFaces(FacesContext faces) 
	{
		this.faces = faces;
				 
		 context = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		external = faces.getExternalContext();				

		servlet = (HttpServletRequest) external.getRequest();
		
		access_client_IP  = servlet.getHeader("X-FORWARDED-FOR");  
	    
		if(access_client_IP == null)
		{
			access_client_IP = servlet.getRemoteAddr();
		}
		
		parameter = external.getRequestParameterMap();
		
		http_sesion = (HttpSession) external.getSession(false);
	}		

	public void setSesion_ENABLED(boolean sesion_ENABLED) 
	{
		this.sesion_ENABLED = sesion_ENABLED;
	}

	public final SessionBean getSesion() 
	{
		return sesion;
	}
	
	public final String getPhone_id() 
	{		
		return phone_id;
	}

	public final String getPage_title() 
	{
		return page_title;
	}

	public final Integer getProyect_loan_id() 
	{
		return proyect_loan_id;
	}
	
	public final Integer getProyect_id() 
	{
		return proyect_id;
	}
	
	public String getCredit_id()
	{
		return credit_id;
	}
	
	public String getAccess_from()
	{
		return access_from;
	}
}
