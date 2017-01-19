package mx.com.kubo.services;

import mx.com.kubo.bean.BehaviorBean;
import mx.com.kubo.model.FraudeDetection;

public interface BehaviorProcessService {

	public  BehaviorBean getBehaviorElements( Integer prospectus_id );
	public boolean saveFraudeDetection( FraudeDetection fraudedet );
	public FraudeDetection getFraudeDetection( Integer company_id, Integer prospectus_id );
	public boolean updateFraudeDetection( FraudeDetection fraudedet );
	
}
