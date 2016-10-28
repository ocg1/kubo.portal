package mx.com.kubo.portal.efectivo;

import java.text.NumberFormat;
import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.model.LoanNegotiation;
import mx.com.kubo.model.LoanNegotiationPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.LoanNegotiationService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.tools.Utilities;

public abstract class LoanNegotiationDMO 
implements LoanNegotiationIMO
{
	protected SimulatorService simulatorService;
	protected LoanNegotiationService service_negotiation;
	protected ServiceCallingService servicecallingService;
	protected ProyectLoanService service_proyect_loan;
	
	protected    FacesContext faces;
	protected  RequestContext request;	
	protected       ELContext elContext;
	
	protected HtmlInputText input_text;
	
	protected SessionBean sesion;
	
	protected ELResolver resolver;		

	protected ProyectLoan proyect_loan;
	
	protected Simulator simulator;
	
	protected LoanNegotiation   negotiation;
	protected LoanNegotiationPK negotiation_PK;
	
	protected Locale locale;
	protected NumberFormat num;
	
	protected String montoNegotiation = "0";	
	
	protected Double pagoMenIni;
	protected Double pagoIni;
	protected Double montoIni;
	protected Double liqIni;
	
	protected Double pagoMenControl;
	
	protected Integer termInt;	
	protected Integer freqIni;
	
	protected Integer company_id;
	protected Integer prospectus_id;
	protected Integer prospectus_id_viewed;
	protected Integer proyect_loan_id;	
	protected Integer proyect_id;

	
	protected boolean hasNegotiation;
	protected boolean dispBotCondiciones;
	protected boolean dispSendNegotiation;;
	
	protected LoanNegotiationDMO()
	{
		   simulatorService = Utilities.findBean("simulatorServiceImp");
		service_negotiation = Utilities.findBean("loanNegotiationServiceImp");
		servicecallingService = Utilities.findBean("serviceCallingServiceImp");
		service_proyect_loan  = Utilities.findBean("proyectLoanServiceImp");
					
		locale = new Locale("es","mx");
		num = NumberFormat.getNumberInstance(locale);
	}
	
	public void setFaces(FacesContext faces) 
	{
		this.faces = faces;
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
	}

	public void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		          company_id = sesion.getCompany_id();
			   prospectus_id = sesion.getCoachProspectus_id();
		prospectus_id_viewed = sesion.getProspectus_id();	  
	}

	public void setProyect_loan(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
		
		     proyect_id = proyect_loan.getProyectloanPk().getProyect_id();
		proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
	}

	public LoanNegotiation getNegotiation() 
	{
		return negotiation;
	}
	
	public String getMontoNegotiation()
	{
		return montoNegotiation;
	}
	
	public boolean isDispBotCondiciones() 
	{
		return dispBotCondiciones;
	}	
}
