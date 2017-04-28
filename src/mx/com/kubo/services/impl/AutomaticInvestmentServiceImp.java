package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.AutomaticInvestment;
import mx.com.kubo.model.InvestmentFrequency;
import mx.com.kubo.repositories.AutomaticInvestmentDao;
import mx.com.kubo.services.AutomaticInvestmentService;

@Service
public class AutomaticInvestmentServiceImp implements AutomaticInvestmentService , Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private AutomaticInvestmentDao repository;
	
	@Override
	public boolean saveAutomaticInvestment( AutomaticInvestment ai ){
		return repository.saveAutomaticInvestment(ai);
	}
	@Override
	public boolean updateAutomaticInvestment( AutomaticInvestment ai ){
		return repository.updateAutomaticInvestment(ai);
	}
	@Override
	public List<AutomaticInvestment> getAutomaticInvestmentList( Date fecha ){
		return repository.getAutomaticInvestmentList(fecha);
	}
	@Override
	public boolean esDiaFeriado( Date fecha ){
		return repository.esDiaFeriado( fecha );
	}
	@Override
	public List<AutomaticInvestment> getAutomaticInvestmentListByProspect( Integer prospectus_id ){
		return repository.getAutomaticInvestmentListByProspect( prospectus_id );
	}
	@Override
	public List<InvestmentFrequency> getInvestmentFrequencyLst(){
		return repository.getInvestmentFrequencyLst();
	}
	
}
