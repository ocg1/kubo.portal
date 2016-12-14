package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.PospectusComment;
import mx.com.kubo.repositories.PospectusCommentDao;
import mx.com.kubo.services.PospectusCommentService;

@Service
public class PospectusCommentServiceImp implements Serializable, PospectusCommentService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	PospectusCommentDao dao;
	
	public List<PospectusComment> getPospectusCommentByType( int prospectus_id, int proyect_loan_id , int comment_type_id ){
		return dao.getPospectusCommentByType(prospectus_id,  proyect_loan_id , comment_type_id);
	}
	
	public List<PospectusComment> getPospectusCommentByProspectus( int prospectus_id ){
		return dao.getPospectusCommentByProspectus(prospectus_id);
	}
	
	public boolean savePospectusComment( PospectusComment pospectuscomment ){
		return dao.savePospectusComment(pospectuscomment);
	}
	
	public boolean updatePospectusComment( PospectusComment pospectuscomment ){
		return dao.updatePospectusComment(pospectuscomment);
	}
	
	public boolean removePospectusComment( PospectusComment pospectuscomment ){
		return dao.removePospectusComment(pospectuscomment);
	}

}
