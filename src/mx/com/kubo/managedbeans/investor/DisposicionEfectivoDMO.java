package mx.com.kubo.managedbeans.investor;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import safisrv.ws.InvKuboServicios.SAFIServicios;
import safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator;
import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.investor.movimientos.MovimientosIMO;
import mx.com.kubo.model.CapitalNeto;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.services.CapitalNetoService;
import mx.com.kubo.services.ClabeAccountService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.investor.ServiceMovimientosIMO;

public abstract class DisposicionEfectivoDMO 
{
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membership_service;		
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectLoanService;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService naturalPersonService;
	
	@ManagedProperty("#{savingAccountServiceImp}")
	private SavingAccountService savingaccountservice;
	
	@ManagedProperty("#{clabeAccountServiceImp}")
	protected ClabeAccountService clabeaccountservice;
	
	@ManagedProperty("#{capitalNetoServiceImp}")
	protected CapitalNetoService capitalnetoservice;
	
	@ManagedProperty("#{serviceCallingServiceImp}")
	protected ServiceCallingService servicecallingservice;
	
	protected ServiceMovimientosIMO service_movimientos;
	
	protected RequestContext requestContext;
	protected FacesContext faces;
	protected ELContext context;
	protected ELResolver resolver;
	
	protected MovimientosIMO    deposito_param_values;
	protected MovimientosIMO disposicion_param_values;
	protected NotificadorIMO notificador;
	
	protected SAFIServiciosServiceLocator locator2;		
	protected SAFIServicios servCliente;		
	
	protected Membership membership;
	protected MembershipPK membership_PK;
	
	protected CapitalNeto capital_neto;
	
	protected SessionBean   sesion;
	
	protected List<InvestorsAccounts>  listInvAccounts;	
	protected List<ClabeAccount>       lstClabes;
	
	protected SimpleDateFormat format;
	
	protected String clabeSel;				
	protected String tagAccount;		
	protected String fechaDep;	
	protected String descDep;	
	protected String cuentaActual;	
	protected String bnkStr;	
	protected String monto_SELECTED;	
	protected String monto_SELECTED_dep;
	protected String cuenta;
	protected String SAFI_client_id;	
		
	protected List<String> SAFI_cuenta;
	
	protected Integer prospectus_id;
	protected Integer company_id;
	
	protected Double saldoActual;
	protected Double saldoTotal;
	protected Double saldotope;

	public void setMembership_service(MembershipService service) 
	{
		membership_service = service;
	}

	public void setProyectLoanService(ProyectLoanService service) 
	{
		proyectLoanService = service;
	}

	public void setNaturalPersonService(NaturalPersonService service) 
	{
		naturalPersonService = service;
	}

	public List<InvestorsAccounts> getListInvAccounts() {
		return listInvAccounts;
	}

	public void setListInvAccounts(List<InvestorsAccounts> listInvAccounts) {
		this.listInvAccounts = listInvAccounts;
	}

	public Double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(Double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public Double getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(Double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	public List<String> getSAFI_cuenta() {
		return SAFI_cuenta;
	}

	public void setSAFI_cuenta(List<String> sAFI_cuenta) {
		SAFI_cuenta = sAFI_cuenta;
	}

	public String getTagAccount() {
		return tagAccount;
	}

	public void setTagAccount(String tagAccount) {
		this.tagAccount = tagAccount;
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public String getCuentaActual() {
		return cuentaActual;
	}

	public void setCuentaActual(String cuentaActual) {
		this.cuentaActual = cuentaActual;
	}

	public SavingAccountService getSavingaccountservice() {
		return savingaccountservice;
	}

	public void setSavingaccountservice(SavingAccountService savingaccountservice) {
		this.savingaccountservice = savingaccountservice;
	}

	public ClabeAccountService getClabeaccountservice() {
		return clabeaccountservice;
	}

	public void setClabeaccountservice(ClabeAccountService clabeaccountservice) {
		this.clabeaccountservice = clabeaccountservice;
	}

	public List<ClabeAccount> getLstClabes() {
		return lstClabes;
	}

	public void setLstClabes(List<ClabeAccount> lstClabes) {
		this.lstClabes = lstClabes;
	}

	public String getClabeSel() {
		return clabeSel;
	}

	public void setClabeSel(String clabeSel) {
		this.clabeSel = clabeSel;
	}

	public String getBnkStr() {
		return bnkStr;
	}

	public void setBnkStr(String bnkStr) {
		this.bnkStr = bnkStr;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public String getMonto_SELECTED() {
		return monto_SELECTED;
	}

	public void setMonto_SELECTED(String monto_SELECTED) {
		this.monto_SELECTED = monto_SELECTED;
	}

	public Double getSaldotope() {
		return saldotope;
	}

	public void setSaldotope(Double saldotope) {
		this.saldotope = saldotope;
	}

	public CapitalNetoService getCapitalnetoservice() {
		return capitalnetoservice;
	}

	public void setCapitalnetoservice(CapitalNetoService capitalnetoservice) {
		this.capitalnetoservice = capitalnetoservice;
	}

	public String getFechaDep() {
		return fechaDep;
	}

	public void setFechaDep(String fechaDep) {
		this.fechaDep = fechaDep;
	}

	public String getDescDep() {
		return descDep;
	}

	public void setDescDep(String descDep) {
		this.descDep = descDep;
	}

	public String getMonto_SELECTED_dep() {
		return monto_SELECTED_dep;
	}

	public void setMonto_SELECTED_dep(String monto_SELECTED_dep) {
		this.monto_SELECTED_dep = monto_SELECTED_dep;
	}

	public ServiceCallingService getServicecallingservice() {
		return servicecallingservice;
	}

	public void setServicecallingservice(ServiceCallingService servicecallingservice) {
		this.servicecallingservice = servicecallingservice;
	}

}
