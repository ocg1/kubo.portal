package mx.com.kubo.services.impl;

import java.io.Serializable;

import mx.com.kubo.model.SegmentAction;
import mx.com.kubo.repositories.SegmentActionDao;
import mx.com.kubo.services.SegmentActionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentActionServiceImp implements Serializable,SegmentActionService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private SegmentActionDao repository;
	
	public SegmentAction getSegmentActionBySegment(int segment_id , int company_id, int action_type){
		return repository.getSegmentActionBySegment( segment_id , company_id, action_type);
	}
	
}
