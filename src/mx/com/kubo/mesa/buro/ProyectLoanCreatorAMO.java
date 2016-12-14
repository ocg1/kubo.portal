package mx.com.kubo.mesa.buro;

import java.util.Date;

import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectPK;

public abstract class ProyectLoanCreatorAMO extends ProyectLoanCreatorDMO 
{
	protected void add_NEW_proyect() 
	{
		proyect_id = proyectService.getMaxProyectID();
		
		proyect    = new Proyect();
		proyect_pk = new ProyectPK(proyect_id, prospectus_id, company_id);							
		
		proyect.setProyectoPk(proyect_pk);
		
		is_proyect_OK = proyectService.add(proyect);
	}
	
	protected void init_NEW_proyect_loan() 
	{
		proyect_loan = new ProyectLoan();													
		
		proyect_loan.setStatus_id(0);											
		proyect_loan.setDeposit_method_id(2);
		proyect_loan.setDays_online(15);
		proyect_loan.setDay_published(null);
		proyect_loan.setConsulting_date(new Date());		
		proyect_loan.setLoan_type("REN");
		proyect_loan.setOpening_payment("D");
		proyect_loan.setFunding_type('T');
		proyect_loan.setIs_collection_solution("N");				
		proyect_loan.setPrevious_proyect_loan_id(proyect_loan_id_ORIGINAL);			
		proyect_loan.setTerm_id(term_id);
		proyect_loan.setFrequency_id(frequency_id);
		proyect_loan.setAmmount(amount);
		proyect_loan.setMx_cat(cat);
		proyect_loan.setMin_ammount(min_amount);								
		proyect_loan.setPayment(payment);
		proyect_loan.setMx_solicitud_buro(bur_sol_num);
		proyect_loan.setRate(rate);
		proyect_loan.setRate_with_opening(rate_opening);
		proyect_loan.setRate_investor(rate_investor);
		proyect_loan.setOpening_commission_amount(comision_apertura);
		proyect_loan.setBc_score(bc_score);
		proyect_loan.setKubo_score_a(kubo_score_A);
		proyect_loan.setKubo_score_b(kubo_score_B);	
		proyect_loan.setCci_score(cci_score);									
	}
	
	protected void init_proyect_loan_PK() 
	{
		proyect = proyectService.getMaxProyect(prospectus_id, company_id);
		
		proyect_id = proyect.getProyectoPk().getProyect_id();
		
		proyect_loan_PK = new ProyectLoanPK();		
		
		proyect_loan_PK.setCompany_id(company_id);
		proyect_loan_PK.setProspectus_id(prospectus_id);
		proyect_loan_PK.setProyect_id(proyect_id);
		proyect_loan_PK.setProyect_loan_id(0);	
	}
	
	protected void add_proyect_loan() 
	{
		proyect_loan.setProyectloanPk(proyect_loan_PK);
		
		proyectloanService.add(proyect_loan);
		
		proyect_loan_PK = proyect_loan.getProyectloanPk();
		
		proyect_loan = proyectloanService.getProyectLoanById(proyect_loan_PK);
	}
}
