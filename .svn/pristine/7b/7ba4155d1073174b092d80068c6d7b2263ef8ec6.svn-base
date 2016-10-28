package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.segment.SegmentProspectus;
import mx.com.kubo.repositories.SegmentProspectusDao;
import mx.com.kubo.services.SegmentProspectusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentProspectusServiceImp implements Serializable,SegmentProspectusService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SegmentProspectusDao repository;
	
	public List< SegmentProspectus > loadSegmentProspectListByType( int prospectus_id , int company_id, int segment_type_id){
		
		return repository.loadSegmentProspectListByType( prospectus_id , company_id, segment_type_id);
		
	}

	public boolean saveSegmentProspectus( SegmentProspectus segment ){
		
		return repository.saveSegmentProspectus(segment);
		
	}
	
}
