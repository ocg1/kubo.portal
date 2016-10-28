package mx.com.kubo.managedbeans.mesa.control;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Partner;
import mx.com.kubo.model.RegistrationReason;
import mx.com.kubo.model.RegistrationReasonPK;

import org.primefaces.context.RequestContext;

public class AltaPartnerIMP extends AltaPartnerDMO
implements AltaPartnerIMO
{
	public final void listener_partner_id(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) evento.getComponent();
		
		partner_id = input_text.getValue().toString().trim().toUpperCase();
		
		System.out.println("AltaPartnerDMO.listener_partner_id(): " + partner_id);
		
		partner_ENABLED = false;
		partner_NEW     = false;
		
		if(partner_id != null && partner_id.length() == 3)
		{			
			partner_ENABLED = true;
			
			partner_PK.setCompany_id(company_id);
			partner_PK.setPartner_id(partner_id);
		
			partner = service_partner.getPartner(partner_PK);
			
			partner_NEW = (partner != null ? false : true);
			
			if(partner_NEW)
			{		
				partner = new Partner();
				partner.setPartnerPK(partner_PK);
				
				registration_reason    = new RegistrationReason();
				registration_reason_PK = new RegistrationReasonPK();
				registration_reason_PK.setCompany_id(company_id);
				
				registration_reason.setRegPK(registration_reason_PK);				
				registration_reason.setPartner_id(partner_id);								
				
			} else {
				
				request.addCallbackParam("partner_name",        partner.getName());
				request.addCallbackParam("partner_descripcion", partner.getDescription());
			}
		}
				
		request.addCallbackParam("partner_ENABLED", partner_ENABLED);
		request.addCallbackParam("partner_NEW",     partner_NEW);
	}
	
	public final void listener_partner_name(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) evento.getComponent();
		
		partner_name = input_text.getValue().toString();
		
		partner_name_OK = false;
		registration_reason_ENABLED = false;
		
		if(partner_name != null && partner_name.length() > 0)
		{
			partner_name_OK = true;
			
			registration_reason_ENABLED = partner_name_OK && partner_descripcion_OK;
			
			partner.setName(partner_name);
			
			System.out.println("AltaPartnerDMO.listener_partner_name(): " + partner_name);
		}
		
		request.addCallbackParam("partner_name_OK", partner_name_OK);
		request.addCallbackParam("registration_reason_ENABLED", registration_reason_ENABLED);
	}
	
	public final void listener_partner_descripcion(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		input_textarea = (HtmlInputTextarea) evento.getComponent();
		
		partner_descripcion = input_textarea.getValue().toString().trim();
		
		partner_descripcion_OK = false;
		
		registration_reason_ENABLED = false;
		
		if(partner_descripcion != null && partner_descripcion.length() > 0)
		{
			partner_descripcion_OK = true;
			
			registration_reason_ENABLED = partner_name_OK && partner_descripcion_OK;
			
			partner.setDescription(partner_descripcion);
			
			System.out.println("AltaPartnerDMO.listener_partner_descripcion(): " + partner_descripcion);
		}
		
		request.addCallbackParam("partner_descripcion_OK", partner_descripcion_OK);
		request.addCallbackParam("registration_reason_ENABLED", registration_reason_ENABLED);
	}
	
	public final void listener_titulo_lista(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();
		
		titulo_lista = input_text.getValue().toString();
		
		if(titulo_lista != null && titulo_lista.length() > 0)
		{
			registration_reason.setName(titulo_lista);
			
			System.out.println("AltaPartnerDMO.listener_titulo_lista(): " + titulo_lista);
		}
	}
	
	public final void listener_orden_lista(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();
		
		orden_lista = input_text.getValue().toString();
		
		if(orden_lista != null && orden_lista.length() > 0)
		{
			int order = Integer.parseInt(orden_lista);
			
			registration_reason.setOrder_list(order);
			
			System.out.println("AltaPartnerDMO.listener_orden_lista(): " + orden_lista);
		}
	}
	
	public final void listener_is_active(AjaxBehaviorEvent evento)
	{
		select_radio = (HtmlSelectOneRadio) evento.getComponent();
		
		Character is_active = select_radio.getValue().toString().charAt(0);
		
		if(is_active != null)
		{		
			registration_reason.setIs_active(is_active);
			
			System.out.println("AltaPartnerDMO.listener_is_active(): " + is_active);
		}		
	}
	
	public final void listener_channel(AjaxBehaviorEvent evento)
	{
		select_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		channel_id = select_menu.getValue().toString();
		
		registration_reason.setChannel_id(channel_id);

		System.out.println("AltaPartnerDMO.listener_channel(): " + channel_id);
	}
	
	public final void listener_guardar(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		save_OK = service_partner.save(partner);
		
		if(save_OK)
		{			
			init_change_control();
			
			save_OK = service_registration_reason.save(registration_reason);					
		}
		
		System.out.println("TableroControlIMP.listener_alta_partner(): " + save_OK);
		
		request.addCallbackParam("save_OK", save_OK);
	}

	private void init_change_control() 
	{
		change_control    = new Change_control();
		change_control_PK = new Change_controlPK();
		
		change_control_PK.setCompany_id(company_id);
		change_control_PK.setProspectus_id(prospectus_id);
		
		change_control.setChange_controlPK(change_control_PK);
		change_control.setChange_prospectus_id(prospectus_id);
		change_control.setAfected_table("ln_partner");
		change_control.setField("partner_id");
		change_control.setOriginal_value("");
		change_control.setNew_value(partner_id);
		change_control.setComments(sesion.getEmail() + " agregó nuevo Partner");
		change_control.setIp(sesion.getIP_address_client());
		
		change_control_OK = service_change_control.addChangeControl(change_control, prospectus_id, company_id);
		
		System.out.println("AltaPartnerIMP.init_change_control(): " + change_control_OK);
	}
}
