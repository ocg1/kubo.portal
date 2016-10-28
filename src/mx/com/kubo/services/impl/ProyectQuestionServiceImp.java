package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.ProyectQuestion;
import mx.com.kubo.model.ProyectQuestionPK;
import mx.com.kubo.repositories.ProyectQuestionDao;
import mx.com.kubo.services.ProyectQuestionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectQuestionServiceImp implements ProyectQuestionService{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ProyectQuestionDao proyectQuestionRepository;
	
	@Override
	public ProyectQuestion getProyectQuestionById(ProyectQuestionPK id){
		return proyectQuestionRepository.loadSelectedProyectQuestion(id);
	}
	
	@Override
	public void add(ProyectQuestion newProyectQuestion){	
		proyectQuestionRepository.saveProyectQuestion(newProyectQuestion);
	}
	
	@Override
	public List<ProyectQuestion> getProyectQuestionList(){
		return proyectQuestionRepository.loadProyectQuestionList();
	}
}
