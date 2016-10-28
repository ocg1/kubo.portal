package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.ExpensesType;
import mx.com.kubo.model.ExpensesTypePK;
import mx.com.kubo.repositories.ExpensesTypeDao;
import mx.com.kubo.services.ExpensesTypeService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpensesTypeServiceImp implements ExpensesTypeService{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ExpensesTypeDao expensesTypeRepository;
	
	@Override
	public ExpensesType getExpensesTypeById(ExpensesTypePK id) {
		return expensesTypeRepository.loadSelectedExpensesType(id);
	}
	
	@Override
	public void add(ExpensesType newExpensesType) {
		expensesTypeRepository.saveExpensesType(newExpensesType);		
	}
	
	@Override
	public List<ExpensesType> getExpensesTypeList() {
		return expensesTypeRepository.loadExpensesTypeList();
	}

}
