package mx.com.kubo.repositories.impl;

import mx.com.kubo.model.FullName;
import mx.com.kubo.model.FullNamePK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
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
		
			ProsByAreaPesonType = em.createQuery("from Prospectus p where p.hs_vid", Prospectus.class).setParameter(1, vid)
							        .getSingleResult();
			
			return ProsByAreaPesonType;
			
		}catch(Exception e){
			
			System.out.println( "No encontrado: vid = " + vid + "   " + e.getMessage());
			return null;
			
		}
		
	}
}
