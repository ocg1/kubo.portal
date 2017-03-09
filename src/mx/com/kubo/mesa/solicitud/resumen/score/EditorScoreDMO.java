package mx.com.kubo.mesa.solicitud.resumen.score;

import mx.com.kubo.mesa.solicitud.resumen.ChangeControlAMO;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.tools.Utilities;

public class EditorScoreDMO extends ChangeControlAMO 
{
	protected String kuboScoreLetter;
	protected String kuboScoreNumber;
	
	protected EditorScoreDMO()
	{
		service_proyect_loan = Utilities.findBean("proyectLoanServiceImp");
		
		tables = new String[]{"ln_proyect_loan"};
		fields = new String[]{"kubo_score"};
				
		afected_table = "ln_proyect_loan";
		field = "kubo_score";	
	}
	
	public final void setProyect_loan(ProyectLoan proyect_loan)
	{
		super.setProyect_loan(proyect_loan);
		
		if(proyect_loan != null)
		{			
			kuboScoreLetter = proyect_loan.getKubo_score_a();
			kuboScoreNumber = proyect_loan.getKubo_score_b();
			
			original_value = kuboScoreLetter + kuboScoreNumber;
			
			init_change_control();
		}
	}
}
