package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.StatusInvCat;
import mx.com.kubo.model.StatusInvCatPK;
import mx.com.kubo.repositories.StatusInvCatDao;
import mx.com.kubo.services.StatusInvService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusInvServiceImp implements Serializable, StatusInvService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StatusInvCatDao statusInvCatDao;

	@Override
	public List<StatusInvCat> getListStatusInvCat() {
		// TODO Auto-generated method stub
		return statusInvCatDao.getListStatusInvCat();
	}

	@Override
	public StatusInvCat getStatusInvCatByPK(
			StatusInvCatPK statusInvCatPK) {
		// TODO Auto-generated method stub
		return statusInvCatDao.getStatusInvCatByPK(statusInvCatPK);
	}

	@Override
	public List<StatusInvCat> getListStatusInvCatByIsEnabled() {
		// TODO Auto-generated method stub
		return statusInvCatDao.getListStatusInvCatByIsEnabled();
	}
	
}
