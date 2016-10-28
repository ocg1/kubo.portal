package mx.com.kubo.portal.expenses;

import mx.com.kubo.bean.ExpensesBean;
import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesPK;
import mx.com.kubo.model.ExpensesType;

public abstract class ExpenseCalculatorAMO extends ExpenseCalculatorDMO 
{		
	protected void init_expense_bean(Expenses expense, ExpensesType expense_type) 
	{
		Integer expense_type_id = expense.getExpense_type_id();					
		Double ammount          = expense.getAmmount();								
		
		expensesBean = new ExpensesBean(expense_type_id, ammount, expense_type.getName());
		expensesBean.setExpense(expense);
		
		init_ammount_modified(expense);									
		init_consolidate(expense);			
			
		listExpensesBean.add(expensesBean);
		
		totalExpenses += ammount;			
		
		expense_bean_NEW_ENABLED = false;		 			
	}
	
	private void init_ammount_modified(Expenses expense) 
	{
		Double ammount          = expense.getAmmount();
		Double ammount_modified = expense.getAmmount_modified();
		
		if(expense.getAmmount_modified() != null)
		{
			String tmp = num.format(ammount_modified);
			
			if(tmp.equals("0"))
			{
				tmp = "0";
			}
			
			expensesBean.setAmmount_modified(tmp);		
			
		} else {
			
			String tmp = num.format(ammount);
			
			if(tmp.equals("0"))
			{
				tmp = "0";
			}
			
			expensesBean.setAmmount_modified(tmp);
		}		
	}
	
	protected void init_monto_indicadores() 
	{
		if(expense_type_id == TANDA)
		{
			monto_tanda = ammount;
		}
		
		if(expense_type_id == CREDITOS)
		{
			monto_creditos = ammount;
		}
		
		if(expense_type_id == TANDA)
		{
			monto_tanda_control = ammount_modified != null ? ammount_modified : ammount;
		}
		
		if(expense_type_id == CREDITOS)
		{
			monto_creditos_control = ammount_modified != null ? ammount_modified : ammount;
		}
	}

	protected void init_expense_bean_NEW(ExpensesType expense_type) 
	{
		expensesBean = new ExpensesBean(expense_type.getPk().getExpenses_type_id(),0D, expense_type.getName());
		expensesBean.setAmmount_modified("0");
		
		Expenses expense = new Expenses();
		expense.setAmmount(0D);
		expense.setAmmount_modified(0D);
		expense.setExpense_type_id(expense_type.getPk().getExpenses_type_id());
		expense.setProspectus_id_modified(null);
		
		ExpensesPK expense_PK = new ExpensesPK();
		expense_PK.setExpense_id(0);
		expense_PK.setCompany_id(company_id);					
		expense_PK.setProspectus_id(prospectus_id);
		
		expense.setExpensesPk(expense_PK);
		
		expensesBean.setExpense(expense);
		
		listExpensesBean.add(expensesBean);		
	}
	
	private void init_consolidate(Expenses expense) 
	{
		Integer expense_type_id = expense.getExpense_type_id();
		Double ammount            = expense.getAmmount();
		Double ammount_modified   = expense.getAmmount_modified();
		Double ammount_minus      = expense.getAmmount_minus();		
		
		if(expense_type_id == CREDITOS )
		{
			ammountConsolidate.setExpense(expense);
			
			if((ammount != null && ammount > 0) || (ammount_modified != null && ammount_modified > 0))
			{										
				ammountConsolidate.setExpense_type_id(expense_type_id);										
				ammountConsolidate.setAmmount(ammount_minus != null ? ammount_minus : 0.0);
				ammountConsolidate.setAmmount_modified(ammount_minus != null ? num.format(ammount_minus) : "0.0");
				
				if(ammount_minus != null && ammount_minus > 0)
				{
					consolidate = 1;
					
				} else {
					
					consolidate = 2;
				}
				
			} else {
				
				ammountConsolidate.setAmmount(0.0);
				
				consolidate = null;
			}
		}
	}

	protected void init_total_expenses() 
	{
		if(!flagExpenses && flagTotalExpenses)
		{			
			totalExpenses = totalExpenseTemp;								
			
		} else if(!flagTotalExpenses && flagExpenses) {
			
			add_total_expenses();					
			
		} else if(flagExpenses && flagTotalExpenses) {
			
			totalExpenses = totalExpenseTemp;

		} else {

			totalExpenses = totalExpenseTemp;
		}
		
		ammountConsolidate.setExcedentConsolidate(excedenteControl + ammountConsolidate.getAmmount());	
	}
	
	private void add_total_expenses() 
	{
		ExpensesPK expensesPK  = new ExpensesPK();
		
		expensesPK.setCompany_id(company_id);
		expensesPK.setExpense_id(14);
		expensesPK.setProspectus_id(prospectus_id);
		
		Expenses totalExpensesObj = new Expenses(); 
		
		totalExpensesObj.setExpensesPk(expensesPK);
		totalExpensesObj.setAmmount(this.totalExpenses);
		totalExpensesObj.setExpense_type_id(GASTOS_TOTALES);
		
		service_expenses.addExpenses(totalExpensesObj, prospectus_id, company_id);		
	}	
}
