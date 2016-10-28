package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Partner;
import mx.com.kubo.model.PartnerPK;

public interface DAOPartnerIMO 
{
	Partner getPartner(PartnerPK partner_PK);

	boolean save(Partner partner);
	
	public List<Partner> getPartnerList();
}
