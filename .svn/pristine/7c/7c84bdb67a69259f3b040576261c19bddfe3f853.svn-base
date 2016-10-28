package mx.com.kubo.mesa.solicitud.telefonos;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.AddPhoneBean;
import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.PersonAndPhones;
import mx.com.kubo.bean.PhoneReview;
import mx.com.kubo.bean.ReferencesReview;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ReferencesService;

public abstract class TelefonosDMO 
implements TelefonosIMO
{
	protected          PhoneService service_telefono;
	protected Change_controlService service_change_control;
	protected  NaturalPersonService service_natural_person;
	protected    ProyectLoanService service_proyect_loan;
	protected     ReferencesService service_references;
	
	protected RequestContext request;
	protected FacesContext   faces;
	protected HtmlSelectOneMenu select_menu;
	protected HtmlInputText     input_text;
	protected HtmlInputTextarea input_text_area;
	
	protected SessionBean sesion;	
	protected NaturalPerson persona;
	
	protected ChangeBean changephones;
	protected Change_control   changeCtrl;
	protected Change_controlPK change_control_PK;
	
	protected Business buss;
	protected Employment employ;
	
	//protected PhoneReview view;
	protected Phone        newPhone;
	protected Phone        phone;
	protected PhonePK      newPhonePK;
	protected AddPhoneBean newAddPhone;
	
	protected PhoneReviewIMO factory;
		
//	protected List<Phone> lstPhonesUpdate;
	protected List<Phone> lista_phone_prospectus;
	protected List <Phone> lstPhoneTmp;
	protected List<PhoneReview>      lista_phone_view;
//	protected List<PersonAndPhones>  lstPerson;
	protected List <PersonAndPhones> lstPersonPhone;
	protected List<Employment>       listEmployment;
	protected List<Business> 	     listBusiness;
	protected List<ReferencesReview> lstReferences;
	protected List<Change_control>   lista_change_control;
	
	protected Locale locale    = new Locale("es","mx");
	protected NumberFormat num = NumberFormat.getNumberInstance(locale);
	
//	protected String tipo_telefono;
//	protected String existPhoneStr;	
//	protected String existPerson;
//	protected String existReferen;	
	protected String phone_number;
	protected String phone_extension;
//	protected String phone_label;
	protected String original_value;
	protected String new_value;
	protected String whyChangeData;	
	
//	protected String[] srr;
	
	protected Integer phone_type_id;
	
	protected int prospectus_id;
	protected int company_id;
	
	//protected boolean insertado;
	protected boolean add_phone_OK;
	protected boolean update_OK;
	protected boolean change_control_OK;
	protected boolean change_control_ENABLED;
	protected boolean panel_lada_ENABLED;
	protected boolean panel_extension_ENABLED;
	
	public void setService_telefono(PhoneService service) 
	{
		service_telefono = service;
	}
	
	public void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}
	
	public void setService_natural_person(NaturalPersonService service) 
	{
		service_natural_person = service;
	}
	
	public void setService_proyect_loan(ProyectLoanService service) 
	{
		service_proyect_loan = service;
	}
	
	public void setService_references(ReferencesService service) 
	{
		service_references = service;
	}
	
	public void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
	}
	
	public final void setPerson(NaturalPerson person)
	{
		persona = person;
		
		prospectus_id = persona.getNatPerPK().getProspectus_id();
		company_id    = persona.getNatPerPK().getCompany_id();
	}
	
	public void setListEmployment(List<Employment> listEmployment) 
	{
		this.listEmployment = listEmployment;
	}

	public void setListBusiness(List<Business> listBusiness) 
	{
		this.listBusiness = listBusiness;
	}

	public boolean isPanel_lada_ENABLED() 
	{
		return panel_lada_ENABLED;
	}

	public boolean isPanel_extension_ENABLED() 
	{
		return panel_extension_ENABLED;
	}

	public AddPhoneBean getNewAddPhone() 
	{
		return newAddPhone;
	}

	public final ChangeBean getChangephones()
	{
		return changephones;
	}
	
	public final List<PhoneReview> getListPhone()
	{
		return lista_phone_view;
	}
	
	public final List<ReferencesReview> getLstReferences()
	{
		return lstReferences;
	}
	
	public List<PersonAndPhones> getLstPersonPhone() 
	{
		return lstPersonPhone;
	}
}
