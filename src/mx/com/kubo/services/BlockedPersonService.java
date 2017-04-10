package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.BlockedPerson;

public interface BlockedPersonService 
{
	boolean delete(Integer citizenship);
	
	boolean save(BlockedPerson blocked_person);
	boolean update(BlockedPerson blocked_person);
	
	List<BlockedPerson> getBlockedPerson();
	List<BlockedPerson> getBlockedPersonByFullName(String full_name);
	List<BlockedPerson> getBlockedPersonByRFC(String mx_rfc);
	List<BlockedPerson> getBlockedPerson(Integer citizenship);
}
