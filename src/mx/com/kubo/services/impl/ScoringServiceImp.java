package mx.com.kubo.services.impl;

import mx.com.kubo.model.Scoring;
import mx.com.kubo.repositories.ScoringDao;
import mx.com.kubo.services.ScoringService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoringServiceImp implements ScoringService {
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ScoringDao scoringRepository;
	
	@Override
	public  Scoring loadSelectedScoring(int scoring_result_id){
		return scoringRepository.loadSelectedScoring(scoring_result_id);
	}
	
	@Override
	public  void saveScoring(Scoring newScoring){
		scoringRepository.saveScoring(newScoring);
	}
	
	@Override
	public  void updateScoring(Scoring newScoring){
		scoringRepository.updateScoring(newScoring);
	}
	
	@Override
	public Scoring loadMaxScoringByProspectus(Integer prospectus_id,Integer company_id){
		return scoringRepository.loadMaxScoringByProspectus(prospectus_id, company_id);
	}
	
	@Override
	public  Scoring loadScoringByBurSolNum(String bursolnum){
		return scoringRepository.loadScoringByBurSolNum(bursolnum);
	}
	
	@Override
	public boolean removeScoring(Scoring score)
	{
		return scoringRepository.removeScoring(score);
	}
	
	@Override
	public int removeScoring(Integer prospectus_id, Integer company_id) 
	{
		return scoringRepository.removeScoring(prospectus_id, company_id);
	}

	@Override
	public Integer getMaxScoringResultID(){
		return scoringRepository.getMaxScoringResultID();
	}
	
}
