package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.StatusInvCat;
import mx.com.kubo.model.StatusInvCatPK;

public interface StatusInvService {
	public abstract List<StatusInvCat> getListStatusInvCat();
	public abstract List<StatusInvCat> getListStatusInvCatByIsEnabled();
	public abstract StatusInvCat getStatusInvCatByPK(StatusInvCatPK StatusInvCatPK);
}
