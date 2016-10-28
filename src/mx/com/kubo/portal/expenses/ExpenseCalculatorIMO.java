package mx.com.kubo.portal.expenses;

import java.util.List;

import mx.com.kubo.bean.ExpensesBean;
import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesType;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SystemParam;

public interface ExpenseCalculatorIMO 
{
	void setProyect_loan(ProyectLoan proyect_loan);
	
	void setSystem_param(SystemParam system_param);	
	
	void setListExpensesType(List<ExpensesType> listExpensesType);
	
	void init();
	
	void setAmmountConsolidate(ExpensesBean ammountConsolidate);
	
	void setExcedenteControl(Double excedenteControl);
	
	ExpensesBean getAmmountConsolidate();
	
	Integer getConsolidate();
	
	Double getTotalExpensesControl();
	Double getTotalExpenses();	
	Double getMonto_deudas_cliente();
	Double getMonto_deudas_control();
	
	List<Expenses>     getListExpenses();
	List<ExpensesBean> getListExpensesBean();		
}
