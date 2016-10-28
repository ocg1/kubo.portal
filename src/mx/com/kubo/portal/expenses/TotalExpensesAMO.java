package mx.com.kubo.portal.expenses;

import java.util.ArrayList;
import java.util.List;

import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesHistory;
import mx.com.kubo.model.ExpensesPK;
import mx.com.kubo.model.ExpensesType;
import mx.com.kubo.model.ExpensesTypePK;

public abstract class TotalExpensesAMO extends TotalExpensesDMO 
{
	protected boolean existlistExpenses()
	{	
		if(proyect_loan == null)
		{
			return false;
		}
		
		int proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
		
		List<ExpensesHistory> lstExHis = service_expenses_history.getExpensesHistoryByProyectLoan(proyect_loan_id);
	
		if(lstExHis != null && lstExHis.size() > 0)
		{		
			listExpenses = new ArrayList<Expenses>();
		
			for( ExpensesHistory exH : lstExHis )
			{			
				Expenses in = new Expenses();
				
				in.setAmmount(exH.getAmmount());
				in.setAmmount_modified(exH.getAmmount_modified());
				in.setDatetime_modified(exH.getDatetime_modified());
				in.setDescription(exH.getDescription());
				in.setExpense_type_id(exH.getExpense_type_id());			
				in.setAmmount_minus(exH.getAmmount_minus());
			
				ExpensesPK incomePk = new ExpensesPK();
				
				incomePk.setCompany_id(exH.getPk().getCompany_id());
				incomePk.setExpense_id(exH.getPk().getExpense_id());
				incomePk.setProspectus_id(exH.getPk().getProspectus_id());
			
				in.setExpensesPk(incomePk);
			
				ExpensesTypePK id = new ExpensesTypePK();
			
				id.setCompany_id(exH.getPk().getCompany_id());
				id.setExpenses_type_id(exH.getExpense_type_id());
			
				ExpensesType type = service_expenses_type.getExpensesTypeById(id);
			
				in.setExpensesType( type );
				in.setProspectus_id_modified(exH.getProspectus_id_modified());
				
				listExpenses.add(in);			
			}
		
			return true;
		
		} else {
		
		return false;
		
		}
	}	
	
	protected void init_total_expenses_control(Double ammount, Double ammount_modified) 
	{
		if(system_param.getValue().equals("1"))
		{						
			if(ammount_modified != null)
			{				
				totalExpensesControl += ammount_modified;
				
			} else {
				
				totalExpensesControl += ammount;
			}
			
		} else if(system_param.getValue().equals("2")) {
			
			if(ammount_modified != null || ammount != null)
			{
				if(ammount_modified != null && ammount != null)
				{
					if(ammount_modified > ammount)
					{
						totalExpensesControl += ammount_modified;
						
					} else {
						
						totalExpensesControl += ammount;
					}
				}
				
				if(ammount_modified != null)
				{
					totalExpensesControl += ammount_modified;
					
				} else if(ammount != null) {
					
					totalExpensesControl += ammount;
				}
			}						
		}
	}
}
