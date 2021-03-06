package mx.com.kubo.managedbeans.portal.solicitud;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.portal.AccessIMO;
import mx.com.kubo.portal.reader.ParameterReaderIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.ProyectLoanService;

public abstract class EditorSolicitudDMO 
{
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access; 
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService service_proyect_loan;
	
	protected FacesContext faces;
	protected ELContext  context;
	protected ELResolver resolver;
	
	protected          AccessIMO auditor;
	protected ParameterReaderIMO reader;
	
	protected SearchSummaySession session_sumary;
	protected SummaryRequest  summary_request;
	
	protected SessionBean sesion;
	
	protected ProyectLoan proyect_loan;	
	
	protected String page_title;
	protected String searchSummary;
	protected String credit_id;
	protected String access_from;
	
	protected String url_request = "../jsf/templates/request.xhtml";
	
	protected Integer company_id;
	protected Integer prospectus_id;
	protected Integer prospectus_id_viewed;
	protected Integer proyect_loan_id;	
	protected Integer proyect_id;
	
	protected final int SCREEN_EDITAR_SOLICITUD = 75;
	
	public final void setService_access(AccessService service) 
	{
		service_access = service;
	}
	
	public void setService_proyect_loan(ProyectLoanService service) 
	{
		service_proyect_loan = service;
	}
	
	public final String getPage_title()
	{
		return page_title;
	}
	
	public final AccessIMO getAuditor()
	{
		return auditor;
	}

	public String getUrl_request() {
		return url_request;
	}

	public void setUrl_request(String url_request) {
		this.url_request = url_request;
	}
}
