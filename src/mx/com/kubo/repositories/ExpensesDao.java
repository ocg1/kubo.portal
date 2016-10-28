package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesPK;

public interface ExpensesDao {
	public Expenses getExpensesbyID(ExpensesPK expensesPk);
	public boolean addExpenses(Expenses newExpenses,int prospectusID,int companyID);
	public boolean updateExpenses(Expenses expenses);
	public Expenses getExpensesByTypeExpensesID(int prospectusID,int companyID,int typeExpensesID);
	public List<Expenses> getListExpensesByProspect(int prospectusID,int companyID);
	
}
