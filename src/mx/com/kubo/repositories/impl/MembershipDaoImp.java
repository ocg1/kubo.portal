package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Promotor;
import mx.com.kubo.repositories.MembershipDaoIMO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MembershipDaoImp
implements MembershipDaoIMO 
{
	
	Logger log = Logger.getLogger(getClass());
	
	private EntityManager em = null;
	
	private Query query_native;
	
	private StringBuilder sb;
	private String query;
	
	private List<Membership> lista_membership;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
	
	
	public Membership loadSelectedMembership(MembershipPK pk) 
	{
		return em.find(Membership.class,pk);
	}
	
	
	public Membership loadMembershipByEmail(String email) 
	{
		query = "from Membership where email = ? ";
		
		lista_membership = em.createQuery(query, Membership.class).setParameter(1,email).getResultList();
		
		if(lista_membership.size() > 0)
		{
			return lista_membership.get(0);
		}
		
		 return null;
	}
	
	public Membership getMembershipbyFBID( String fb_id ){
		
		query = "from Membership where fb_id = ? ";
		
		lista_membership = em.createQuery(query, Membership.class).setParameter(1,fb_id).getResultList();
		
		if(lista_membership.size() > 0)
		{
			return lista_membership.get(0);
		}
		
		 return null;
		
	}

	
	public void saveMembership(Membership newMembership) {
		log.info("saveMembership.MembershipDaoImp");
		newMembership.setIs_active(0);
		em.merge(newMembership);
	}
	
	
	public boolean updateMembership(Membership newMembership) 
	{
		try
		{
			em.merge(newMembership);
			
			return true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
	
	
	public List<Membership> loadMembershipList() {
		 List<Membership> cats = em.createQuery(
			    "from Membership ", Membership.class)
			    .getResultList();
		 return cats;
	}

	public Promotor getPromotor(Integer prospectus_id) 
	{
		Promotor promotor = null;
		
		query = "from Promotor where prospectus_id = ? ";
		
		try
		{
			promotor = em.createQuery(query, Promotor.class).setParameter(1, prospectus_id).getSingleResult();
			
		} catch (Exception e) {
			
			promotor = null;
		}
		
		return promotor;
	}
	
	public List<Promotor> getListPromotors() 
	{
		return em.createQuery("from Promotor", Promotor.class).getResultList();
	}


	
	public Membership getMemberShipByActivCode(String code) {		
		return em.createQuery("from Membership where activation_code= ? ", Membership.class).setParameter(1,code).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	
	public List<Membership> getMemberShipForConsulting() {	
		return (List<Membership>)em.createNativeQuery("select m.* from ln_membership m where registration_reason_id = 6 and is_active = 1 and prospectus_id not in (select prospectus_id from gn_scoring_result) order by prospectus_id desc",Membership.class).getResultList();

	}
	
	
	public List<Membership> getMemberShipForAssignPerfil(){
		return (List<Membership>)em.createQuery("from Membership m where m.is_active = 1 and m.email like '%@kubofinanciero.com%' and is_blocked='N' order by m.person.prospectus.role.sequence, m.person.first_name  ",Membership.class).getResultList();
	}
	
	public List<Membership> getMembershipByArea(Character area)
	{		
		query = "from Membership as m "
			  + "where m.person.prospectus.area = ? ";
		
		lista_membership = em.createQuery(query, Membership.class)
						     .setParameter(1, area)
						     .getResultList();
		
		return lista_membership;
	}
	
	public List<Membership> getMembershipByArea(Character area, Integer limite_inferior, Integer limite_superior)
	{		
		query = "from Membership as m "
			  + "where m.person.prospectus.area = ? "
			  + "and   m.membershipPK.prospectus_id >= ? "
			  + "and   m.membershipPK.prospectus_id <  ?";
		
		lista_membership = em.createQuery(query, Membership.class)
						     .setParameter(1, area)
						     .setParameter(2, limite_inferior)
						     .setParameter(3, limite_superior)
						     .getResultList();
		
		return lista_membership;
	}
	
	
	@Transactional
	public boolean bloqueaProspectos( String prospectos ){
		
		String query = "from Membership as m where  m.membershipPK.prospectus_id in( "+prospectos+" ) ";
		
		try{
		
			lista_membership = em.createQuery(query, Membership.class).getResultList();
			
			if( lista_membership != null && lista_membership.size() > 0 ){
				
				for( Membership m : lista_membership ){
					m.setIs_blocked("S");
					em.merge(m);
				}
				
			}
			
			return true;
		
		}catch(Exception e){
			
			return false;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public final List<Membership> getLista_personas_CURP_duplicado(Integer prospectus_id, String curp)
	{		
		try
		{		
			sb = new StringBuilder();
			
			sb.append("select m.* from gn_prospectus p, ln_membership m, gn_natural_person n ");
			sb.append("where m.promotor_id is not null ");
			sb.append("and n.prospectus_id <> ? ");		
			sb.append("and n.mx_curp = ? ");
			sb.append("and p.area = 'L' ");
			sb.append("and (m.is_duplicated is null or m.is_duplicated <> 'S') ");			
			sb.append("and m.company_id = n.company_id ");
			sb.append("and m.company_id = p.company_id ");
			sb.append("and m.prospectus_id = n.prospectus_id ");
			sb.append("and m.prospectus_id = p.prospectus_id ");
							
			query_native = em.createNativeQuery(sb.toString(), Membership.class);
			query_native.setParameter(1, prospectus_id);
			query_native.setParameter(2, curp);
			
			lista_membership =  (List<Membership>) query_native.getResultList();
			
			return lista_membership;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Membership> getMembershipListByRegistrationReasonAndOrigin( int registration_reason_id , String origin_value ){
		
		String query = "select m.* from ln_membership m where  is_active = 1 and prospectus_id not in (select prospectus_id from gn_scoring_result)" ;
		
		if( registration_reason_id > 0 ){
			query += " and registration_reason_id = "+registration_reason_id;
		}
		
		if( origin_value.trim().length() > 0 ){
			query += " and origin = '"+origin_value+"' ";
		}
			
			query += " order by prospectus_id desc ";
		
		return (List<Membership>)em.createNativeQuery( query ,Membership.class).getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public Membership getMemberShipBySafiClientID( String safi_client_id ){
		
		//String query = "select m.* from ln_membership m where prospectus_id in (select prospectus_id from gn_natural_person where  CAST( safi_client_id  AS UNSIGNED INTEGER) = "+safi_client_id+")" ;
		
		String query = "select m.* from ln_membership m , gn_natural_person n where m.prospectus_id = n.prospectus_id and CAST( n.safi_client_id  AS UNSIGNED INTEGER) = "+safi_client_id+"";
		
		List<Membership> lst =  (List<Membership>)em.createNativeQuery( query ,Membership.class).getResultList();
		
		if( lst != null && lst.size() > 0 ){
	
		return lst.get(0);
		
		}else{
			return null;
		}
		
	}
	
	public List<Membership> getInvestorActive(){
		
		
String query = "select m.* "
		+ "from  "
		+ "		ln_membership m , "
		+ "		gn_natural_person n, "
		+ "		gn_prospectus p "
		+ "where "
		+ "m.prospectus_id 	= n.prospectus_id and "
		+ "m.company_id 	= n.company_id and "
		+ "m.prospectus_id 	= p.prospectus_id and "
		+ "m.company_id 	= p.company_id and "
		+ "p.area 			= 'I' and "
		+ "n.safi_client_id is not null";
		
	
		return (List<Membership>)em.createNativeQuery( query ,Membership.class).getResultList();
		
	}
	
}
