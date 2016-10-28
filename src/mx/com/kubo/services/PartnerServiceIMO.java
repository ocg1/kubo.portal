package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.Partner;
import mx.com.kubo.model.PartnerPK;

public interface PartnerServiceIMO 
{
	Partner getPartner(PartnerPK partner_PK);

	boolean save(Partner partner);
	
	public List<Partner> getPartnerList();
	
}
