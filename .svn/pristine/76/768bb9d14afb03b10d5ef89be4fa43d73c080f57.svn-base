package mx.com.kubo.managedbeans.mesa.administracion;

import java.util.Date;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.controller.InversionAutomatica;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.AutomaticInvestment;
import mx.com.kubo.model.InvestmentProgress;
import mx.com.kubo.model.Partner;
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
	
	protected RequestContext  request;
	protected FacesContext    faces;		
	protected ELResolver      resolver;
	protected ELContext       context;	
	
	protected SessionBean sesion;
		
	protected List<AutomaticInvestment> listaInversionistas;
	protected List<InvestmentProgress>	listaInversionesRealizadas;
	protected List<Partner> partnerLst;
		
	protected String sesion_search;
	protected String actualPage = "asignaPerfil.xhtml";	
	protected String menu_SELECTED;	
	protected String separador;
	protected String partnerId;
	protected String archivo_exitoso ;    
	protected String archivo_error;
	protected String strResConsultaProspector;
	
	protected String strSendConsultaProspector;
	
	protected boolean  consultaSatisfactoria;
	
	
	protected StringBuilder sb_exito;
	protected StringBuilder sb_error;
	
	protected boolean bln_archivos ;
	protected boolean flgStatusBuro;
					
	protected InversionAutomatica inversionAutomatica;
	
	protected Date fechaInversion;
	
	protected Date dateInicio;
	protected Date dateFin;
	
	public String getActualPage() 
	{
		return actualPage;
	}
	
	public void setPartnerserviceimp(PartnerServiceIMP partnerserviceimp) {
		this.partnerserviceimp = partnerserviceimp;
	}

	public void setMassiveprospectorservice(MassiveProspectorService massiveprospectorservice) {
		this.massiveprospectorservice = massiveprospectorservice;
	}

	public void setNaturalpersonservice(NaturalPersonService naturalpersonservice) {
		this.naturalpersonservice = naturalpersonservice;
	}

	public List<AutomaticInvestment> getListaInversionistas() {
		return listaInversionistas;
	}

	public void setListaInversionistas(List<AutomaticInvestment> listaInversionistas) {
		this.listaInversionistas = listaInversionistas;
	}

	public List<InvestmentProgress> getListaInversionesRealizadas() {
		return listaInversionesRealizadas;
	}

	public List<Partner> getPartnerLst() {
		return partnerLst;
	}

	public void setListaInversionesRealizadas(List<InvestmentProgress> listaInversionesRealizadas) {
		this.listaInversionesRealizadas = listaInversionesRealizadas;
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

	public SystemParamService getSystemParamService() {
		return systemParamService;
	}

	public void setSystemParamService(SystemParamService systemParamService) {
		this.systemParamService = systemParamService;
	}

	public boolean isFlgStatusBuro() {
		return flgStatusBuro;
	}

	public void setFlgStatusBuro(boolean flgStatusBuro) {
		this.flgStatusBuro = flgStatusBuro;
	}
}
