package mx.com.kubo.managedbeans.registro.sobre;

import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.ActividadEconomicaDMO;
import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.EmploymentBean;
import mx.com.kubo.bean.PhoneBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Contract_typeCat;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.FromWhereCat;
import mx.com.kubo.model.Legal_Status;
import mx.com.kubo.model.Marital_Status;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.Purpose;
import mx.com.kubo.model.PurposePK;
import mx.com.kubo.model.Study_Level;
import mx.com.kubo.model.TenureCat;
import mx.com.kubo.model.TypeCat;
import mx.com.kubo.model.WhoAnsweredCat;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.model.catalogos.DependantsNumber;
import mx.com.kubo.registro.sobreti.actividad.DomicilioIMO;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.BusinessService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.FromWhereCatService;
import mx.com.kubo.services.LegalStatusService;
import mx.com.kubo.services.MaritalStatusService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.PurposeService;
import mx.com.kubo.services.StudyLevelService;
import mx.com.kubo.services.WhoAnsweredCatService;
import mx.com.kubo.listeners.registro.ListenerMiPrestamoIMO;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

public abstract class MiPrestamoDMO
implements MiPrestamoIMO
{		
	@ManagedProperty("#{purposeServiceImp}")
	protected PurposeService service_purpose;
	
	@ManagedProperty("#{proyectServiceImp}")
	protected ProyectService service_proyect;
	
	@ManagedProperty("#{employmentServiceImp}")
	protected EmploymentService  service_employment;
	
	@ManagedProperty("#{businessServiceImp}")
	protected BusinessService service_business;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService service_natural_person;
	
	@ManagedProperty("#{addressServiceImp}")
	protected AddressService service_address;
	
	@ManagedProperty("#{phoneServiceImp}")
	protected PhoneService service_phone;
	
	@ManagedProperty("#{fromWhereServiceImp}")
	protected FromWhereCatService service_fromwhere;
	
	@ManagedProperty("#{whoAnsweredCatServiceImp}")
	protected WhoAnsweredCatService service_whoAnswered;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService service_change_control;	
	
	@ManagedProperty("#{legalStatusServiceImp}")
	protected LegalStatusService service_legal_status;
	
	@ManagedProperty("#{maritalStatusServiceImp}")
	protected MaritalStatusService service_marital_status;
	
	@ManagedProperty("#{studyLevelServiceImp}")
	protected StudyLevelService service_study_level;

	protected ListenerMiPrestamoIMO listener_mi_prestamo;
	
	protected RequestContext request;
	protected FacesContext faces;
	protected ELContext context;
	protected ELResolver resolver;
	protected ExternalContext external;
	
	protected HtmlInputText input_text;
	protected HtmlSelectOneMenu select_one_menu;
	
	protected SessionBean   sesion;
	protected NaturalPerson     naturalperson;
	protected gnNaturalPersonPK natural_person_PK;
	
	protected Membership    thisMembership;	
	protected MembershipPK  membership_PK;
	
	protected Proyect  thisProyect;	
	protected ProyectPK project_PK;
	protected Purpose   purpose;
	protected PurposePK purpose_PK;
	
	protected PhoneBean phone_fixed;
	protected Phone phone_NEW;
	protected PhonePK phonePk;		
			
	protected Logger log = Logger.getLogger(getClass());
	
	protected EmploymentBean employBean = null;	
	
	protected Change_controlPK changeCtrlPK;
	protected Change_control   changeCtrl;
	protected ChangeBean       change_control_bean;
	
	protected DomicilioIMO domicilio;
	protected DomicilioIMO domicilio_negocio;
	
	protected List<TypeCat>          listType;
	protected List<Purpose>          listPurpose;
	
	protected List<Business>         listBusiness;
	protected List<Employment>     lista_employment;
	protected List<ActividadEconomicaDMO>   employmentList;
	protected List<ActividadEconomicaDMO>     businessList;
	
	protected List<FromWhereCat>     listFromWhere;
	protected List<WhoAnsweredCat>   listWhoAnswered;
//	protected List<NeighborhoodCat>  listAsents;
	protected List<Contract_typeCat> listContractType;	
	
	protected List<TenureCat>        lista_tenure;
	protected List<Legal_Status>     lista_estado_civil;
	protected List<Marital_Status>   lista_regimen_conyugal;
	protected List<Study_Level> 	  lista_nivel_estudios;
	protected List<DependantsNumber> lista_dependants_number;
	
	protected StringBuilder sb;
	
	protected String goal;
	protected String benefits;
	protected String latLong;
	protected String other_debts_ORIGINAL;
	protected String regimen_conyugal_ENABLED = "none";
	protected String strComputeHidden; 
	protected String strInternetHidden;
	protected String lada;
	protected String telefono;
	protected String extension;
	
	protected List<String> selectedComputer;
	protected List<String> selectedInternet;
	
	protected Integer estado_civil_SELECTED;
	protected Integer type_id;
	protected Integer purpose_id;
	protected Integer marital_status_id;
	protected Integer dependientes_economicos;
	protected Integer dependants_number_id;
	protected Integer nivel_estudios;
	protected Integer prospectus_id;
	protected Integer prospectus_id_coach;
	protected Integer change_prospectus_id;
	protected Integer company_id;
	
	protected Character area;
	
	protected int employment_id_SELECTED;
	
	protected final int address_type_NEGOCIO = 3;
	protected final int address_type_EMPLEO  = 4;
	
	protected boolean hasProyect;
	protected boolean flagUpdate = false;
	protected boolean flagSave   = false;
	protected boolean flag;
		
	public void setService_natural_person(NaturalPersonService service) 
	{
		service_natural_person = service;
	}

	public void setService_address(AddressService service) 
	{
		service_address = service;
	}

	public void setService_phone(PhoneService service) 
	{
		service_phone = service;
	}
	
	public void setService_purpose(PurposeService service) 
	{
		service_purpose = service;
	}

	public void setService_proyect(ProyectService service) 
	{
		service_proyect = service;
	}
	
	public void setService_employment(EmploymentService service) 
	{
		service_employment = service;
	}
	
	public void setService_business(BusinessService service) 
	{
		service_business = service;
	}
	
	public void setService_fromwhere(FromWhereCatService service) 
	{
		service_fromwhere = service;
	}
	
	public void setService_whoAnswered(WhoAnsweredCatService service)
	{
		service_whoAnswered = service;
	}
	
	public void setService_membership(MembershipService service) 
	{
		service_membership = service;
	}

	public void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}
		
	public final void setService_legal_status(LegalStatusService service) 
	{
		service_legal_status = service;
	}
	
	public final void setService_marital_status(MaritalStatusService service) 
	{
		service_marital_status = service;
	}

	public final void setService_study_level(StudyLevelService service) 
	{
		service_study_level = service;
	}		

	public DomicilioIMO getDomicilio() 
	{
		return domicilio;
	}
	
	public DomicilioIMO getDomicilio_negocio() 
	{
		return domicilio_negocio;
	}

	public final String getOther_debts_ORIGINAL() 
	{
		return other_debts_ORIGINAL;
	}

	public List<Business> getListBusiness() 
	{
		return listBusiness;
	}
	
	public List<Employment> getlistEmployment()
	{
		return lista_employment;
	}

	public NaturalPerson getNaturalperson() 
	{
		return naturalperson;
	}
	
	public List<TypeCat> getListType() 
	{
		return listType;
	}
	
	public List<Purpose> getListPurpose() 
	{
		return listPurpose;
	}
		
	public final List<Legal_Status> getLista_estado_civil() 
	{
		return lista_estado_civil;
	}
	
	public final List<Marital_Status> getLista_regimen_conyugal() 
	{
		return lista_regimen_conyugal;
	}	

	public final List<Study_Level> getLista_nivel_estudios() 
	{
		return lista_nivel_estudios;
	}
	
	public final List<DependantsNumber> getLista_dependants_number() 
	{
		return lista_dependants_number;
	}

	public Integer getType_id() 
	{
		return type_id;
	}
	
	public String getRegimen_conyugal_ENABLED() 
	{
		
		/*if (naturalperson.getLegal_status_id() != null && naturalperson.getLegal_status_id() == 2) */
		if (naturalperson.getLegal_status_id() != null && naturalperson.getLegal_status_id() == 2) 
		{
			regimen_conyugal_ENABLED = "none";
			
		} else {
			
			regimen_conyugal_ENABLED = "none";
		}
		
		return regimen_conyugal_ENABLED;
	}
	
	public List<String> getSelectedComputer() 
	{
		return selectedComputer;
	}
	
	public List<String> getSelectedInternet() {
		return selectedInternet;
	}

	public String getStrInternetHidden() {
		return strInternetHidden;
	}

	public void setStrInternetHidden(String strInternetHidden) {
		this.strInternetHidden = strInternetHidden;
	}
	
	public String getStrComputeHidden() 
	{
		return strComputeHidden;
	}

	public void setStrComputeHidden(String strComputeHidden) 
	{
		this.strComputeHidden = strComputeHidden;
	}
	
	public void setType_id(Integer type_id) 
	{
		this.type_id = type_id;
	}
	
	public Integer getPurpose_id() 
	{
		return purpose_id;
	}
	
	public String getGoal() {
		return goal;
	}
	
	public void setGoal(String goal) {
		this.goal = goal;
	}
	
	public String getBenefits() {
		return benefits;
	}
	
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	
	public Proyect getThisProyect() {
		return thisProyect;
	}
	
	public boolean isHasProyect() {
		return hasProyect;
	}	
	
	public List<ActividadEconomicaDMO> getEmploymentList() {
		return employmentList;
	}
	
/*	
	public List<NeighborhoodCat> getListAsents() {
		return listAsents;
	}
*/	
	
	public List<Contract_typeCat> getListContractType() {
		return listContractType;
	}
	
	public String getLatLong() {
		return latLong;
	}
	
	public void setLatLong(String latLong) {
		this.latLong = latLong;
	}	
	
	public List<ActividadEconomicaDMO> getBusinessList() 
	{
		return businessList;
	}
	
	public final List<TenureCat> getLista_tenure() 
	{
		return lista_tenure;
	}

	public Membership getThisMembership() {
		return thisMembership;
	}
	
	public List<FromWhereCat> getListFromWhere() {
		return listFromWhere;
	}
	
	public List<WhoAnsweredCat> getListWhoAnswered() {
		return listWhoAnswered;
	}
}
