package mx.com.kubo.mesa.solicitud.resumen.rate;

import mx.com.kubo.mesa.solicitud.resumen.ChangeControlAMO;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.tools.Utilities;

public abstract class EditorRateDMO extends ChangeControlAMO 
implements EditorRateIMO
{
	protected Double rate;
	
	protected EditorRateDMO()
	{
		service_proyect_loan = Utilities.findBean("proyectLoanServiceImp");
		
		tables = new String[]{"ln_proyect_loan"};
		fields = new String[]{"rate"};
		
		afected_table_type = "";
		afected_table = "ln_proyect_loan";
		field = "rate";		
	}
	
	public final void setProyect_loan(ProyectLoan proyect_loan)
	{
		super.setProyect_loan(proyect_loan);
		
		if(proyect_loan != null)
		{			
			rate = proyect_loan.getRate();
			
			original_value = rate + "";
			
			init_change_control();
		}
	}
	
	public void setRate(Double rate)
	{
		this.rate = rate;
	}
}
