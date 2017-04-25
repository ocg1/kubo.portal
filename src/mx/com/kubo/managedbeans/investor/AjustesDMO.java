package mx.com.kubo.managedbeans.investor;

import java.util.Hashtable;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import mx.com.kubo.investor.settings.AutomaticInvestmentIMO;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.InvestmentParamCat;
import mx.com.kubo.model.InvestorParam;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.InvestmentParamCatService;
import mx.com.kubo.services.InvestorParamService;
import mx.com.kubo.services.MontoInvertido_F_G_CollectorService;
import mx.com.kubo.services.ProfileInvService;
import mx.com.kubo.services.SystemParamService;

public abstract class AjustesDMO 
{
	@ManagedProperty("#{investmentParamCatServiceImp}")
	protected InvestmentParamCatService paramcatsevice;
	
	@ManagedProperty("#{investorParamServiceImp}")
	protected InvestorParamService investor_param_service;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	@ManagedProperty("#{montoInvertido_F_G_CollectorServiceImp}")
	protected MontoInvertido_F_G_CollectorService montoInvertido_F_G_service;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService service_change_control;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{profileInvServiceImp}")
	protected ProfileInvService profile_inv_service;
	
	protected FacesContext faces;
	protected ELResolver resolver;
	protected ELContext context;
	
	protected AutomaticInvestmentIMO filter;

	protected List<InvestmentParamCat> paramCat;
	
	protected InvestorParam investor_param;
	
	protected String  investor_param_script;
	protected String cuentaActual;
	protected String ipAddressClient;
	
	protected Hashtable<Integer, InvestorParam> ht;
	
	protected SessionBean sesion;
	protected InvestmentList investment_list ;
	
	protected Double saldoTotalActual;
	protected Double montoInvertido_E5;
	protected Double montoInvertido_FG;
	
	protected final String MENU_ITEM = "menu-item";
	
	protected int menu_item = 1;
	
	protected boolean puedeInvertire_en_F_G;
	protected boolean investment_param_ENABLED = true;
	protected boolean automatic_investment_ENABLED = false;
	
	protected final int LIMITES = 1 ;
	protected final int INVERSIONES_AUTOMATICAS = 2 ;
	
	protected final int ID_SUMA_MONTO_MAXIMO_E5_INV = 1 ;
	protected final int ID_SUMA_MONTO_MAXIMO_FG_INV = 2;
	protected final int ID_SUMA_MONTO_MAXIMO_E5_SYS = 95;
	protected final int ID_SUMA_MONTO_MAXIMO_FG_SYS = 82;
	
	public void setParamcatsevice(InvestmentParamCatService service) 
	{
		paramcatsevice = service;
	}

	public void setInvestor_param_service(InvestorParamService service) 
	{
		investor_param_service = service;
	}
	
	public void setSystemparamservice(SystemParamService service) 
	{
		systemparamservice = service;
	}
	
	public void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}
	
	public void setAccessService(AccessService service) 
	{
		accessService = service;
	}
	
	public AutomaticInvestmentIMO getFilter()
	{
		return filter;
	}
	
	public void setProfile_inv_service(ProfileInvService service) 
	{
		profile_inv_service = service;
	}
	
	public List<InvestmentParamCat> getParamCat() {
		return paramCat;
	}
	
	public void setParamCat(List<InvestmentParamCat> paramCat) {
		this.paramCat = paramCat;
	}
	
	public InvestorParam getInvestor_param() {
		return investor_param;
	}
	
	public void setInvestor_param(InvestorParam investor_param) {
		this.investor_param = investor_param;
	}
	
	public String getInvestor_param_script() {
		return investor_param_script;
	}
	
	public void setInvestor_param_script(String investor_param_script) {
		this.investor_param_script = investor_param_script;
	}
		
	public Double getSaldoTotalActual() {
		return saldoTotalActual;
	}
	
	public void setSaldoTotalActual(Double saldoTotalActual) {
		this.saldoTotalActual = saldoTotalActual;
	}
	
	public String getCuentaActual() {
		return cuentaActual;
	}
	
	public void setCuentaActual(String cuentaActual) {
		this.cuentaActual = cuentaActual;
	}
	
	public Double getMontoInvertido_E5() {
		return montoInvertido_E5;
	}
	
	public void setMontoInvertido_E5(Double montoInvertido_E5) {
		this.montoInvertido_E5 = montoInvertido_E5;
	}
	
	public Double getMontoInvertido_FG() {
		return montoInvertido_FG;
	}
	
	public void setMontoInvertido_FG(Double montoInvertido_FG) {
		this.montoInvertido_FG = montoInvertido_FG;
	}
	
	public MontoInvertido_F_G_CollectorService getMontoInvertido_F_G_service() {
		return montoInvertido_F_G_service;
	}
	
	public void setMontoInvertido_F_G_service(MontoInvertido_F_G_CollectorService montoInvertido_F_G_service) {
		this.montoInvertido_F_G_service = montoInvertido_F_G_service;
	}
	
	public InvestmentList getInvestment_list() {
		return investment_list;
	}
	
	public void setInvestment_list(InvestmentList investment_list) {
		this.investment_list = investment_list;
	}
	
	public int getMenu_item()
	{
		return menu_item;
	}
	
	public boolean isPuedeInvertire_en_F_G() {
		return puedeInvertire_en_F_G;
	}
	
	public void setPuedeInvertire_en_F_G(boolean puedeInvertire_en_F_G) {
		this.puedeInvertire_en_F_G = puedeInvertire_en_F_G;
	}
		
	public boolean isInvestment_param_ENABLED()
	{
		return investment_param_ENABLED;
	}
	
	public boolean isAutomatic_investment_ENABLED()
	{
		return automatic_investment_ENABLED;
	}
}
