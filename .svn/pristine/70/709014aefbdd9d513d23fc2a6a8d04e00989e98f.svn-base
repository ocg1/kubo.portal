package mx.com.kubo.managedbeans.portal.ofertas;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.mesa.solicitud.adicional.ReasignadorIMO;
import mx.com.kubo.model.LoanType;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Purpose;
import mx.com.kubo.portal.ofertas.ParserRenovacionAutomaticaIMO;
import mx.com.kubo.portal.ofertas.PreaprobadorIMO;
import mx.com.kubo.portal.ofertas.SimuladorIMO;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.FrequencyService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.PurposeService;

public abstract class OfertaRenovacionDMO 
{
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService service_proyect_loan;
	
	@ManagedProperty("#{purposeServiceImp}")
	protected PurposeService service_purpose;
	
	@ManagedProperty("#{frequencyServiceImp}")
	protected FrequencyService service_frequency;
		
	@ManagedProperty("#{reasignador_service}")
	protected ReasignadorIMO reasignador;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService service_change_control;
	
	protected RequestContext request;
	protected FacesContext faces; 
	protected ELContext elContext;  
	protected ELResolver resolver;
	
	protected HtmlInputText input_text;
		
	protected SessionBean sesion;	
	//protected Simulator simulator;
	
	protected ProyectLoan proyect_loan;
	
	protected NumberFormat format;
	
	protected ParserRenovacionAutomaticaIMO parser;
	protected    SimuladorIMO simulador;
	protected PreaprobadorIMO preaprobador;
	
	protected List<LoanType> lista_loan_type;
	protected List<Purpose>  listPurpose;
	
	protected final int FROM_TABLA_OFERTAS = 1;
	protected final int FROM_SIMULATOR = 2;
	
	protected boolean panel_simulador_ENABLED;
	protected boolean panel_preaprobado_ENABLED;
	
	protected OfertaRenovacionDMO()
	{
		format = NumberFormat.getNumberInstance(new Locale("es", "MX"));
	}
	
	public final void setService_proyect_loan(ProyectLoanService service) 
	{
		service_proyect_loan = service;
	}	
	
	public void setService_purpose(PurposeService service) 
	{
		service_purpose = service;
	}			
	
	public void setService_frequency(FrequencyService service)
	{
		service_frequency = service;
	}
	
	public void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}

	public void setReasignador(ReasignadorIMO reasignador) 
	{
		this.reasignador = reasignador;
	}

	public SimuladorIMO getSimulador() 
	{
		return simulador;
	}					

	public PreaprobadorIMO getPreaprobador() 
	{
		return preaprobador;
	}		

	public ParserRenovacionAutomaticaIMO getParser() 
	{
		return parser;
	}

	public List<LoanType> getLista_loan_type() 
	{
		return lista_loan_type;
	}	
	
	public List<Purpose> getListPurpose() 
	{
		return listPurpose;
	}

	public boolean isPanel_simulador_ENABLED() 
	{
		return panel_simulador_ENABLED;
	}

	public boolean isPanel_preaprobado_ENABLED() 
	{
		return panel_preaprobado_ENABLED;
	}			
}
