package mx.com.kubo.services;

import mx.com.kubo.model.SegmentAction;

public interface SegmentActionService {

	public SegmentAction getSegmentActionBySegment(int segment_id , int company_id, int action_type);
	
}
