package mx.com.kubo.test.acreditado;

import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.managedbeans.mesa.solicitud.adicional.TipoCreditoAdicional;
import mx.com.kubo.model.LoanType;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.portal.ofertas.SimuladorIMO;
import mx.com.kubo.mesa.solicitud.adicional.ReasignadorIMO;
import mx.com.kubo.services.ProyectLoanService;

public abstract class TestAdditionalCreditDMO 
{
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService service_proyect_loan;	
	
	protected RequestContext request;
	protected FacesContext faces; 
	protected ELContext elContext;  
	protected ELResolver resolver;
	
	protected HtmlSelectOneMenu select_one;
	
	protected   SimuladorIMO simulador;
	protected ReasignadorIMO reasignador;		
	
	protected SessionBean sesion;	
	protected Simulator simulator;
	
	protected ProyectLoan proyect_loan;
	
	protected TipoCreditoAdicional   tipo_credito_adicional;
	
	protected List<LoanType> lista_loan_type;
	
	protected String loan_type_id;
		
	protected Integer consulta_ENABLED;
	
	protected int loan_type; 
	
	public final void setService_proyect_loan(ProyectLoanService service) 
	{
		service_proyect_loan = service;
	}		

	public Integer getConsulta_ENABLED() 
	{
		return consulta_ENABLED;
	}

	public String getLoan_type_id() 
	{
		return loan_type_id;
	}
	
	public SimuladorIMO getSimulador() 
	{
		return simulador;
	}

	public List<LoanType> getLista_loan_type() 
	{
		return lista_loan_type;
	}		
}
