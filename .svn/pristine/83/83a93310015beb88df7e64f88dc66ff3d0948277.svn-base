package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomePK;

public interface IncomeDao {
	public Income getIncomebyID(IncomePK incomePk);
	public boolean addIncome(Income newIncome,int prospectusID,int companyID);
	public boolean updateIncome(Income income);
	public Income getIncomeByTypeIncomeID(int prospectusID,int companyID,int typeIncomeID);
	public List<Income> getListIncomeByProspect(int prospectusID,int companyID);
	public boolean removeIncome(IncomePK incomePk);
	
}
