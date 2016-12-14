package mx.com.kubo.mesa.solicitud.adicional;

import com.mx.kubo.sgbws.models.dto.NewProjectRequestDTO;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.responses.WsSgbResponse;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.repositories.ServiceCallingDao;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.tools.Utilities;

public abstract class SGBNewProyectDMO 
implements SGBNewProyectIMO
{
	protected ProyectLoanService proyectloanService;
	
	protected ServiceCallingDao service_calling;
	
	protected MembershipService membershipservice;
	
	protected ServiceCalling srvCall;
	
	protected WsSgbRiskServiceLocator locator;
	protected WsSgbRisk     service_SGB;
	protected WsSgbResponse response;
	protected NewProjectRequestDTO request;
	
	protected ProyectLoan proyect_loan;
	protected Proyect proyect;
	protected MembershipPK  membershipPK;
	protected Membership    membership;	
	
	protected String response_msg;
	protected String bursolnum;
	protected String loan_type;
	protected String is_collection_solution;
	protected String is_prospector_score;
	protected String is_automatic_aproved;
	
	protected final String SGB_INIT_MSG;
	protected final String SGB_RESPONSE_MSG;
	protected final String PROSPECTOR_INVALID;
	
	protected Double ammount;
	protected Double rate;
	protected Double opening_commission;
	
	protected Integer company_id;
	protected Integer prospectus_id;
	protected Integer proyect_loan_id;
	protected Integer type_id;
	
	protected int frequency_id;
	protected int term_id;
	
	protected final int INIT = 1;
	protected final int RESPONSE_OK = 2;
	protected final int ERROR = 3;
	
	protected boolean change_status_ENABLED;
	
	protected SGBNewProyectDMO()
	{
		proyectloanService = Utilities.findBean("proyectLoanServiceImp");
		service_calling    = Utilities.findBean("serviceCallingDaoImp");
		membershipservice  = Utilities.findBean("membershipServiceImp");
		
		PROSPECTOR_INVALID = "PROSPECTOR_INVALID";
		      SGB_INIT_MSG = "Invocando Servicio Web WsSgbRiskServiceLocator.getWsSgbRisk.newProject";
		  SGB_RESPONSE_MSG = "Regresando Satisfactoriamente de Servicio WsSgbRiskServiceLocator.getWsSgbRisk.newProject: ";				  		  
	}
	
	public void setProyect(Proyect proyect)
	{
		this.proyect = proyect;
		
		if(proyect != null)
		{
			type_id = proyect.getType_id();	
		}		
	}
	
	public void setProyect_loan(ProyectLoan proyect_loan)
	{
		this.proyect_loan = proyect_loan;
		
		            company_id = proyect_loan.getProyectloanPk().getCompany_id();
		         prospectus_id = proyect_loan.getProyectloanPk().getProspectus_id();		
		       proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();				
		          frequency_id = proyect_loan.getFrequency_id();
		               term_id = proyect_loan.getTerm_id();
		               ammount = proyect_loan.getAmmount();
		             loan_type = proyect_loan.getLoan_type();
		                  rate = proyect_loan.getRate();		        
		    opening_commission = proyect_loan.getOpening_commission();
		is_collection_solution = proyect_loan.getIs_collection_solution();
		   is_prospector_score = proyect_loan.getIs_prospector_score();		
		
		boolean prospector_score_ENABLED = is_prospector_score != null && is_prospector_score.equals("S");
		
		if(prospector_score_ENABLED)
		{
			bursolnum = PROSPECTOR_INVALID;
			
		} else {
			
			bursolnum = proyect_loan.getMx_solicitud_buro();
		}								
	}
	
	public void setIs_automatic_aproved(String is_automatic_aproved)
	{
		this.is_automatic_aproved = is_automatic_aproved;
	}
	
	public boolean isChange_status_ENABLED()
	{
		return change_status_ENABLED;
	}
}
