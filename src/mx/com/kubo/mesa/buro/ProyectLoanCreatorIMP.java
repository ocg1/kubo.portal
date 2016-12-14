package mx.com.kubo.mesa.buro;

import mx.com.kubo.model.ProyectLoan;

public class ProyectLoanCreatorIMP extends ProyectLoanCreatorAMO 
implements ProyectLoanCreatorIMO
{
	public void init() 
	{		
		try
		{					
			ProyectLoan proyect_loan = proyectloanService.getMaxProyectLoanByProspect(prospectus_id, company_id);
			
			     term_id = proyect_loan.getTerm_id();
			frequency_id = proyect_loan.getFrequency_id();
			proyect_loan_id_ORIGINAL = proyect_loan.getProyectloanPk().getProyect_loan_id();
			
			add_NEW_proyect();
			
			init_NEW_proyect_loan();																							
			
			if(is_proyect_OK)
			{					
				init_proyect_loan_PK();
				
				add_proyect_loan();									
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
}
