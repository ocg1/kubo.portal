package mx.com.kubo.managedbeans.portal.ofertas;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.mesa.solicitud.adicional.ReasignadorIMO;
import mx.com.kubo.model.LoanType;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Purpose;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.portal.ofertas.ParserRenovacionAutomaticaIMO;
import mx.com.kubo.portal.ofertas.RenovacionBean;
import mx.com.kubo.portal.ofertas.SimuladorIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.FrequencyService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.PurposeService;
import mx.com.kubo.services.ScoringService;

public abstract class OfertaRenovacionDMO 
{
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService service_proyect_loan;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService service_scoring;
	
	@ManagedProperty("#{purposeServiceImp}")
	protected PurposeService service_purpose;
	
	@ManagedProperty("#{frequencyServiceImp}")
	protected FrequencyService service_frequency;			
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService service_change_control;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	protected RequestContext request;
	protected FacesContext faces; 
	protected ELContext elContext;  
	protected ELResolver resolver;
	
	protected HtmlInputText input_text;
	protected HtmlSelectOneMenu select_one;
		
	protected SessionBean sesion;	
	
	protected ProyectLoan proyect_loan;
	
	protected Scoring score;
	
	protected RenovacionBean renovacion;
	protected Membership   membership;
	protected MembershipPK membership_PK;
		
	protected ParserRenovacionAutomaticaIMO parser;
	protected   SimuladorIMO simulador;
	protected ReasignadorIMO reasignador;
	protected NotificadorIMO notificador;
	
	protected List<LoanType> lista_loan_type;
	protected List<Purpose>  listPurpose;
	
	protected StringBuilder sb;
	protected NumberFormat format;		
	
	protected String SAFI_credit_id;
	protected String ofert_TOKEN;
	protected String ofert_rate_TOKEN;
	
	protected final String MEJOR_TASA;
	protected final String PRESTAMO_APROBADO;	
	protected final String TABLA_AMORTIZACION_XHTML;
	
	protected Double actual_rate;
	protected Double rate;
	protected Double opening_commission;
	
	protected Integer company_id;
	protected Integer prospectus_id;

	protected String loan_type_id; 
	
	protected final int FROM_TABLA_OFERTAS = 1;
	protected final int FROM_SIMULATOR = 2;
	protected final int PUBLICADO = 1;
	
	protected boolean publicacion_ENABLED;
	protected boolean panel_simulador_ENABLED;
	protected boolean panel_preaprobado_ENABLED;
	protected boolean max_payment_ENABLED;
	protected boolean same_rate_ENABLED;
	protected boolean automatic_aproved_ENABLED;
	
	protected OfertaRenovacionDMO()
	{
		format = NumberFormat.getNumberInstance(new Locale("es", "MX"));
		
		PRESTAMO_APROBADO = "Tu nuevo préstamo ya está aprobado";
		MEJOR_TASA = " con una mejor tasa:";
		
		TABLA_AMORTIZACION_XHTML = "/Kubo/Portal/acreditado/simulador/tabla-amortizacion.xhtml";
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
	
	public void setService_scoring(ScoringService service)
	{
		service_scoring = service;
	}
	
	public final void setService_membership(MembershipService service) 
	{
		service_membership = service;
	}

	public SimuladorIMO getSimulador() 
	{
		return simulador;
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
	
	public String getSAFI_credit_id()
	{
		return SAFI_credit_id;
	}	
	
	public String getOfert_TOKEN() 
	{
		return ofert_TOKEN;
	}
	
	public String getOfert_rate_TOKEN() 
	{
		return ofert_rate_TOKEN;
	}		
	
	public boolean isPublicacion_ENABLED() 
	{
		return publicacion_ENABLED;
	}

	public boolean isPanel_simulador_ENABLED() 
	{
		return panel_simulador_ENABLED;
	}

	public boolean isPanel_preaprobado_ENABLED() 
	{
		return panel_preaprobado_ENABLED;
	}

	public AccessService getAccessService() {
		return accessService;
	}

	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}			
}
