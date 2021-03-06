package mx.com.kubo.model.segment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SegmentPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int company_id; //, tinyint(3), NO, PRI, ,
	@Column
	private int segment_id; // , int(11), NO, PRI, , 
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getSegment_id() {
		return segment_id;
	}
	public void setSegment_id(int segment_id) {
		this.segment_id = segment_id;
	}

}
