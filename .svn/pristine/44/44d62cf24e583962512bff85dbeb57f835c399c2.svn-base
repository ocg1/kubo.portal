package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.SecurityQuestion;
import mx.com.kubo.model.SecurityQuestionPK;
import mx.com.kubo.repositories.SecurityQuestionDao;
import mx.com.kubo.services.SecurityQuestionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityQuestionServiceImp 
implements SecurityQuestionService
{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private SecurityQuestionDao securityQuestionRepository;
	
	@Override
	public SecurityQuestion getSecurityQuestionId(SecurityQuestionPK pk) 
	{
		return securityQuestionRepository.loadSelectedSecurityQuestion(pk);
	}
	
	@Override
	public void add(SecurityQuestion newSecurityQuestion) 
	{
		securityQuestionRepository.saveSecurityQuestion(newSecurityQuestion);		
	}
	@Override
	public List<SecurityQuestion> getSecurityQuestionList() 
	{
		return securityQuestionRepository.loadSecurityQuestionList();
	}	
}
