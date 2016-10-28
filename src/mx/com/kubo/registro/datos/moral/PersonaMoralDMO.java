package mx.com.kubo.registro.datos.moral;

import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlSelectOneRadio;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.SocietyType;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.catalogos.ServiceCatalogosIMO;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;

public abstract class PersonaMoralDMO 
implements PersonaMoralIMO
{
	protected ProspectusService     service_prospectus;
	protected NaturalPersonService  service_natural_person;
	protected ServiceCatalogosIMO   service_catalogos;
	protected Change_controlService service_change_control;
	
	protected RequestContext request;
	
	protected HtmlSelectOneMenu  select_menu;
	protected HtmlSelectOneRadio select_radio;
	protected HtmlInputText input_text;
	
	protected SessionBean   sesion;
	protected Prospectus    prospecto;
	protected NaturalPerson person;
	
	protected Change_controlPK change_control_PK;
	protected Change_control   change_control;
	
	protected List<SocietyType> lista_society_type;
	
	protected String person_type_id;
	protected String legal_name;
	protected String legal_name_ORIGINAL;
	protected String mx_company_rfc;
	protected String company_RFC_ORIGINAL;
	protected String IP_address_client;
	protected String original_value;
	protected String new_value;
	protected String comments;
	
	
	protected final String NATURAL_PERSON;
	protected final String PROSPECTUS;
	
	protected Integer society_type_id;
	protected Integer company_id;
	protected Integer prospectus_id;
	
	protected boolean persona_moral_ENABLED;
	protected boolean change_OK;
	
	protected PersonaMoralDMO()
	{
		NATURAL_PERSON = "gn_natural_person";
		PROSPECTUS     = "gn_prospectus";
		
		service_catalogos = Utilities.findBean("service_catalogos");
	}
	
	public final void setService_natural_person(NaturalPersonService service) 
	{
		service_natural_person = service;
	}

	public final void setService_prospectus(ProspectusService service) 
	{
		service_prospectus = service;
	}
	
	public final void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}
	
	public final void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		IP_address_client = sesion.getIP_address_client();
	}	

	public final void setPerson(NaturalPerson person) 
	{
		this.person = person;		
		
		legal_name_ORIGINAL  = person.getLegal_name()     != null ? person.getLegal_name()     : "";
		company_RFC_ORIGINAL = person.getMx_company_rfc() != null ? person.getMx_company_rfc() : "";
		
		prospecto = person.getProspectus();
		
		prospectus_id  = prospecto.getProspectusPK().getProspectus_id();
		company_id     = prospecto.getProspectusPK().getCompany_id();		
		person_type_id = prospecto.getPerson_type().toString();
		
		init_persona_moral_ENABLED();
	}
	
	abstract void init_persona_moral_ENABLED();
	
	public final boolean isPersona_moral_ENABLED() 
	{
		return persona_moral_ENABLED;
	}

	public final List<SocietyType> getLista_society_type() 
	{
		return lista_society_type;
	}
}
