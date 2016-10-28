package mx.com.kubo.repositories;

import mx.com.kubo.model.Scoring;

public interface ScoringDao 
{

	Scoring loadSelectedScoring(int scoring_result_id);	
	Scoring loadMaxScoringByProspectus(Integer prospectus_id,Integer company_id);
	Scoring loadScoringByBurSolNum(String bursolnum);
	
	void saveScoring(Scoring newScoring) ;
	void updateScoring(Scoring newScoring);
	
	boolean removeScoring(Scoring score);
	public int removeScoring(Integer prospectus_id, Integer company_id);
	
	Integer getMaxScoringResultID();
	
}
