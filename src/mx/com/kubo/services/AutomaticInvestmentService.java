package mx.com.kubo.services;

import java.util.Date;
import java.util.List;

import mx.com.kubo.model.AutomaticInvestment;
import mx.com.kubo.model.InvestmentFrequency;

public interface AutomaticInvestmentService {

	public boolean saveAutomaticInvestment( AutomaticInvestment ai );
	
	public boolean updateAutomaticInvestment( AutomaticInvestment ai );		
	
	AutomaticInvestment getAutomaticInvestment(int automatic_investment_id);
	
	List<AutomaticInvestment> getAutomaticInvestmentList( Date fecha );
	
	boolean esDiaFeriado( Date fecha );
	
	List<AutomaticInvestment> getAutomaticInvestmentListByProspect( Integer prospectus_id );
	
	List<InvestmentFrequency> getInvestmentFrequencyLst();
	
}
