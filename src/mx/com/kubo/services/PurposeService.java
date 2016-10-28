package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.Purpose;
import mx.com.kubo.model.PurposePK;

public interface PurposeService {
	
	public abstract Purpose getPurposeById(PurposePK pk);
	public abstract void add(Purpose newPurpose);
	public abstract List<Purpose> getPurposeList();
	public List<Purpose> loadPurposeListByType(Integer type_id);
	
}
