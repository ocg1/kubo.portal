package mx.com.kubo.repositories;

import mx.com.kubo.model.SegmentAction;

public interface SegmentActionDao {

	public SegmentAction getSegmentActionBySegment(int segment_id , int company_id, int action_type);
	
}
