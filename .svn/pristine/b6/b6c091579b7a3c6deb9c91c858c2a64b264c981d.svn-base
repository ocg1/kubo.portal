package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.ProyectQuestion;
import mx.com.kubo.model.PublicForum;
import mx.com.kubo.model.PublicForumPK;

public interface PublicForumService {
	public abstract PublicForum getPublicForumById(PublicForumPK id);
	public abstract void add(PublicForum newPublicForum);
	public boolean updatePublicForum(PublicForum publicForum);
	public abstract List<PublicForum> getPublicForumList();
	public List<PublicForum> getListPublicForumByProyect(int proyectID, int companyID);
	public List<ProyectQuestion> getListUnrealizedQuestions(int proyectID, int companyID);

}
