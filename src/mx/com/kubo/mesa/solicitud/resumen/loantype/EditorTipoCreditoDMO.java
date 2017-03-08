package mx.com.kubo.mesa.solicitud.resumen.loantype;

import java.util.List;

import mx.com.kubo.mesa.solicitud.resumen.ChangeControlAMO;
import mx.com.kubo.model.LoanType;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.tools.Utilities;

public abstract class EditorTipoCreditoDMO extends ChangeControlAMO
implements EditorTipoCreditoIMO
{
	protected ProyectLoanService service_proyect_loan;	

	protected List<LoanType> lista_loan_type;
	
	protected ProyectLoan proyect_loan;
	
	protected String loan_type_id;
	protected String tipo_de_credito;		
	
	protected EditorTipoCreditoDMO()
	{		
		service_proyect_loan = Utilities.findBean("proyectLoanServiceImp");
		
		lista_loan_type = service_proyect_loan.getLista_loan_type();
		
		tables = new String[]{"ln_proyect_loan"};
		fields = new String[]{"loan_type"};
		
		afected_table_type = "ln_loan_type";
		afected_table = "ln_proyect_loan";
		field = "loan_type";	
	}
	
	public final String getLoan_type_id() 
	{
		return loan_type_id;
	}
	
	public final String getTipo_de_credito() 
	{
		return tipo_de_credito;
	}

	public final List<LoanType> getLista_loan_type() 
	{
		return lista_loan_type;
	}
	
	public final void setProyect_loan(ProyectLoan proyect_loan)
	{
		this.proyect_loan = proyect_loan;
		
		if(proyect_loan != null)
		{
			company_id    = proyect_loan.getProyectloanPk().getCompany_id();
			prospectus_id = proyect_loan.getProyectloanPk().getProspectus_id();	
			
			loan_type_id    = proyect_loan.getLoantype().getPk().getLoan_type_id();
			tipo_de_credito = proyect_loan.getLoantype().getLoan_type_desc();
			
			original_value = loan_type_id + "";
			
			init_change_control();
		}
	}
}
