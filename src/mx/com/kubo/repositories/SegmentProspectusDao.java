package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.segment.SegmentProspectus;

public interface SegmentProspectusDao {

	public List< SegmentProspectus > loadSegmentProspectListByType( int prospectus_id , int company_id, int segment_type_id);
	
	boolean saveSegmentProspectus( SegmentProspectus segment );
	
}
