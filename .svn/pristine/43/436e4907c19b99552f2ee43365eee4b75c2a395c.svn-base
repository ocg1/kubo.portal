package mx.com.kubo.mesa.solicitud;

import java.util.List;

import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.catalogos.UnusualBehavior;
import mx.com.kubo.model.mesa.solicitud.PldNotification;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.PrevencionLDService;
import mx.com.kubo.services.catalogos.ServiceCatalogosIMO;

public abstract class ReporteInusualDMO 
implements ReporteInusualIMO
{
	protected ServiceCatalogosIMO service_catalogos;
	protected PrevencionLDService service_PLD;
	protected AccessService       service_access;
	
	protected RequestContext request;
	protected HtmlSelectOneMenu select_one_menu;
	protected HtmlInputTextarea text_area;
	
	protected PldNotification notification_log;
	
	protected List<UnusualBehavior> lista_unusual_behavior;
	
	protected SessionBean sesion;
	protected NaturalPerson person;
	protected Access access;
	
	protected String comments;
	
	protected Integer prospectus_id;
	protected Integer prospectus_id_viewed;
	
	protected int behavior_id;
	protected int company_id;
	
	protected final int REPORTE_INUSUAL = 60;
	
	protected boolean persist_OK;
	
	public final void setService_catalogos(ServiceCatalogosIMO service)
	{
		service_catalogos = service;
	}
	
	public final void setService_PLD(PrevencionLDService service) 
	{
		service_PLD = service;
	}
	
	public final void setService_access(AccessService service) 
	{
		service_access = service;
	}		

	public final void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		prospectus_id = sesion.getProspectus_id();
		company_id    = sesion.getCompany_id();
	}
	
	public final void setPerson(NaturalPerson person) 
	{
		this.person = person;
		
		prospectus_id_viewed = person.getNatPerPK().getProspectus_id();
	}

	public final List<UnusualBehavior> getLista_unusual_behavior()
	{
		return lista_unusual_behavior;
	}
}
