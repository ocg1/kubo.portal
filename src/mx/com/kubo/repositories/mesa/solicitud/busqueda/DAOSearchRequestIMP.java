package mx.com.kubo.repositories.mesa.solicitud.busqueda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;
import mx.com.kubo.model.mesa.solicitud.busqueda.UserViewFullName;
import mx.com.kubo.model.mesa.solicitud.busqueda.UserViewFullNameForCoach;

@Repository("dao_search_request")
public class DAOSearchRequestIMP extends DAOSearchRequestDMO
implements DAOSearchRequestIMO
{	
	public List<UserViewFullNameForCoach> getLista_prospectus_for_coach(String search_TOKEN, Integer promotor_id) 
	{
		Date d1 = new Date();
		
		Calendar cd_1 = Calendar.getInstance();
		cd_1.setTime(d1);
		
		//String query = "from UserViewFullNameForCoach where full_name is not null and full_name like :search_TOKEN and area = 'L' and promotor_id = :promotor_id";
		
		String query = 
		
		" select distinct " + 
			" mem.email AS email,  " + 
			" f.full_name AS full_name, " + 
			" p.tracking_id AS tracking_id, " + 
			" p.prospectus_id AS prospectus_id, " + 
			" p.area AS area, " + 
			" mem.promotor_id AS promotor_id " + 
		" from " + 
		" ((gn_prospectus p " + 
		" join ln_membership mem) " + 
		" join gn_full_name f) " + 
		" where " + 
		" ((f.prospectus_id = mem.prospectus_id) " + 
		" and (f.company_id = mem.company_id) " + 
		" and (f.prospectus_id = p.prospectus_id) " + 
		" and (f.company_id = p.company_id) " + 
		" and (isnull(mem.is_duplicated) " + 
		" or (mem.is_duplicated <> 'S'))) " +
		" and mem.promotor_id =  " + promotor_id +" " +
		" and f.full_name like '%" + search_TOKEN  + "%' " +
		" order by f.full_name " ;
		

		List<UserViewFullNameForCoach> lista_client_view = em.createNativeQuery(query, UserViewFullNameForCoach.class).getResultList();
		
		Calendar cd_2 = Calendar.getInstance();
		cd_2.setTime(new Date());
		
		long dif_1 = cd_2.getTimeInMillis() - cd_1.getTimeInMillis();
		
		System.out.println( "Fin ejecuta Consulta busqueda USUARIOS POR COACH =  " + search_TOKEN + "   coach: " + promotor_id + " tiempo: " + dif_1 );
		
		return lista_client_view;
	}
	
	public List<ClientViewFullName> getLista_inversionistas(String strName, String area)
	{		
		//TypedQuery <UserViewFullName> query = null;
		
		try
		{
			
			/*
			
			StringBuilder sb = new StringBuilder();
			sb.append("from  ").append("UserViewFullName ");
			sb.append("where ").append("full_name   ").append("is not null ");
			sb.append("and   ").append("full_name   ").append("like :strName ");
			sb.append("and   ").append("tracking_id ").append("is not null ");			
			
			if(area != null)
			{
				sb.append("and area = '").append(area).append("' ");
			}			
			
			query = em.createQuery(sb.toString(), UserViewFullName.class);
			query.setParameter("strName", "%" + strName + "%");
			query.setMaxResults(30);
			
			List<UserViewFullName> lista_user_view = query.getResultList();
						
			List<ClientViewFullName> lista_client_view = new ArrayList<ClientViewFullName>();
			
			if(lista_user_view != null)
			{
				for(UserViewFullName usr : lista_user_view)
				{					
					ClientViewFullName client_view = new ClientViewFullName();					
					client_view.setEmail        (usr.getEmail());
					client_view.setFull_name    (usr.getFull_name());
					client_view.setProspectus_id(usr.getProspectus_id());
					client_view.setTracking_id  (usr.getTracking_id());
					
					lista_client_view.add(client_view);					
				}
			}
			
			*/
			
			Date d1 = new Date();
			
			Calendar cd_1 = Calendar.getInstance();
			cd_1.setTime(d1);
			
			String query = "select  " +
		  	" fn.prospectus_id as prospectus_id,  " +
					" fn.email as email, " +
					" fn.full_name as full_name,"
					+ "'0000000' as tracking_id" +
					" from  " +
					" gn_full_name fn join " + 
					" gn_prospectus pr " +
					" on (fn.prospectus_id = pr.prospectus_id) " + 
					" where  " +
					" (fn.full_name like '%"+strName+"%')  and pr.area='"+ area +"' ";
			
			
			List<ClientViewFullName> lista_client_view = em.createNativeQuery(query, ClientViewFullName.class).getResultList();
			
			Calendar cd_2 = Calendar.getInstance();
			cd_2.setTime(new Date());
			
			long dif_1 = cd_2.getTimeInMillis() - cd_1.getTimeInMillis();
			
			System.out.println( "Fin ejecuta Consulta busqueda USUARIOS =  " + strName + "   area: " + area + " tiempo: " + dif_1 );
			
			return lista_client_view;
		
		} catch(Exception e) {
			
			e.printStackTrace();
			
			List<ClientViewFullName> lista_client_view = new ArrayList<ClientViewFullName>();
			
			return lista_client_view;
		}		
	}
	
	public List<ClientViewFullName> getLista_by_email(String search_TOKEN, String area){
		
		try
		{
		
			Date d1 = new Date();
			
			Calendar cd_1 = Calendar.getInstance();
			cd_1.setTime(d1);
			
			String query = "select  " +
		  	" m.prospectus_id as prospectus_id,  " +
					" '' as email, " +
					" m.email as full_name,"
					+ "'0000000' as tracking_id" +
					" from  " +
					" ln_membership m join " + 
					" gn_prospectus pr " +
					" on (m.prospectus_id = pr.prospectus_id) " + 
					" where  " +
					" (m.email like '%"+search_TOKEN+"%')  and pr.area='"+ area +"' ";
			
			
			List<ClientViewFullName> lista_client_view = em.createNativeQuery(query, ClientViewFullName.class).getResultList();
			
			Calendar cd_2 = Calendar.getInstance();
			cd_2.setTime(new Date());
			
			long dif_1 = cd_2.getTimeInMillis() - cd_1.getTimeInMillis();
			
			System.out.println( "Fin ejecuta Consulta busqueda USUARIOS =  " + search_TOKEN + "   area: " + area + " tiempo: " + dif_1 );
			
			return lista_client_view;
		
		} catch(Exception e) {
			
			e.printStackTrace();
			
			List<ClientViewFullName> lista_client_view = new ArrayList<ClientViewFullName>();
			
			return lista_client_view;
		}
		
	}
	
	
	public List<ClientViewFullName> getLista_inver_personas_morales(String strName, String area)
	{		
		
		
		try
		{
		
			Date d1 = new Date();
			
			Calendar cd_1 = Calendar.getInstance();
			cd_1.setTime(d1);
			
			String query = "select  " +
		  	" gn.prospectus_id as prospectus_id,  " +
					" '' as email, " +
					" gn.legal_name as full_name,"
					+ "'0000000' as tracking_id" +
					" from  " +
					" gn_natural_person gn join " + 
					" gn_prospectus pr " +
					" on (gn.prospectus_id = pr.prospectus_id) " + 
					" where  " +
					" (gn.legal_name like '%"+strName+"%')  and pr.area='"+ area +"' ";
			
			
			List<ClientViewFullName> lista_client_view = em.createNativeQuery(query, ClientViewFullName.class).getResultList();
			
			Calendar cd_2 = Calendar.getInstance();
			cd_2.setTime(new Date());
			
			long dif_1 = cd_2.getTimeInMillis() - cd_1.getTimeInMillis();
			
			System.out.println( "Fin ejecuta Consulta busqueda USUARIOS =  " + strName + "   area: " + area + " tiempo: " + dif_1 );
			
			return lista_client_view;
		
		} catch(Exception e) {
			
			e.printStackTrace();
			
			List<ClientViewFullName> lista_client_view = new ArrayList<ClientViewFullName>();
			
			return lista_client_view;
		}		
	}
	
	
}
