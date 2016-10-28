package mx.com.kubo.services;


import java.util.List;

import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomePK;

public interface IncomeService {
	public abstract Income getIncomebyID(IncomePK incomePk);
	public abstract boolean addIncome(Income newIncome,int prospectusID,int companyID);
	public abstract boolean updateIncome(Income income);
	public abstract Income getIncomeByTypeIncomeID(int prospectusID,int companyID,int typeIncomeID);
	public abstract List<Income> getListIncomeByProspect(int prospectusID,int companyID);
	public abstract boolean removeIncome(IncomePK incomePk);
}
