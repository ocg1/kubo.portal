package mx.com.kubo.repositories.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Clients;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.RoleAssignment;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.Screen;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.repositories.LoginDaoIMO;
import mx.com.kubo.tools.Utilities;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LoginDaoImp extends LoginDaoAMO
implements LoginDaoIMO
{			
	@Transactional 
	public final boolean isUser(SessionBean sesion, String user, String password)
	{		
		log.info("isUser pass= " + Utilities.encrypt(password));
		
		boolean acceso = false;				
		
		List<Membership> list_membership = em.createQuery("from Membership where email = ?", Membership.class).setParameter(1,user).getResultList();
		
		if(list_membership.size() > 0)
		{
			Membership membership = list_membership.get(0);						
			
			String is_cancelled    = membership.getIs_canceled();
			String member_password = membership.getPassword();
			String last_login;
			
			SimpleDateFormat date_format = new SimpleDateFormat("dd / MM / yyyy  hh:mm:ss a");
			
			if(membership.getLast_login_attempt() != null)
			{
				last_login = date_format.format(membership.getLast_login_attempt());
				
			}else{
				
				last_login = date_format.format(new Date());
			}
				
			
			
			if(is_cancelled == null || is_cancelled.equals("N") )
			{
				if(sesion.getPartner() != null || membership.getIs_active()==1)
				{
					if(member_password.equals((Utilities.encrypt(password))))
					{
						if(membership.getPerson()!=null && membership.getPerson().getProspectus().getTracking_id()!=null)
						{
							if(checkCountUsed(membership.getPerson(),membership.getEmail(),sesion.getPartner()))
							{
								if(initSession(membership))
								{									
									sesion.setFecha_acceso_LAST(last_login);
									sesion.setFecha_acceso_ACTUAL(date_format.format(new Date()));
									
									addLastLogin(membership,false);
									
									acceso=true;
									sesion.setTemporalUser("");
									sesion.setFailedActive(false);
									sesion.setFailedPass(false);
									sesion.setFailedUser(false);
									sesion.setSessionUsed(false);
									sesion.setFailedTracking(false);
									sesion.setCanceled(false);
								}
							}else{
								sesion.setTemporalUser(user);
								sesion.setFailedActive(false);
								sesion.setFailedPass(false);
								sesion.setFailedUser(false);
								sesion.setSessionUsed(true);
								sesion.setFailedTracking(false);
								sesion.setCanceled(false);
							}
						}else{
							sesion.setTemporalUser(user);
							sesion.setFailedActive(false);
							sesion.setFailedPass(false);
							sesion.setFailedUser(false);
							sesion.setSessionUsed(false);
							sesion.setFailedTracking(true);
							sesion.setCanceled(false);
						}
					}else{
						
						addFailedPass(membership);
						
						sesion.setTemporalUser(user);
						sesion.setFailedActive(false);
						sesion.setFailedPass(true);
						sesion.setFailedUser(false);
						sesion.setSessionUsed(false);
						sesion.setFailedTracking(false);
						sesion.setCanceled(false);
						
					}
				}else{
					
					//active
					sesion.setTemporalUser(user);
					sesion.setFailedActive(true);
					sesion.setFailedPass(false);
					sesion.setFailedUser(false);
					sesion.setSessionUsed(false);
					sesion.setFailedTracking(false);
					sesion.setCanceled(false);
				}
			
			}else{
				//cancelado
				
				sesion.setTemporalUser(user);
				sesion.setFailedActive(false);
				sesion.setFailedPass(false);
				sesion.setFailedUser(false);
				sesion.setSessionUsed(false);
				sesion.setFailedTracking(false);
				sesion.setCanceled(true);
			}
			
		}else{
			
			//user
			sesion.setTemporalUser(user);
			sesion.setFailedActive(false);
			sesion.setFailedPass(false);
			sesion.setFailedUser(true);
			sesion.setSessionUsed(false);
			sesion.setFailedTracking(false);
			sesion.setCanceled(false);
			
		}
		 return acceso;
	}
	
	@Transactional 
	public boolean isFBUser( SessionBean sesion , Membership membership )
	{		
		log.info( "FBisUser " );
		
		boolean acceso = false;
								
			
			String is_cancelled    = membership.getIs_canceled();
			String last_login;
			String user = membership.getEmail();
			
			SimpleDateFormat date_format = new SimpleDateFormat("dd / MM / yyyy  hh:mm:ss a");
			
			if(membership.getLast_login_attempt() != null)
			{
				last_login = date_format.format(membership.getLast_login_attempt());
				
			}else{
				
				last_login = date_format.format(new Date());
			}
				
			
			
			if(is_cancelled == null || is_cancelled.equals("N") )
			{
				if(sesion.getPartner() != null || membership.getIs_active()==1)
				{
						if(membership.getPerson()!=null && membership.getPerson().getProspectus().getTracking_id()!=null)
						{
							if(checkCountUsed(membership.getPerson(),membership.getEmail(),sesion.getPartner()))
							{
								if(initSession(membership))
								{									
									sesion.setFecha_acceso_LAST(last_login);
									sesion.setFecha_acceso_ACTUAL(date_format.format(new Date()));
									
									addLastLogin(membership,true);
									
									acceso=true;
									sesion.setTemporalUser("");
									sesion.setFailedActive(false);
									sesion.setFailedPass(false);
									sesion.setFailedUser(false);
									sesion.setSessionUsed(false);
									sesion.setFailedTracking(false);
									sesion.setCanceled(false);
								}
							}else{
								sesion.setTemporalUser(user);
								sesion.setFailedActive(false);
								sesion.setFailedPass(false);
								sesion.setFailedUser(false);
								sesion.setSessionUsed(true);
								sesion.setFailedTracking(false);
								sesion.setCanceled(false);
							}
						}else{
							sesion.setTemporalUser(user);
							sesion.setFailedActive(false);
							sesion.setFailedPass(false);
							sesion.setFailedUser(false);
							sesion.setSessionUsed(false);
							sesion.setFailedTracking(true);
							sesion.setCanceled(false);
						}
					
				}else{
					
					//active
					sesion.setTemporalUser(user);
					sesion.setFailedActive(true);
					sesion.setFailedPass(false);
					sesion.setFailedUser(false);
					sesion.setSessionUsed(false);
					sesion.setFailedTracking(false);
					sesion.setCanceled(false);
				}
			
			}else{
				//cancelado
				
				sesion.setTemporalUser(user);
				sesion.setFailedActive(false);
				sesion.setFailedPass(false);
				sesion.setFailedUser(false);
				sesion.setSessionUsed(false);
				sesion.setFailedTracking(false);
				sesion.setCanceled(true);
			}
			
		
		 return acceso;
	}
	

	@Transactional	
	public boolean activeUser(String user, String password,String active){
		log.info("activeUser pass= " + Utilities.encrypt(password));
		
		boolean acceso = false;
		List<Membership> usr = em.createQuery(
			    "from Membership where email= ? and password = ? and activation_code = ?", Membership.class)
			    				.setParameter(1,user)
			    				.setParameter(2, Utilities.encrypt(password))
			    				.setParameter(3, active)
			    .getResultList();
		if(usr.size()>0){
			if(activeSession(usr.get(0))){
				if(initSession(usr.get(0)))
					acceso=true;
			}
			else
				acceso=false;
		}
		 return acceso;
	}
	
	public boolean existMail(String email){
		boolean existe = false;
		try{
		List<Membership> usr = em.createQuery(
			    "from Membership where email= ? ", Membership.class).setParameter(1,email)
			    .getResultList();
		
		
		
		if(usr.size()>0){
			existe=true;
		}
		
		}catch(Exception e){
			e.printStackTrace();
			existe = false;
	}
		 return existe;
	}
	
	public boolean existAlias(String alias)
	{
		boolean existe = false;
		List<Membership> usr = em.createQuery(
			    "from Membership where nickname= ? ", Membership.class).setParameter(1,alias)
			    .getResultList();
		if(usr.size()>0){
			existe=true;
		}
		 return existe;
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean checkCountUsed(NaturalPerson np,String email,String partner)
	{
		boolean flag = false;
		
		FacesContext faces       = FacesContext.getCurrentInstance();
		ExternalContext external    = faces.getExternalContext();		
		HttpSession sessionUsed = (HttpSession) external.getSession(false);
		ServletContext servlet     = sessionUsed.getServletContext();
		
		Hashtable<String, Hashtable <String, HttpSession>> ht = (Hashtable<String, Hashtable<String,HttpSession>>)servlet.getAttribute("usuariosFirmados");
		
		if(ht == null)
		{
			servlet.setAttribute("usuariosFirmados",new Hashtable());
			ht = (Hashtable<String, Hashtable<String,HttpSession>>)servlet.getAttribute("usuariosFirmados");
		}
		
		Enumeration<String> enumKey = ht.keys();

		while(enumKey.hasMoreElements()) 
		{
			
			String key = enumKey.nextElement();
		    
		    Hashtable<String,HttpSession> htVal = (Hashtable<String,HttpSession>)ht.get(key);
		    
		    if( htVal.get(np.getProspectus().getTracking_id()) != null )
		    {
		    	flag = true;
		    	
		    	break;
		    }
		    
		}
		
		if( flag )
		{
			
			return false;
			
		} else {
					    
			sessionUsed.setAttribute("userName", email);
			
			faces     = FacesContext.getCurrentInstance();			
			ELContext elContext = faces.getELContext();
			ELResolver resolver  = faces.getApplication().getELResolver();
			
			SessionBean sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		    
		    if(partner != null)
		    {
		    	sessionUsed.setMaxInactiveInterval(30 * 60);//30 min
		    	sesion.setMaxTimeInactiveSegundos( (30*60) );
		    	
		    }else if(np.getProspectus().getArea().equals('L')) {
		    	
		    	sessionUsed.setMaxInactiveInterval(20 * 60);//20 min
		    	sesion.setMaxTimeInactiveSegundos( (20*60) );
		    	
		    }else if(np.getProspectus().getArea().equals('I')) {
		    	
		    	sessionUsed.setMaxInactiveInterval(20 * 60);//20 min
		    	sesion.setMaxTimeInactiveSegundos( (20*60) );
		    	
		    }else if(np.getProspectus().getArea().equals('M')) {
		    	
		    	sessionUsed.setMaxInactiveInterval(20 * 60);//20 min
		    	sesion.setMaxTimeInactiveSegundos( (20*60) );
		    	
		    } else {
		    	
		    	sessionUsed.setMaxInactiveInterval(20 * 60);//20 min
		    	
		    	sesion.setMaxTimeInactiveSegundos( (20*60) );
		    	
		    }
		    
		    Hashtable<String, Hashtable <String, HttpSession>>  usuarios = (Hashtable<String, Hashtable<String,HttpSession>>)servlet.getAttribute("usuariosFirmados");
		    
		    Hashtable<String,HttpSession> newHtVal = new Hashtable<String,HttpSession> () ;
		    
		    newHtVal.put( np.getProspectus().getTracking_id() , sessionUsed);
			
			usuarios.put(sessionUsed.getId(),newHtVal);
		    
		    return true;
		}
	}

	public final boolean initSession(Membership member)
	{
		try
		{
			FacesContext faces     = FacesContext.getCurrentInstance();			
			ELContext elContext = faces.getELContext();
			ELResolver resolver  = faces.getApplication().getELResolver();
										
			SessionBean sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
			
			Prospectus prospecto  = member.getPerson().getProspectus();			
			Integer prospectus_id = member.getMembershipPK().getProspectus_id();
			Integer company_id    = member.getMembershipPK().getCompany_id();
			
			sesion.setProspectus_id(prospectus_id);
			sesion.setCompany_id(company_id);
			sesion.setNickname(member.getNickname());
	
			Scoring score = getScore(prospectus_id, company_id);
	
			if(score != null)
			{
				if(score.getRate() != null && !score.getRate().equals(""))
				{
					sesion.setRate(score.getRate());
					sesion.setOpeningCommission(score.getOpening_commission());
				}
			}
				
			sesion.setTrackId(prospecto.getTracking_id());
			sesion.setArea   (prospecto.getArea());
			sesion.setUsrImg (prospecto.getImage());
			
			sesion.setEmail(member.getEmail());
			sesion.setNombre( member.getPerson().getFirst_name() + " " +  member.getPerson().getFather_last_name());
				
			validaRoleAssignment(prospecto);
			
			HttpSession sessionUsed = (HttpSession) faces.getExternalContext().getSession(false);
			
			sesion.setSessionID(sessionUsed.getId());				
			sesion.setRole_id(member.getPerson().getProspectus().getRole_id());
			
			sesion.setIsClient(validaIsClient( prospectus_id, company_id ));
				
			Screen sc = getScreen(prospectus_id, company_id, sesion);
			
			if(sc == null)
			{
				sesion.setLastPage("");
				
			} else { 
				
				sesion.setLastPage(sc.getName());
			}
				
			return true;
			
		} catch(Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	public String validateActivationNumber(String active)
	{
		String existe = "";
		List<Membership> usr = em.createQuery(
			    "from Membership where activation_code= ? ", Membership.class).setParameter(1,active)
			    .getResultList();
		if(usr != null && usr.size()>0){
			if( usr.get(0).getEmail_verified() == null || usr.get(0).getEmail_verified().equals("N")  ){
				existe="ACCESO::Ok";
			}else{
				existe="ACTIVADA::"+usr.get(0).getEmail();
			}
		}else{
			existe = "FALSA::fail";
		}
		
		
		 return existe;
	}
	
	public Membership getMembershipByActivationNumber(String active)
	{		
		Membership existe = null;
		List<Membership> usr = em.createQuery(
			    "from Membership where activation_code= ? ", Membership.class).setParameter(1,active)
			    .getResultList();
		if(usr != null && usr.size()>0){
			existe = usr.get(0);
		}
		
		return existe;
	}
	
	public boolean validateEmailActNum(String active,String email)
	{
		boolean existe = false;
		List<Membership> usr = em.createQuery(
			    "from Membership where activation_code= ? and email= ? ", Membership.class).setParameter(1,active).setParameter(2,email)
			    .getResultList();
		if(usr != null && usr.size()>0){
			existe = true;
		}
		
		 return existe;
	}
	
	public boolean validateEmailPass(String active,String user,String password)
	{
		boolean acceso = false;
		List<Membership> usr = em.createQuery(
			    "from Membership where email= ? and password = ? and activation_code = ?", Membership.class)
			    				.setParameter(1,user)
			    				.setParameter(2, Utilities.encrypt(password))
			    				.setParameter(3, active)
			    .getResultList();
		if(usr.size()>0){
			acceso=true;
		}
		 return acceso;
	}
	
	public boolean validatePass(String user,String password)
	{
		boolean acceso = false;
		List<Membership> usr = em.createQuery(
			    "from Membership where email= ? and password = ?", Membership.class)
			    				.setParameter(1,user)
			    				.setParameter(2, Utilities.encrypt(password))
			    .getResultList();
		if(usr!=null&&usr.size()>0){
			acceso=true;
		}
		 return acceso;
	}
	
	public Hashtable<String, Object> validateUser( String usr002)
	{						
		List<Membership> lista_membership = null;
		
		Hashtable<String, Object> response = null;
		ResourceBundle recurso = null;
		SystemParam system_param = null;
		SystemParamPK system_param_PK = null;
		
		Membership membership = null;
		
		StringBuilder sb = null;
		
		String msg = "";
		String is_canceled = null;
		String is_blocked  = null;
		String area = null;
				
		Integer is_active = null;
		Integer failed_login_attempts = null;
		
		int company_id;
		final int SECCION_INVERSIONISTA_ENABLED = 51;
		
		boolean valido;
		boolean desbloqueo_password_ENABLED = false;
		
		lista_membership = em.createQuery("from Membership where email= ? ", Membership.class).setParameter(1,usr002).getResultList();
		
		response = new Hashtable<String, Object>();
		
		if(lista_membership != null && lista_membership.size() > 0)
		{
			valido = true;
			recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
			
			membership = lista_membership.get(0);
			
			company_id            = membership.getMembershipPK().getCompany_id();
			is_canceled           = membership.getIs_canceled();
			is_blocked            = membership.getIs_blocked();
			is_active             = membership.getIs_active();
			failed_login_attempts = membership.getFailed_login_attempts();				
			
			area = membership.getPerson().getProspectus().getArea().toString();
			
			if(is_canceled != null && is_canceled.trim().length() > 0 && (!is_canceled.equals("N")))
			{
				valido = false;
				
				sb = new StringBuilder();
				sb.append("<span style='font-size: 1.0em; color: #ff0000;'>La cuenta <b>");
				sb.append(lista_membership.get(0).getEmail());
				sb.append("</b> está cancelada.<br />");
				sb.append("*Si existe un error o deseas reactivarla de nuevo ponte en contacto con nosotros al teléfono: <b>");
				sb.append(recurso.getString("Kubo_telefono"));
				sb.append("</b>  </span> ");
				
				msg = sb.toString();
				
			} else {
				
				if(failed_login_attempts >= 3 && failed_login_attempts < MAX_FAILED_LOGIN)
				{
					sb = new StringBuilder();
					
					sb.append("Has tenido ");
					sb.append(failed_login_attempts);
					sb.append(" intentos fallidos de ");
					sb.append(MAX_FAILED_LOGIN);
					sb.append(" posibles");
					
					msg = sb.toString();
					
				}
				
				if(failed_login_attempts >= MAX_FAILED_LOGIN || (is_blocked != null && is_blocked.equals("S")))
				{
					valido = false;
					msg = "La contraseña ha sido bloqueda";	
					desbloqueo_password_ENABLED = true;
				}								
				
				if(is_canceled != null && is_canceled.equals("SK"))
				{
					desbloqueo_password_ENABLED = false;
				}
			}
			
			system_param_PK = new SystemParamPK();			
			system_param_PK.setCompany_id(company_id);
			system_param_PK.setSystem_param_id(SECCION_INVERSIONISTA_ENABLED);
			
			system_param = em.find(SystemParam.class, system_param_PK);
			
			if(system_param.getValue().equals("N") && (is_active != 1) )
			{			
				if(area.equals("I"))
				{
					valido = false;
					msg = "Usted está registrado como INVERSIONISTA. Por el momento esta funcionalidad no está disponible, próximamente le informaremos cuándo puede comenzar a invertir. ";
				}
				
			}
			
		} else {
			
			valido = false;
			msg = "El usuario " + usr002 + " no existe";
		}
		
		response.put("valido", valido);
		response.put("msg", msg);
		response.put("desbloqueo_password_ENABLED", desbloqueo_password_ENABLED);
		
		return response;
		
	}
		
	private boolean addLastLogin (Membership member, Boolean isFB)
	{
		Membership member2 = em.find(Membership.class, member.getMembershipPK());
		
		member2.setLast_login_attempt(new Date());
		member2.setFailed_login_attempts(0);
		
		if( isFB ){
			
			member2.setLast_FB_login_attempt(new Date());
			
		}
		
		em.merge(member2);
		
		return true;
	}
		
	@Transactional
	public boolean validaRoleAssignment(  Prospectus prospectus )
	{
		
		String query = "from RoleAssignment where pk.prospectus_id = ? and pk.company_id = ?  ";
		
		List<RoleAssignment> lstTotal = em.createQuery(query, RoleAssignment.class)
										.setParameter(1, prospectus.getProspectusPK().getProspectus_id())
										.setParameter(2, prospectus.getProspectusPK().getCompany_id())
										.getResultList();
		
		if(lstTotal != null && lstTotal.size() > 0)
		{
			Integer prospectus_id = prospectus.getProspectusPK().getProspectus_id();
			Integer company_id    = prospectus.getProspectusPK().getCompany_id();
			
			RoleAssignment role = getRoleAssignPendiente(prospectus_id , company_id);
			
			if(role != null){
			
				Date d1  = role.getDate_application();
				Date d2 = new Date();
				
				if(d2.getTime() > d1.getTime())
				{
				
					List<RoleAssignment> lst = em.createQuery("from RoleAssignment where pk.prospectus_id = ? and company_id = ? ", RoleAssignment.class)
													.setParameter(1, role.getPk().getProspectus_id())
													.setParameter(2, role.getPk().getCompany_id())
													.getResultList();
					if(lst != null)
					{
						
						for( RoleAssignment ra : lst )
						{
							
							if( !ra.getStatus_id().equals("P") )
							{								
								ra.setStatus_id("I");
								em.merge(ra);
								
							}							
						}						
					}
					
					role.setStatus_id("V");
					em.merge(role);
					
					prospectus.setRole_id(role.getPk().getRole_id());
					
					em.merge(prospectus);
					
				}					
			}
		}
		
		return true;
		
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional
	public boolean saveRoleAssignment( RoleAssignment role ){
		try{
			
			String hql= "select max(pk.role_assignment_id) from RoleAssignment";
			
			List list = em.createQuery(hql).getResultList();
			
			int maxID = (((Integer)list.get(0))==null)?0:((Integer)list.get(0)).intValue();
			
			role.getPk().setRole_assignment_id(maxID);
			
			em.persist(role);
			
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional 
	public final boolean isUserEncrypt(SessionBean sesion, String user, String password)
	{		
		log.info("isUser pass= " + password);
		
		boolean acceso = false;
		
		List<Membership> list_membership = em.createQuery("from Membership where email= ?", Membership.class).setParameter(1,user).getResultList();
		
		if(list_membership.size() > 0)
		{
			Membership membership = list_membership.get(0);
			
			String is_cancelled    = membership.getIs_canceled();
			String member_password = membership.getPassword();
			String last_login;
			
			SimpleDateFormat date_format = new SimpleDateFormat("dd / MM / yyyy  hh:mm:ss a");
			
			if(membership.getLast_login_attempt() != null)
			{
				last_login = date_format.format(membership.getLast_login_attempt());
			}else{
				last_login = date_format.format(new Date());
			}
				
			
			
			if(is_cancelled == null || is_cancelled.equals("N") )
			{
				if(sesion.getPartner() != null || membership.getIs_active()==1)
				{
					if(member_password.equals(password))
					{
						if(membership.getPerson()!=null && membership.getPerson().getProspectus().getTracking_id()!=null)
						{
							if(checkCountUsed(membership.getPerson(),membership.getEmail(),sesion.getPartner()))
							{
								if(initSession(membership))
								{									
									sesion.setFecha_acceso_LAST(last_login);
									sesion.setFecha_acceso_ACTUAL(date_format.format(new Date()));
									
									addLastLogin(membership,false);
									
									acceso=true;
									sesion.setTemporalUser("");
									sesion.setFailedActive(false);
									sesion.setFailedPass(false);
									sesion.setFailedUser(false);
									sesion.setSessionUsed(false);
									sesion.setFailedTracking(false);
									sesion.setCanceled(false);
								}
							}else{
								sesion.setTemporalUser(user);
								sesion.setFailedActive(false);
								sesion.setFailedPass(false);
								sesion.setFailedUser(false);
								sesion.setSessionUsed(true);
								sesion.setFailedTracking(false);
								sesion.setCanceled(false);
							}
						}else{
							sesion.setTemporalUser(user);
							sesion.setFailedActive(false);
							sesion.setFailedPass(false);
							sesion.setFailedUser(false);
							sesion.setSessionUsed(false);
							sesion.setFailedTracking(true);
							sesion.setCanceled(false);
						}
					}else{
						
						addFailedPass(membership);
						
						sesion.setTemporalUser(user);
						sesion.setFailedActive(false);
						sesion.setFailedPass(true);
						sesion.setFailedUser(false);
						sesion.setSessionUsed(false);
						sesion.setFailedTracking(false);
						sesion.setCanceled(false);
						
					}
				}else{
					
					//active
					sesion.setTemporalUser(user);
					sesion.setFailedActive(true);
					sesion.setFailedPass(false);
					sesion.setFailedUser(false);
					sesion.setSessionUsed(false);
					sesion.setFailedTracking(false);
					sesion.setCanceled(false);
				}
			
			}else{
				//cancelado
				
				sesion.setTemporalUser(user);
				sesion.setFailedActive(false);
				sesion.setFailedPass(false);
				sesion.setFailedUser(false);
				sesion.setSessionUsed(false);
				sesion.setFailedTracking(false);
				sesion.setCanceled(true);
			}
			
		}else{
			
			//user
			sesion.setTemporalUser(user);
			sesion.setFailedActive(false);
			sesion.setFailedPass(false);
			sesion.setFailedUser(true);
			sesion.setSessionUsed(false);
			sesion.setFailedTracking(false);
			sesion.setCanceled(false);
			
		}
		 return acceso;
	}
	
	private boolean validaIsClient( int prospectus_id, int company_id ){
		
		try{
			
			String  query = "from Clients where pk.prospectus_id = ? and pk.company_id = ? ";
			Clients cl = null ;
				try{
					cl = em.createQuery(query, Clients.class)
									.setParameter(1,prospectus_id)
									.setParameter(2,company_id)
									.getSingleResult();
				}catch(NoResultException ur){
					System.out.println("No es Cliente..");
				}
			if( cl !=  null ){
				return cl.getPk().getProspectus_id() == prospectus_id;
			}else{
				return false;
			}
			
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		
	}



}
