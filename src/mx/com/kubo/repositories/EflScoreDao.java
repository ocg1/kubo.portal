package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.EflScore;

public interface EflScoreDao {

	public boolean saveEflScore( EflScore efl );
	public EflScore getMaxEflScoreByBurSolNum( String bur_sol_num );
	public List<EflScore> getEflScoreListByBurSolNum( String bur_sol_num );
	
}
