package mx.com.kubo.session;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProspectusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class SessionServiceDMO 
{
	protected FacesContext    faces;
	protected SessionBean     sesion;
	protected ELResolver      resolver;
	protected ELContext       context_EL;	
	protected ExternalContext external;
	
	protected Integer prospectus_id, company_id;
	
	protected Prospectus        prospecto;
	protected ProspectusPK      prospecto_PK;
	protected gnNaturalPersonPK persona_PK;
	
	
	@Autowired @Qualifier("prospectusServiceImp")
	protected ProspectusService prospecto_service;
	
	@Autowired @Qualifier("naturalPersonServiceImp")
	protected NaturalPersonService persona_service;

	public final void setProspecto_service(ProspectusService service) 
	{
		prospecto_service = service;
	}

	public final void setPersona_service(NaturalPersonService service) {
		persona_service = service;
	}
	
}
