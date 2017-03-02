package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.BlockedPerson;

public interface BlockedPersonDAO 
{
	boolean delete();
	boolean save  (BlockedPerson blocked_person);
	boolean update(BlockedPerson blocked_person);
	
	List<BlockedPerson> getLista_blocked_person();
	List<BlockedPerson> getBlockedPersonByFullName(String full_name);
	List<BlockedPerson> getBlockedPersonByRFC(String mx_rfc);
	List<BlockedPerson> getBlockedPerson();
}
