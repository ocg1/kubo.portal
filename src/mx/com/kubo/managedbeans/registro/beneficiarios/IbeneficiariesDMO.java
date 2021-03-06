package mx.com.kubo.managedbeans.registro.beneficiarios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.AddressBean;
import mx.com.kubo.bean.Benefi_ciaries;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.Beneficiaries;
import mx.com.kubo.model.BeneficiariesPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.TownCat;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.registro.beneficiarios.BeneficiarioIMO;
import mx.com.kubo.registro.beneficiarios.DomicilioIMO;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.BeneficiariesService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.SavingAccountService;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

public abstract class IbeneficiariesDMO 
implements IbeneficiariesIMO
{	
	protected Logger log = Logger.getLogger(getClass());
	
	@ManagedProperty("#{beneficiariesServiceImp}")
	protected BeneficiariesService service_beneficiarios;
	
	@ManagedProperty("#{savingAccountServiceImp}")
	protected SavingAccountService accountService;
	
	@ManagedProperty("#{addressServiceImp}")
	protected AddressService service_address;
	
	@ManagedProperty("#{employmentServiceImp}")
	protected EmploymentService  service_employment;
		
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService service_natural_person;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService service_change_control;	
	
	protected RequestContext request;
	protected FacesContext faces;
	protected ELResolver resolver;
	protected ELContext elContext;
	protected ExternalContext external;
	
	protected SessionBean sesion;
	protected NaturalPerson person;
	protected gnNaturalPersonPK person_PK;
	
	protected Address address;
	protected AddressPK address_PK;
	protected AddressBean addressbean;
	protected StateCat gn_state;
	protected TownCat  gn_town;
	
	protected Beneficiaries   beneficiarie;
	protected BeneficiariesPK beneficiario_PK;	
	protected Benefi_ciaries  beneficiario_bean;
		
	protected DomicilioIMO domicilio;
	protected BeneficiarioIMO beneficiario;
	
	protected List<SavingAccount>  listAccount;
	protected List<Beneficiaries>  listBenefic;
	protected List<Benefi_ciaries> listBenefi_ciaries;
	
	protected SimpleDateFormat date_format;	
	
	protected String zipcode;	
	protected String descriptioAccount;
	protected String neighborhood_text;
	protected String neighborhood_id_ENABLED;
	protected String neighborhood_text_ENABLED;
	
	protected String [] monthStr;
	
	protected List<NeighborhoodCat> lista_neighborhood;
	
	protected List<String> listPercentage;	
	
	protected Integer neighborhood_id;
	protected Integer prospectus_id_coach;
	protected Integer change_prospectus_id;
	
	protected int actualAccountID;
	protected int sizeBenefic;
	protected int address_id;
	protected int prospectus_id;
	protected int company_id;
	protected int beneficiario_id;
	
	protected final int address_type_BENEFICIARIO  = 6;
	
	protected boolean is_save_OK;
	protected boolean is_remove_OK;
	
	protected final int CASA;
	protected final int BENEFICIARIO;
	
	private String day;
	private String month;
	private String year;
	
	protected IbeneficiariesDMO()
	{
		date_format = new SimpleDateFormat("dd/MM/yyyy");
		
		monthStr = new String[]
		{ "Enero",   "Febrero",   "Marzo",  "Abril", "Mayo",
		  "Junio",   "Julio",     "Agosto", "Septiembre", 
		  "Octubre", "Noviembre", "Diciembre"
		 };	
		
		listPercentage = new ArrayList<String>();		
		
		CASA = 1;
		BENEFICIARIO = 6;
		
		is_save_OK = false;
	}
	
	public final void setService_beneficiarios(BeneficiariesService service) 
	{
		service_beneficiarios = service;
	}

	public final void setAccountService(SavingAccountService service) 
	{
		accountService = service;
	}

	public void setService_address(AddressService service) 
	{
		service_address = service;
	}

	public void setService_natural_person(NaturalPersonService service) 
	{
		service_natural_person = service;
	}
	
	public void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}
	
	public void setService_employment(EmploymentService service) 
	{
		service_employment = service;
	}

	public DomicilioIMO getDomicilio() 
	{
		return domicilio;
	}

	public BeneficiarioIMO getBeneficiario() 
	{
		return beneficiario;
	}

	public List<SavingAccount> getListAccount() 
	{
		return listAccount;
	}

	public List<Beneficiaries> getListBenefic() {
		return listBenefic;
	}

	public String getDay() {
		return day;
	}

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}

	public String getDescriptioAccount() {
		return descriptioAccount;
	}

	public void setListAccount(List<SavingAccount> listAccount) {
		this.listAccount = listAccount;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setDescriptioAccount(String descriptioAccount) {
		this.descriptioAccount = descriptioAccount;
	}
	
	public int getActualAccountID() 
	{
		return actualAccountID;
	}

	public void setActualAccountID(int actualAccountID) {
		this.actualAccountID = actualAccountID;
	}
	
	public int getSizeBenefic() {
		return sizeBenefic;
	}

	public void setSizeBenefic(int sizeBenefic) {
		this.sizeBenefic = sizeBenefic;
	}

	public List<Benefi_ciaries> getListBenefi_ciaries() {
		return listBenefi_ciaries;
	}

	public void setListBenefi_ciaries(List<Benefi_ciaries> listBenefi_ciaries) {
		this.listBenefi_ciaries = listBenefi_ciaries;
	}
	
	public List<String> getListPercentage() {
		return listPercentage;
	}

	public NaturalPerson getPerson() {
		return person;
	}

	public void setPerson(NaturalPerson person) {
		this.person = person;
	}
}
