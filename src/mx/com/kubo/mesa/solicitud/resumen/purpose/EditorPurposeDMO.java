package mx.com.kubo.mesa.solicitud.resumen.purpose;

import java.util.List;

import mx.com.kubo.mesa.solicitud.resumen.ChangeControlAMO;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Purpose;
import mx.com.kubo.tools.Utilities;

public abstract class EditorPurposeDMO extends ChangeControlAMO
implements EditorPurposeIMO
{		
	protected Purpose purpose;
	
	protected List<Purpose> purpose_list;
	
	protected StringBuilder sb;
	
	protected String name;
	
	protected Integer purpose_id;
	protected Integer type_id;
	
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
			purpose    = proyect_loan.getProyect().getPurpose();
			
			type_id = purpose.getType_id();
			   name = purpose.getName();
			
			sb = new StringBuilder();
			sb.append("purpose_id = ").append(purpose_id).append(" - ");
			sb.append("type_id = ").append(type_id).append(" - ");
			sb.append(name);
			
			original_value = sb.toString();
			
			field_type_id = purpose_id;
			
			init_change_control();
		}
	}
}
