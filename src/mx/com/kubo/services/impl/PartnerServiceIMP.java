package mx.com.kubo.services.impl;

import mx.com.kubo.model.Partner;
import mx.com.kubo.model.PartnerPK;
import mx.com.kubo.repositories.DAOPartnerIMO;
import mx.com.kubo.services.PartnerServiceIMO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("service_partner")
public class PartnerServiceIMP 
implements PartnerServiceIMO
{
	@Autowired
	protected DAOPartnerIMO dao;
	
	public Partner getPartner(PartnerPK partner_PK) 
	{		
		return dao.getPartner(partner_PK);
	}
	
	public List<Partner> getPartnerList(){
		return dao.getPartnerList();
	}

	public boolean save(Partner partner) 
	{
		return dao.save(partner);
	}
}
