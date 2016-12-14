package mx.com.kubo.services.impl;

import mx.com.kubo.bean.HS_OBJ;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.services.ProspectusService;

import org.springframework.stereotype.Service;

@Service(value = "prospectusServiceImp")
public class ProspectusServiceImp extends ServiceProspectusDMO
implements ProspectusService 
{	
	public Prospectus add(Prospectus newProspectus) 
	{
		log.info("add.CompanyServiceImp");
		
		return dao.saveProspectus(newProspectus);		
	}
	
	public boolean saveProspectAndNaturalPersonAndMembership(Prospectus prospectus,NaturalPerson naturalPerson,Membership membership)
	{
		log.info("savePropectAndNaturalPersonAndMembership.CompanyServiceImp");
		
		return dao.saveProspectAndNaturalPersonAndMembership(prospectus,naturalPerson,membership);
	}
	
	public boolean saveProspectAndNaturalPerson(Prospectus prospectus,NaturalPerson naturalPerson) 
	{		
		return dao.saveProspectAndNaturalPerson(prospectus, naturalPerson);
	}
		
	public void update(Prospectus newProspectus) 
	{
		log.info("update.CompanyServiceImp");
		
		dao.updateProspectus(newProspectus);		
	}
	
	public Prospectus getProspectusByHSId(Integer vid){
		return dao.getProspectusByHSId( vid);	
	}
	
	public boolean altaProspectoHS( HS_OBJ hs_obj ){
		
		return dao.altaProspectoHS( hs_obj );
		
	}
	
	public boolean updateProspectoHS( HS_OBJ hs_obj ){
		
		return dao.updateProspectoHS( hs_obj );
		
	}
}
