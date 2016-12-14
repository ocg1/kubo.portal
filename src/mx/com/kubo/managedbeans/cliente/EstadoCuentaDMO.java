package mx.com.kubo.managedbeans.cliente;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.CuentasAhoMovDep;
import mx.com.kubo.bean.MovsCuentaAhorro;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.bean.TAmortizacionBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TicketConfig;
import mx.com.kubo.portal.reader.ParameterReaderIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.EstadoCuentaService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TicketConfigService;
import mx.com.kubo.services.TicketService;
import mx.com.kubo.services.cliente.cuenta.ServiceEstadoCuentaIMO;
import mx.com.kubo.services.cliente.cuenta.ServiceReferenciaPagoIMP;
import mx.com.kubo.services.mesa.solicitud.busqueda.ClientViewFullNameService;

import mx.com.kubo.model.TSafiCreditosMovs;
import mx.com.kubo.model.TSafiCuentasAhoMovDep;
import mx.com.kubo.model.TSafiPagosCuota;
import mx.com.kubo.model.TSafiPosicionInt;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;

public abstract class EstadoCuentaDMO 
{	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	@ManagedProperty("#{clientViewFullNameServiceImp}")
	protected ClientViewFullNameService clientViewFullNameService;
	
	@ManagedProperty("#{prospectusServiceImp}")
	protected ProspectusService prospectusService;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectLoanService;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService gnNaturalService;
	
	@ManagedProperty("#{ticketServiceImp}")
	protected TicketService ticketservice;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService servicioProyecto;
	
	@ManagedProperty("#{ticketConfigServiceImp}")
	protected TicketConfigService ticketconfigservice;
	
	@ManagedProperty("#{savingAccountServiceImp}")
	protected SavingAccountService savingaccountservice;
	
	@ManagedProperty("#{addressServiceImp}")
	protected AddressService direccionService;		
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{estadoCuentaServiceImp}")
	protected EstadoCuentaService estadocuentaservice;
	
	protected ServiceEstadoCuentaIMO service_estado_cuenta;
	
	
	protected ServiceReferenciaPagoIMP service_referencia_pago;
		
	protected FacesContext    faces;				
	protected ExternalContext external;
	protected ELResolver      resolver;
	protected ELContext       elContext;
	
	protected Locale              locale;	
	protected NumberFormat   dec;	
	protected NumberFormat   num;
	protected ResourceBundle      recurso;
	protected SearchSummaySession searchsum;	
	protected SystemParam         systemparam;
	protected SystemParamPK       systemParam_PK;
	protected WsSgbRiskServiceLocator locator;
	protected WsSgbRisk           web_service_SGB;	
	protected ParameterReaderIMO reader;
	
	protected Prospectus prospectus;
	protected SessionBean sesion;	
	protected Access access;
	
	protected SimpleDateFormat formato_ddMMyyyy;
	
	protected List<ProyectLoan> list_proyect_loan;
		
	protected List<TSafiCreditosMovs>     movs;
	protected List<TSafiCuentasAhoMovDep> movs2 = null;
	protected List<TSafiPagosCuota>       tamortizacion;
	protected List<TSafiPosicionInt>      posicionInt;
		
	protected List<CuentasAhoMovDep>  ahoMov = null;
	protected List<TicketConfig>      thisList;	
	protected List<MovsCuentaAhorro>  movimientos;	
	protected List<MovsCuentaAhorro>  tableMovs;
	protected List<CreditoEMO>        lista_creditos;
	protected List<TAmortizacionBean> tablaAmort;
	
	protected Date fechaSofipo;
	protected Date fechaCorte;
	protected Date fechaInicio;
	protected Date fechaFin;
		
	protected SimpleDateFormat fm1;
	protected SimpleDateFormat formatStr; 
	protected SimpleDateFormat	formatStr2; 
	protected SimpleDateFormat formatStr3;	
	protected SimpleDateFormat formatStr4; 
	protected SimpleDateFormat formatStr5;
		
	protected String addressStr;
	protected String colores;			
	protected String edoHtml;
	protected String fechaFinStr;
	protected String fechaInicioStr;
	protected String mesStr;
	protected String photo = "img/sinimagen.jpg";
	protected String reportePago;
	protected String saldoLiquidarStr;
	protected String scriptPade;
	protected String values;
	protected String capitalDes = "";
	protected String interesDes = "";
	protected String ivaDes     = "";	
	protected String fecha_inicio;
	protected String fecha_vencimiento;
	protected String cuota;
	protected String nombre;
	protected String credit_id;
	protected String saldo_liquidar;
	
	protected  boolean accessFromURL;
	
	//protected boolean saldo_deudor_superior_al_MIN;
	
	private String comisionesStr = "";
	private String domicilio;
	private String font_size;
	private String fechaActualizacion; 
	private String fechaCorteStr;
	private String imgWidth;
	private String imgHeight;
	private String razonSocial; 
	private String rfcKubo;		
	private String search;
	private String telefonoKubo;
	
	
	
	
	protected String monthStr[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo","Junio", 
									 "Julio",   "Agosto", "Septiembre", "Octubre", "Noviembre","Diciembre" };			
	
	
	protected Hashtable<String,Double[]> htComisiones;
	
	protected ArrayList<String> months = new ArrayList<String>();
	protected ArrayList<String> years  = new ArrayList<String>();
	
	protected Double capitales;
	protected Double capital_pagado;
	protected Double capital_vencido;
	protected Double intereses;
	protected Double intereses_generados;		
	protected Double iva_generado;
	protected Double saldo_insoluto;
	protected Double tasa_mora;
	protected Double total = 0D;		
	protected Double saldoLiquidar = 0D;
	private Double 	 comisiones = 0D;
	
	protected Double indice_saldo_deudor_MIN = 0D;
		
	protected Integer contador_creditos_vigentes;
	protected Integer contador_creditos_liquidados;
	protected Integer contador_creditos_mora;
	protected Integer creditos_totales;
	protected Integer contador_creditos_vencidos; 
	protected Integer prospectus_id; 
	protected Integer company_id;
	protected Integer mes ;	
	protected Integer factorMora = 0;
				
	protected boolean displayMoreProyect = true;
	protected boolean flagRenderEdoCuenta = false;
	protected boolean displayPag;
	protected boolean panel_OPENED;
	
	protected boolean flag_In_for_min_100_per;
	
	private boolean restructure_flag;
	private boolean pademovil;	
	private boolean disPnlScriptPade;	
	
	private int year ;
	private int radioTypeSearch = 1;
	
	protected final int CASA   = 1;
	
	protected final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;

	protected EstadoCuentaDMO()
	{				
		capital_vencido              = 0.0;
		
 		contador_creditos_vigentes   = 0;
		contador_creditos_liquidados = 0;
		contador_creditos_mora       = 0;
		creditos_totales             = 0;
		contador_creditos_vencidos   = 0;		

		formato_ddMMyyyy = new SimpleDateFormat("dd'-'MMM'-'yyyy", new Locale("ES"));		
		fm1        = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");	 
		formatStr  = new SimpleDateFormat("EEE dd'-'MMM'-'yyyy",   		 new Locale("ES"));			
		formatStr2 = new SimpleDateFormat("dd'-'MM'-'yyyy",         	 new Locale("ES"));
		formatStr3 = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy",		 new Locale("ES"));	
		formatStr4 = new SimpleDateFormat("hh:mm a");
		formatStr5 = new SimpleDateFormat("EEEE dd' de 'MMMM' de 'yyyy", new Locale("ES"));
		locale 	       = new Locale("es","mx");				
		fechaSofipo    = new Date();
		systemParam_PK = new SystemParamPK();
		dec 		   = NumberFormat.getCurrencyInstance(locale);
		num 		   = NumberFormat.getNumberInstance(locale);
		
		service_referencia_pago = new ServiceReferenciaPagoIMP();
		
	}
	

	public void setService_access(AccessService service) 
	{
		service_access = service;
	}

	public void setSystemparamservice(SystemParamService service) 
	{
		systemparamservice = service;
	}

	public void setClientViewFullNameService(ClientViewFullNameService service) 
	{
		clientViewFullNameService = service;
	}

	public void setProspectusService(ProspectusService service) 
	{
		prospectusService = service;
	}

	public void setProyectLoanService(ProyectLoanService service) 
	{
		proyectLoanService = service;
	}

	public void setGnNaturalService(NaturalPersonService service) 
	{
		gnNaturalService = service;
	}

	public void setTicketservice(TicketService service) 
	{
		ticketservice = service;
	}

	public void setServicioProyecto(ProyectLoanService service) 
	{
		servicioProyecto = service;
	}

	public void setTicketconfigservice(TicketConfigService service) 
	{
		ticketconfigservice = service;
	}

	public void setSavingaccountservice(SavingAccountService service) 
	{
		savingaccountservice = service;
	}

	public void setDireccionService(AddressService service) 
	{
		direccionService = service;
	}
	
	public void setMembershipService(MembershipService service) 
	{
		membershipService = service;
	}
	
	public final void setService_estado_cuenta(ServiceEstadoCuentaIMO service) 
	{
		service_estado_cuenta = service;
	}

	public boolean isDisplayMoreProyect() {
		return displayMoreProyect;
	}

	public boolean isRestructure() {
		return restructure_flag;
	}

	public void setRestructure(boolean bandera) {
		restructure_flag = bandera;
	}

	public boolean isFlagRenderEdoCuenta() {
		return flagRenderEdoCuenta;
	}

	public boolean isPademovil() {
		return pademovil;
	}

	public void setPademovil(boolean pademovil) {
		this.pademovil = pademovil;
	}

	public boolean isDisPnlScriptPade() {
		return disPnlScriptPade;
	}

	public void setDisPnlScriptPade(boolean disPnlScriptPade) {
		this.disPnlScriptPade = disPnlScriptPade;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getRadioTypeSearch() {
		return radioTypeSearch;
	}

	public void setRadioTypeSearch(int radioTypeSearch) {
		this.radioTypeSearch = radioTypeSearch;
	}

	public final Integer getContador_creditos_vigentes()
	{
		return contador_creditos_vigentes;
	}
	
	public final Integer getContador_creditos_liquidados() 
	{
		return contador_creditos_liquidados;
	}

	public final Integer getContador_creditos_vencidos() 
	{
		return contador_creditos_vencidos;
	}

	public final Integer getContador_creditos_mora() 
	{
		return contador_creditos_mora;
	}

	public final Integer getCreditos_totales() 
	{
		return creditos_totales;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getComisiones() {
		return comisiones;
	}

	public void setComisiones(Double comisiones) 
	{
		this.comisiones = comisiones;		
		comisionesStr    = dec.format(comisiones);		
	}

	public Date getFechaSofipo() {
		return fechaSofipo;
	}

	public void setFechaSofipo(Date fechaSofipo) {
		this.fechaSofipo = fechaSofipo;
	}

	public String getComisionesStr() {
		return comisionesStr;
	}

	public void setComisionesStr(String comisionesStr) {
		this.comisionesStr = comisionesStr;
	}

	public String getRazonSocial() {
		return razonSocial;
	}
	
	public final String getNombre()
	{
		return nombre;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRfcKubo() {
		return rfcKubo;
	}

	public void setRfcKubo(String rfcKubo) {
		this.rfcKubo = rfcKubo;
	}

	public String getTelefonoKubo() {
		return telefonoKubo;
	}

	public void setTelefonoKubo(String telefonoKubo) {
		this.telefonoKubo = telefonoKubo;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getMesStr() {
		return mesStr;
	}

	public void setMesStr(String mesStr) {
		this.mesStr = mesStr;
		
		if (mesStr != null && mesStr.length() > 0
				&& !mesStr.equals("0")) {
			for (int i = 0; i < monthStr.length; i++) {
				if ((monthStr[i]).equals(mesStr)) {
					if ((i + 1) < 10)
						mes = (i + 1);
					else
						mes = (i + 1) ;
				}
			}
			
		} else{
			mes = 0;
			return;
		}
		
	}

	public String getScriptPade() {
		return scriptPade;
	}

	public void setScriptPade(String scriptPade) {
		this.scriptPade = scriptPade;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getEdoHtml() {
		return edoHtml;
	}

	public void setEdoHtml(String edoHtml) {
		this.edoHtml = edoHtml;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getFont_size() {
		return font_size;
	}

	public void setFont_size(String font_size) {
		this.font_size = font_size;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}

	public String getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}

	public String getFechaInicioStr() 
	{
		if(fechaInicio != null)
		{
			fechaInicioStr = (formatStr3.format(fechaInicio)).toUpperCase();
		}else{
			fechaInicioStr ="";
		}
		return fechaInicioStr;
	}
	
	public String getFechaFinStr() 
	{
		if(fechaFin != null)
		{
			fechaFinStr = (formatStr3.format(fechaFin)).toUpperCase();
		}else{
			fechaFinStr ="";
		}
		return fechaFinStr;
	}
	
	public void setFechaInicioStr(String fechaInicioStr) {
		this.fechaInicioStr = fechaInicioStr;
	}

	public void setFechaFinStr(String fechaFinStr) {
		this.fechaFinStr = fechaFinStr;
	}

	public String getFechaCorteStr() {
		return fechaCorteStr;
	}

	public void setFechaCorteStr(String fechaCorteStr) {
		this.fechaCorteStr = fechaCorteStr;
	}

	public String getAddressStr() {
		return addressStr;
	}

	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}

	public String[] getMonthStr() {
		return monthStr;
	}

	public void setMonthStr(String[] monthStr) {
		this.monthStr = monthStr;
	}

	public List<CuentasAhoMovDep> getAhoMov() {
		return ahoMov;
	}

	public void setAhoMov(List<CuentasAhoMovDep> ahoMov) {
		this.ahoMov = ahoMov;
	}

	public List<TicketConfig> getThisList() {
		return thisList;
	}

	public void setThisList(List<TicketConfig> thisList) {
		this.thisList = thisList;
	}

	protected void setLstTicketConfig( List<TicketConfig> lst)
	{
		thisList = lst;
	}
	
	public List<MovsCuentaAhorro> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<MovsCuentaAhorro> movimientos) {
		this.movimientos = movimientos;
	}

	public List<MovsCuentaAhorro> getTableMovs() {
		return tableMovs;
	}

	public void setTableMovs(List<MovsCuentaAhorro> tableMovs) {
		this.tableMovs = tableMovs;
	}

	public List<CreditoEMO> getCreditlst() 
	{
		return lista_creditos;
	}

	public Hashtable<String, Double[]> getHtComisiones() {
		return htComisiones;
	}

	public void setHtComisiones(Hashtable<String, Double[]> htComisiones) {
		this.htComisiones = htComisiones;
	}

	public ArrayList<String> getMonths() {
		return months;
	}

	public void setMonths(ArrayList<String> months) {
		this.months = months;
	}

	public ArrayList<String> getYears() {
		return years;
	}

	public void setYears(ArrayList<String> years) {
		this.years = years;
	}

	public SystemParam getSystemparam() {
		return systemparam;
	}

	public void setSystemparam(SystemParam systemparam) {
		this.systemparam = systemparam;
	}

	public Prospectus getProspectus() {
		return prospectus;
	}

	public void setProspectus(Prospectus prospectus) {
		this.prospectus = prospectus;
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public List<TSafiCuentasAhoMovDep> getMovs2() {
		return movs2;
	}

	public void setMovs2( List<TSafiCuentasAhoMovDep> movs2) {
		this.movs2 = movs2;
	}
	
	protected ProyectLoan getProyectLoanByCreditId(List<ProyectLoan> lstPyL, String credit_id )
	{		
		ProyectLoan res = null;
				
		for(ProyectLoan pyl : lstPyL)
		{			
			if(pyl.getSafi_credit_id() != null && Integer.parseInt(pyl.getSafi_credit_id()) == Integer.parseInt((credit_id)) )
			{
				res = pyl;
				break;
			}			
		}
		
		return res;		
	}


	public String getCredit_id() {
		return credit_id;
	}


	public void setCredit_id(String credit_id) {
		this.credit_id = credit_id;
	}


	public String getSaldo_liquidar() {
		return saldo_liquidar;
	}


	public void setSaldo_liquidar(String saldo_liquidar) {
		this.saldo_liquidar = saldo_liquidar;
	}


	public EstadoCuentaService getEstadocuentaservice() {
		return estadocuentaservice;
	}


	public void setEstadocuentaservice(EstadoCuentaService estadocuentaservice) {
		this.estadocuentaservice = estadocuentaservice;
	}
	
}
