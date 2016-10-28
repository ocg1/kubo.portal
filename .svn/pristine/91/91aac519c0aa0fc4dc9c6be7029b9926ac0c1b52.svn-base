package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.ProyectQuestion;
import mx.com.kubo.model.PublicForum;
import mx.com.kubo.model.PublicForumPK;
import mx.com.kubo.repositories.PublicForumDao;
import mx.com.kubo.services.PublicForumService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicForumServiceImp implements PublicForumService{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private PublicForumDao publicForumRepository;
	
	@Override
	public PublicForum getPublicForumById(PublicForumPK id){
		return publicForumRepository.loadSelectedPublicForum(id);
	}
	
	@Override
	public void add(PublicForum newPublicForum){	
		publicForumRepository.savePublicForum(newPublicForum);
	}
	
	@Override
	public List<PublicForum> getPublicForumList(){
		return publicForumRepository.loadPublicForumList();
	}
	
	@Override
	public List<PublicForum> getListPublicForumByProyect(int proyectID,int companyID) {
		return publicForumRepository.getListPublicForumByProyect(proyectID, companyID);
	}
	
	@Override
	public List<ProyectQuestion> getListUnrealizedQuestions(int proyectID,int companyID) {
		return publicForumRepository.getListUnrealizedQuestions(proyectID, companyID);
	}

	@Override
	public boolean updatePublicForum(PublicForum publicForum) {
		return publicForumRepository.updatePublicForum(publicForum);
	}
}
