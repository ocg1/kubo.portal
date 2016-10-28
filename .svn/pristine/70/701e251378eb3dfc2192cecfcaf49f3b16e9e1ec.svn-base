package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.ViewPipeline;
import mx.com.kubo.repositories.ViewPipelineDao;
import mx.com.kubo.services.ViewPipelineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewPipelineServiceImp implements ViewPipelineService{

	@Autowired
	ViewPipelineDao viewPipelineDao;
	
	@Override
	public List<ViewPipeline> getViewPipeline() {
		// TODO Auto-generated method stub
		return viewPipelineDao.getViewPipeline();
	}

}
