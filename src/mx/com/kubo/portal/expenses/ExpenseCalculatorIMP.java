package mx.com.kubo.portal.expenses;

import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesType;

public class ExpenseCalculatorIMP extends ExpenseCalculatorAMO
implements ExpenseCalculatorIMO
{
	public void init()
	{
		total_calculator = new TotalExpensesIMP();
		total_calculator.setSystem_param(system_param);
		total_calculator.setProyect_loan(proyect_loan);
		total_calculator.init();
		
		listExpenses         = total_calculator.getListExpenses();
		totalExpensesControl = total_calculator.getTotalExpensesControl();		
		
		if(listExpensesType != null && listExpenses != null)
		{
			init_expenses();
		}
		
		init_total_expenses();		
		
		monto_deudas_cliente = monto_tanda + monto_creditos;
		monto_deudas_control = monto_tanda_control + monto_creditos_control;
	}
	
	private void init_expenses() 
	{				
		for(ExpensesType expense_type : listExpensesType)
		{						
			expense_bean_NEW_ENABLED = true;
			
			for(Expenses expense : listExpenses)
			{					
				expense_type_id  = expense.getExpense_type_id();
				ammount          = expense.getAmmount();	
				ammount_modified = expense.getAmmount_modified();						
				
				type_EQUALS = expense_type_id.equals(expense_type.getPk().getExpenses_type_id());			
				
				if(expense_type_id == GASTOS_TOTALES)
				{										
					flagTotalExpenses = true;					
										
					totalExpenseTemp = ammount;
				}		
				
				if(type_EQUALS && expense_type_id < GASTOS_TOTALES)
				{
					init_expense_bean(expense, expense_type);
					
					flagExpenses = true;
				}				
				
				init_monto_indicadores();				
			}				
			
			if(expense_bean_NEW_ENABLED)
			{
				init_expense_bean_NEW(expense_type);
			}
		}			
	}
}
