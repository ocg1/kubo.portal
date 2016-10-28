package mx.com.kubo.managedbeans;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.bean.SimBeanForList;
import mx.com.kubo.model.Frequency;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.Purpose;
import mx.com.kubo.repositories.ServiceCallingDao;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.FrequencyService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.PrevencionLDService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.PurposeService;
import mx.com.kubo.services.SimulationCacheService;
import mx.com.kubo.services.SimulationConfigService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.services.SystemParamService;

import org.apache.log4j.Logger;

public abstract class SimulatorDMO 
{
	@ManagedProperty("#{frequencyServiceImp}")
	protected FrequencyService frequencyService;
	
	@ManagedProperty("#{purposeServiceImp}")
	protected PurposeService purposeService;
	
	@ManagedProperty("#{simulatorServiceImp}")
	protected SimulatorService simulatorService;
	
	@ManagedProperty("#{serviceCallingDaoImp}")
	protected ServiceCallingDao servicecallingRepository;
	
	@ManagedProperty("#{prevencionLDServiceImp}")
	protected PrevencionLDService prevencionldservice;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService changeControlService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipservice;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectLoanService;
	
	@ManagedProperty("#{proyectServiceImp}")
	protected ProyectService proyectService;
	
	@ManagedProperty("#{simulationConfigServiceImp}")
	protected SimulationConfigService simulationConfigService;
	
	@ManagedProperty("#{simulationCacheServiceImp}")
	protected SimulationCacheService simulationCacheService;	
	
	private Prospectus prospectus;
	protected SessionBean sesion ;
	protected PrevencionLD prevencionld;
	private Purpose purpose     = new Purpose();
	private Frequency frequency = new Frequency();
	
	protected Logger log = Logger.getLogger(getClass());
	public  Locale locale = new Locale("es","mx");
	public  NumberFormat dec = NumberFormat.getCurrencyInstance(locale);
	
	protected List<SimBeanForList> lstSim;
	protected List<Purpose>        listPurpose;
	protected List<Frequency> listFrequency;
	
	private String freqStr;
	private String totalPagarStr="0";
	private String montoCuotaStr="0";
	private String ivaInteresStr="0";
	private String interesStr="0";
	private String tableAmort;
	private String catStr="0";
	private String frequencyStr="0";
	private String frequencyStr2="0";
	protected String partnerID = null;
	protected String area = null;
	private String fechaInicio;
	private String montoMax;
	private String montoMin;
	private String termMax;
	private String loanTypeID = null;
	
	private Double tasaTotal=52.6d;  /////////////////////////////////  TASA  TOTAL
	private Double iva=0.16d;
	private Double ivaInteres=0d;
	private Double interes=0d;
	private Double totalPagar=0d;
	private Double tasaPeriodo=0d;
	private Double tasaPeriodoPorc=0d;
	private Double tasaCalculada=0d;
	private Double tasaEfectivaAnual=0d;	
	private Double cat;
	private Double montoComisionApert;
	private Double montoCuota=0d;
		
	protected double ammount =50000;
	
	private int frequencyAnual=12;
	private int term_id =12;
	private int wsim;
	private int wgral;
	private int wcat;
	private int msim;
	private int numCuota=0;
	private int diasFreq = 0;
	private boolean hasPLD;	
	
	public SimulationConfigService getSimulationConfigService() {
		return simulationConfigService;
	}
	
	public SimulatorService getSimulatorService() 
	{
		return simulatorService;
	}
	
	public PurposeService getPurposeService() 
	{
		return purposeService;
	}
	
	public FrequencyService getFrequencyService() 
	{
		return frequencyService;
	}
	
	public ProyectLoanService getProyectLoanService() 
	{
		return proyectLoanService;
	}
	
	public PrevencionLDService getPrevencionldservice() 
	{
		return prevencionldservice;
	}
	
	public ServiceCallingDao getServicecallingRepository() 
	{
		return servicecallingRepository;
	}

	public SystemParamService getSystemparamservice() 
	{
		return systemparamservice;
	}
	
	public MembershipService getMembershipservice() 
	{
		return membershipservice;
	}

	public void setMembershipservice(MembershipService service) 
	{
		membershipservice = service;
	}

	public SimulationCacheService getSimulationCacheService() 
	{
		return simulationCacheService;
	}

	public void setSimulationCacheService(SimulationCacheService service) 
	{
		simulationCacheService = service;
	}

	public void setSystemparamservice(SystemParamService service) 
	{
		systemparamservice = service;
	}
	
	public void setServicecallingRepository(ServiceCallingDao service) 
	{
		this.servicecallingRepository = service;
	}
	
	public void setPrevencionldservice(PrevencionLDService service) 
	{
		this.prevencionldservice = service;
	}
	
	public Change_controlService getChangeControlService() 
	{
		return changeControlService;
	}
	
	public void setChangeControlService(Change_controlService service) 
	{
		this.changeControlService = service;
	}

	public void setProyectLoanService(ProyectLoanService service) 
	{
		proyectLoanService = service;
	}
	
	public void setSimulationConfigService(SimulationConfigService service) 
	{
		simulationConfigService = service;
	}

	public void setPurposeService(PurposeService service) 
	{
		purposeService = service;
	}
	
	public void setFrequencyService(FrequencyService service) 
	{
		frequencyService = service;
	}

	public void setSimulatorService(SimulatorService service) 
	{
		simulatorService = service;
	}
	
	public int getWsim() {
		return wsim;
	}

	public void setWsim(int wsim) {
		this.wsim = wsim;
	}

	public int getWgral() {
		return wgral;
	}

	public void setWgral(int wgral) {
		this.wgral = wgral;
	}

	public int getWcat() {
		return wcat;
	}

	public void setWcat(int wcat) {
		this.wcat = wcat;
	}
	
	public int getMsim() {
		return msim;
	}

	public void setMsim(int msim) {
		this.msim = msim;
	}

	public String getMontoMax() {
		return montoMax;
	}

	public void setMontoMax(String montoMax) {
		this.montoMax = montoMax;
	}

	public String getMontoMin() {
		return montoMin;
	}

	public void setMontoMin(String montoMin) {
		this.montoMin = montoMin;
	}

	public String getTermMax() {
		return termMax;
	}

	public void setTermMax(String termMax) {
		this.termMax = termMax;
	}
	
	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public void setMontoCuotaStr(String montoCuotaStr) 
	{
		this.montoCuotaStr = montoCuotaStr;
	}
	
	public Double getMontoComisionApert() {
		return montoComisionApert;
	}

	public void setMontoComisionApert(Double montoComisionApert) {
		this.montoComisionApert = montoComisionApert;
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public java.text.NumberFormat getDec() {
		return dec;
	}

	public void setDec(java.text.NumberFormat dec) {
		this.dec = dec;
	}

	public void setTotalPagarStr(String totalPagarStr) {
		this.totalPagarStr = totalPagarStr;
	}
	
	public List<SimBeanForList> getLstSim() 
	{	
		return lstSim;
	}

	public void setLstSim(List<SimBeanForList> lstSim) 
	{
		this.lstSim = lstSim;
	}
	
	public String getFrequencyStr2() {
		return frequencyStr2;
	}

	public void setFrequencyStr2(String frequencyStr2) {
		this.frequencyStr2 = frequencyStr2;
	}


	public String getFreqStr() {
		return freqStr;
	}

	public void setFreqStr(String freqStr) {
		this.freqStr = freqStr;
	}


	public Double getCat() {
		return cat;
	}

	public void setCat(Double cat) {
		this.cat = cat;
	}
	
	public Double getTasaTotal() 
	{	
		if(sesion.getRate() != null && !sesion.getArea().toString().equals("M"))
		{
			tasaTotal = sesion.getRate();
		}
		
		return tasaTotal;
	}

	public String getCatStr() 
	{
		if(getCat() != null)
		{
			catStr = "" + ((double) Math.round((getCat()) * 100) / 100);
		}
		
		return catStr;
	}

	public void setCatStr(String catStr) {
		this.catStr = catStr;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public void setIvaInteresStr(String ivaInteresStr) {
		this.ivaInteresStr = ivaInteresStr;
	}

	public void setInteresStr(String interesStr) {
		this.interesStr = interesStr;
	}
	
	public Double getTasaEfectivaAnual() {
		return tasaEfectivaAnual;
	}

	public void setTasaEfectivaAnual(Double tasaEfectivaAnual) {
		this.tasaEfectivaAnual = tasaEfectivaAnual;
	}
	
	public int getFrequencyAnual() {
		return frequencyAnual;
	}

	public void setFrequencyAnual(int frequencyAnual) {
		this.frequencyAnual = frequencyAnual;
	}
	
	public String getTableAmort() {
		return tableAmort;
	}

	public void setTableAmort(String tableAmort) {
		this.tableAmort = tableAmort;
	}

	public String getFrequencyStr() {
		return frequencyStr;
	}

	public void setFrequencyStr(String frequencyStr) {
		this.frequencyStr = frequencyStr;
	}

	public Double getTasaPeriodoPorc() {
		return tasaPeriodoPorc;
	}

	public void setTasaPeriodoPorc(Double tasaPeriodoPorc) {
		this.tasaPeriodoPorc = tasaPeriodoPorc;
	}
	
	public boolean isHasPLD() {
		return hasPLD;
	}
	
	public void setHasPLD(boolean hasPLD) {
		this.hasPLD = hasPLD;
	}
	
	public PrevencionLD getPrevencionld() {
		return prevencionld;
	}
	
	public void setPrevencionld(PrevencionLD prevencionld) {
		this.prevencionld = prevencionld;
	}
	
	public int getNumCuota() {
		return numCuota;
	}
	
	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public int getTerm_id() {
		return term_id;
	}

	public void setTerm_id(int term_id) {
		this.term_id = term_id;
	}
	
	public String getLoanTypeID() {
		return loanTypeID;
	}

	public void setLoanTypeID(String loanTypeID) {
		this.loanTypeID = loanTypeID;
	}

	public String getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setNumCuota(int numCuota) {
		this.numCuota = numCuota;
	}

	public Double getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(Double montoCuota) {
		this.montoCuota = montoCuota;
	}

	public Double getTasaPeriodo() {
		return tasaPeriodo;
	}

	public void setTasaPeriodo(Double tasaPeriodo) {
		this.tasaPeriodo = tasaPeriodo;
	}

	public void setTasaTotal(Double tasaTotal) {
		this.tasaTotal = tasaTotal;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public Double getInteres() {
		return interes;
	}

	public void setInteres(Double interes) {
		this.interes = interes;
	}  

	public Double getIvaInteres() {
		return ivaInteres;
	}

	public void setIvaInteres(Double ivaInteres) {
		this.ivaInteres = ivaInteres;
	}
	
	public Double getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(Double totalPagar) {
		this.totalPagar = totalPagar;
	}
	
	public Double getTasaCalculada() {
		return tasaCalculada;
	}

	public void setTasaCalculada(Double tasaCalculada) {
		this.tasaCalculada = tasaCalculada;
	}
	
	public Prospectus getProspectus() {
		return prospectus;
	}

	public void setProspectus(Prospectus prospectus) {
		this.prospectus = prospectus;
	}
	
	public Purpose getPurpose() {
		return purpose;
	}

	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public List<Frequency> getListFrequency() {
		return listFrequency;
	}

	public void setListFrequency(List<Frequency> listFrequency) {
		this.listFrequency = listFrequency;
	}

	public List<Purpose> getListPurpose() {
		return listPurpose;
	}

	public void setListPurpose(List<Purpose> listPurpose) {
		this.listPurpose = listPurpose;
	}

	public int getDiasFreq() {
		return diasFreq;
	}

	public void setDiasFreq(int diasFreq) {
		this.diasFreq = diasFreq;
	}

	public ProyectService getProyectService() {
		return proyectService;
	}

	public void setProyectService(ProyectService proyectService) {
		this.proyectService = proyectService;
	}
}
