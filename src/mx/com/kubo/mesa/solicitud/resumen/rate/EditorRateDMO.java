package mx.com.kubo.mesa.solicitud.resumen.rate;

import mx.com.kubo.mesa.solicitud.resumen.ChangeControlAMO;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.tools.Utilities;

public class EditorRateDMO extends ChangeControlAMO 
{
	protected Double rate_investor;
	
	protected EditorRateDMO()
	{
		service_proyect_loan = Utilities.findBean("proyectLoanServiceImp");
		
		tables = new String[]{"ln_proyect_loan"};
		fields = new String[]{"rate_investor"};
		
		afected_table_type = "";
		afected_table = "ln_proyect_loan";
		field = "rate_investor";		
	}
	
	public final void setProyect_loan(ProyectLoan proyect_loan)
	{
		super.setProyect_loan(proyect_loan);
		
		if(proyect_loan != null)
		{			
			rate_investor = proyect_loan.getRate_investor();
			
			original_value = rate_investor + "";
			
			init_change_control();
		}
	}
}
