package mx.com.kubo.mesa.solicitud.resumen.purpose;

import java.util.List;

import mx.com.kubo.mesa.solicitud.resumen.ChangeControlAMO;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Purpose;
import mx.com.kubo.tools.Utilities;

public abstract class EditorPurposeDMO extends ChangeControlAMO
implements EditorPurposeIMO
{		
	protected List<Purpose> purpose_list;
	
	protected Integer purpose_id;
	
	protected EditorPurposeDMO()
	{
		service_purpose      = Utilities.findBean("purposeServiceImp");
		service_proyect      = Utilities.findBean("proyectServiceImp");
		service_proyect_loan = Utilities.findBean("proyectLoanServiceImp");
		
		tables = new String[]{"ln_proyect"};
		fields = new String[]{"purpose_id"};
		
		afected_table_type = "ln_purpose";
		afected_table = "ln_proyect";
		field = "purpose_id";		
	}
	
	public List<Purpose> getPurpose_list()
	{
		return purpose_list;
	}
		
	public final void setProyect_loan(ProyectLoan proyect_loan)
	{
		super.setProyect_loan(proyect_loan);
		
		if(proyect_loan != null)
		{			
			purpose_id = proyect_loan.getProyect().getPurpose_id();
			
			original_value = purpose_id + "";
			
			init_change_control();
		}
	}
}
