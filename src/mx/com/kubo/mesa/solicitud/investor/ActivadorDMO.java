package mx.com.kubo.mesa.solicitud.investor;

import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.CreaCreditoService;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.services.InvestorService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.PrevencionLDService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.ServiceCallingService;

public abstract class ActivadorDMO 
implements ActivadorIMO
{			
	protected PrevencionLDService   service_PLD;
	protected SavingAccountService  service_saving_account;
	protected InvestorService       service_investor;
	protected ServiceCallingService service_calling;
	protected NaturalPersonService  service_natural_person;
	protected MembershipService     service_membership;
	
	protected RequestContext request;
	protected FacesContext faces;
	protected ELContext elContext;
	protected ELResolver resolver;	
	
	protected SessionBean sesion;
	
	protected Membership   membership;
	protected MembershipPK membership_PK;
	
	protected CreaCreditoService creacredito;
	protected NotificadorIMO notificador;
	
	protected ProyectLoan   proyect_loan;
	protected ProyectLoanPK proyect_loan_PK;
	
	protected PrevencionLD pld;
	protected SavingAccount saving;
	
	protected Investor   investor;
	protected InvestorPK investor_PK;
	
	protected List<String> lista_errores;
	
	protected String SAFI_client_id;
	protected String acreditado_IFE;
	protected String strRes;
	
	protected Integer company_id;
	
	protected int prospectus_id_inv;
	protected static final int IFE = 1;
	protected static final int INE = 2;
	
	protected final int EXTRANJERO = 0;
	
	protected boolean cuenta_OK;
	protected boolean alta_prospecto_OK;
	
	public void setService_PLD(PrevencionLDService service) 
	{
		service_PLD = service;
	}
	
	public void setService_saving_account(SavingAccountService service) 
	{
		service_saving_account = service;
	}
	
	public void setService_investor(InvestorService service) 
	{
		service_investor = service;
	}
	
	public void setService_calling(ServiceCallingService service) 
	{
		service_calling = service;
	}
	
	public void setService_natural_person(NaturalPersonService service) 
	{
		service_natural_person = service;
	}
	
	public void setService_membership(MembershipService service) 
	{
		service_membership = service;
	}
}
