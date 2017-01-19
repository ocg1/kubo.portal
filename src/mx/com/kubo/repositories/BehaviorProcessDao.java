package mx.com.kubo.repositories;

import mx.com.kubo.bean.BehaviorBean;
import mx.com.kubo.model.FraudeDetection;

public interface BehaviorProcessDao {

	public  BehaviorBean getBehaviorElements( Integer prospectus_id );
	public boolean saveFraudeDetection( FraudeDetection fraudedet );
	public FraudeDetection getFraudeDetection( Integer company_id, Integer prospectus_id );
	public boolean updateFraudeDetection( FraudeDetection fraudedet );
	
}
