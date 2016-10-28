package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.BlockedPerson;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.PrevencionLDPK;
import mx.com.kubo.model.mesa.solicitud.PldNotification;

public interface PrevencionLDService 
{
	boolean add(PldNotification notification_log);
	
	void savePrevencionLD  (PrevencionLD prvencionld);
	void updatePrevencionLD(PrevencionLD prvencionld);
	void deletePrevencionLD(PrevencionLD prvencionld);
	
	PrevencionLD getSelectedPrevencionLDById(PrevencionLDPK pk);

	PrevencionLD getPrevencionLDByProspectus(int prospectus_id, int company_id);
	
	List<BlockedPerson> getBlockedPersonByFullName(String full_name);
	List<BlockedPerson> getBlockedPersonByRFC(String mx_rfc);
	
	List<PldNotification> getLista_pld_notification(int prospectus_id, int company_id);
}
