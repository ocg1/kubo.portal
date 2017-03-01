package mx.com.kubo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.BlockedPerson;
import mx.com.kubo.repositories.BlockedPersonDAO;
import mx.com.kubo.services.BlockedPersonService;

@Service
public class BlockedPersonServiceImp implements BlockedPersonService
{
	@Autowired
	private BlockedPersonDAO dao;
	
	public boolean save(BlockedPerson blocked_person)
	{
		return dao.save(blocked_person);
	}
	
	public boolean update(BlockedPerson blocked_person)
	{
		return dao.update(blocked_person);
	}
	
	public List<BlockedPerson> getBlockedPersonByFullName(String full_name)
	{		
		return dao.getBlockedPersonByFullName(full_name);
	}
	
	public List<BlockedPerson> getBlockedPersonByRFC(String mx_rfc)
	{		
		return dao.getBlockedPersonByRFC(mx_rfc);
	}

	public List<BlockedPerson> getBlockedPerson() 
	{		
		return dao.getBlockedPerson();
	}
}
