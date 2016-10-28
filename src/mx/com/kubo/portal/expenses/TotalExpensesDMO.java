package mx.com.kubo.portal.expenses;

import java.util.HashSet;
import java.util.List;

import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.services.ExpensesHistoryService;
import mx.com.kubo.services.ExpensesService;
import mx.com.kubo.services.ExpensesTypeService;
import mx.com.kubo.tools.Utilities;

public abstract class TotalExpensesDMO 
implements TotalExpensesIMO
{
	protected        ExpensesService service_expenses;
	protected    ExpensesTypeService service_expenses_type;
	protected ExpensesHistoryService service_expenses_history;
	
	protected Expenses total_expense;
	protected ExpensesPK expensesPK;
	
	protected List <Expenses> listExpenses;
	protected HashSet <Expenses> hs_expenseS;
	
	protected ProyectLoan proyect_loan;
	
	protected SystemParam system_param;
		
	protected Double totalExpenseTemp = 0D;
	protected Double totalExpenseControlTemp = 0D;
	protected Double totalExpensesControl=0D;
	protected Double totalExpenses=0.0;		
	
	protected int prospectus_id;
	protected int company_id;		
	
	protected final int GASTOS_TOTALES = 14;

	protected boolean flagExpenses;
	protected boolean flagTotalExpenses;
	
	protected TotalExpensesDMO()
	{
		service_expenses         = Utilities.findBean("expensesServiceImp");
		service_expenses_type    = Utilities.findBean("expensesTypeServiceImp");
		service_expenses_history = Utilities.findBean("expensesHistoryServiceImp");
	}
	
	public void setProyect_loan(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
		
		prospectus_id = proyect_loan.getProyectloanPk().getProspectus_id();
		   company_id = proyect_loan.getProyectloanPk().getCompany_id();
	}
	
	public void setSystem_param(SystemParam system_param) 
	{
		this.system_param = system_param;
	}	
	
	public Double getTotalExpensesControl() 
	{
		return totalExpensesControl;
	}

	public List<Expenses> getListExpenses() 
	{
		return listExpenses;
	}		
}
