package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.BlockedPerson;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.PrevencionLDPK;
import mx.com.kubo.model.mesa.solicitud.PldNotification;

public interface PrevencionLDDao 
{
	PrevencionLD getSelectedPrevencionLDById(PrevencionLDPK pk);
	
	void savePrevencionLD(PrevencionLD prvencionld);
	void updatePrevencionLD(PrevencionLD prvencionld);
	void deletePrevencionLD(PrevencionLD prvencionld);
	
	PrevencionLD getPrevencionLDByProspectus(int prospectus_id,int company_id);

	boolean add(PldNotification notification_log);

	List<PldNotification> getLista_pld_notification(int prospectus_id, int company_id);
}
