package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.BlockedPerson;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.PrevencionLDPK;
import mx.com.kubo.model.mesa.solicitud.PldNotification;
import mx.com.kubo.repositories.PrevencionLDDao;
import mx.com.kubo.services.PrevencionLDService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrevencionLDServiceImp 
implements PrevencionLDService 
{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private PrevencionLDDao dao;
	
	public  PrevencionLD getSelectedPrevencionLDById(PrevencionLDPK pk)
	{
		return dao.getSelectedPrevencionLDById(pk);
	}
	
	public  void savePrevencionLD(PrevencionLD prvencionld)
	{
		dao.savePrevencionLD(prvencionld);
	}
	
	public  void updatePrevencionLD(PrevencionLD prvencionld)
	{
		dao.updatePrevencionLD(prvencionld);
	}
	
	public  void deletePrevencionLD(PrevencionLD prvencionld)
	{
		dao.deletePrevencionLD(prvencionld);
	}
	
	public PrevencionLD getPrevencionLDByProspectus(int prospectus_id,int company_id)
	{
		return dao.getPrevencionLDByProspectus(prospectus_id, company_id);
	}
	
	public List<BlockedPerson> getBlockedPersonByFullName(String full_name)
	{		
		return dao.getBlockedPersonByFullName(full_name);
	}
	
	public List<BlockedPerson> getBlockedPersonByRFC(String mx_rfc)
	{		
		return dao.getBlockedPersonByRFC(mx_rfc);
	}

	public boolean add(PldNotification notification_log) 
	{		
		return dao.add(notification_log);
	}

	public List<PldNotification> getLista_pld_notification(int prospectus_id, int company_id) 
	{
		return dao.getLista_pld_notification(prospectus_id, company_id);
	}
}
