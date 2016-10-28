package mx.com.kubo.portal.expenses;

import java.util.List;

import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesType;
import mx.com.kubo.model.ListPorc;
import mx.com.kubo.model.ProyectLoan;

public interface PercentageCalculatorIMO 
{
	void setListExpenses    (List<Expenses>     listExpenses);
	void setListExpensesType(List<ExpensesType> listExpensesType);
	
	void setUser(String user);
	void setProyect_loan(ProyectLoan proyect_loan);
	
	void setTotalIncome(Double totalIncome);
	void setTotalIncomeControl(Double totalIncomeControl);	
	
	void init();
	
	List<ListPorc> getListPorcClientBean();	
	
	String getPorcActionUser();
	
	boolean isDispListPorcWait();
	boolean isDispListPorc();
}
