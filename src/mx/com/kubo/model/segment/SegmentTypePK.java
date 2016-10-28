package mx.com.kubo.model.segment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SegmentTypePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int company_id; //, tinyint(3), NO, PRI, , 
	@Column
	private int segment_type_id; //, int(11), NO, PRI, ,
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getSegment_type_id() {
		return segment_type_id;
	}
	public void setSegment_type_id(int segment_type_id) {
		this.segment_type_id = segment_type_id;
	}
	
}
