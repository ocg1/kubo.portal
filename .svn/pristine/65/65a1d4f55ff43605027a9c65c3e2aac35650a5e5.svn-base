package mx.com.kubo.repositories.mesa.solicitud.busqueda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.TypedQuery;

import mx.com.kubo.model.RFCCollector;
import mx.com.kubo.model.UserViewFullNameForReferred;
import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;
import mx.com.kubo.model.mesa.solicitud.busqueda.UserViewFullName;

import org.springframework.stereotype.Repository;

@Repository("dao_view_full_name")
public class ClientViewFullNameDaoImp extends DAOClientViewFullNameDMO
implements ClientViewFullNameDao
{			
	
	public List<ClientViewFullName> getListClientByName(String strName) 
	{
//		TypedQuery <ClientViewFullName> query = null;
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append("from  ").append("ClientViewFullName ");
//		sb.append("where ").append("full_name   ").append("is not null ");
//		sb.append("and   ").append("full_name   ").append("like :strName ");
//		sb.append("and   ").append("tracking_id ").append("is not null ");
//		
//		query = em.createQuery(sb.toString(), ClientViewFullName.class);
//		query.setParameter("strName", "%" + strName + "%");
//		query.setMaxResults(30);
//		
//		List<ClientViewFullName> lstClient = query.getResultList();
		
		Date d1 = new Date();
		
		Calendar cd_1 = Calendar.getInstance();
		cd_1.setTime(d1);
		
		List<ClientViewFullName> lstClient=  em.createNamedQuery("collectorClientViewFullName",ClientViewFullName.class)
				.setParameter("strLIKE", strName)
				.getResultList();
		
		Calendar cd_2 = Calendar.getInstance();
		cd_2.setTime(new Date());
		
		long dif_1 = cd_2.getTimeInMillis() - cd_1.getTimeInMillis();
		
		System.out.println( "Fin ejecuta Consulta collectorClientViewFullName =  " + strName + " tiempo: " + dif_1 );
		
				
		return lstClient;
	}

	@Override
	public List<ClientViewFullName> getListClientByEmail(String strEmail) 
	{
		String query = "from ClientViewFullName c "
				+ "where c.full_name   is not null "
				+ "and   c.email       is not null "
				+ "and   c.tracking_id is not null "
				+ "and   c.email like :strEmail";
		
		return em.createQuery(query, ClientViewFullName.class)
				.setParameter("strEmail", "%" +strEmail+ "%")
				.setMaxResults(30)
				.getResultList();
		
	}
	
	@Override
	public List<ClientViewFullName> getListAllUserByName(String strName)
	{		
		try
		{
			String query = "from  UserViewFullName "
					+ "where full_name   is not null "
					+ "and   tracking_id is not null "
					+ "and   full_name   like :strName";
			
			List<UserViewFullName> userLst =  em.createQuery(query, UserViewFullName.class)
						 .setParameter("strName", "%" + strName + "%")
						 .setMaxResults(30)
						 .getResultList();
			
			
			List<ClientViewFullName> lstClient = new ArrayList<ClientViewFullName>();
			
			if(userLst != null)
			{
				for(UserViewFullName usr : userLst)
				{					
					ClientViewFullName clt = new ClientViewFullName();					
					clt.setEmail        (usr.getEmail());
					clt.setFull_name    (usr.getFull_name());
					clt.setProspectus_id(usr.getProspectus_id());
					clt.setTracking_id  (usr.getTracking_id());
					
					lstClient.add(clt);
					
				}
			}
			
			return lstClient;
		
		}catch(Exception e){
			e.printStackTrace();
			
			List<ClientViewFullName> lstClient = new ArrayList<ClientViewFullName>();
			
			return lstClient;
		}		
	}
	
	@Override
	public LinkedList<ClientViewFullName> getListAllUserForReferred(String strName)
	{		
		try
		{
			String query = "from  UserViewFullNameForReferred "
					+ "where full_name is not null and "
					+ "tracking_id is not null "
					+ "and full_name like :strName";
			
			List<UserViewFullNameForReferred> userLst =  em.createQuery(query, UserViewFullNameForReferred.class)
															.setParameter("strName", "%" + strName + "%")
															.setMaxResults(30)
															.getResultList();
			
			
			LinkedList<ClientViewFullName> lstClient = new LinkedList<ClientViewFullName>();
			
			if(userLst != null)
			{
				for(UserViewFullNameForReferred usr : userLst)
				{
					
					ClientViewFullName clt = new ClientViewFullName();
					
					clt.setEmail(usr.getEmail());
					clt.setFull_name(usr.getFull_name());
					clt.setProspectus_id(usr.getProspectus_id());
					clt.setTracking_id(usr.getTracking_id());
					
					lstClient.add(clt);
					
				}
			}
			
			return lstClient;
		
		} catch(Exception e) {
			e.printStackTrace();
			LinkedList<ClientViewFullName> lstClient = new LinkedList<ClientViewFullName>();
			return lstClient;
		}		
	}

}
