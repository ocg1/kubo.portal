package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ViewPipeline;
import mx.com.kubo.repositories.ViewPipelineDao;

import org.springframework.stereotype.Repository;

@Repository
public class ViewPipelineDaoImp implements ViewPipelineDao{
	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Override
	public List<ViewPipeline> getViewPipeline() {
		// TODO Auto-generated method stub
		String query = "from ViewPipeline";
		List<ViewPipeline> viewPipelineList = null;
		try{
			viewPipelineList = em.createQuery(query, ViewPipeline.class)
				.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return viewPipelineList;
	}

}
