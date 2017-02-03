package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.InvestorGroup;
import mx.com.kubo.repositories.InvestorGroupDao;
import mx.com.kubo.services.InvestorGroupService;

@Service
public class InvestorGroupServiceImp implements Serializable, InvestorGroupService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	InvestorGroupDao dao;
	

	public boolean saveInvestorGroup( InvestorGroup group ){
		return dao.saveInvestorGroup(group);
	}
	
	public boolean updateInvestorGroup( InvestorGroup group ){
		return dao.updateInvestorGroup(group);
	}
	
}
