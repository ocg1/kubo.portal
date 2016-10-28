package mx.com.kubo.managedbeans.mesa.control;

import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlSelectOneRadio;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Partner;
import mx.com.kubo.model.PartnerPK;
import mx.com.kubo.model.RegistrationReason;
import mx.com.kubo.model.RegistrationReasonPK;
import mx.com.kubo.model.catalogos.Channel;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.PartnerServiceIMO;
import mx.com.kubo.services.RegistrationReasonService;
import mx.com.kubo.services.catalogos.ServiceCatalogosIMO;
import mx.com.kubo.tools.Utilities;

public abstract class AltaPartnerDMO 
implements AltaPartnerIMO
{
	protected PartnerServiceIMO         service_partner;
	protected ServiceCatalogosIMO       service_catalogos;
	protected RegistrationReasonService service_registration_reason;
	protected Change_controlService     service_change_control;
	
	protected RequestContext request;
	protected HtmlInputText     input_text;
	protected HtmlInputTextarea input_textarea;
	protected HtmlSelectOneMenu  select_menu;
	protected HtmlSelectOneRadio select_radio;
	
	protected Partner   partner;
	protected PartnerPK partner_PK;
	protected RegistrationReason   registration_reason;
	protected RegistrationReasonPK registration_reason_PK;
	
	protected SessionBean sesion;
	
	protected Change_control   change_control;
	protected Change_controlPK change_control_PK;
	
	protected List<Channel> lista_canales;
	
	protected String partner_id;
	protected String partner_name;
	protected String partner_descripcion;
	protected String channel_id;
	protected String titulo_lista;
	protected String orden_lista;
	
	protected Character is_active;
	
	protected int company_id;
	protected int prospectus_id;
	
	protected boolean partner_ENABLED;
	protected boolean partner_NEW;
	protected boolean partner_name_OK;
	protected boolean partner_descripcion_OK;
	protected boolean registration_reason_ENABLED;
	protected boolean save_OK;
	protected boolean change_control_OK;
	
	protected AltaPartnerDMO()
	{		
		partner_PK = new PartnerPK();				
								
		service_partner        = Utilities.findBean("service_partner");
		service_catalogos      = Utilities.findBean("service_catalogos");
		service_change_control = Utilities.findBean("change_controlServiceImp");
		service_registration_reason = Utilities.findBean("registrationReasonServiceImp");
		
		lista_canales = service_catalogos.getLista_canales();
		
		orden_lista = "0";
	}
	
	public final void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		company_id    = sesion.getCompany_id();
		prospectus_id = sesion.getProspectus_id();
	}

	public final List<Channel> getLista_canales() 
	{
		return lista_canales;
	}
}
