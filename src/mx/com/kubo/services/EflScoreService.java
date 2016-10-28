package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.EflScore;

public interface EflScoreService {

	public boolean saveEflScore( EflScore efl );
	public EflScore getMaxEflScoreByBurSolNum( String bur_sol_num );
	public List<EflScore> getEflScoreListByBurSolNum( String bur_sol_num );
	
}
