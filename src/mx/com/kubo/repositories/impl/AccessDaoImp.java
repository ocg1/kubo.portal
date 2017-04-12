package mx.com.kubo.repositories.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.com.kubo.model.Access;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.Screen;
import mx.com.kubo.model.ScreenPK;
import mx.com.kubo.repositories.AccessDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccessDaoImp extends DAOBitacoraAccess
implements AccessDao 
{		
	@Transactional
	public void saveAccess(Access newAccess) 
	{
		log.info("saveAccess.AccessDaoImp");

		newAccess.setAccess_datetime(new Date());						
		
		em.persist(newAccess);
	}
	
	public Access loadMaxAccess(Integer prospectus_id, Integer company_id) 
	{
		log.info("saveAccess.AccessDaoImp");
		String query="select MAX(a.access_id) from Access a where a.prospectus_id = ?";
		Integer id=0;
		id=(Integer)  em.createQuery(query)
						.setParameter(1, prospectus_id)
						.getSingleResult();
		if(id == null)
			return null;
		else
			return em.find(Access.class,id);
		
	}
	
	public List<AccessCollector> loadControlMenu(Integer role_id, Integer company_id)
	{		
		try
		{
		
		String query = "select "
						+ "	ra.company_id, "
						+ " s.menu_order, "
						+ " s.resource_name, "
						+ " s.name,s.screen_id,"
						+ " 0 percentage,"
						+ " 'N' is_obligatory, "
						+ " 0 access_id " +
					   " from "
					   + "	gn_role_access ra, "
					   + "	pr_screen s " +
					   " where "
					   + "	ra.screen_id = s.screen_id " +
					   "	and ra.role_id = ? " +
					   "	and ra.company_id = ?" +
					   " 	order by s.menu_order;";
		
		Date dI = new Date();
		Calendar cd_I = Calendar.getInstance();
		cd_I.setTime(dI);
		
		@SuppressWarnings("unchecked")
		List<AccessCollector> menu =   em.createNativeQuery(query,AccessCollector.class)
				.setParameter(2, company_id)
				.setParameter(1, role_id )
				.getResultList();
		
		Date dF = new Date();
		Calendar cd_F = Calendar.getInstance();
		cd_F.setTime(dF);
		
long dif_1 = cd_F.getTimeInMillis() - cd_I.getTimeInMillis();
		
		
		System.out.println( "Tiempo en ejecutar el Query que carga los menus de Mesa de Control : " + dif_1 +"  milisegundos.");
		
		return menu;
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
		
		
	}
	
	public Access getMaxAccessByScreen(int prospectus_id, int company_id, int screen_id ){
		log.info("saveAccess.AccessDaoImp");
		String query="select MAX(a.access_id) from Access a where a.prospectus_id = ? and a.company_id = ? and screen_id = ?";
		Integer id=0;
		id=(Integer)  em.createQuery(query)
						.setParameter(1, prospectus_id)
						.setParameter(2, company_id)
						.setParameter(3, screen_id)
						.getSingleResult();
		if(id == null)
			return null;
		else
			return em.find(Access.class,id);
	}
	
	@Transactional
	public void update(Access newAccess) 
	{
		log.info("updateAccess.AccessDaoImp");
		
		em.merge(newAccess);
	}
	
	
	public List<AccessCollector> loadMenu(Integer prospectus_id, Integer company_id,Character area) 
	{
		try
		{
		/*List<AccessCollector> menu =   em.createNamedQuery("findAccessMenuReg",AccessCollector.class)
															.setParameter("prospectus", prospectus_id)
															.setParameter("company", company_id)
															.getResultList();*/
			
			String query = 
		/*	"Select  " +
		    "s.company_id as company_id,  " +  
		    "s.name as name,  " +  
		    "s.menu_order as menu_order,  " +  
		    "s.screen_id as screen_id,  " +  
		    "s.resource_name as resource_name,  " +  
		    "max(a.access_id) as access_id,  " +
		    "a.percentage as percentage  " +
		"From  " + 
		    "pr_screen as s  " +  
		    "Left Join  " + 
		          "  pr_access as a  " +  
		          "On  " +
		          "( s.screen_id = a.screen_id  " +  
		           "and 	a.company_id = s.company_id  " +  
		           "and 	a.company_id = ?  " +  
		           "and 	a.prospectus_id = ?  " +  
		           ")  " +
		"where  " + 
		   " s.menu_order > 0  " +
		"Group By  " + 
		    "s.company_id,  " +  
		    "s.name,  " +  
		   "s.menu_order,    " +
		    "s.screen_id,  " +  
		    "s.resource_name  " + 
		"order by  " + 
		    "menu_order  ";	*/
					
					/* "select ax.*, bx.percentage from    "  +
					"(Select       "  +
					"            s.company_id as company_id,    "  +   
					"            s.name as name,       "  +
					"            s.menu_order as menu_order,    "  +  
					"			 s.is_obligatory as is_obligatory,   "+
					"            s.screen_id as screen_id,       "  +
					"            s.resource_name as resource_name,    "  +   
					"            max(a.access_id) as access_id,    "  +                
					"            a.prospectus_id    "  + 
					"        From    "  +   
					"            pr_screen as s    "  +   
					"            Left Join    "  +   
					"                    pr_access as a    "  +   
					"                  On    "  +  
					"                  ( s.screen_id = a.screen_id    "  +   
					"                   and     a.company_id = s.company_id    "  +   
					"                   and     a.company_id = ?    "  +   
					"                   and     a.prospectus_id = ?    "  + 
					"                   )    "  +   
					"        where    "  +   
					"            s.menu_order > 0    "  + 
					"		 and s.area=?					 "	+		
					"        Group By    "  +   
					"            s.company_id,    "  +   
					"            s.name,    "  +   
					"            s.menu_order,    "  +    
					"            s.screen_id,    "  +   
					"            s.resource_name,    "  +
					"            a.prospectus_id    "  + 
					"        order by    "  +   
					"            s.menu_order    "  +
					"  ) as ax left join pr_access as bx  on (ax.access_id = bx.access_id)    "  ; */
					
					
					/* " select ax.*, bx.percentage from  "  +
					"(Select  "
					+ "	s.company_id as company_id,   "
					+ " s.name as name,    "
					+ " s.menu_order as menu_order, "  
					+ " s.is_obligatory as is_obligatory,  "
					+ " s.screen_id as screen_id,  "
					+ " s.resource_name as resource_name, "  
					+ " max(a.access_id) as access_id,   "
					+ " a.prospectus_id  "  +
					"From pr_screen as s Left Join (select * from pr_access where prospectus_id = ? and company_id = ?) as a On "  + 
					"  (s.screen_id = a.screen_id and a.company_id = s.company_id)  "  +
					"  where s.menu_order > 0 and "
					+ "s.area=? "
					+ "Group By "
							+ "s.company_id, "
							+ "s.name, "
							+ "s.menu_order, "
							+ "s.screen_id, "
							+ "s.resource_name, "  
							+ "a.prospectus_id "
					+ "order by s.menu_order) as ax left join pr_access as bx  on (ax.access_id = bx.access_id); ";   */
			
			" select ax.*, bx.percentage  "  + 
			" from  (Select  	s.company_id as company_id,    s.name as name,     s.menu_order as menu_order,  s.is_obligatory as is_obligatory, "  +    
			" s.screen_id as screen_id,   s.resource_name as resource_name,  a.access_id as access_id,    a.prospectus_id   "  + 
			" From pr_screen as s Left Join (select prospectus_id, company_id, screen_id, max(access_id) access_id  "  + 
			" from pr_access use index(FK_pr_access_gn_prospectus)  "  + 
			" where prospectus_id = ? and company_id = ?  "  + 
			" group by prospectus_id, company_id, screen_id)  "  + 
			" as a On   (s.screen_id = a.screen_id and a.company_id = s.company_id)     "  + 
			" where s.menu_order > 0 and s.area=?  "  + 
			" Group By s.company_id, s.name, s.menu_order, s.screen_id, s.resource_name, a.prospectus_id order by s.menu_order)  "  + 
			" as ax left join pr_access as bx  on (ax.access_id = bx.access_id); ";
					
		
			@SuppressWarnings("unchecked")
			List<AccessCollector> menu =   em.createNativeQuery(query,AccessCollector.class)
					.setParameter(2, company_id)
					.setParameter(1, prospectus_id )
					.setParameter(3, area)
					.getResultList();
		
		return menu;
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<AccessCollector> getMenuIncomplete(Integer prospectus_id, Integer company_id, Character area) 
	{		
		List<AccessCollector> menu = null;
		
		try
		{			
			String query = getMenu_imcomplete_QUERY(); 	
										
			menu = em.createNativeQuery(query, AccessCollector.class)
					 .setParameter(2, prospectus_id)
					 .setParameter(1, company_id)
					 .setParameter(3, area)
					 .getResultList();
		
			return menu;
		
		} catch(Exception e) {
			
			e.printStackTrace();					
		}
		
		return menu;
	}	
	
	public Screen getLastAccessMenuScreen( int prospectus_id, int company_id, String area  )
	{		
		Access access;
		
		String query = "select MAX(a.access_id) from Access a where a.prospectus_id = ? and a.company_id = ? and a.screen_id not in (select s.screenPK.screen_id from Screen s where s.menu_order = (0) ) ";
		Integer id=0;
		id=(Integer)  em.createQuery(query)
						.setParameter(1, prospectus_id)
						.setParameter(2, company_id)
						.getSingleResult();
		
		if(id == null)
		{
			return null;
			
		} else {
			
			access =  em.find(Access.class,id);
		}
		
		ScreenPK scpk = new ScreenPK();
		scpk.setCompany_id(company_id);
		
		if(area != null && area.equals("M") && access != null && access.getScreen_id() == 2  )
		{			
			scpk.setScreen_id( 12 ); // pantalla de consulta de solicitud
		
		} else {
			
			scpk.setScreen_id(access.getScreen_id());			
		}
		
		return em.find(Screen.class,scpk);		
	}
	
}
