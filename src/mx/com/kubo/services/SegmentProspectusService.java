package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.segment.SegmentProspectus;

public interface SegmentProspectusService {

	public List< SegmentProspectus > loadSegmentProspectListByType( int prospectus_id , int company_id, int segment_type_id);
	
	public boolean saveSegmentProspectus( SegmentProspectus segment );
	
}
