package mx.com.kubo.services;

import java.util.Date;
import java.util.List;

import mx.com.kubo.model.AutomaticInvestment;

public interface AutomaticInvestmentService {

	public boolean saveAutomaticInvestment( AutomaticInvestment ai );
	
	public boolean updateAutomaticInvestment( AutomaticInvestment ai );
	
	public List<AutomaticInvestment> getAutomaticInvestmentList( Date fecha );
	
	public boolean esDiaFeriado( Date fecha );
	
	public List<AutomaticInvestment> getAutomaticInvestmentListByProspect( Integer prospectus_id );
	
}
