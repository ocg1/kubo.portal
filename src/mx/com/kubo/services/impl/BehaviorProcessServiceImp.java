package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.bean.BehaviorBean;
import mx.com.kubo.model.FraudeDetection;
import mx.com.kubo.repositories.BehaviorProcessDao;
import mx.com.kubo.services.BehaviorProcessService;

@Service
public class BehaviorProcessServiceImp implements BehaviorProcessService, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	BehaviorProcessDao dao;
	
	public  BehaviorBean getBehaviorElements( Integer prospectus_id ){
		return dao.getBehaviorElements(prospectus_id);
	}
	
	public boolean saveFraudeDetection( FraudeDetection fraudedet ){
		return dao.saveFraudeDetection( fraudedet );
	}
	
	public FraudeDetection getFraudeDetection( Integer company_id, Integer prospectus_id ){
		return dao.getFraudeDetection( company_id, prospectus_id );
	}
	
	public boolean updateFraudeDetection( FraudeDetection fraudedet ){
		return dao.updateFraudeDetection( fraudedet );
	}
	
	
}
