package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.SecQuestPoolPK;
import mx.com.kubo.model.SecurityQuestionPool;

public interface SecurityQuestionPoolDao 
{	
	List<SecurityQuestionPool> loadSecQuestPoolByProspect(int prospectus_id, int company_id);
	
	boolean removeSecQuest(SecQuestPoolPK pk);
	boolean saveSecQuest( SecurityQuestionPool secQuest );
	boolean updateSecQuest( SecurityQuestionPool secQuest );
	
	SecurityQuestionPool getNextRandomSecQuestPool(int prospectus_id, int company_id);
	SecurityQuestionPool getSecQuestPoolByPK(SecQuestPoolPK pk);
	
}
