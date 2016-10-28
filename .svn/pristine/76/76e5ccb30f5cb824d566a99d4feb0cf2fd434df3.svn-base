package mx.com.kubo.portal.expenses;

import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesPK;

public final class TotalExpensesIMP extends TotalExpensesAMO 
implements TotalExpensesIMO
{
	public void init()
	{					
		if(proyect_loan != null)
		{		
			totalExpenses = 0.0;
			totalExpensesControl = 0.0;
			
			if(!existlistExpenses())
			{			
				listExpenses = service_expenses.getListExpensesByProspect(prospectus_id, company_id) ;			
			}
			
			for(Expenses expense: listExpenses)
			{
				Integer expense_type_id = expense.getExpense_type_id();
				Double ammount          = expense.getAmmount();
				Double ammount_modified = expense.getAmmount_modified();
				
				if(expense_type_id != GASTOS_TOTALES)
				{				
					if(ammount != null)
					{					
						totalExpenses += ammount;
						
						flagExpenses = true;
					}
					
					init_total_expenses_control(ammount, ammount_modified);
																			
				} else {
					
					flagTotalExpenses = true;
					totalExpenseTemp = ammount;
					
					if( !flagExpenses )
					{					
						if(ammount_modified!=null)
						{
							totalExpenseControlTemp = ammount_modified;
							
						} else if(ammount!=null) {
							
							totalExpenseControlTemp += ammount;
						}
					}
				}					
			}
			
			if(!flagExpenses &&  flagTotalExpenses )
			{			
				totalExpenses = totalExpenseTemp;
				totalExpensesControl = totalExpenseControlTemp;				
								
			} else if(!flagTotalExpenses && flagExpenses  ) {
				
				expensesPK  = new ExpensesPK();				
				expensesPK.setCompany_id(company_id);
				expensesPK.setExpense_id(14);
				expensesPK.setProspectus_id(prospectus_id);
				
				total_expense = new Expenses(); 				
				total_expense.setExpensesPk(expensesPK);
				total_expense.setAmmount(totalExpenses);
				total_expense.setAmmount_modified(totalExpensesControl);
				total_expense.setExpense_type_id(GASTOS_TOTALES);
				
				service_expenses.addExpenses(total_expense, prospectus_id, company_id);
				
			} else if(flagExpenses && flagTotalExpenses) {
				
				totalExpenses = totalExpenseTemp;
				
				if(totalExpensesControl == 0D)
				{				
					totalExpensesControl = totalExpenseControlTemp;				
				}
				
			} else {
				
				totalExpenses = totalExpenseTemp;
				totalExpensesControl = totalExpenseControlTemp;
			}					
		}		
	}
}
