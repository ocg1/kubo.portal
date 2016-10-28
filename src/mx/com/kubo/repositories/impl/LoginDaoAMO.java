package mx.com.kubo.repositories.impl;

import java.util.List;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.RoleAssignment;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.Screen;
import mx.com.kubo.model.ScreenPK;

public abstract class LoginDaoAMO extends LoginDaoDMO
{	
	protected Scoring getScore(Integer prospectus_id, Integer company_id)
	{
		String query="select MAX(s.scoring_result_id) from Scoring s where s.prospectus_id = ? and s.company_id = ?";
		Integer id=0;
		id=(Integer) em.createQuery(query).setParameter(1, prospectus_id).setParameter(2,company_id).getSingleResult();	
		
		if(id!=null)
			return em.find(Scoring.class,id);
		else
			return null;
	}
	
	protected Screen getScreen(Integer prospectus_id,Integer company_id, SessionBean sesion)
	{
		Access access;
		//String query="select MAX(a.access_id) from Access a where a.prospectus_id = ? and a.company_id = ? and a.screen_id not in (select s.screenPK.screen_id from Screen s where s.menu_order = (0) ) ";
		
		//String query="select MAX(a.access_id) from Access a where a.prospectus_id = ? and a.company_id = ? and a.screen_id not in (select s.screenPK.screen_id from Screen s where s.menu_order = (0) )  and a.screen_id in (select s.screenPK.screen_id from Screen s where s.area = ?) ";
		
		
		String query=" select MAX(a.access_id) from Access a , Screen s where  a.prospectus_id = ? and  a.company_id = ? and a.screen_id = s.screenPK.screen_id and s.menu_order <> 0  and s.area = ?"; 		
		
		Integer id=0;
		id=(Integer)  em.createQuery(query)
						.setParameter(1, prospectus_id)
						.setParameter(2, company_id)
						.setParameter(3, sesion.getArea().toString())
						.getSingleResult();
		if(id == null)
			return null;
		else
			access =  em.find(Access.class,id);
		
		ScreenPK scpk = new ScreenPK();
		scpk.setCompany_id(company_id);
		if(sesion.getArea() != null && sesion.getArea().toString().equals("M") && access != null && access.getScreen_id() == 2  ){
			
			scpk.setScreen_id( 12 ); // pantalla de consulta de solicitud
		
		}else{
			
			scpk.setScreen_id(access.getScreen_id());
			
		}
		
		return em.find(Screen.class,scpk);
	}
	
	protected boolean activeSession(Membership member)
	{
		boolean flag = false;
		
		try
		{
			log.info("Inicia Actualizacion");
			Membership member2 = em.find(Membership.class, member.getMembershipPK());
			member2.setIs_active(1);
			log.info("Termina Actualizacion");
			flag =  true;
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		finally{
			em.close();
		}
		return flag;
	}
	
	protected boolean addFailedPass(Membership member)
	{
		try
		{
			Membership member2 = em.find(Membership.class, member.getMembershipPK());
			
			int i = member.getFailed_login_attempts() + 1;
			
			member2.setFailed_login_attempts(i);
			
			if(i==MAX_FAILED_LOGIN)
			{
				member2.setIs_blocked("S");
			}
			
			em.merge(member2);
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	protected RoleAssignment getRoleAssignPendiente( int prospectus_id, int company_id )
	{
		
		String query = "from RoleAssignment where status_id = 'P' and pk.prospectus_id = ? and pk.company_id = ?  ";
		
		try
		{
			
			List<RoleAssignment> lst =  em.createQuery(query,RoleAssignment.class)
											.setParameter(1, prospectus_id)
											.setParameter(2, company_id)
											.getResultList();
			
			if( lst!= null && lst.size()>0 )
			{
				
				return lst.get(0);
				
			}else{
				
				return null;
				
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}		
	}
}
