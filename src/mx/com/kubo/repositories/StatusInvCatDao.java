package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.StatusInvCat;
import mx.com.kubo.model.StatusInvCatPK;

public interface StatusInvCatDao {
	
	public abstract List<StatusInvCat> getListStatusInvCat();
	public abstract List<StatusInvCat> getListStatusInvCatByIsEnabled();
	public abstract StatusInvCat getStatusInvCatByPK(StatusInvCatPK StatusInvCatPK);
	
}
