package mx.com.kubo.managedbeans.mesa.solicitud.busqueda;

public abstract class SearchEngineAMO extends SearchEngineDMO 
{
	public final void init_proyect_loan_TOKEN()
	{				
		proyect_loan_PK = proyect_loan.getProyectloanPk();
		
		proyect_loan_id = proyect_loan_PK.getProyect_loan_id();
	    proyect_id      = proyect_loan_PK.getProyect_id();      
		
		sb = new StringBuilder();				
		sb.append(proyect_loan_id).append("::");
		sb.append(proyect_id).append("::");
		sb.append(prospectus_id).append("::");
		sb.append(company_id);
		
		proyect_loan_TOKEN = sb.toString();		
	}
}
