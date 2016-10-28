package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.SecQuestPoolPK;
import mx.com.kubo.model.SecurityQuestionPool;
import mx.com.kubo.repositories.SecurityQuestionPoolDao;
import mx.com.kubo.services.SecurityQuestionPoolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityQuestionPoolServiceImp 
implements SecurityQuestionPoolService 
{	
	@Autowired
	private SecurityQuestionPoolDao dao;
		
	public List<SecurityQuestionPool> loadSecQuestPoolByProspect(int prospectus_id, int company_id)
	{		
		return dao.loadSecQuestPoolByProspect(prospectus_id, company_id);		
	}
	
	public boolean removeSecQuest(SecQuestPoolPK pk)
	{	
		return dao.removeSecQuest(pk);	
	}
	
	public SecurityQuestionPool getNextRandomSecQuestPool(int prospectus_id, int company_id)
	{	
		return dao.getNextRandomSecQuestPool(prospectus_id, company_id);		
	}
		
	public boolean saveSecQuest( SecurityQuestionPool secQuest )
	{		
		return dao.saveSecQuest(secQuest);		
	}

	public boolean updateSecQuest( SecurityQuestionPool secQuest )
	{		
		return dao.updateSecQuest(secQuest);		
	}
	
	public SecurityQuestionPool getSecQuestPoolByPK(SecQuestPoolPK pk)
	{
		return dao.getSecQuestPoolByPK(pk);
	}
}
