package mx.com.kubo.managedbeans.portal.telefonos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.AddPhoneBean;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.portal.telefonos.ChangePhoneIMP;

@ManagedBean (name = "editor_telefono") @ViewScoped 
public final class EditorTelefonoIMP extends EditorTelefonoAMO
implements EditorTelefonoIMO, Serializable
{
	private static final long serialVersionUID = -2044931961650823819L;

	@PostConstruct
	public final void init()
	{
		faces = FacesContext.getCurrentInstance();
		
		init_sesion();
		
		if(sesion != null)
		{
			init_lista_phone();		
			
			add_phone = new AddPhoneBean();
			
			listPhoneType = service_phone.loadAllPhoneType();
			
			changer = new ChangePhoneIMP();
			changer.setSesion(sesion);
			changer.setService_change_control(service_change_control);
			changer.setService_phone(service_phone);
		}
	}
	
	public final void init_phone_view() 
	{
		request = RequestContext.getCurrentInstance();
		
		init_lista_phone();
		
		request.addCallbackParam("phone_view_OK", true);
	}
	
	public final void init_phone_type(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_menu = (HtmlSelectOneMenu) event.getComponent();
		
		phone_type_id = Integer.parseInt(select_menu.getValue().toString());
		
		switch (phone_type_id) 
		{
			case 1:
				panel_lada_ENABLED = false;
				panel_extension_ENABLED = true;
			break;
			
			case 2:
			case 4:
			case 6:
				panel_lada_ENABLED = true;
				panel_extension_ENABLED = false;
			break;
	
			default:
				panel_lada_ENABLED = false;
				panel_extension_ENABLED = false;
			break;
		}
		
		changer.setPhone_type_id(phone_type_id);
				 		
		request.addCallbackParam("phone_type_id", phone_type_id);
		request.addCallbackParam("panel_lada_ENABLED", panel_lada_ENABLED);
		request.addCallbackParam("panel_extension_ENABLED", panel_extension_ENABLED);
	}
		
	public final void init_add_phone()
	{				
		request = RequestContext.getCurrentInstance();
		
		phone_NEW = new Phone();
		phone_PK  = new PhonePK(prospectus_id, company_id);
		
		phone_NEW.setPhonePk(phone_PK);
		phone_NEW.setOwned("1");
		phone_NEW.setArea('M');
		phone_NEW.setPhone_type_id(add_phone.getPhone_type_id());
		phone_NEW.setPhone_number (add_phone.getNumPhone());
		phone_NEW.setExtension    (add_phone.getExtension());
		
		persist_OK = service_phone.addPhone(phone_NEW, prospectus_id, company_id);
		
		if(persist_OK)
		{	
			comments = add_phone.getComments();
			
			String original_value = "";
			String new_value = add_phone.getNumPhone();
			
			int field_type_id = add_phone.getPhone_type_id();
			
			init_change_control(field_type_id, original_value, new_value, comments);
		}
		
		init_lista_phone();
		
		add_phone = new AddPhoneBean();
		
		request.addCallbackParam("persist_OK", persist_OK);
		request.addCallbackParam("change_control_OK", change_control_OK);
	}
}
