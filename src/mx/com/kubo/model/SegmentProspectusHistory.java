package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mx.com.kubo.model.segment.SegmentProspectus;

@Entity
@Table(name="gn_segment_prospectus_history")
public class SegmentProspectusHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int segment_prospectus_history_id;
	@Column
	private Integer company_id; // , tinyint(3), NO, PRI, ,
	@Column
	private Integer segment_id; //, int(11), NO, PRI, ,
	@Column
	private Integer segment_type_id; //, int(11), NO, PRI, ,
	@Column
	private Integer prospectus_id; //, int(11), NO, PRI, , 
	@Column
	private Date assign_date;
	@Column
	private Date last_date;
	
	public SegmentProspectusHistory(){
		
	}
	
	public SegmentProspectusHistory( SegmentProspectus segment ){
		
		this.assign_date = segment.getAssign_date();
		this.company_id = segment.getPk().getCompany_id();
		this.prospectus_id = segment.getPk().getProspectus_id();
		this.segment_id = segment.getPk().getSegment_id();
		this.segment_type_id = segment.getPk().getSegment_type_id();
		
	}
	
	public int getSegment_prospectus_history_id() {
		return segment_prospectus_history_id;
	}
	public void setSegment_prospectus_history_id(int segment_prospectus_history_id) {
		this.segment_prospectus_history_id = segment_prospectus_history_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public Integer getSegment_id() {
		return segment_id;
	}
	public void setSegment_id(Integer segment_id) {
		this.segment_id = segment_id;
	}
	public Integer getSegment_type_id() {
		return segment_type_id;
	}
	public void setSegment_type_id(Integer segment_type_id) {
		this.segment_type_id = segment_type_id;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Date getAssign_date() {
		return assign_date;
	}
	public void setAssign_date(Date assign_date) {
		this.assign_date = assign_date;
	}
	public Date getLast_date() {
		return last_date;
	}
	public void setLast_date(Date last_date) {
		this.last_date = last_date;
	}
	
}
