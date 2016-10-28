package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SegmentActionPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int company_id; // ` TINYINT(3) NOT NULL,
	@Column
	private int segment_id; //` INT(11) NOT NULL,
	@Column
	private int type_action_id; //` INT(3) NOT NULL,
	
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
	public int getType_action_id() {
		return type_action_id;
	}
	public void setType_action_id(int type_action_id) {
		this.type_action_id = type_action_id;
	}
	
}
