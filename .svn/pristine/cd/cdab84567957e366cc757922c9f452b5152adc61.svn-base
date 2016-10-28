package mx.com.kubo.mesa.solicitud.adicional;

import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.SimulatorBean;

public abstract class ScoreAllocDMO 
implements ScoreAllocIMO
{
	protected ProyectLoan proyect_loan;
	protected Scoring score;
	protected SimulatorBean simulation;
	
	protected String score_A;
	protected String score_B;
	protected String cci_score;
	protected String mx_solicitud_buro;
	
	protected Double ammount;
	protected Double rate;
	protected Double rate_investor;
	protected Double opening_commission;
	protected Double mx_cat;
	protected Double payment;
	protected Double liquidity;
	
	protected Integer bc_score;	
	protected Integer term_id;
	protected Integer frequency_id;
	protected Integer prospectus_id;
	protected Integer company_id;
	
	public void setProyect_loan(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
	}
	
	public ProyectLoan getProyect_loan()
	{
		return proyect_loan;
	}
	
	public void setSimulation(SimulatorBean simulation)
	{
		this.simulation = simulation;
		
		mx_cat  = simulation.getMx_cat();
		payment = simulation.getPayment();
		ammount = simulation.getAmmount();
		term_id = simulation.getTerm_id();
		frequency_id = simulation.getFrequency_id();		
	}

	public void setScore(Scoring score) 
	{
		this.score = score;				
		
		if(score != null)
		{
			bc_score = Integer.parseInt(score.getBc_score());
			cci_score 		   = score.getCci_score();
			score_A            = score.getKubo_score_a();
			score_B            = score.getKubo_score_b();		
			opening_commission = score.getOpening_commission();
			rate 			   = score.getRate();
			rate_investor      = score.getRate_investor();
			mx_solicitud_buro  = score.getMx_solicitud_buro();
			liquidity 		   = score.getLiquidity();			
		}
	}
	
	
}
