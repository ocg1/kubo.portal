package mx.com.kubo.mesa.solicitud.resumen.loantype;

import java.util.List;

import mx.com.kubo.mesa.solicitud.resumen.ChangeControlIMO;
import mx.com.kubo.model.LoanType;

public interface EditorTipoCreditoIMO extends ChangeControlIMO
{				
	List<LoanType> getLista_loan_type();
	
	String getLoan_type_id();
	String getTipo_de_credito();		
	
	void save();	
}
