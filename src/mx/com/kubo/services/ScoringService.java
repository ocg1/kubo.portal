package mx.com.kubo.services;

import mx.com.kubo.model.Scoring;

public interface ScoringService 
{	
	Scoring loadSelectedScoring(int scoring_result_id);	
	Scoring loadScoringByBurSolNum(String bursolnum);
	Scoring loadMaxScoringByProspectus(Integer prospectus_id,Integer company_id);
	
	Integer getMaxScoringResultID();
	
	void saveScoring(Scoring newScoring) ;
	void updateScoring(Scoring newScoring);
	
	boolean removeScoring(Scoring score);
	int removeScoring(Integer prospectus_id, Integer company_id);
	
}
