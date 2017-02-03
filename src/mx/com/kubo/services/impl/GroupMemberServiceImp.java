package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.GroupMember;
import mx.com.kubo.repositories.GroupMemberDao;
import mx.com.kubo.services.GroupMemberService;

@Service
public class GroupMemberServiceImp implements Serializable, GroupMemberService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	GroupMemberDao dao;
	
	public boolean saveMember( GroupMember gm ){
		return dao.saveMember(gm);
	}
	public boolean updateMember( GroupMember gm ){
		return dao.updateMember(gm);
	}
	public boolean deleteMember( GroupMember gm ){
		return dao.deleteMember(gm);
	}
	public List<GroupMember> getMembersGroupByGroupId( Integer group_id ){
		return dao.getMembersGroupByGroupId(group_id);
	}
	public List<GroupMember> getMembersGroupByProspectus( Integer prospectus_id ){
		return dao.getMembersGroupByProspectus(prospectus_id);
	}
	
}
