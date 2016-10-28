package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.investor.NavigationInvest;
import mx.com.kubo.managedbeans.mesa.MenuControlTableBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.services.ProyectLoanService;

import org.primefaces.context.RequestContext;

import com.soa.model.businessobject.Logs;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.responses.ApplicationLogResponse;

@ManagedBean
@RequestScoped
public class LogBean implements Serializable{

	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{proyectLoanServiceImp}")
	private ProyectLoanService servicioProyecto;
	
	public LogBean()  
	{
		super();
	}
	
	private ApplicationLogResponse appLog;
	private List<Logs> lstLogs;
	private String pin;
	private ProyectLoan proyectLoan;
	private String termAndFrequency;
	
	SessionBean sesion;
	
	@PostConstruct
	public void init(){
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		sesion = (SessionBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "sessionBean");
		
		
		SearchSummaySession searchsum= (SearchSummaySession) FacesContext.getCurrentInstance()
                .getApplication().getELResolver()
                .getValue(elContext, null, "searchSummaySession");
			if(searchsum.getSearchSummary() !=null)
			{	
				proyectLoan=servicioProyecto.findProyect(new ProyectLoanPK(Integer.parseInt(searchsum.getSearchSummary().split("::")[0]),Integer.parseInt(searchsum.getSearchSummary().split("::")[1]),Integer.parseInt(searchsum.getSearchSummary().split("::")[2]),Integer.parseInt(searchsum.getSearchSummary().split("::")[3])));
				switch (proyectLoan.getFrequency_id()) {
					case 1:
						termAndFrequency=proyectLoan.getTerm_id()>1 ? " a " +proyectLoan.getTerm_id()+" semanas": proyectLoan.getTerm_id()+" semana";						 
						break;
					case 2:
						termAndFrequency=proyectLoan.getTerm_id()>1 ? " a " +proyectLoan.getTerm_id()+" catorcenas": proyectLoan.getTerm_id()+" catorcena";				
						break;
					case 3:
						termAndFrequency=proyectLoan.getTerm_id()>1 ? " a " +proyectLoan.getTerm_id()+" quincenas": proyectLoan.getTerm_id()+" quincena";
						break;
					case 4:
						termAndFrequency=proyectLoan.getTerm_id()>1 ? " a " +proyectLoan.getTerm_id()+" Meses": proyectLoan.getTerm_id()+" Mes";
						break;
					default:
						break;
				}
				setPin(searchsum.getSearchSummary().split("::")[2]);	
				if(searchsum.getTypeLog() != null){
						
					if(searchsum.getTypeLog().equals("SOL"))
			        	prepareApplicationLog();
			        else if(searchsum.getTypeLog().equals("CRE"))
			        	prepareCreditLog();
			        else if(searchsum.getTypeLog().equals("EVE"))
			        	prepareEventLog();
			        else if(searchsum.getTypeLog().equals("COB"))
			        	prepareRecoveryLog();
			        else if(searchsum.getTypeLog().equals("EVA"))
			        	prepareEvaluationLog();
						
				}else{
					
						searchsum.setTypeLog("EVA");
			        	prepareEvaluationLog();
					
				}
			}
			
	}
	
	
	
	
	public List<Logs> getLstLogs() {
		return lstLogs;
	}
	public void setLstLogs(List<Logs> lstLogs) {
		this.lstLogs = lstLogs;
	}
	public ApplicationLogResponse getAppLog() {
		return appLog;
	}
	public void setAppLog(ApplicationLogResponse appLog) {
		this.appLog = appLog;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ProyectLoanService getServicioProyecto() {
		return servicioProyecto;
	}
	public ProyectLoan getProyectLoan() {
		return proyectLoan;
	}
	public String getTermAndFrequency() {
		return termAndFrequency;
	}
	public void setServicioProyecto(ProyectLoanService servicioProyecto) {
		this.servicioProyecto = servicioProyecto;
	}
	public void setProyectLoan(ProyectLoan proyectLoan) {
		this.proyectLoan = proyectLoan;
	}
	public void setTermAndFrequency(String termAndFrequency) {
		this.termAndFrequency = termAndFrequency;
	}

	public void prepareEventLog()
	{
		try {
			WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
			WsSgbRisk service = locator.getWsSgbRisk();
			this.appLog = service.eventsLogs(this.pin, "0");
			
			Logs[] aux = this.appLog.getLogs();
			this.lstLogs = new ArrayList<Logs>();
			if(aux!=null)
			{
				int size= aux.length;
				for(int i =0;i<size;i++)
				{
					lstLogs.add(aux[i]);
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("Error @mx.com.kubo.managedbeans.LogBean.prepareEventLog: " + e.getMessage() + " \n " + e.getCause());
			e.printStackTrace();
		}
	}
	public void prepareRecoveryLog()
	{
		try {
			WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
			WsSgbRisk service = locator.getWsSgbRisk();
			this.appLog = service.recoveryLogs(this.pin, "1");
			
			Logs[] aux = this.appLog.getLogs();
			this.lstLogs = new ArrayList<Logs>();
			if(aux!=null)
			{
				int size= aux.length;
				for(int i =0;i<size;i++)
				{
					lstLogs.add(aux[i]);
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("Error @mx.com.kubo.managedbeans.LogBean.prepareRecoveryLog: " + e.getMessage() + " \n " + e.getCause());
			e.printStackTrace();
		}
	}
	
	public void prepareEvaluationLog()
	{
		try {
			WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
			WsSgbRisk service = locator.getWsSgbRisk();
			this.appLog = service.evaluationLogs(this.pin, "0");
			
			Logs[] aux = this.appLog.getLogs();
			this.lstLogs = new ArrayList<Logs>();
			if(aux!=null)
			{
				int size= aux.length;
				for(int i =0;i<size;i++)
				{
					lstLogs.add(aux[i]);
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("Error @mx.com.kubo.managedbeans.LogBean.prepareEvaluationLog: " + e.getMessage() + " \n " + e.getCause());
			e.printStackTrace();
		}
	}
	
	public void prepareCreditLog()
	{
		try {
			WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
			WsSgbRisk service = locator.getWsSgbRisk();
			//TODO: Cambiar a creditLog
			this.appLog = service.creditsLogs(this.pin, "0");
			
			Logs[] aux = this.appLog.getLogs();
			this.lstLogs = new ArrayList<Logs>();
			if(aux!=null)
			{
				int size= aux.length;
				for(int i =0;i<size;i++)
				{
					lstLogs.add(aux[i]);
				}
			}

		} catch (Exception e) {
			System.out.println("Error @mx.com.kubo.managedbeans.LogBean.prepareLogs: " + e.getMessage() + " \n " + e.getCause());
			e.printStackTrace();
		}
	}
	
	public void prepareApplicationLog()
	{
		try {
			WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
			WsSgbRisk service = locator.getWsSgbRisk();
			this.appLog = service.applicationsLogs(this.pin, "0");
			Logs[] aux = this.appLog.getLogs();
			this.lstLogs = new ArrayList<Logs>();
			if(aux!=null)
			{
				int size= aux.length;
				for(int i =0;i<size;i++)
				{
					lstLogs.add(aux[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("Error @mx.com.kubo.managedbeans.LogBean.prepareApplicationLog: " + e.getMessage() + " \n " + e.getCause());
			e.printStackTrace();
		}
	}
	
	
	public void changeLogs(ActionEvent e){
		String tabId=(String) e.getComponent().getAttributes().get("section").toString();
        if(tabId.equals("SOL"))
        	prepareApplicationLog();
        else if(tabId.equals("CRE"))
        	prepareCreditLog();
        else if(tabId.equals("EVE"))
        	prepareEventLog();
        else if(tabId.equals("COB"))
        	prepareRecoveryLog();
        else if(tabId.equals("EVA"))
        	prepareEvaluationLog();
        	
     
    }  
	
	public String prepareLogs()
	{
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, String> map = context.getExternalContext().getRequestParameterMap();
			this.pin = map.get("pin");
			
			prepareEvaluationLog();
			
			
		} catch (Exception e) {
			System.out.println("Error @mx.com.kubo.managedbeans.LogBean.prepareLogs: " + e.getMessage() + " \n " + e.getCause());
			e.printStackTrace();
		}
		return "bitacoras";
	}
	
	public void gotoSummary(ActionEvent e){
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		if (sesion.getArea().equals('M')) {
			RequestContext requestContext = RequestContext.getCurrentInstance();
			MenuControlTableBean navMenu= (MenuControlTableBean) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "menuControlTableBean");			
			e.getComponent().getAttributes().put("section", "controlTable/searchRequest::12::menu1");
			navMenu.cambiaPagina(e);
			
			SearchSummaySession summarysesion = (SearchSummaySession) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "searchSummaySession");
			summarysesion.setTypeLog("EVA");
			
			SummaryRequest summary = (SummaryRequest) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "summaryRequest");
			summary.mapeoDatos(summarysesion.getSearchSummary());
			
			requestContext.addPartialUpdateTarget("form_Prin");	
			requestContext.addPartialUpdateTarget("actualPage");
		}else if (sesion.getArea().equals('I')) {
						
			SearchSummaySession summarysesion = (SearchSummaySession) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "searchSummaySession");
			summarysesion.setTypeLog("SOL");
			SummaryRequest summary = (SummaryRequest) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "summaryRequest");
			
			summary.mapeoDatos(summarysesion.getSearchSummary());
			
			NavigationInvest navigationinvest = (NavigationInvest) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "navigationInvest");
			
			e.getComponent().getAttributes().put("seccionInv", "summary");
			navigationinvest.changePage(e);
			
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.addPartialUpdateTarget("pnlContentInvest");	
			requestContext.addPartialUpdateTarget("panelContentListProyect");
		}
	}
	
}
