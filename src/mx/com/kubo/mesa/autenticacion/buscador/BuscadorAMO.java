package mx.com.kubo.mesa.autenticacion.buscador;

public abstract class BuscadorAMO extends BuscadorDMO
{	
	protected void init_prospectus_TOKEN() 
	{
		if(prospectus_OK)
		{
			switch(area)
			{
				case 'L':
					proyect_loan = service_proyect_loan.getMaxProyectLoanByProspect(prospectus_id, company_id);
					
					if(proyect_loan != null)
					{
						proyect_loan_PK = proyect_loan.getProyectloanPk();
						proyect_id      = proyect_loan_PK.getProyect_id();
						proyect_loan_id = proyect_loan_PK.getProyect_loan_id();
						
						sb = new StringBuilder();				
						sb.append(proyect_loan_id).append("::");
						sb.append(proyect_id).append("::");
						sb.append(prospectus_id).append("::");
						sb.append(company_id);
						
						prospectus_TOKEN = sb.toString();
					}
				break;
				
				case 'I':
					sb = new StringBuilder();
					sb.append(prospectus_id).append("::");
					sb.append(company_id);
					
					prospectus_TOKEN = sb.toString();
				break;
				
				default: break;
			}																					
		}
	}
}
