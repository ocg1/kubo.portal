package mx.com.kubo.investor.settings;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.AutomaticInvestment;
import mx.com.kubo.model.InvestmentFrequency;
import mx.com.kubo.model.Purpose;

public interface AutomaticInvestmentIMO 
{
	void setSesion(SessionBean sesion);
	
	void init();
	void init_is_active(AjaxBehaviorEvent event);
	
	String getLabel();
	String getFrequency();
	
	List<Purpose> getPurposelst();
	List<AutomaticInvestment> getAutomatic_investment_list();
	List<InvestmentFrequency> getInvestment_frequency();
}
