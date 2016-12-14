package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.PospectusComment;

public interface PospectusCommentService {

	public List<PospectusComment> getPospectusCommentByType( int prospectus_id, int proyect_loan_id , int comment_type_id );
	public List<PospectusComment> getPospectusCommentByProspectus( int prospectus_id );
	public boolean savePospectusComment( PospectusComment pospectuscomment );
	public boolean updatePospectusComment( PospectusComment pospectuscomment );
	public boolean removePospectusComment( PospectusComment pospectuscomment );
	
}
