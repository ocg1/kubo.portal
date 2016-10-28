package mx.com.kubo.managedbeans.portal.telefonos;

import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.AddPhoneBean;
import mx.com.kubo.bean.PhoneReviewEditor;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.portal.reader.ParameterReaderIMO;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.model.PhoneType;
import mx.com.kubo.portal.AccessIMO;
import mx.com.kubo.portal.telefonos.ChangePhoneIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.PhoneService;

public abstract class EditorTelefonoDMO
implements EditorTelefonoIMO
{
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access; 
	
	@ManagedProperty("#{phoneServiceImp}")
	protected PhoneService service_phone;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService service_change_control;
	
	protected   FacesContext faces;
	protected RequestContext request;	
	protected HtmlSelectOneMenu select_menu;
	
	protected SessionBean sesion;
	
	protected PhoneReviewEditor view;
	protected AddPhoneBean add_phone;
	protected Phone        phone_NEW;
	protected PhonePK      phone_PK;
	
	protected List <Phone> lista_phone_prospectus;
	protected List <PhoneType>    listPhoneType;
	protected List <PhoneReviewEditor>  lista_phone_view;
	
	protected          AccessIMO auditor;
	protected ParameterReaderIMO reader;
	
	protected ChangePhoneIMO changer;
	protected Change_control   changeCtrl;
	protected Change_controlPK change_control_PK;
	
	protected String comments;
	protected String phone_id;
	protected String IP_address_client;
	protected String page_title;
	protected String access_from;
	
	protected Integer company_id;
	protected Integer prospectus_id;
	protected Integer change_prospectus_id;	
	protected Integer phone_type_id;
	
	protected boolean persist_OK;
	protected boolean change_control_OK;
	protected boolean panel_lada_ENABLED;
	protected boolean panel_extension_ENABLED;
	
	protected final int SCREEN_EDITAR_TELEFONOS = 68;
	
	public final void setService_access(AccessService service) 
	{
		service_access = service;
	}
	
	public final void setService_phone(PhoneService service) 
	{
		service_phone = service;
	}
	
	public void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}
	
	public final List<PhoneReviewEditor> getLista_phone_view()
	{
		return lista_phone_view;
	}
	
	public final List <PhoneType> getListPhoneType()
	{
		return listPhoneType;
	}
	
	public final AddPhoneBean getNewAddPhone() 
	{
		return add_phone;
	}
	
	public final AccessIMO getAuditor()
	{
		return auditor;
	}
	
	public final ChangePhoneIMO getChanger()
	{
		return changer;
	}

	public String getPhone_id() 
	{
		return phone_id;
	}
	
	public final String getPage_title() 
	{
		return page_title;
	}
}
