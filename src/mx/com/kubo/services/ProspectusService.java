package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.bean.HS_OBJ;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;

public interface ProspectusService 
{
	void update(Prospectus newProspectus);
	
	
	Prospectus getProspectusById(ProspectusPK pk);
	
	Prospectus getProspectusByHSId(Integer vid);
	
	Prospectus add(Prospectus newProspectus);		
	
	Prospectus getProspectByAreaByPersonType(int prospectID,int companyID,char area,char personType);
	Prospectus getProspectByTrackingID(String tracking_id);
	
	List<Prospectus> getProspectusList();
	
	int getMaxProspectus();
	
	boolean saveProspectAndNaturalPersonAndMembership(Prospectus prospectus,NaturalPerson naturalPerson,Membership membership);
	boolean saveProspectAndNaturalPerson(Prospectus prospectus,NaturalPerson naturalPerson);
	
	boolean altaProspectoHS( HS_OBJ hs_obj );
	
	boolean updateProspectoHS( HS_OBJ hs_obj );
	
}
