package mx.com.kubo.repositories.impl;

import mx.com.kubo.bean.HS_OBJ;
import mx.com.kubo.model.FullName;
import mx.com.kubo.model.FullNamePK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.SP_AltaProspectoCallector;
import mx.com.kubo.model.SP_UpdateProspectoCallector;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.repositories.ProspectusDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public final class ProspectusDaoImp extends DAOProspectusDMO
implements ProspectusDao 
{
	@Transactional	
	public Prospectus saveProspectus(Prospectus newProspectus) 
	{
		log.info("saveProspectus.ProspectusDaoImp");
		
		newProspectus.getProspectusPK().setProspectus_id((getMaxProspectus() + 1));
		
		em.persist(newProspectus);
		
		return newProspectus;
	}
	
	@Transactional	
	public void updateProspectus(Prospectus newProspectus) 
	{
		log.info("updateProspectus.ProspectusDaoImp");
		
		em.merge(newProspectus);
	}
	
	@Transactional	
	public boolean saveProspectAndNaturalPersonAndMembership(Prospectus prospectus, NaturalPerson naturalPerson, Membership membership) 
	{
		int newProspectID = 0;
		int COMPANY = 1;
		
		try 
		{

			prospectus.getProspectusPK().setProspectus_id((getMaxProspectus() + 1));
			
			em.persist(prospectus);
			
			newProspectID = prospectus.getProspectusPK().getProspectus_id();
			naturalPerson.setNatPerPK(new gnNaturalPersonPK(newProspectID,COMPANY));			
			em.persist(naturalPerson);
			
			membership.setMembershipPK(new MembershipPK(newProspectID, COMPANY));
			em.persist(membership);
			
			FullNamePK fpk = new FullNamePK();
			
			fpk.setCompany_id(prospectus.getProspectusPK().getCompany_id());
			fpk.setProspectus_id(newProspectID);
			
			FullName fullname = new FullName();
			
			String first_name       = naturalPerson.getFirst_name()        == null ? "" : naturalPerson.getFirst_name();	
			String middle_name      = naturalPerson.getMiddle_name()       == null ? ""  : naturalPerson.getMiddle_name();
			String father_last_name = naturalPerson.getFather_last_name()  == null ? "" : naturalPerson.getFather_last_name();
			String mother_last_name = naturalPerson.getMother_last_name()  == null ? "" : naturalPerson.getMother_last_name();
			
			String fullnameStr = first_name; 
			
			if( middle_name.length() > 0 ){
				
				if( fullnameStr.length() > 0 ){
					fullnameStr += " ";
				}
				
				fullnameStr +=  middle_name;
				
			}
			if( father_last_name.length() > 0 ){
				
				if( fullnameStr.length() > 0 ){
					fullnameStr += " ";
				}
				
				fullnameStr += father_last_name;
				
			}
			if( mother_last_name.length() > 0 ){
				
				if( fullnameStr.length() > 0 ){
					fullnameStr += " ";
				}
				
				fullnameStr	+= mother_last_name ;
				
			}
			
			fullname.setFull_name(fullnameStr);
			fullname.setPk(fpk);
			
			em.persist(fullname);
			

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Transactional	
	public boolean saveProspectAndNaturalPerson(Prospectus prospectus, NaturalPerson naturalPerson) 
	{
		int newProspectID = 0;
		int COMPANY = 1;
		
		try 
		{
			prospectus.getProspectusPK().setProspectus_id((getMaxProspectus() + 1));
			
			em.persist(prospectus);
			
			newProspectID = prospectus.getProspectusPK().getProspectus_id();
			
			naturalPerson.setNatPerPK(new gnNaturalPersonPK(newProspectID, COMPANY));
			
			em.persist(naturalPerson);
			
			return true;

		} catch (Exception e) {
			return false;
		}
	}
	
	public Prospectus getProspectusByHSId(Integer vid){
		
		try{
		
			return em.createQuery("from Prospectus p where p.hs_vid = ? ", Prospectus.class).setParameter(1, vid)
							        .getSingleResult();
			
			
		}catch(Exception e){
			
			System.out.println( "No encontrado: vid = " + vid + "   " + e.getMessage());
			return null;
			
		}
		
	}
	
	
	@Transactional  
	  public boolean updateProspectoHS( HS_OBJ hs_obj_in  ){
		  try{
		 
			  //SystemParam res = em.createQuery(" from SystemParam where pk.system_param_id = ? ",SystemParam.class).setParameter(1, 79) .getSingleResult();
			  
			  HS_OBJ hs_obj = validaHSobj( hs_obj_in );
			  
			  SP_UpdateProspectoCallector s = em.createNamedQuery("collectorUpdateProspecto",SP_UpdateProspectoCallector.class)
					  
					  .setParameter("param_prospectus_id",hs_obj.getProspectus_id())
					  .setParameter("param_primer_nombre",hs_obj.getFirstname_value())
					  .setParameter("param_segundo_nombre",hs_obj.getSecond_name())
					  .setParameter("param_ap_paterno",hs_obj.getLast_name())
					  .setParameter("param_ap_materno",hs_obj.getLast_name2())
					  .setParameter("param_hs_vid",hs_obj.getV_id())
					  .setParameter("param_s_email",hs_obj.getEmail_value())
					  .setParameter("param_url_origin",hs_obj.getUrl_value())
					  .setParameter("param_phone_mobile",hs_obj.getMobil_value())
					  
	    		.getSingleResult();
			  
			  System.out.println( "Update Usuario HS: " + s );
		  
			  return true;
		  
		  }catch( Exception e ){
			  e.printStackTrace();
			  return false;
		  }
	  }
	
	private HS_OBJ validaHSobj( HS_OBJ hs_obj ){
		
		NaturalPerson per 	= em.createQuery( "from NaturalPerson	where natPerPK.prospectus_id = ? ", NaturalPerson.class).setParameter(1, hs_obj.getProspectus_id()).getSingleResult();
		Phone phone 		= em.createQuery( "from Phone 			where phonePk.prospectus_id = ? and phone_type_id = 6 " , Phone.class).setParameter(1, hs_obj.getProspectus_id()).getSingleResult();
		Membership mem 		= em.createQuery( "from Membership 		where membershipPK.prospectus_id = ? ", Membership.class).setParameter(1, hs_obj.getProspectus_id()).getSingleResult();
		
		
			
			if( per != null ){
			
				if( (hs_obj.getFirstname_value() == null || hs_obj.getFirstname_value().trim().length() == 0 ) ){
					hs_obj.setFirstname_value( per.getFirst_name() );
				}
				
				if( hs_obj.getSecond_name() == null || hs_obj.getSecond_name().trim().length() == 0){
					hs_obj.setSecond_name( per.getMiddle_name() );
				}
				
				if( hs_obj.getLast_name() == null || hs_obj.getLast_name().trim().length() == 0){
					hs_obj.setLast_name(per.getFather_last_name() );
				}
				
				if( hs_obj.getLast_name2() == null || hs_obj.getLast_name2().trim().length() == 0){
					hs_obj.setLast_name2( per.getMother_last_name() );
				}
			
			}
			
			if( phone != null ){
				
				if( hs_obj.getMobil_value() == null || hs_obj.getMobil_value().trim().length() == 0){
					hs_obj.setMobil_value( phone.getPhone_number() );
				}
			}
			
			if( mem != null ){
				
				if( hs_obj.getEmail_value() == null ||  hs_obj.getEmail_value().trim().length() == 0){
					hs_obj.setEmail_value( mem.getEmail() );
				}
				if( hs_obj.getRegistration_reason_id() == null){
					hs_obj.setRegistration_reason_id( mem.getRegistration_reason_id() );
				}
				if(hs_obj.getUrl_value() == null || hs_obj.getUrl_value().trim().length() == 0){
					hs_obj.setUrl_value( mem.getUrl_origin() );
				}
				
			}
			
			return hs_obj;
			
			//return obj;
			
		
	}
	
	  @Transactional  
	  public boolean altaProspectoHS( HS_OBJ hs_obj  ){
		  try{
		 
			  //SystemParam res = em.createQuery(" from SystemParam where pk.system_param_id = ? ",SystemParam.class).setParameter(1, 79) .getSingleResult();
			  
			  SP_AltaProspectoCallector s = em.createNamedQuery("collectorAltaProspecto",SP_AltaProspectoCallector.class)
					  
							.setParameter("par_primer_nombre", 		hs_obj.getFirstname_value() )
							.setParameter("par_segundo_nombre", 	hs_obj.getSecond_name())
							.setParameter("par_ap_paterno", 		hs_obj.getLast_name())
							.setParameter("par_ap_materno",			hs_obj.getLast_name2())
							.setParameter("par_hs_vid", 			hs_obj.getV_id())
							.setParameter("par_hs_email",			hs_obj.getEmail_value())
							.setParameter("par_url_origin",			hs_obj.getUrl_value())
							.setParameter("par_phone_mobile", 		hs_obj.getMobil_value())
							.setParameter("par_area", 				hs_obj.getArea())
							.setParameter("par_registration_reason", 				hs_obj.getRegistration_reason_id())
							.setParameter("par_utm_medium",			hs_obj.getUrl_medium())
							.setParameter("par_utm_campaign",		hs_obj.getUrl_campaign())
					  
	    		.getSingleResult();
			  
			  System.out.println( "Alta Usuario HS: " + s );
		  
			  return true;
		  
		  }catch( Exception e ){
			  e.printStackTrace();
			  return false;
		  }
	  }
	
	
}
