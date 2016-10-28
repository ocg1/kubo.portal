package mx.com.kubo.portal.efectivo;

import mx.com.kubo.bean.ExpensesBean;
import mx.com.kubo.model.LoanNegotiation;
import mx.com.kubo.model.ProyectLoan;

public interface IndicadoresIMO 
{
	void setProyect_loan(ProyectLoan proyect_loan);
	
	void setAmmountConsolidate(ExpensesBean ammountConsolidate);
	
	void setNegotiation(LoanNegotiation negotiation);
	
	void setTotalIncome         (Double totalIncome);
	void setTotalIncomeControl  (Double totalIncomeControl);
	void setTotalExpenses       (Double totalExpenses);	
	void setTotalExpensesControl(Double totalExpensesControl);
	void setIngreso_neto_cliente(Double ingreso_neto_cliente);
	void setIngreso_neto_control(Double ingreso_neto_control);
	void setMonto_deudas_cliente(Double monto_deudas_cliente);
	void setMonto_deudas_control(Double monto_deudas_control);
	
	void init();
	
	ExpensesBean getAmmountConsolidate();
	
	Double getPagoMenControl();
	Double getExcedenteControl();
}
