package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.LegalStatusPK;
import mx.com.kubo.model.Legal_Status;

public interface LegalStatusService {
	
	public abstract Legal_Status getLegalStatusById(LegalStatusPK pk);
	public abstract void add(Legal_Status newLegalStatus);
	public abstract List<Legal_Status> getLegalStatusList();

}
