package mx.com.kubo.managedbeans.mesa.administracion;

import java.util.Date;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.controller.InversionAutomatica;
import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.mesa.administracion.DocumentUploaderIMO;
import mx.com.kubo.model.AutomaticInvestment;
import mx.com.kubo.model.BlockedPerson;
import mx.com.kubo.model.InvestmentProgress;
import mx.com.kubo.model.Partner;
import mx.com.kubo.services.BlockedPersonService;
import mx.com.kubo.services.MailLogService;
import mx.com.kubo.services.MassiveProspectorService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.impl.PartnerServiceIMP;

public abstract class AdministrationProfileDMO 
{
	@ManagedProperty("#{service_partner}")
	protected PartnerServiceIMP  partnerserviceimp;
	
	@ManagedProperty("#{massiveProspectorServiceImp}")
	protected MassiveProspectorService massiveprospectorservice;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService naturalpersonservice;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemParamService;
	
	@ManagedProperty("#{mailLogServiceImp}")
	protected MailLogService service_mail_log;
	
	@ManagedProperty("#{blockedPersonServiceImp}")
	protected BlockedPersonService service_blocked_person;
	
	protected  RequestContext request;
	protected    FacesContext faces;
	protected ExternalContext external;
	
	protected ELResolver resolver;
	protected ELContext context;	
	
	protected HtmlInputText input;

	protected NotificadorConfigRequest request_notificar_config;
	protected PublicProyectServiceLocator locator;
	protected PublicProyect kubo_services;
	protected WsResponse response;
	
	protected SessionBean sesion;
		
	protected InversionAutomatica inversionAutomatica;
	protected DocumentUploaderIMO loader;
	protected    BlockedPersonIMO manager;
	
	protected List<BlockedPerson> lista_blocked_person;
	protected List<AutomaticInvestment> listaInversionistas;
	protected List<InvestmentProgress>	listaInversionesRealizadas;
	protected List<Partner> partnerLst;
	
	protected StringBuilder sb_exito;
	protected StringBuilder sb_error;
		
	protected String sesion_search;
	protected String actualPage = "asignaPerfil.xhtml";	
	protected String menu_SELECTED;	
	protected String separador=";";
	protected String partnerId;
	protected String archivo_exitoso ;    
	protected String archivo_error;
	protected String strResConsultaProspector;
	protected String strSendConsultaProspector;
	protected String email_date = "";
	
	protected Integer citizenship;
	
	protected boolean consultaSatisfactoria;	
	protected boolean bln_archivos;
	protected boolean flgStatusBuro;
	protected boolean email_date_ENABLED;
	protected boolean notificar_OK;
	
	protected Date fechaInversion;
	protected Date dateInicio;
	protected Date dateFin;
	
	protected final String RESUMEN_TABLERO_NORMATIVO = "61";
	
	public final int EXTRAJEROS = 0;
	public final int NACIONALES = 1;	
	
	public String getActualPage() 
	{
		return actualPage;
	}
	
	public void setPartnerserviceimp(PartnerServiceIMP service) 
	{
		partnerserviceimp = service;
	}

	public void setMassiveprospectorservice(MassiveProspectorService service) 
	{
		massiveprospectorservice = service;
	}

	public void setNaturalpersonservice(NaturalPersonService service) 
	{
		naturalpersonservice = service;
	}
	
	public void setSystemParamService(SystemParamService service) 
	{
		systemParamService = service;
	}

	public void setService_mail_log(MailLogService service) 
	{
		service_mail_log = service;
	}
	
	public void setService_blocked_person(BlockedPersonService service)
	{
		service_blocked_person = service;
	}

	public List<AutomaticInvestment> getListaInversionistas() 
	{
		return listaInversionistas;
	}

	public List<InvestmentProgress> getListaInversionesRealizadas() 
	{
		return listaInversionesRealizadas;
	}

	public List<Partner> getPartnerLst() 
	{
		return partnerLst;
	}
	
	public List<BlockedPerson> getLista_blocked_person()
	{
		return lista_blocked_person;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public boolean isBln_archivos() {
		return bln_archivos;
	}

	public String getArchivo_exitoso() {
		return archivo_exitoso;
	}

	public String getArchivo_error() {
		return archivo_error;
	}

	public String getStrResConsultaProspector() {
		return strResConsultaProspector;
	}

	public void setStrResConsultaProspector(String strResConsultaProspector) {
		this.strResConsultaProspector = strResConsultaProspector;
	}

	public boolean isConsultaSatisfactoria() {
		return consultaSatisfactoria;
	}

	public void setConsultaSatisfactoria(boolean consultaSatisfactoria) {
		this.consultaSatisfactoria = consultaSatisfactoria;
	}

	public String getStrSendConsultaProspector() {
		return strSendConsultaProspector;
	}

	public void setStrSendConsultaProspector(String strSendConsultaProspector) {
		this.strSendConsultaProspector = strSendConsultaProspector;
	}

	public Date getDateInicio() {
		return dateInicio;
	}

	public void setDateInicio(Date dateInicio) {
		this.dateInicio = dateInicio;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public boolean isFlgStatusBuro() {
		return flgStatusBuro;
	}
	
	public boolean isEmail_date_ENABLED()
	{
		return email_date_ENABLED;
	}

	public void setFlgStatusBuro(boolean flgStatusBuro) {
		this.flgStatusBuro = flgStatusBuro;
	}
	
	public String getEmail_date()
	{
		return email_date;
	}
}
