package mx.com.kubo.mesa.solicitud.resumen.loantype;

import java.util.List;

import mx.com.kubo.mesa.solicitud.resumen.ChangeControlIMO;
import mx.com.kubo.model.LoanType;
import mx.com.kubo.model.ProyectLoan;

public interface EditorTipoCreditoIMO extends ChangeControlIMO
{		
	ProyectLoan getProyect_loan();
	
	List<LoanType> getLista_loan_type();
	
	String getLoan_type_id();
	String getTipo_de_credito();
		
	void setProyect_loan(ProyectLoan proyect_loan);
	void listener_guardar_cambios();	
}
