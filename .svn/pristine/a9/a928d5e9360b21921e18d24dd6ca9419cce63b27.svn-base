package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.EflScore;
import mx.com.kubo.repositories.EflScoreDao;
import mx.com.kubo.services.EflScoreService;

@Service
public class EflScoreServiceImp implements Serializable, EflScoreService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private EflScoreDao dao;
	
	public boolean saveEflScore( EflScore efl ){
		return dao.saveEflScore(efl);
	}
	
	public EflScore getMaxEflScoreByBurSolNum( String bur_sol_num ){
		return dao.getMaxEflScoreByBurSolNum(bur_sol_num);
	}
	
	public List<EflScore> getEflScoreListByBurSolNum( String bur_sol_num ){
		return dao.getEflScoreListByBurSolNum(bur_sol_num);
	}
	
}
