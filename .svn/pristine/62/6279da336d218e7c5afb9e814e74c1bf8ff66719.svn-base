package mx.com.kubo.services;

import java.util.Hashtable;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;

public interface LoginServiceIMO 
{
	boolean verifyEmail        (String email);
	boolean verifyAlias        (String alias);
	boolean validateEmailActNum(String active, String email);
	boolean validatePass       (String user,   String password);
	boolean verifySession      (SessionBean sesion, String user, String password);
	public boolean verifySessionFB(SessionBean sesion, Membership membership  );
	boolean verifySessionEncrypt (SessionBean sesion, String user, String password);
	boolean activeSession      (String user,   String password, String active);
	boolean validateEmailPass  (String active, String user,     String password);
	
	
	boolean checkCountUsed(NaturalPerson person, String email);
	boolean initSession  (Membership member);
	//boolean addLastLogin (Membership member);
	
	String validateActivationNumber(String active);

	Hashtable<String, Object> validateUser( String usr);
	
	Membership getMembershipByActivationNumber(String active);	
	Membership getMembershipByEmail(String email);
	Membership getMembershipByfb_id( String fb_id);
}
