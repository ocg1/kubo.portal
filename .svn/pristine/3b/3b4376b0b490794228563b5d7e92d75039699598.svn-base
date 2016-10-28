package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Study_Level;
import mx.com.kubo.model.Study_LevelPK;
import mx.com.kubo.repositories.StudyLevelDao;
import mx.com.kubo.services.StudyLevelService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyLevelServiceImp implements StudyLevelService{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private StudyLevelDao studyLevelRepository;
	
	@Override
	public Study_Level getStudyLevelById(Study_LevelPK id){
		return studyLevelRepository.loadSelectedStudyLevel(id);
	}
	
	@Override
	public void add(Study_Level newStudyLevel){	
		studyLevelRepository.saveStudyLevel(newStudyLevel);
	}
	
	@Override
	public List<Study_Level> getStudyLevelList(){
		return studyLevelRepository.loadStudyLevelList();
	}
	
}