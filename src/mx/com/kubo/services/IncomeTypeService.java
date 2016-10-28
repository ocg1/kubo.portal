package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.IncomeType;
import mx.com.kubo.model.IncomeTypePK;

public interface IncomeTypeService 
{
	void add(IncomeType newIncomeType);
	
	IncomeType getIncomeTypeById(IncomeTypePK id);	
	
	List<IncomeType> getIncomeTypeList();
	List<IncomeType> getIncomeTypeListOrderByConsec();
}
