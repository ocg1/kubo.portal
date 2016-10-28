package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.ExpensesType;
import mx.com.kubo.model.ExpensesTypePK;

public interface ExpensesTypeDao {
	public ExpensesType loadSelectedExpensesType(ExpensesTypePK pk);
	public void saveExpensesType(ExpensesType newExpensesType);
	public List<ExpensesType> loadExpensesTypeList();
}
