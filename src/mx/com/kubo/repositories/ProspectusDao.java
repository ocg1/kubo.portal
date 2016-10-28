package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;

public interface ProspectusDao {
	

	boolean saveProspectAndNaturalPersonAndMembership(Prospectus prospectus, NaturalPerson naturalPerson, Membership membership);
	boolean saveProspectAndNaturalPerson             (Prospectus prospectus, NaturalPerson naturalPerson);
	
	void updateProspectus(Prospectus newProspectus);
	
	int getMaxProspectus();
	
	Prospectus loadSelectedProspectus(ProspectusPK pk);
	Prospectus saveProspectus(Prospectus newProspectus);
	Prospectus getProspectByAreaByPersonType(int prospectID,int companyID,char area,char personType);
	Prospectus getProspectByTrackingID(String tracking_id);
	
	List<Prospectus> loadProspectsList();
	
	Prospectus getProspectusByHSId(Integer vid);
}
