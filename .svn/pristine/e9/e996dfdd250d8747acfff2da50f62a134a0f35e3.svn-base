package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.RecommendationType;
import mx.com.kubo.repositories.RecommendationTypeDao;
import mx.com.kubo.services.RecommendationTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationTypeServiceImp implements RecommendationTypeService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RecommendationTypeDao recommendationtyperepository;
	
	@Override
	public List<RecommendationType> getRecommendationTypeLst(){
		return recommendationtyperepository.getRecommendationTypeLst();
	}
	
}
