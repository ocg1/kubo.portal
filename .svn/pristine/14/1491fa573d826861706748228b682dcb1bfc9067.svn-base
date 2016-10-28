package mx.com.kubo.model.segment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="gn_segment")
public class Segment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private SegmentPK pk;
	@Column
	private int segment_type_id; //, int(11), YES, MUL, , 
	@Column
	private String name; //, varchar(100), YES, , , 
	@Column
	private Integer enabled; //, int(11), YES, , , 
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false),
	        @JoinColumn(name = "segment_type_id", referencedColumnName = "segment_type_id", insertable = false, updatable = false)
	    })	
	private SegmentType segmentType;
	
	public SegmentPK getPk() {
		return pk;
	}
	public void setPk(SegmentPK pk) {
		this.pk = pk;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public SegmentType getSegmentType() {
		return segmentType;
	}
	public void setSegmentType(SegmentType segmentType) {
		this.segmentType = segmentType;
	}
	public int getSegment_type_id() {
		return segment_type_id;
	}
	public void setSegment_type_id(int segment_type_id) {
		this.segment_type_id = segment_type_id;
	}


}
