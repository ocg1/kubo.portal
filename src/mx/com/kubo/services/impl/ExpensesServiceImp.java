package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesPK;
import mx.com.kubo.repositories.ExpensesDao;
import mx.com.kubo.services.ExpensesService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpensesServiceImp implements ExpensesService {
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ExpensesDao expensesRepository;
	
	@Override
	public Expenses getExpensesbyID(ExpensesPK expensesPk) {
		// TODO Auto-generated method stub
		return expensesRepository.getExpensesbyID(expensesPk);
	}

	@Override
	public boolean addExpenses(Expenses newExpenses, int prospectusID,int companyID) {
		// TODO Auto-generated method stub
		return expensesRepository.addExpenses(newExpenses, prospectusID, companyID);
	}

	@Override
	public boolean updateExpenses(Expenses expenses) {
		// TODO Auto-generated method stub
		return expensesRepository.updateExpenses(expenses);
	}

	@Override
	public Expenses getExpensesByTypeExpensesID(int prospectusID,int companyID, int typeExpensesID) {
		// TODO Auto-generated method stub
		return expensesRepository.getExpensesByTypeExpensesID(prospectusID, companyID, typeExpensesID);
	}

	@Override
	public List<Expenses> getListExpensesByProspect(int prospectusID,int companyID) {
		// TODO Auto-generated method stub
		return expensesRepository.getListExpensesByProspect(prospectusID, companyID);
	}

}
