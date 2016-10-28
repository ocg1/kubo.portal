package mx.com.kubo.repositories;

import java.util.Hashtable;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;

public interface LoginDaoIMO 
{
	boolean isUser(SessionBean sesion, String user, String password);
	boolean isUserEncrypt(SessionBean sesion, String user, String password);
	boolean checkCountUsed(NaturalPerson np,String email,String partner);
	boolean initSession  (Membership member);
	//boolean addLastLogin (Membership member);
	boolean activeUser(String user,String pass,String active);
	
	boolean existMail(String email);
	boolean existAlias(String alias);
	
	boolean validateEmailActNum(String active,String email);
	boolean validateEmailPass(String active,String user,String password);
	boolean validatePass(String user,String password);
	
	String validateActivationNumber(String active);
	
	Membership getMembershipByActivationNumber(String active);
	Membership getMembershipByEmail(String email);
	
	Hashtable<String, Object> validateUser( String usr);	
	
	public Membership getMembershipByfb_id( String fb_id);
	public boolean isFBUser( SessionBean sesion , Membership membership );
	
}
