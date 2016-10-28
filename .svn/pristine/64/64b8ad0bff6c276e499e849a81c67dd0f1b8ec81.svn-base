package mx.com.kubo.services.impl;

import java.util.Hashtable;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.repositories.LoginDaoIMO;
import mx.com.kubo.services.LoginServiceIMO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "loginServiceImp")
public class LoginServiceImp 
implements LoginServiceIMO 
{
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private LoginDaoIMO dao;
	
	public boolean verifySessionFB(SessionBean sesion, Membership membership  )
	{
		return dao.isFBUser(sesion, membership);
	}
	
	public boolean verifySession(SessionBean sesion, String user, String password)
	{
		return dao.isUser(sesion, user, password);
	}
	
	public boolean verifySessionEncrypt(SessionBean sesion, String user, String password)
	{
		return dao.isUserEncrypt(sesion, user, password);
	}
	
	public boolean checkCountUsed(NaturalPerson person, String email)
	{
		return dao.checkCountUsed(person, email, null);
	}
	
	public boolean initSession(Membership member)
	{
		return dao.initSession(member);
	}
	
//	public boolean addLastLogin (Membership member)
//	{
//		return dao.addLastLogin(member);
//	}
	
	public boolean activeSession(String user, String password,String active)
	{
		return dao.activeUser(user,password,active);
	}
	
	public boolean verifyEmail(String email)
	{
		return dao.existMail(email);
	}
	
	public boolean verifyAlias(String alias)
	{
		return dao.existAlias(alias);
	}

	public LoginDaoIMO getLoginRepository() 
	{
		return dao;
	}

	public void setLoginRepository(LoginDaoIMO loginRepository) 
	{
		this.dao = loginRepository;
	}
	
	public String validateActivationNumber(String active)
	{
		return dao.validateActivationNumber(active);
	}
	
	public boolean validateEmailActNum(String active,String email)
	{
		return dao.validateEmailActNum(active, email);
	}
	
	public boolean validateEmailPass(String active,String user,String password)
	{
		return dao.validateEmailPass(active, user, password);
	}
	
	public boolean validatePass(String user,String password)
	{
		return dao.validatePass(user, password);
	}
	
	public Hashtable<String, Object> validateUser( String usr)
	{
		return dao.validateUser(usr);
	}
	
	public Membership getMembershipByActivationNumber(String active)
	{
		return dao.getMembershipByActivationNumber( active );
	}

	public Membership getMembershipByEmail(String email) 
	{		
		return dao.getMembershipByEmail(email);
	}
	
	public Membership getMembershipByfb_id( String fb_id){
		
		return dao.getMembershipByfb_id(fb_id);
		
	}
	
}
