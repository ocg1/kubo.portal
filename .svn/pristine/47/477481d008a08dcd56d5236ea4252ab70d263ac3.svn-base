package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.ProyectQuestion;
import mx.com.kubo.model.PublicForum;
import mx.com.kubo.model.PublicForumPK;

public interface PublicForumDao {
	public PublicForum loadSelectedPublicForum(PublicForumPK pk);
	public void savePublicForum(PublicForum newPublicForum);
	public boolean updatePublicForum(PublicForum publicForum);
	public List<PublicForum> loadPublicForumList();
	public List<PublicForum> getListPublicForumByProyect(int proyectID,int companyID);
	public List<ProyectQuestion> getListUnrealizedQuestions(int proyectID,int companyID);
}
