package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.SecQuestPoolPK;
import mx.com.kubo.model.SecurityQuestionPool;

public interface SecurityQuestionPoolService 
{
	List<SecurityQuestionPool> loadSecQuestPoolByProspect(int prospectus_id, int company_id);
	
	SecurityQuestionPool getSecQuestPoolByPK(SecQuestPoolPK pk);
	SecurityQuestionPool getNextRandomSecQuestPool(int prospectus_id, int company_id);
	
	boolean removeSecQuest (SecQuestPoolPK pk);
	
	boolean saveSecQuest   (SecurityQuestionPool secQuest);
	
	boolean updateSecQuest (SecurityQuestionPool secQuest);
}
