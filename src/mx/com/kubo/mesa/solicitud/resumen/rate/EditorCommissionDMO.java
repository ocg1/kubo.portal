package mx.com.kubo.mesa.solicitud.resumen.rate;

import mx.com.kubo.mesa.solicitud.resumen.ChangeControlAMO;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.tools.Utilities;

public abstract class EditorCommissionDMO extends ChangeControlAMO 
implements EditorCommissionIMO 
{
	protected Double opening_commission;
	
	protected EditorCommissionDMO()
	{
		service_proyect_loan = Utilities.findBean("proyectLoanServiceImp");
		
		tables = new String[]{"ln_proyect_loan"};
		fields = new String[]{"opening_commission"};
				
		afected_table = "ln_proyect_loan";
		field = "opening_commission";	
	}

	public final void setProyect_loan(ProyectLoan proyect_loan)
	{
		super.setProyect_loan(proyect_loan);
		
		if(proyect_loan != null)
		{			
			opening_commission = proyect_loan.getOpening_commission();
			
			original_value = opening_commission + "";
			
			init_change_control();
		}
	}
}
