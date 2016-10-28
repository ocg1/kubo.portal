package mx.com.kubo.session;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
 /** 
 * When the user session timedout,  
 * ({@link sessionDestroyed(HttpSessionEvent)})method will be invoked. 
 * This method will make necessary cleanups (logging out user,  
 * updating db and audit logs, etc...) 
 * As a result; after this method, we will be in a clear 
 * and stable state. So nothing left to think about 
 * because session expired, user can do nothing after this point. 
 *  
 **/    
 public class MySessionListener implements HttpSessionListener {  
   
	 public MySessionListener() {  
	   
	 }  
   
	 public void sessionCreated(HttpSessionEvent event) {  
	   
		 System.out.println("Current Session created : " + event.getSession().getId()+ " at "+ new Date());  
	   
	 }  
   
 public void sessionDestroyed(HttpSessionEvent event) {  
   
	 // get the destroying session...  
//	 ContextBean ctxBean = new ContextBean();
//	 ActionBean objActionBean = (ActionBean) ctxBean.getBean("ActionBean");
	 HttpSession session = event.getSession();  
	   
	 System.out.println("Current Session destroyed :"  
	  + session.getId()+ " Logging out user...");  
	   
	 
	 FacesContext.getCurrentInstance().getExternalContext()
     .invalidateSession();
	 
	 /* 
	  
	 * nobody can reach user data after this point because  
	 * session is invalidated already. 
	 * So, get the user data from session and save its  
	 * logout information before losing it. 
	 * User's redirection to the timeout page will be  
	 * handled by the SessionTimeoutFilter. 
	 */  
	   
	 // Only if needed  
   
	 try{  
		 prepareLogoutInfoAndLogoutActiveUser(session);  	
//		 objActionBean.setJspName("welcomeLogin");
	 }catch(Exception e){  		 
		 System.out.println("Error while logging out at session destroyed : "     + e.getMessage() + "\n" + e.getCause());     
	 } 
//	 if(session!=null) {
//		 session.invalidate();
//		 } 
 }  
   
	 /** 
	 * Clean your logout operations. 
	 */  
	   
	 public void prepareLogoutInfoAndLogoutActiveUser(HttpSession httpSession) {  
		 // Only if needed  
	 }    
 }  