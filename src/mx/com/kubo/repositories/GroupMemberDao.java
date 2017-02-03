package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.GroupMember;

public interface GroupMemberDao  {

	public boolean saveMember( GroupMember gm );
	public boolean updateMember( GroupMember gm );
	public boolean deleteMember( GroupMember gm );
	public List<GroupMember> getMembersGroupByGroupId( Integer group_id );
	public List<GroupMember> getMembersGroupByProspectus( Integer prospectus_id );
	
}
