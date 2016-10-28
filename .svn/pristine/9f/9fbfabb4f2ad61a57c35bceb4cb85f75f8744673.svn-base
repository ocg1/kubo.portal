package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesPK;

public interface ExpensesService {
	public abstract Expenses getExpensesbyID(ExpensesPK expensesPk);
	public abstract boolean addExpenses(Expenses newExpenses,int prospectusID,int companyID);
	public abstract boolean updateExpenses(Expenses expenses);
	public abstract Expenses getExpensesByTypeExpensesID(int prospectusID,int companyID,int typeExpensesID);
	public abstract List<Expenses> getListExpensesByProspect(int prospectusID,int companyID);
	
}
