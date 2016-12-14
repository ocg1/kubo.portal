package mx.com.kubo.mesa.buro;

import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.tools.Utilities;

public abstract class ProyectLoanCreatorDMO 
implements ProyectLoanCreatorIMO
{
	protected ProyectLoanService proyectloanService;
	
	protected ProyectService proyectService;
	
	protected Proyect       proyect;
	protected ProyectPK     proyect_pk;
	protected ProyectLoan proyect_loan;
	protected ProyectLoanPK proyect_loan_PK;
	
	protected Scoring score;
	
	protected String bur_sol_num;   
	protected String kubo_score_A;
	protected String kubo_score_B;
	protected String cci_score;  
	
	protected Double amount        = 0D;
	protected Double min_amount    = 0D;
	protected Double payment       = 0D;
	protected Double cat           = 0D;
	protected Double rate;
	protected Double rate_opening;
	protected Double rate_investor;
	protected Double comision_apertura;
	
	protected Integer frequency_id = 0;
	protected Integer term_id      = 0;
	protected Integer bc_score;
	
	protected int prospectus_id;
	protected int company_id;
	protected int proyect_id;
	protected int proyect_loan_id_ORIGINAL;
	
	protected boolean is_proyect_OK;
	
	protected ProyectLoanCreatorDMO()
	{
		proyectService     = Utilities.findBean("proyectServiceImp");
		proyectloanService = Utilities.findBean("proyectLoanServiceImp");
	}
	
	public void setScore(Scoring score)
	{
		prospectus_id = score.getProspectus_id();
		company_id    = score.getCompany_id();
		
		bur_sol_num  = score.getMx_solicitud_buro();
		kubo_score_A = score.getKubo_score_a() == null ? "" : score.getKubo_score_a();
		kubo_score_B = score.getKubo_score_b() == null ? "" : score.getKubo_score_b();
		cci_score    = score.getCci_score();		
		
		rate              = score.getRate();
		rate_opening      = score.getRate();
		rate_investor     = score.getRate_investor();
		comision_apertura = score.getOpening_commission();
		
		bc_score = Integer.parseInt(score.getBc_score());
	}
}
